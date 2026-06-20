package com.carbonwise.app.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MediatorLiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Transformations;
import androidx.lifecycle.ViewModel;

import com.carbonwise.app.data.local.dao.CarbonEntryDao;
import com.carbonwise.app.data.repository.CarbonRepository;
import com.carbonwise.app.util.TimeUtils;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import dagger.hilt.android.lifecycle.HiltViewModel;

@HiltViewModel
public class AnalyticsViewModel extends ViewModel {

    private final CarbonRepository carbonRepository;
    private final MutableLiveData<TimeRange> timeRange = new MutableLiveData<>(TimeRange.WEEK);

    @Inject
    public AnalyticsViewModel(CarbonRepository carbonRepository) {
        this.carbonRepository = carbonRepository;
    }

    public void setTimeRange(TimeRange range) {
        timeRange.setValue(range);
    }

    public LiveData<TimeRange> getTimeRange() {
        return timeRange;
    }

    public LiveData<List<CarbonEntryDao.CategoryTotal>> getEmissionSources() {
        return Transformations.switchMap(timeRange, range -> {
            long now = System.currentTimeMillis();
            long start, end;
            if (range == TimeRange.MONTH) {
                start = TimeUtils.getStartOfMonth(now);
                end = TimeUtils.getEndOfMonth(now);
            } else {
                start = TimeUtils.getStartOfWeek(now);
                end = TimeUtils.getEndOfWeek(now);
            }
            return carbonRepository.getCategoryBreakdown(start, end);
        });
    }

    public LiveData<List<DailyEmission>> getTrendData() {
        return Transformations.switchMap(timeRange, range -> {
            long now = System.currentTimeMillis();
            long start, end;
            int days;
            if (range == TimeRange.MONTH) {
                start = TimeUtils.getStartOfMonth(now);
                end = TimeUtils.getEndOfMonth(now);
                Calendar cal = Calendar.getInstance();
                cal.setTimeInMillis(now);
                days = cal.getActualMaximum(Calendar.DAY_OF_MONTH);
            } else {
                start = TimeUtils.getStartOfWeek(now);
                end = TimeUtils.getEndOfWeek(now);
                days = 7;
            }

            return Transformations.map(carbonRepository.getDailyTotals(start, end), totals -> {
                Map<String, Double> dataMap = new HashMap<>();
                if (totals != null) {
                    for (CarbonEntryDao.DailyTotal t : totals) {
                        dataMap.put(t.date, t.total);
                    }
                }

                List<DailyEmission> result = new ArrayList<>();
                Calendar cal = Calendar.getInstance();
                cal.setTimeInMillis(start);
                
                java.text.SimpleDateFormat dbSdf = new java.text.SimpleDateFormat("yyyy-MM-dd", java.util.Locale.getDefault());
                java.text.SimpleDateFormat labelSdf = new java.text.SimpleDateFormat(range == TimeRange.MONTH ? "d" : "EEE", java.util.Locale.getDefault());

                for (int i = 0; i < days; i++) {
                    String dbKey = dbSdf.format(cal.getTime());
                    String label = labelSdf.format(cal.getTime());
                    double val = dataMap.getOrDefault(dbKey, 0.0);
                    result.add(new DailyEmission(label, val));
                    cal.add(Calendar.DAY_OF_MONTH, 1);
                }
                return result;
            });
        });
    }

    public LiveData<Double> getTotalReduction() {
        return Transformations.switchMap(timeRange, range -> {
            long now = System.currentTimeMillis();
            long start, end, prevStart, prevEnd;
            if (range == TimeRange.MONTH) {
                start = TimeUtils.getStartOfMonth(now);
                end = TimeUtils.getEndOfMonth(now);
                Calendar cal = Calendar.getInstance();
                cal.setTimeInMillis(start);
                cal.add(Calendar.MONTH, -1);
                prevStart = cal.getTimeInMillis();
                cal.set(Calendar.DAY_OF_MONTH, cal.getActualMaximum(Calendar.DAY_OF_MONTH));
                prevEnd = cal.getTimeInMillis();
            } else {
                start = TimeUtils.getStartOfWeek(now);
                end = TimeUtils.getEndOfWeek(now);
                prevStart = start - 604800000L;
                prevEnd = start;
            }

            LiveData<Double> currentTotal = carbonRepository.getTotalForMonth(start, end);
            LiveData<Double> previousTotal = carbonRepository.getTotalForMonth(prevStart, prevEnd);

            MediatorLiveData<Double> result = new MediatorLiveData<>();
            result.addSource(currentTotal, curr -> combineReduction(result, curr, previousTotal.getValue()));
            result.addSource(previousTotal, prev -> combineReduction(result, currentTotal.getValue(), prev));
            return result;
        });
    }

    private void combineReduction(MediatorLiveData<Double> result, Double curr, Double prev) {
        double c = curr != null ? curr : 0.0;
        double p = prev != null ? prev : 0.0;
        if (p == 0) result.setValue(0.0);
        else result.setValue(((p - c) / p) * 100);
    }

    public LiveData<Double> getTreesEquivalent() {
        return Transformations.map(getEmissionSources(), (List<CarbonEntryDao.CategoryTotal> categories) -> {
            if (categories == null) return 0.0;
            double total = 0;
            for (CarbonEntryDao.CategoryTotal ct : categories) {
                total += ct.total;
            }
            // Roughly 20kg CO2 per tree per year.
            return total / 2.0;
        });
    }

    public enum TimeRange {
        WEEK, MONTH
    }

    public static class DailyEmission {
        public final String day;
        public final double value;

        public DailyEmission(String day, double value) {
            this.day = day;
            this.value = value;
        }
    }
}
