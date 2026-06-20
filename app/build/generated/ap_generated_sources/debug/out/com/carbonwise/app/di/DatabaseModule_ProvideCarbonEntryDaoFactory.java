package com.carbonwise.app.di;

import com.carbonwise.app.data.local.CarbonWiseDatabase;
import com.carbonwise.app.data.local.dao.CarbonEntryDao;
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
public final class DatabaseModule_ProvideCarbonEntryDaoFactory implements Factory<CarbonEntryDao> {
  private final DatabaseModule module;

  private final Provider<CarbonWiseDatabase> dbProvider;

  public DatabaseModule_ProvideCarbonEntryDaoFactory(DatabaseModule module,
      Provider<CarbonWiseDatabase> dbProvider) {
    this.module = module;
    this.dbProvider = dbProvider;
  }

  @Override
  public CarbonEntryDao get() {
    return provideCarbonEntryDao(module, dbProvider.get());
  }

  public static DatabaseModule_ProvideCarbonEntryDaoFactory create(DatabaseModule module,
      Provider<CarbonWiseDatabase> dbProvider) {
    return new DatabaseModule_ProvideCarbonEntryDaoFactory(module, dbProvider);
  }

  public static CarbonEntryDao provideCarbonEntryDao(DatabaseModule instance,
      CarbonWiseDatabase db) {
    return Preconditions.checkNotNullFromProvides(instance.provideCarbonEntryDao(db));
  }
}
