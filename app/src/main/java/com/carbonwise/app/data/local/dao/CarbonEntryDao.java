package com.carbonwise.app.data.local.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import com.carbonwise.app.model.CarbonEntry;

import java.util.List;

@Dao
public interface CarbonEntryDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(CarbonEntry entry);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertAll(List<CarbonEntry> entries);

    @Update
    void update(CarbonEntry entry);

    @Delete
    void delete(CarbonEntry entry);

    @Query("SELECT * FROM carbon_entries WHERE id = :id")
    LiveData<CarbonEntry> getById(String id);

    @Query("SELECT * FROM carbon_entries ORDER BY timestamp DESC")
    LiveData<List<CarbonEntry>> getAllEntries();

    @Query("SELECT * FROM carbon_entries WHERE timestamp >= :startOfDay AND timestamp < :endOfDay ORDER BY timestamp DESC")
    LiveData<List<CarbonEntry>> getEntriesForDay(long startOfDay, long endOfDay);

    @Query("SELECT * FROM carbon_entries WHERE timestamp >= :startOfWeek AND timestamp < :endOfWeek ORDER BY timestamp DESC")
    LiveData<List<CarbonEntry>> getEntriesForWeek(long startOfWeek, long endOfWeek);

    @Query("SELECT * FROM carbon_entries WHERE timestamp >= :startOfMonth AND timestamp < :endOfMonth ORDER BY timestamp DESC")
    LiveData<List<CarbonEntry>> getEntriesForMonth(long startOfMonth, long endOfMonth);

    @Query("SELECT SUM(carbonValue) FROM carbon_entries WHERE timestamp >= :startOfDay AND timestamp < :endOfDay")
    LiveData<Double> getTotalForDay(long startOfDay, long endOfDay);

    @Query("SELECT SUM(carbonValue) FROM carbon_entries WHERE timestamp >= :startOfWeek AND timestamp < :endOfWeek")
    LiveData<Double> getTotalForWeek(long startOfWeek, long endOfWeek);

    @Query("SELECT SUM(carbonValue) FROM carbon_entries WHERE timestamp >= :startOfMonth AND timestamp < :endOfMonth")
    LiveData<Double> getTotalForMonth(long startOfMonth, long endOfMonth);

    @Query("SELECT category, SUM(carbonValue) as total FROM carbon_entries WHERE timestamp >= :startTime AND timestamp < :endTime GROUP BY category")
    LiveData<List<CategoryTotal>> getCategoryBreakdown(long startTime, long endTime);

    @Query("SELECT STRFTIME('%Y-%m-%d', timestamp/1000, 'unixepoch') as date, SUM(carbonValue) as total FROM carbon_entries WHERE timestamp >= :start AND timestamp < :end GROUP BY date ORDER BY date ASC")
    LiveData<List<DailyTotal>> getDailyTotals(long start, long end);

    @Query("SELECT * FROM carbon_entries WHERE synced = 0")
    List<CarbonEntry> getUnsyncedEntries();

    @Query("UPDATE carbon_entries SET synced = 1 WHERE id = :id")
    void markAsSynced(String id);

    @Query("SELECT COUNT(*) FROM carbon_entries")
    LiveData<Integer> getEntryCount();

    @Query("SELECT DISTINCT STRFTIME('%Y-%m-%d', timestamp/1000, 'unixepoch') FROM carbon_entries ORDER BY timestamp DESC")
    List<String> getActiveDays();

    class CategoryTotal {
        public String category;
        public double total;
    }

    class DailyTotal {
        public String date;
        public double total;
    }
}
