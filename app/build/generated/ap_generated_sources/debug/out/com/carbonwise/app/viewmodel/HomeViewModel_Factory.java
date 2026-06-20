package com.carbonwise.app.viewmodel;

import com.carbonwise.app.data.repository.CarbonRepository;
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
public final class HomeViewModel_Factory implements Factory<HomeViewModel> {
  private final Provider<CarbonRepository> carbonRepositoryProvider;

  private final Provider<UserRepository> userRepositoryProvider;

  private final Provider<ChallengeRepository> challengeRepositoryProvider;

  public HomeViewModel_Factory(Provider<CarbonRepository> carbonRepositoryProvider,
      Provider<UserRepository> userRepositoryProvider,
      Provider<ChallengeRepository> challengeRepositoryProvider) {
    this.carbonRepositoryProvider = carbonRepositoryProvider;
    this.userRepositoryProvider = userRepositoryProvider;
    this.challengeRepositoryProvider = challengeRepositoryProvider;
  }

  @Override
  public HomeViewModel get() {
    return newInstance(carbonRepositoryProvider.get(), userRepositoryProvider.get(), challengeRepositoryProvider.get());
  }

  public static HomeViewModel_Factory create(Provider<CarbonRepository> carbonRepositoryProvider,
      Provider<UserRepository> userRepositoryProvider,
      Provider<ChallengeRepository> challengeRepositoryProvider) {
    return new HomeViewModel_Factory(carbonRepositoryProvider, userRepositoryProvider, challengeRepositoryProvider);
  }

  public static HomeViewModel newInstance(CarbonRepository carbonRepository,
      UserRepository userRepository, ChallengeRepository challengeRepository) {
    return new HomeViewModel(carbonRepository, userRepository, challengeRepository);
  }
}
