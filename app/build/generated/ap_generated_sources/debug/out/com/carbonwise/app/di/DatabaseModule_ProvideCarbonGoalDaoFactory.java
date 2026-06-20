package com.carbonwise.app.di;

import com.carbonwise.app.data.local.CarbonWiseDatabase;
import com.carbonwise.app.data.local.dao.CarbonGoalDao;
import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
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
public final class DatabaseModule_ProvideCarbonGoalDaoFactory implements Factory<CarbonGoalDao> {
  private final DatabaseModule module;

  private final Provider<CarbonWiseDatabase> dbProvider;

  public DatabaseModule_ProvideCarbonGoalDaoFactory(DatabaseModule module,
      Provider<CarbonWiseDatabase> dbProvider) {
    this.module = module;
    this.dbProvider = dbProvider;
  }

  @Override
  public CarbonGoalDao get() {
    return provideCarbonGoalDao(module, dbProvider.get());
  }

  public static DatabaseModule_ProvideCarbonGoalDaoFactory create(DatabaseModule module,
      Provider<CarbonWiseDatabase> dbProvider) {
    return new DatabaseModule_ProvideCarbonGoalDaoFactory(module, dbProvider);
  }

  public static CarbonGoalDao provideCarbonGoalDao(DatabaseModule instance, CarbonWiseDatabase db) {
    return Preconditions.checkNotNullFromProvides(instance.provideCarbonGoalDao(db));
  }
}
