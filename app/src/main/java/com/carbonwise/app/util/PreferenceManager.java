package com.carbonwise.app.util;

import android.content.Context;
import android.content.SharedPreferences;

import javax.inject.Inject;
import javax.inject.Singleton;

import dagger.hilt.android.qualifiers.ApplicationContext;

@Singleton
public class PreferenceManager {

    private static final String PREF_NAME = "carbon_wise_prefs";
    private static final String KEY_USER_ID = "user_id";
    private static final String KEY_ONBOARDING_COMPLETE = "onboarding_complete";
    private static final String KEY_LAST_SYNC = "last_sync";
    private static final String KEY_NOTIFICATIONS_ENABLED = "notifications_enabled";
    private static final String KEY_DAILY_REMINDER = "daily_reminder";
    private static final String KEY_DARK_MODE = "dark_mode";

    private final SharedPreferences prefs;

    @Inject
    public PreferenceManager(@ApplicationContext Context context) {
        this.prefs = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);
    }

    public void setUserId(String userId) {
        prefs.edit().putString(KEY_USER_ID, userId).apply();
    }

    public String getUserId() {
        return prefs.getString(KEY_USER_ID, null);
    }

    public void setOnboardingComplete(boolean complete) {
        prefs.edit().putBoolean(KEY_ONBOARDING_COMPLETE, complete).apply();
    }

    public boolean isOnboardingComplete() {
        return prefs.getBoolean(KEY_ONBOARDING_COMPLETE, false);
    }

    public void setLastSync(long timestamp) {
        prefs.edit().putLong(KEY_LAST_SYNC, timestamp).apply();
    }

    public long getLastSync() {
        return prefs.getLong(KEY_LAST_SYNC, 0);
    }

    public void setNotificationsEnabled(boolean enabled) {
        prefs.edit().putBoolean(KEY_NOTIFICATIONS_ENABLED, enabled).apply();
    }

    public boolean isNotificationsEnabled() {
        return prefs.getBoolean(KEY_NOTIFICATIONS_ENABLED, true);
    }

    public void setDailyReminder(boolean enabled) {
        prefs.edit().putBoolean(KEY_DAILY_REMINDER, enabled).apply();
    }

    public boolean isDailyReminderEnabled() {
        return prefs.getBoolean(KEY_DAILY_REMINDER, true);
    }

    public void setDarkMode(boolean darkMode) {
        prefs.edit().putBoolean(KEY_DARK_MODE, darkMode).apply();
    }

    public boolean isDarkMode() {
        return prefs.getBoolean(KEY_DARK_MODE, true);
    }

    public void clearAll() {
        prefs.edit().clear().apply();
    }
}
