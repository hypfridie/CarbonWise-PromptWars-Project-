package com.carbonwise.app.data.repository;

import androidx.lifecycle.LiveData;

import com.carbonwise.app.data.local.dao.CarbonEntryDao;
import com.carbonwise.app.model.CarbonEntry;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
public class CarbonRepository {

    private final CarbonEntryDao carbonEntryDao;
    private final ExecutorService executor;

    @Inject
    public CarbonRepository(CarbonEntryDao carbonEntryDao) {
        this.carbonEntryDao = carbonEntryDao;
        this.executor = Executors.newSingleThreadExecutor();
    }

    public LiveData<List<CarbonEntry>> getAllEntries() {
        return carbonEntryDao.getAllEntries();
    }

    public LiveData<List<CarbonEntry>> getEntriesForDay(long startOfDay, long endOfDay) {
        return carbonEntryDao.getEntriesForDay(startOfDay, endOfDay);
    }

    public LiveData<List<CarbonEntry>> getEntriesForWeek(long startOfWeek, long endOfWeek) {
        return carbonEntryDao.getEntriesForWeek(startOfWeek, endOfWeek);
    }

    public LiveData<List<CarbonEntry>> getEntriesForMonth(long startOfMonth, long endOfMonth) {
        return carbonEntryDao.getEntriesForMonth(startOfMonth, endOfMonth);
    }

    public LiveData<Double> getTotalForDay(long startOfDay, long endOfDay) {
        return carbonEntryDao.getTotalForDay(startOfDay, endOfDay);
    }

    public LiveData<Double> getTotalForWeek(long startOfWeek, long endOfWeek) {
        return carbonEntryDao.getTotalForWeek(startOfWeek, endOfWeek);
    }

    public LiveData<Double> getTotalForMonth(long startOfMonth, long endOfMonth) {
        return carbonEntryDao.getTotalForMonth(startOfMonth, endOfMonth);
    }

    public LiveData<List<CarbonEntryDao.CategoryTotal>> getCategoryBreakdown(long startTime, long endTime) {
        return carbonEntryDao.getCategoryBreakdown(startTime, endTime);
    }

    public LiveData<List<CarbonEntryDao.DailyTotal>> getDailyTotals(long start, long end) {
        return carbonEntryDao.getDailyTotals(start, end);
    }

    public void insertEntry(CarbonEntry entry) {
        executor.execute(() -> carbonEntryDao.insert(entry));
    }

    public void insertEntries(List<CarbonEntry> entries) {
        executor.execute(() -> carbonEntryDao.insertAll(entries));
    }

    public void deleteEntry(CarbonEntry entry) {
        executor.execute(() -> carbonEntryDao.delete(entry));
    }

    public void updateEntry(CarbonEntry entry) {
        executor.execute(() -> carbonEntryDao.update(entry));
    }
}
