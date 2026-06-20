package com.carbonwise.app.viewmodel;

import com.carbonwise.app.data.repository.GoalRepository;
import com.carbonwise.app.data.repository.UserRepository;
import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
import javax.annotation.processing.Generated;
import javax.inject.Provider;

@ScopeMetadata
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
public final class ProfileViewModel_Factory implements Factory<ProfileViewModel> {
  private final Provider<UserRepository> userRepositoryProvider;

  private final Provider<GoalRepository> goalRepositoryProvider;

  public ProfileViewModel_Factory(Provider<UserRepository> userRepositoryProvider,
      Provider<GoalRepository> goalRepositoryProvider) {
    this.userRepositoryProvider = userRepositoryProvider;
    this.goalRepositoryProvider = goalRepositoryProvider;
  }

  @Override
  public ProfileViewModel get() {
    return newInstance(userRepositoryProvider.get(), goalRepositoryProvider.get());
  }

  public static ProfileViewModel_Factory create(Provider<UserRepository> userRepositoryProvider,
      Provider<GoalRepository> goalRepositoryProvider) {
    return new ProfileViewModel_Factory(userRepositoryProvider, goalRepositoryProvider);
  }

  public static ProfileViewModel newInstance(UserRepository userRepository,
      GoalRepository goalRepository) {
    return new ProfileViewModel(userRepository, goalRepository);
  }
}
