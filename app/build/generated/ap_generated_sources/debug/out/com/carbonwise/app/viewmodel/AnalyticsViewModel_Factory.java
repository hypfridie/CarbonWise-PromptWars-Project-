package com.carbonwise.app.viewmodel;

import com.carbonwise.app.data.repository.CarbonRepository;
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
public final class AnalyticsViewModel_Factory implements Factory<AnalyticsViewModel> {
  private final Provider<CarbonRepository> carbonRepositoryProvider;

  public AnalyticsViewModel_Factory(Provider<CarbonRepository> carbonRepositoryProvider) {
    this.carbonRepositoryProvider = carbonRepositoryProvider;
  }

  @Override
  public AnalyticsViewModel get() {
    return newInstance(carbonRepositoryProvider.get());
  }

  public static AnalyticsViewModel_Factory create(
      Provider<CarbonRepository> carbonRepositoryProvider) {
    return new AnalyticsViewModel_Factory(carbonRepositoryProvider);
  }

  public static AnalyticsViewModel newInstance(CarbonRepository carbonRepository) {
    return new AnalyticsViewModel(carbonRepository);
  }
}
