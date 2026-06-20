package com.carbonwise.app.viewmodel;

import com.carbonwise.app.data.repository.CarbonRepository;
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
public final class TrackViewModel_Factory implements Factory<TrackViewModel> {
  private final Provider<CarbonRepository> carbonRepositoryProvider;

  private final Provider<UserRepository> userRepositoryProvider;

  public TrackViewModel_Factory(Provider<CarbonRepository> carbonRepositoryProvider,
      Provider<UserRepository> userRepositoryProvider) {
    this.carbonRepositoryProvider = carbonRepositoryProvider;
    this.userRepositoryProvider = userRepositoryProvider;
  }

  @Override
  public TrackViewModel get() {
    return newInstance(carbonRepositoryProvider.get(), userRepositoryProvider.get());
  }

  public static TrackViewModel_Factory create(Provider<CarbonRepository> carbonRepositoryProvider,
      Provider<UserRepository> userRepositoryProvider) {
    return new TrackViewModel_Factory(carbonRepositoryProvider, userRepositoryProvider);
  }

  public static TrackViewModel newInstance(CarbonRepository carbonRepository,
      UserRepository userRepository) {
    return new TrackViewModel(carbonRepository, userRepository);
  }
}
