package com.carbonwise.app.ui.profile;

import com.carbonwise.app.data.repository.UserRepository;
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
public final class SettingsFragment_MembersInjector implements MembersInjector<SettingsFragment> {
  private final Provider<PreferenceManager> preferenceManagerProvider;

  private final Provider<UserRepository> userRepositoryProvider;

  public SettingsFragment_MembersInjector(Provider<PreferenceManager> preferenceManagerProvider,
      Provider<UserRepository> userRepositoryProvider) {
    this.preferenceManagerProvider = preferenceManagerProvider;
    this.userRepositoryProvider = userRepositoryProvider;
  }

  public static MembersInjector<SettingsFragment> create(
      Provider<PreferenceManager> preferenceManagerProvider,
      Provider<UserRepository> userRepositoryProvider) {
    return new SettingsFragment_MembersInjector(preferenceManagerProvider, userRepositoryProvider);
  }

  @Override
  public void injectMembers(SettingsFragment instance) {
    injectPreferenceManager(instance, preferenceManagerProvider.get());
    injectUserRepository(instance, userRepositoryProvider.get());
  }

  @InjectedFieldSignature("com.carbonwise.app.ui.profile.SettingsFragment.preferenceManager")
  public static void injectPreferenceManager(SettingsFragment instance,
      PreferenceManager preferenceManager) {
    instance.preferenceManager = preferenceManager;
  }

  @InjectedFieldSignature("com.carbonwise.app.ui.profile.SettingsFragment.userRepository")
  public static void injectUserRepository(SettingsFragment instance,
      UserRepository userRepository) {
    instance.userRepository = userRepository;
  }
}
