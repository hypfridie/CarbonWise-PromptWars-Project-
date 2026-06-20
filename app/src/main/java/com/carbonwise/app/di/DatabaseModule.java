package com.carbonwise.app.di;

import android.content.Context;

import androidx.room.Room;

import com.carbonwise.app.data.local.CarbonWiseDatabase;
import com.carbonwise.app.data.local.dao.AchievementDao;
import com.carbonwise.app.data.local.dao.CarbonEntryDao;
import com.carbonwise.app.data.local.dao.CarbonGoalDao;
import com.carbonwise.app.data.local.dao.ChallengeDao;
import com.carbonwise.app.data.local.dao.UserProfileDao;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import dagger.hilt.InstallIn;
import dagger.hilt.android.qualifiers.ApplicationContext;
import dagger.hilt.components.SingletonComponent;

@Module
@InstallIn(SingletonComponent.class)
public class DatabaseModule {

    @Provides
    @Singleton
    public CarbonWiseDatabase provideDatabase(@ApplicationContext Context context) {
        return Room.databaseBuilder(
                context,
                CarbonWiseDatabase.class,
                "carbon_wise_db"
        )
        .fallbackToDestructiveMigration()
        .build();
    }

    @Provides
    public CarbonEntryDao provideCarbonEntryDao(CarbonWiseDatabase db) {
        return db.carbonEntryDao();
    }

    @Provides
    public UserProfileDao provideUserProfileDao(CarbonWiseDatabase db) {
        return db.userProfileDao();
    }

    @Provides
    public ChallengeDao provideChallengeDao(CarbonWiseDatabase db) {
        return db.challengeDao();
    }

    @Provides
    public AchievementDao provideAchievementDao(CarbonWiseDatabase db) {
        return db.achievementDao();
    }

    @Provides
    public CarbonGoalDao provideCarbonGoalDao(CarbonWiseDatabase db) {
        return db.carbonGoalDao();
    }
}
