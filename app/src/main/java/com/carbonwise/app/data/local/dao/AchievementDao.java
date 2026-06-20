package com.carbonwise.app.data.local.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import com.carbonwise.app.model.Achievement;

import java.util.List;

@Dao
public interface AchievementDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(Achievement achievement);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertAll(List<Achievement> achievements);

    @Update
    void update(Achievement achievement);

    @Query("SELECT * FROM achievements ORDER BY unlocked DESC, pointsRequired ASC")
    LiveData<List<Achievement>> getAllAchievements();

    @Query("SELECT * FROM achievements WHERE unlocked = 1 ORDER BY unlockedDate DESC")
    LiveData<List<Achievement>> getUnlockedAchievements();

    @Query("SELECT * FROM achievements WHERE unlocked = 0 ORDER BY pointsRequired ASC")
    LiveData<List<Achievement>> getLockedAchievements();

    @Query("SELECT COUNT(*) FROM achievements WHERE unlocked = 1")
    LiveData<Integer> getUnlockedCount();
}
