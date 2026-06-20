package com.carbonwise.app.data.repository;

import androidx.lifecycle.LiveData;

import com.carbonwise.app.data.local.dao.CarbonGoalDao;
import com.carbonwise.app.model.CarbonGoal;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
public class GoalRepository {

    private final CarbonGoalDao carbonGoalDao;
    private final ExecutorService executor;

    @Inject
    public GoalRepository(CarbonGoalDao carbonGoalDao) {
        this.carbonGoalDao = carbonGoalDao;
        this.executor = Executors.newSingleThreadExecutor();
    }

    public LiveData<List<CarbonGoal>> getActiveGoals() {
        return carbonGoalDao.getActiveGoals();
    }

    public LiveData<List<CarbonGoal>> getAllGoals() {
        return carbonGoalDao.getAllGoals();
    }

    public LiveData<CarbonGoal> getGoalById(String id) {
        return carbonGoalDao.getById(id);
    }

    public void insertGoal(CarbonGoal goal) {
        executor.execute(() -> carbonGoalDao.insert(goal));
    }

    public void updateGoal(CarbonGoal goal) {
        executor.execute(() -> carbonGoalDao.update(goal));
    }

    public void updateProgress(String goalId, double percent) {
        executor.execute(() -> carbonGoalDao.updateProgress(goalId, percent));
    }

    public void deactivateGoal(String goalId) {
        executor.execute(() -> carbonGoalDao.deactivateGoal(goalId));
    }
}
