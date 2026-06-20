package com.carbonwise.app.data.local.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import com.carbonwise.app.model.CarbonGoal;

import java.util.List;

@Dao
public interface CarbonGoalDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(CarbonGoal goal);

    @Update
    void update(CarbonGoal goal);

    @Query("SELECT * FROM carbon_goals WHERE active = 1 ORDER BY createdAt DESC")
    LiveData<List<CarbonGoal>> getActiveGoals();

    @Query("SELECT * FROM carbon_goals ORDER BY createdAt DESC")
    LiveData<List<CarbonGoal>> getAllGoals();

    @Query("SELECT * FROM carbon_goals WHERE id = :id")
    LiveData<CarbonGoal> getById(String id);

    @Query("UPDATE carbon_goals SET active = 0 WHERE id = :id")
    void deactivateGoal(String id);

    @Query("UPDATE carbon_goals SET currentReductionPercent = :percent WHERE id = :id")
    void updateProgress(String id, double percent);
}
