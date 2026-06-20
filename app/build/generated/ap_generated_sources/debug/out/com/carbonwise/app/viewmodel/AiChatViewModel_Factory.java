package com.carbonwise.app.viewmodel;

import com.carbonwise.app.data.remote.AiChatApiService;
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
public final class AiChatViewModel_Factory implements Factory<AiChatViewModel> {
  private final Provider<AiChatApiService> apiServiceProvider;

  private final Provider<CarbonRepository> carbonRepositoryProvider;

  private final Provider<UserRepository> userRepositoryProvider;

  public AiChatViewModel_Factory(Provider<AiChatApiService> apiServiceProvider,
      Provider<CarbonRepository> carbonRepositoryProvider,
      Provider<UserRepository> userRepositoryProvider) {
    this.apiServiceProvider = apiServiceProvider;
    this.carbonRepositoryProvider = carbonRepositoryProvider;
    this.userRepositoryProvider = userRepositoryProvider;
  }

  @Override
  public AiChatViewModel get() {
    return newInstance(apiServiceProvider.get(), carbonRepositoryProvider.get(), userRepositoryProvider.get());
  }

  public static AiChatViewModel_Factory create(Provider<AiChatApiService> apiServiceProvider,
      Provider<CarbonRepository> carbonRepositoryProvider,
      Provider<UserRepository> userRepositoryProvider) {
    return new AiChatViewModel_Factory(apiServiceProvider, carbonRepositoryProvider, userRepositoryProvider);
  }

  public static AiChatViewModel newInstance(AiChatApiService apiService,
      CarbonRepository carbonRepository, UserRepository userRepository) {
    return new AiChatViewModel(apiService, carbonRepository, userRepository);
  }
}
