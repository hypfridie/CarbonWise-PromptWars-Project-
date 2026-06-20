package com.carbonwise.app.data.local.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import com.carbonwise.app.model.UserProfile;

@Dao
public interface UserProfileDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(UserProfile profile);

    @Update
    void update(UserProfile profile);

    @Query("SELECT * FROM user_profile WHERE userId = :userId")
    LiveData<UserProfile> getById(String userId);

    @Query("SELECT * FROM user_profile LIMIT 1")
    LiveData<UserProfile> getFirst();

    @Query("SELECT * FROM user_profile LIMIT 1")
    UserProfile getFirstSync();

    @Query("UPDATE user_profile SET greenPoints = greenPoints + :points, updatedAt = :timestamp")
    void addPoints(int points, long timestamp);

    @Query("UPDATE user_profile SET currentStreak = :streak, longestStreak = CASE WHEN :streak > longestStreak THEN :streak ELSE longestStreak END, updatedAt = :timestamp")
    void updateStreak(int streak, long timestamp);

    @Query("SELECT EXISTS(SELECT 1 FROM user_profile WHERE onboardingComplete = 1)")
    boolean isOnboardingComplete();
}
