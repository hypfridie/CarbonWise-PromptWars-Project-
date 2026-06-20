package com.carbonwise.app;

import android.app.Application;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.os.Build;

import androidx.hilt.work.HiltWorkerFactory;
import androidx.work.Configuration;

import com.carbonwise.app.data.repository.AchievementRepository;
import com.carbonwise.app.data.repository.ChallengeRepository;
import com.carbonwise.app.data.repository.UserRepository;
import com.carbonwise.app.model.Achievement;
import com.carbonwise.app.model.Challenge;
import com.carbonwise.app.util.DailyReminderManager;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import dagger.hilt.android.HiltAndroidApp;

@HiltAndroidApp
public class CarbonWiseApplication extends Application implements Configuration.Provider {

    @Inject
    HiltWorkerFactory workerFactory;

    @Inject
    ChallengeRepository challengeRepository;

    @Inject
    AchievementRepository achievementRepository;

    @Inject
    UserRepository userRepository;

    @Override
    public void onCreate() {
        super.onCreate();
        createNotificationChannels();
        DailyReminderManager.scheduleDailyReminder(this);
        initializeChallenges();
        initializeAchievements();
        userRepository.recalculateStreak();
    }

    private void initializeChallenges() {
        challengeRepository.getActiveChallenges().observeForever(challenges -> {
            if (challenges == null || challenges.isEmpty()) {
                List<Challenge> defaults = new ArrayList<>();
                
                defaults.add(createChallenge("Meatless Monday", "Skip meat for the day and try a delicious plant-based meal.", "Food", 50, 1));
                defaults.add(createChallenge("Pedal Power", "Use your bicycle for all short trips today instead of driving.", "Transport", 100, 2));
                defaults.add(createChallenge("Local Hero", "Buy your groceries from a local farmers' market or small community shop.", "Shopping", 40, 1));
                defaults.add(createChallenge("Unplugged", "Unplug all electronics not in use before going to bed tonight.", "Energy", 30, 1));
                defaults.add(createChallenge("Plastic-Free Day", "Avoid using any single-use plastics for 24 hours.", "Waste", 60, 2));
                defaults.add(createChallenge("Walk the Talk", "Walk for at least 30 minutes today instead of taking a vehicle.", "Transport", 45, 1));
                defaults.add(createChallenge("Cold Wash Only", "Wash your laundry using cold water settings to save heating energy.", "Energy", 25, 1));
                defaults.add(createChallenge("Digital Declutter", "Delete 50 old emails or unused files to reduce server energy footprint.", "Energy", 20, 1));
                defaults.add(createChallenge("Second-Hand Chic", "Buy something pre-loved or donate an item you no longer need.", "Shopping", 75, 2));
                defaults.add(createChallenge("Compost Starter", "Start a small compost bin or research local composting options.", "Waste", 80, 3));

                challengeRepository.insertChallenges(defaults);
            }
        });
    }

    private void initializeAchievements() {
        achievementRepository.getAllAchievements().observeForever(achievements -> {
            if (achievements == null || achievements.isEmpty()) {
                List<Achievement> defaults = new ArrayList<>();
                defaults.add(createAchievement("Eco Explorer", "Complete your first challenge.", 1));
                defaults.add(createAchievement("Green Commuter", "Log 5 transport activities.", 10));
                defaults.add(createAchievement("Carbon Conscious", "Set your first monthly goal.", 1));
                defaults.add(createAchievement("Streak Starter", "Maintain a 3-day streak.", 3));
                defaults.add(createAchievement("Waste Warrior", "Log 10 recycling activities.", 10));
                achievementRepository.insertAchievements(defaults);
            }
        });
    }

    private Challenge createChallenge(String title, String desc, String cat, int points, int difficulty) {
        Challenge c = new Challenge();
        c.setTitle(title);
        c.setDescription(desc);
        c.setCategory(cat);
        c.setPointsReward(points);
        c.setDifficulty(difficulty);
        return c;
    }

    private Achievement createAchievement(String title, String desc, int req) {
        Achievement a = new Achievement();
        a.setId(java.util.UUID.randomUUID().toString());
        a.setTitle(title);
        a.setDescription(desc);
        a.setPointsRequired(req);
        return a;
    }

    private void createNotificationChannels() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationManager manager = getSystemService(NotificationManager.class);

            // Daily reminder channel
            NotificationChannel reminderChannel = new NotificationChannel(
                    getString(R.string.default_notification_channel_id),
                    getString(R.string.channel_reminders),
                    NotificationManager.IMPORTANCE_DEFAULT
            );
            reminderChannel.setDescription(getString(R.string.channel_reminders_desc));
            manager.createNotificationChannel(reminderChannel);

            // Challenge completion channel
            NotificationChannel challengeChannel = new NotificationChannel(
                    getString(R.string.challenge_notification_channel_id),
                    getString(R.string.channel_challenges),
                    NotificationManager.IMPORTANCE_HIGH
            );
            challengeChannel.setDescription(getString(R.string.channel_challenges_desc));
            manager.createNotificationChannel(challengeChannel);

            // Achievement channel
            NotificationChannel achievementChannel = new NotificationChannel(
                    getString(R.string.achievement_notification_channel_id),
                    getString(R.string.channel_achievements),
                    NotificationManager.IMPORTANCE_HIGH
            );
            achievementChannel.setDescription(getString(R.string.channel_achievements_desc));
            manager.createNotificationChannel(achievementChannel);
        }
    }

    @Override
    public Configuration getWorkManagerConfiguration() {
        return new Configuration.Builder()
                .setWorkerFactory(workerFactory)
                .build();
    }
}
