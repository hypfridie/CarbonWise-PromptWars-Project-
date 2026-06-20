package com.carbonwise.app.data.repository;

import com.carbonwise.app.data.local.dao.CarbonEntryDao;
import com.carbonwise.app.data.local.dao.ChallengeDao;
import com.carbonwise.app.data.local.dao.UserProfileDao;
import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
import javax.annotation.processing.Generated;
import javax.inject.Provider;

@ScopeMetadata("javax.inject.Singleton")
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
public final class UserRepository_Factory implements Factory<UserRepository> {
  private final Provider<UserProfileDao> userProfileDaoProvider;

  private final Provider<CarbonEntryDao> carbonEntryDaoProvider;

  private final Provider<ChallengeDao> challengeDaoProvider;

  public UserRepository_Factory(Provider<UserProfileDao> userProfileDaoProvider,
      Provider<CarbonEntryDao> carbonEntryDaoProvider,
      Provider<ChallengeDao> challengeDaoProvider) {
    this.userProfileDaoProvider = userProfileDaoProvider;
    this.carbonEntryDaoProvider = carbonEntryDaoProvider;
    this.challengeDaoProvider = challengeDaoProvider;
  }

  @Override
  public UserRepository get() {
    return newInstance(userProfileDaoProvider.get(), carbonEntryDaoProvider.get(), challengeDaoProvider.get());
  }

  public static UserRepository_Factory create(Provider<UserProfileDao> userProfileDaoProvider,
      Provider<CarbonEntryDao> carbonEntryDaoProvider,
      Provider<ChallengeDao> challengeDaoProvider) {
    return new UserRepository_Factory(userProfileDaoProvider, carbonEntryDaoProvider, challengeDaoProvider);
  }

  public static UserRepository newInstance(UserProfileDao userProfileDao,
      CarbonEntryDao carbonEntryDao, ChallengeDao challengeDao) {
    return new UserRepository(userProfileDao, carbonEntryDao, challengeDao);
  }
}
