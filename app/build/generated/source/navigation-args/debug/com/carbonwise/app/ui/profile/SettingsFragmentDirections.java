package com.carbonwise.app.ui.profile;

import androidx.annotation.NonNull;
import androidx.navigation.ActionOnlyNavDirections;
import androidx.navigation.NavDirections;
import com.carbonwise.app.R;

public class SettingsFragmentDirections {
  private SettingsFragmentDirections() {
  }

  @NonNull
  public static NavDirections actionSettingsToEdit() {
    return new ActionOnlyNavDirections(R.id.action_settings_to_edit);
  }
}
