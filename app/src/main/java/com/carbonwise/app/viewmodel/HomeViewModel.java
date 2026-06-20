package com.carbonwise.app.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MediatorLiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Transformations;
import androidx.lifecycle.ViewModel;

import com.carbonwise.app.data.local.dao.CarbonEntryDao;
import com.carbonwise.app.data.repository.CarbonRepository;
import com.carbonwise.app.data.repository.ChallengeRepository;
import com.carbonwise.app.data.repository.UserRepository;
import com.carbonwise.app.model.Challenge;
import com.carbonwise.app.model.DashboardSummary;
import com.carbonwise.app.model.UserProfile;
import com.carbonwise.app.util.CarbonCalculator;
import com.carbonwise.app.util.TimeUtils;

import java.util.List;

import javax.inject.Inject;

import dagger.hilt.android.lifecycle.HiltViewModel;

@HiltViewModel
public class HomeViewModel extends ViewModel {

    private final CarbonRepository carbonRepository;
    private final UserRepository userRepository;
    private final ChallengeRepository challengeRepository;

    private final MutableLiveData<Long> currentTime = new MutableLiveData<>(System.currentTimeMillis());

    @Inject
    public HomeViewModel(CarbonRepository carbonRepository, UserRepository userRepository,
                         ChallengeRepository challengeRepository) {
        this.carbonRepository = carbonRepository;
        this.userRepository = userRepository;
        this.challengeRepository = challengeRepository;
    }

    public LiveData<UserProfile> getUserProfile() {
        return userRepository.getFirstProfile();
    }

    public LiveData<DashboardSummary> getDashboardSummary() {
        return Transformations.switchMap(currentTime, time -> {
            MutableLiveData<DashboardSummary> result = new MutableLiveData<>();

            long startOfDay = TimeUtils.getStartOfDay(time);
            long endOfDay = TimeUtils.getEndOfDay(time);
            long startOfWeek = TimeUtils.getStartOfWeek(time);
            long endOfWeek = TimeUtils.getEndOfWeek(time);
            long startOfMonth = TimeUtils.getStartOfMonth(time);
            long endOfMonth = TimeUtils.getEndOfMonth(time);

            LiveData<Double> todayTotal = carbonRepository.getTotalForDay(startOfDay, endOfDay);
            LiveData<Double> weekTotal = carbonRepository.getTotalForWeek(startOfWeek, endOfWeek);
            LiveData<Double> lastWeekTotal = carbonRepository.getTotalForWeek(startOfWeek - 604800000L, startOfWeek);
            LiveData<Double> monthTotal = carbonRepository.getTotalForMonth(startOfMonth, endOfMonth);
            LiveData<List<CarbonEntryDao.CategoryTotal>> breakdown = carbonRepository.getCategoryBreakdown(startOfMonth, endOfMonth);
            LiveData<UserProfile> profile = userRepository.getFirstProfile();

            DashboardSummary summary = new DashboardSummary();

            todayTotal.observeForever(val -> {
                summary.setTodayCarbonKg(val != null ? val : 0.0);
                result.setValue(summary);
            });

            MediatorLiveData<Double> weeklyStats = new MediatorLiveData<>();
            weeklyStats.addSource(weekTotal, val -> {
                summary.setWeeklyTotalKg(val != null ? val : 0.0);
                updateWeeklyChange(summary, lastWeekTotal.getValue(), result);
            });
            weeklyStats.addSource(lastWeekTotal, val -> {
                updateWeeklyChange(summary, val, result);
            });
            weeklyStats.observeForever(v -> {});

            monthTotal.observeForever(val -> {
                summary.setMonthlyTotalKg(val != null ? val : 0.0);
                String status = CarbonCalculator.getCarbonHealthStatus(summary.getMonthlyTotalKg());
                summary.setCarbonHealthStatus(status);
                result.setValue(summary);
            });

            monthTotal.observeForever(val -> {
                summary.setMonthlyTotalKg(val != null ? val : 0.0);
                String status = CarbonCalculator.getCarbonHealthStatus(summary.getMonthlyTotalKg());
                summary.setCarbonHealthStatus(status);
                result.setValue(summary);
            });

            breakdown.observeForever(categories -> {
                if (categories != null) {
                    double total = 0;
                    for (CarbonEntryDao.CategoryTotal ct : categories) {
                        total += ct.total;
                    }
                    for (CarbonEntryDao.CategoryTotal ct : categories) {
                        double pct = total > 0 ? (ct.total / total) * 100 : 0;
                        switch (ct.category != null ? ct.category : "") {
                            case "Transport": summary.setTransportPercent(pct); break;
                            case "Energy": summary.setEnergyPercent(pct); break;
                            case "Food": summary.setFoodPercent(pct); break;
                            case "Shopping": summary.setShoppingPercent(pct); break;
                            case "Waste": summary.setWastePercent(pct); break;
                        }
                    }
                }
                result.setValue(summary);
            });

            profile.observeForever(p -> {
                if (p != null) {
                    summary.setCurrentStreak(p.getCurrentStreak());
                    summary.setGreenPoints(p.getGreenPoints());
                    summary.setCurrentLevel(p.getCurrentLevel());
                    double progress = p.getMonthlyCarbonGoal() > 0
                            ? Math.min(100, (1 - (summary.getMonthlyTotalKg() / p.getMonthlyCarbonGoal())) * 100)
                            : 0;
                    summary.setGoalProgressPercent(progress);
                }
                result.setValue(summary);
            });

            return result;
        });
    }

    public LiveData<List<Challenge>> getActiveChallenges() {
        return challengeRepository.getActiveChallenges();
    }

    public void refreshData() {
        currentTime.setValue(System.currentTimeMillis());
    }

    private void updateWeeklyChange(DashboardSummary summary, Double lastWeekVal, MutableLiveData<DashboardSummary> result) {
        double current = summary.getWeeklyTotalKg();
        double prev = lastWeekVal != null ? lastWeekVal : 0.0;
        double diff = current - prev;
        double pct = prev > 0 ? (diff / prev) * 100 : 0;
        summary.setWeeklyChangePercent(pct);
        result.setValue(summary);
    }
}
