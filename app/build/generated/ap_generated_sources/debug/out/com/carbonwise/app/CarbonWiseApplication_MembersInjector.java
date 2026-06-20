package com.carbonwise.app;

import androidx.hilt.work.HiltWorkerFactory;
import com.carbonwise.app.data.repository.AchievementRepository;
import com.carbonwise.app.data.repository.ChallengeRepository;
import com.carbonwise.app.data.repository.UserRepository;
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
public final class CarbonWiseApplication_MembersInjector implements MembersInjector<CarbonWiseApplication> {
  private final Provider<HiltWorkerFactory> workerFactoryProvider;

  private final Provider<ChallengeRepository> challengeRepositoryProvider;

  private final Provider<AchievementRepository> achievementRepositoryProvider;

  private final Provider<UserRepository> userRepositoryProvider;

  public CarbonWiseApplication_MembersInjector(Provider<HiltWorkerFactory> workerFactoryProvider,
      Provider<ChallengeRepository> challengeRepositoryProvider,
      Provider<AchievementRepository> achievementRepositoryProvider,
      Provider<UserRepository> userRepositoryProvider) {
    this.workerFactoryProvider = workerFactoryProvider;
    this.challengeRepositoryProvider = challengeRepositoryProvider;
    this.achievementRepositoryProvider = achievementRepositoryProvider;
    this.userRepositoryProvider = userRepositoryProvider;
  }

  public static MembersInjector<CarbonWiseApplication> create(
      Provider<HiltWorkerFactory> workerFactoryProvider,
      Provider<ChallengeRepository> challengeRepositoryProvider,
      Provider<AchievementRepository> achievementRepositoryProvider,
      Provider<UserRepository> userRepositoryProvider) {
    return new CarbonWiseApplication_MembersInjector(workerFactoryProvider, challengeRepositoryProvider, achievementRepositoryProvider, userRepositoryProvider);
  }

  @Override
  public void injectMembers(CarbonWiseApplication instance) {
    injectWorkerFactory(instance, workerFactoryProvider.get());
    injectChallengeRepository(instance, challengeRepositoryProvider.get());
    injectAchievementRepository(instance, achievementRepositoryProvider.get());
    injectUserRepository(instance, userRepositoryProvider.get());
  }

  @InjectedFieldSignature("com.carbonwise.app.CarbonWiseApplication.workerFactory")
  public static void injectWorkerFactory(CarbonWiseApplication instance,
      HiltWorkerFactory workerFactory) {
    instance.workerFactory = workerFactory;
  }

  @InjectedFieldSignature("com.carbonwise.app.CarbonWiseApplication.challengeRepository")
  public static void injectChallengeRepository(CarbonWiseApplication instance,
      ChallengeRepository challengeRepository) {
    instance.challengeRepository = challengeRepository;
  }

  @InjectedFieldSignature("com.carbonwise.app.CarbonWiseApplication.achievementRepository")
  public static void injectAchievementRepository(CarbonWiseApplication instance,
      AchievementRepository achievementRepository) {
    instance.achievementRepository = achievementRepository;
  }

  @InjectedFieldSignature("com.carbonwise.app.CarbonWiseApplication.userRepository")
  public static void injectUserRepository(CarbonWiseApplication instance,
      UserRepository userRepository) {
    instance.userRepository = userRepository;
  }
}
