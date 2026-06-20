package com.carbonwise.app.di;

import com.carbonwise.app.data.local.CarbonWiseDatabase;
import com.carbonwise.app.data.local.dao.ChallengeDao;
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
public final class DatabaseModule_ProvideChallengeDaoFactory implements Factory<ChallengeDao> {
  private final DatabaseModule module;

  private final Provider<CarbonWiseDatabase> dbProvider;

  public DatabaseModule_ProvideChallengeDaoFactory(DatabaseModule module,
      Provider<CarbonWiseDatabase> dbProvider) {
    this.module = module;
    this.dbProvider = dbProvider;
  }

  @Override
  public ChallengeDao get() {
    return provideChallengeDao(module, dbProvider.get());
  }

  public static DatabaseModule_ProvideChallengeDaoFactory create(DatabaseModule module,
      Provider<CarbonWiseDatabase> dbProvider) {
    return new DatabaseModule_ProvideChallengeDaoFactory(module, dbProvider);
  }

  public static ChallengeDao provideChallengeDao(DatabaseModule instance, CarbonWiseDatabase db) {
    return Preconditions.checkNotNullFromProvides(instance.provideChallengeDao(db));
  }
}
