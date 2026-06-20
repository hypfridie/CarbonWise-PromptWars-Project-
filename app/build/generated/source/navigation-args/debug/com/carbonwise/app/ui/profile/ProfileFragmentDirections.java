package com.carbonwise.app.ui.profile;

import androidx.annotation.NonNull;
import androidx.navigation.ActionOnlyNavDirections;
import androidx.navigation.NavDirections;
import com.carbonwise.app.R;

public class ProfileFragmentDirections {
  private ProfileFragmentDirections() {
  }

  @NonNull
  public static NavDirections actionProfileToSettings() {
    return new ActionOnlyNavDirections(R.id.action_profile_to_settings);
  }

  @NonNull
  public static NavDirections actionProfileToEdit() {
    return new ActionOnlyNavDirections(R.id.action_profile_to_edit);
  }
}
