package com.carbonwise.app.data.repository;

import androidx.lifecycle.LiveData;

import com.carbonwise.app.data.local.dao.CarbonEntryDao;
import com.carbonwise.app.data.local.dao.ChallengeDao;
import com.carbonwise.app.data.local.dao.UserProfileDao;
import com.carbonwise.app.model.UserProfile;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
public class UserRepository {

    private final UserProfileDao userProfileDao;
    private final CarbonEntryDao carbonEntryDao;
    private final ChallengeDao challengeDao;
    private final ExecutorService executor;

    @Inject
    public UserRepository(UserProfileDao userProfileDao, CarbonEntryDao carbonEntryDao, ChallengeDao challengeDao) {
        this.userProfileDao = userProfileDao;
        this.carbonEntryDao = carbonEntryDao;
        this.challengeDao = challengeDao;
        this.executor = Executors.newSingleThreadExecutor();
    }

    public LiveData<UserProfile> getUserProfile(String userId) {
        return userProfileDao.getById(userId);
    }

    public LiveData<UserProfile> getFirstProfile() {
        return userProfileDao.getFirst();
    }

    public UserProfile getFirstProfileSync() {
        return userProfileDao.getFirstSync();
    }

    public void saveProfile(UserProfile profile) {
        executor.execute(() -> userProfileDao.insert(profile));
    }

    public void updateProfile(UserProfile profile) {
        executor.execute(() -> userProfileDao.update(profile));
    }

    public void addPoints(int points) {
        executor.execute(() -> {
            UserProfile profile = userProfileDao.getFirstSync();
            if (profile != null) {
                profile.setGreenPoints(profile.getGreenPoints() + points);
                profile.setUpdatedAt(System.currentTimeMillis());
                userProfileDao.update(profile);
            } else {
                // Failsafe: Create default profile if missing
                UserProfile newProfile = new UserProfile();
                newProfile.setUserId(java.util.UUID.randomUUID().toString());
                newProfile.setName("CarbonWise User");
                newProfile.setGreenPoints(points);
                newProfile.setOnboardingComplete(true);
                newProfile.setCurrentLevel("Seedling");
                newProfile.setUpdatedAt(System.currentTimeMillis());
                userProfileDao.insert(newProfile);
            }
        });
    }

    public void updateStreak(int streak) {
        executor.execute(() -> {
            UserProfile profile = userProfileDao.getFirstSync();
            if (profile != null) {
                profile.setCurrentStreak(streak);
                if (streak > profile.getLongestStreak()) {
                    profile.setLongestStreak(streak);
                }
                profile.setUpdatedAt(System.currentTimeMillis());
                userProfileDao.update(profile);
            } else {
                UserProfile newProfile = new UserProfile();
                newProfile.setUserId(java.util.UUID.randomUUID().toString());
                newProfile.setName("CarbonWise User");
                newProfile.setCurrentStreak(streak);
                newProfile.setLongestStreak(streak);
                newProfile.setOnboardingComplete(true);
                newProfile.setCurrentLevel("Seedling");
                newProfile.setUpdatedAt(System.currentTimeMillis());
                userProfileDao.insert(newProfile);
            }
        });
    }

    public void recalculateStreak() {
        executor.execute(() -> {
            List<String> entryDays = carbonEntryDao.getActiveDays();
            List<String> challengeDays = challengeDao.getCompletedDays();
            
            Set<String> allDaysSet = new HashSet<>();
            if (entryDays != null) allDaysSet.addAll(entryDays);
            if (challengeDays != null) allDaysSet.addAll(challengeDays);
            
            if (allDaysSet.isEmpty()) {
                userProfileDao.updateStreak(0, System.currentTimeMillis());
                return;
            }

            List<String> sortedDays = new ArrayList<>(allDaysSet);
            Collections.sort(sortedDays, Collections.reverseOrder());

            java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat("yyyy-MM-dd", java.util.Locale.getDefault());
            Calendar cal = Calendar.getInstance();
            String today = sdf.format(cal.getTime());
            cal.add(Calendar.DAY_OF_MONTH, -1);
            String yesterday = sdf.format(cal.getTime());

            if (!sortedDays.get(0).equals(today) && !sortedDays.get(0).equals(yesterday)) {
                userProfileDao.updateStreak(0, System.currentTimeMillis());
                return;
            }

            int streak = 0;
            Calendar streakCal = Calendar.getInstance();
            if (!sortedDays.get(0).equals(today)) {
                streakCal.add(Calendar.DAY_OF_MONTH, -1);
            }

            for (String day : sortedDays) {
                if (day.equals(sdf.format(streakCal.getTime()))) {
                    streak++;
                    streakCal.add(Calendar.DAY_OF_MONTH, -1);
                } else {
                    break;
                }
            }
            updateStreak(streak);
        });
    }

    public boolean isOnboardingComplete() {
        return userProfileDao.isOnboardingComplete();
    }
}
