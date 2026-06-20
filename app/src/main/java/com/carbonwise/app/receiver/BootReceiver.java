package com.carbonwise.app.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import com.carbonwise.app.util.DailyReminderManager;
import com.carbonwise.app.util.PreferenceManager;

public class BootReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        if (Intent.ACTION_BOOT_COMPLETED.equals(intent.getAction())) {
            PreferenceManager prefs = new PreferenceManager(context);
            if (prefs.isDailyReminderEnabled()) {
                DailyReminderManager.scheduleDailyReminder(context);
            }
        }
    }
}
