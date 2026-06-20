package com.carbonwise.app.ui.onboarding;

import com.carbonwise.app.util.PreferenceManager;
import dagger.MembersInjector;
import dagger.internal.DaggerGenerated;
import dagger.internal.InjectedFieldSignature;
import dagger.internal.QualifierMetadata;
import javax.annotation.processing.Generated;
import javax.inject.Provider;

@QualifierMetadata
@DaggerGenerated
@Generated(
    value = "dagger.internal.codegen.ComponentProcessor",
    comments = "https://dagger.dev"
)
@SuppressWarnings({
    "unchecked",
    "rawtypes",
    "KotlinInternal",
    "KotlinInternalInJava",
    "cast"
})
public final class OnboardingActivity_MembersInjector implements MembersInjector<OnboardingActivity> {
  private final Provider<PreferenceManager> preferenceManagerProvider;

  public OnboardingActivity_MembersInjector(Provider<PreferenceManager> preferenceManagerProvider) {
    this.preferenceManagerProvider = preferenceManagerProvider;
  }

  public static MembersInjector<OnboardingActivity> create(
      Provider<PreferenceManager> preferenceManagerProvider) {
    return new OnboardingActivity_MembersInjector(preferenceManagerProvider);
  }

  @Override
  public void injectMembers(OnboardingActivity instance) {
    injectPreferenceManager(instance, preferenceManagerProvider.get());
  }

  @InjectedFieldSignature("com.carbonwise.app.ui.onboarding.OnboardingActivity.preferenceManager")
  public static void injectPreferenceManager(OnboardingActivity instance,
      PreferenceManager preferenceManager) {
    instance.preferenceManager = preferenceManager;
  }
}
