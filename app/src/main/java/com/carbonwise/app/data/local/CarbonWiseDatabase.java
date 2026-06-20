package com.carbonwise.app.data.local;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.carbonwise.app.data.local.dao.AchievementDao;
import com.carbonwise.app.data.local.dao.CarbonEntryDao;
import com.carbonwise.app.data.local.dao.CarbonGoalDao;
import com.carbonwise.app.data.local.dao.ChallengeDao;
import com.carbonwise.app.data.local.dao.UserProfileDao;
import com.carbonwise.app.model.Achievement;
import com.carbonwise.app.model.CarbonEntry;
import com.carbonwise.app.model.CarbonGoal;
import com.carbonwise.app.model.Challenge;
import com.carbonwise.app.model.UserProfile;

@Database(
    entities = {
        CarbonEntry.class,
        UserProfile.class,
        Challenge.class,
        Achievement.class,
        CarbonGoal.class
    },
    version = 1,
    exportSchema = true
)
public abstract class CarbonWiseDatabase extends RoomDatabase {

    private static volatile CarbonWiseDatabase INSTANCE;
    private static final String DATABASE_NAME = "carbon_wise_db";

    public abstract CarbonEntryDao carbonEntryDao();
    public abstract UserProfileDao userProfileDao();
    public abstract ChallengeDao challengeDao();
    public abstract AchievementDao achievementDao();
    public abstract CarbonGoalDao carbonGoalDao();

    public static CarbonWiseDatabase getInstance(Context context) {
        if (INSTANCE == null) {
            synchronized (CarbonWiseDatabase.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(
                            context.getApplicationContext(),
                            CarbonWiseDatabase.class,
                            DATABASE_NAME
                    )
                    .fallbackToDestructiveMigration()
                    .build();
                }
            }
        }
        return INSTANCE;
    }
}
