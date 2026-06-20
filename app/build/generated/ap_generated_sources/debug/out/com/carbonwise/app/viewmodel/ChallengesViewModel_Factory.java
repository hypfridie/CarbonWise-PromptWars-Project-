package com.carbonwise.app.viewmodel;

import com.carbonwise.app.data.repository.AchievementRepository;
import com.carbonwise.app.data.repository.ChallengeRepository;
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
public final class ChallengesViewModel_Factory implements Factory<ChallengesViewModel> {
  private final Provider<ChallengeRepository> challengeRepositoryProvider;

  private final Provider<AchievementRepository> achievementRepositoryProvider;

  private final Provider<UserRepository> userRepositoryProvider;

  public ChallengesViewModel_Factory(Provider<ChallengeRepository> challengeRepositoryProvider,
      Provider<AchievementRepository> achievementRepositoryProvider,
      Provider<UserRepository> userRepositoryProvider) {
    this.challengeRepositoryProvider = challengeRepositoryProvider;
    this.achievementRepositoryProvider = achievementRepositoryProvider;
    this.userRepositoryProvider = userRepositoryProvider;
  }

  @Override
  public ChallengesViewModel get() {
    return newInstance(challengeRepositoryProvider.get(), achievementRepositoryProvider.get(), userRepositoryProvider.get());
  }

  public static ChallengesViewModel_Factory create(
      Provider<ChallengeRepository> challengeRepositoryProvider,
      Provider<AchievementRepository> achievementRepositoryProvider,
      Provider<UserRepository> userRepositoryProvider) {
    return new ChallengesViewModel_Factory(challengeRepositoryProvider, achievementRepositoryProvider, userRepositoryProvider);
  }

  public static ChallengesViewModel newInstance(ChallengeRepository challengeRepository,
      AchievementRepository achievementRepository, UserRepository userRepository) {
    return new ChallengesViewModel(challengeRepository, achievementRepository, userRepository);
  }
}
