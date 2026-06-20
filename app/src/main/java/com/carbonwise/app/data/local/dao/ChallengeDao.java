package com.carbonwise.app.data.local.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import com.carbonwise.app.model.Challenge;

import java.util.List;

@Dao
public interface ChallengeDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(Challenge challenge);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertAll(List<Challenge> challenges);

    @Update
    void update(Challenge challenge);

    @Query("SELECT * FROM challenges ORDER BY assignedDate DESC")
    LiveData<List<Challenge>> getAllChallenges();

    @Query("SELECT * FROM challenges WHERE completed = 0 ORDER BY assignedDate DESC")
    LiveData<List<Challenge>> getActiveChallenges();

    @Query("SELECT * FROM challenges WHERE completed = 1 ORDER BY completedDate DESC")
    LiveData<List<Challenge>> getCompletedChallenges();

    @Query("SELECT DISTINCT STRFTIME('%Y-%m-%d', completedDate/1000, 'unixepoch') FROM challenges WHERE completed = 1 ORDER BY completedDate DESC")
    List<String> getCompletedDays();

    @Query("SELECT * FROM challenges WHERE id = :id")
    LiveData<Challenge> getById(String id);

    @Query("SELECT COUNT(*) FROM challenges WHERE completed = 1 AND completedDate >= :startOfDay")
    int getCompletedCountForDay(long startOfDay);

    @Query("DELETE FROM challenges WHERE completed = 1 AND completedDate < :cutoffDate")
    void deleteOldCompleted(long cutoffDate);
}
