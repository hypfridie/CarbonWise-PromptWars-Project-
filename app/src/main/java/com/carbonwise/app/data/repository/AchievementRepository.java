package com.carbonwise.app.data.repository;

import androidx.lifecycle.LiveData;

import com.carbonwise.app.data.local.dao.AchievementDao;
import com.carbonwise.app.model.Achievement;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
public class AchievementRepository {

    private final AchievementDao achievementDao;
    private final ExecutorService executor;

    @Inject
    public AchievementRepository(AchievementDao achievementDao) {
        this.achievementDao = achievementDao;
        this.executor = Executors.newSingleThreadExecutor();
    }

    public LiveData<List<Achievement>> getAllAchievements() {
        return achievementDao.getAllAchievements();
    }

    public LiveData<List<Achievement>> getUnlockedAchievements() {
        return achievementDao.getUnlockedAchievements();
    }

    public LiveData<List<Achievement>> getLockedAchievements() {
        return achievementDao.getLockedAchievements();
    }

    public LiveData<Integer> getUnlockedCount() {
        return achievementDao.getUnlockedCount();
    }

    public void insertAchievement(Achievement achievement) {
        executor.execute(() -> achievementDao.insert(achievement));
    }

    public void insertAchievements(List<Achievement> achievements) {
        executor.execute(() -> achievementDao.insertAll(achievements));
    }

    public void unlockAchievement(Achievement achievement) {
        achievement.setUnlocked(true);
        achievement.setUnlockedDate(System.currentTimeMillis());
        executor.execute(() -> achievementDao.update(achievement));
    }
}
