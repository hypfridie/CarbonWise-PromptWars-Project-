package com.carbonwise.app.ui.challenges;

import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.lifecycle.SavedStateHandle;
import androidx.navigation.NavArgs;
import java.lang.Object;
import java.lang.Override;
import java.lang.String;
import java.lang.SuppressWarnings;
import java.util.HashMap;

public class ChallengesFragmentArgs implements NavArgs {
  private final HashMap arguments = new HashMap();

  private ChallengesFragmentArgs() {
  }

  @SuppressWarnings("unchecked")
  private ChallengesFragmentArgs(HashMap argumentsMap) {
    this.arguments.putAll(argumentsMap);
  }

  @NonNull
  @SuppressWarnings("unchecked")
  public static ChallengesFragmentArgs fromBundle(@NonNull Bundle bundle) {
    ChallengesFragmentArgs __result = new ChallengesFragmentArgs();
    bundle.setClassLoader(ChallengesFragmentArgs.class.getClassLoader());
    if (bundle.containsKey("initialTab")) {
      int initialTab;
      initialTab = bundle.getInt("initialTab");
      __result.arguments.put("initialTab", initialTab);
    } else {
      __result.arguments.put("initialTab", 0);
    }
    return __result;
  }

  @NonNull
  @SuppressWarnings("unchecked")
  public static ChallengesFragmentArgs fromSavedStateHandle(
      @NonNull SavedStateHandle savedStateHandle) {
    ChallengesFragmentArgs __result = new ChallengesFragmentArgs();
    if (savedStateHandle.contains("initialTab")) {
      int initialTab;
      initialTab = savedStateHandle.get("initialTab");
      __result.arguments.put("initialTab", initialTab);
    } else {
      __result.arguments.put("initialTab", 0);
    }
    return __result;
  }

  @SuppressWarnings("unchecked")
  public int getInitialTab() {
    return (int) arguments.get("initialTab");
  }

  @SuppressWarnings("unchecked")
  @NonNull
  public Bundle toBundle() {
    Bundle __result = new Bundle();
    if (arguments.containsKey("initialTab")) {
      int initialTab = (int) arguments.get("initialTab");
      __result.putInt("initialTab", initialTab);
    } else {
      __result.putInt("initialTab", 0);
    }
    return __result;
  }

  @SuppressWarnings("unchecked")
  @NonNull
  public SavedStateHandle toSavedStateHandle() {
    SavedStateHandle __result = new SavedStateHandle();
    if (arguments.containsKey("initialTab")) {
      int initialTab = (int) arguments.get("initialTab");
      __result.set("initialTab", initialTab);
    } else {
      __result.set("initialTab", 0);
    }
    return __result;
  }

  @Override
  public boolean equals(Object object) {
    if (this == object) {
        return true;
    }
    if (object == null || getClass() != object.getClass()) {
        return false;
    }
    ChallengesFragmentArgs that = (ChallengesFragmentArgs) object;
    if (arguments.containsKey("initialTab") != that.arguments.containsKey("initialTab")) {
      return false;
    }
    if (getInitialTab() != that.getInitialTab()) {
      return false;
    }
    return true;
  }

  @Override
  public int hashCode() {
    int result = 1;
    result = 31 * result + getInitialTab();
    return result;
  }

  @Override
  public String toString() {
    return "ChallengesFragmentArgs{"
        + "initialTab=" + getInitialTab()
        + "}";
  }

  public static final class Builder {
    private final HashMap arguments = new HashMap();

    @SuppressWarnings("unchecked")
    public Builder(@NonNull ChallengesFragmentArgs original) {
      this.arguments.putAll(original.arguments);
    }

    public Builder() {
    }

    @NonNull
    public ChallengesFragmentArgs build() {
      ChallengesFragmentArgs result = new ChallengesFragmentArgs(arguments);
      return result;
    }

    @NonNull
    @SuppressWarnings("unchecked")
    public Builder setInitialTab(int initialTab) {
      this.arguments.put("initialTab", initialTab);
      return this;
    }

    @SuppressWarnings({"unchecked","GetterOnBuilder"})
    public int getInitialTab() {
      return (int) arguments.get("initialTab");
    }
  }
}
