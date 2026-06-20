package com.carbonwise.app.data.repository;

import com.carbonwise.app.data.local.dao.CarbonGoalDao;
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
public final class GoalRepository_Factory implements Factory<GoalRepository> {
  private final Provider<CarbonGoalDao> carbonGoalDaoProvider;

  public GoalRepository_Factory(Provider<CarbonGoalDao> carbonGoalDaoProvider) {
    this.carbonGoalDaoProvider = carbonGoalDaoProvider;
  }

  @Override
  public GoalRepository get() {
    return newInstance(carbonGoalDaoProvider.get());
  }

  public static GoalRepository_Factory create(Provider<CarbonGoalDao> carbonGoalDaoProvider) {
    return new GoalRepository_Factory(carbonGoalDaoProvider);
  }

  public static GoalRepository newInstance(CarbonGoalDao carbonGoalDao) {
    return new GoalRepository(carbonGoalDao);
  }
}
