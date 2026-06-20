package com.carbonwise.app.data.repository;

import com.carbonwise.app.data.local.dao.CarbonEntryDao;
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
public final class CarbonRepository_Factory implements Factory<CarbonRepository> {
  private final Provider<CarbonEntryDao> carbonEntryDaoProvider;

  public CarbonRepository_Factory(Provider<CarbonEntryDao> carbonEntryDaoProvider) {
    this.carbonEntryDaoProvider = carbonEntryDaoProvider;
  }

  @Override
  public CarbonRepository get() {
    return newInstance(carbonEntryDaoProvider.get());
  }

  public static CarbonRepository_Factory create(Provider<CarbonEntryDao> carbonEntryDaoProvider) {
    return new CarbonRepository_Factory(carbonEntryDaoProvider);
  }

  public static CarbonRepository newInstance(CarbonEntryDao carbonEntryDao) {
    return new CarbonRepository(carbonEntryDao);
  }
}
