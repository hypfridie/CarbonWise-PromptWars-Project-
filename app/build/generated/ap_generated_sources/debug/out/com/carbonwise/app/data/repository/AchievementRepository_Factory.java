package com.carbonwise.app.data.repository;

import com.carbonwise.app.data.local.dao.AchievementDao;
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
public final class AchievementRepository_Factory implements Factory<AchievementRepository> {
  private final Provider<AchievementDao> achievementDaoProvider;

  public AchievementRepository_Factory(Provider<AchievementDao> achievementDaoProvider) {
    this.achievementDaoProvider = achievementDaoProvider;
  }

  @Override
  public AchievementRepository get() {
    return newInstance(achievementDaoProvider.get());
  }

  public static AchievementRepository_Factory create(
      Provider<AchievementDao> achievementDaoProvider) {
    return new AchievementRepository_Factory(achievementDaoProvider);
  }

  public static AchievementRepository newInstance(AchievementDao achievementDao) {
    return new AchievementRepository(achievementDao);
  }
}
