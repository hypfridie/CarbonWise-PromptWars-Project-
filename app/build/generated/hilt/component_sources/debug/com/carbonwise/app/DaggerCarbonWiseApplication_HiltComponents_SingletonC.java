package com.carbonwise.app;

import android.app.Activity;
import android.app.Service;
import android.view.View;
import androidx.fragment.app.Fragment;
import androidx.hilt.work.HiltWorkerFactory;
import androidx.hilt.work.WorkerAssistedFactory;
import androidx.hilt.work.WorkerFactoryModule_ProvideFactoryFactory;
import androidx.lifecycle.SavedStateHandle;
import androidx.lifecycle.ViewModel;
import androidx.work.ListenableWorker;
import com.carbonwise.app.data.local.CarbonWiseDatabase;
import com.carbonwise.app.data.local.dao.AchievementDao;
import com.carbonwise.app.data.local.dao.CarbonEntryDao;
import com.carbonwise.app.data.local.dao.CarbonGoalDao;
import com.carbonwise.app.data.local.dao.ChallengeDao;
import com.carbonwise.app.data.local.dao.UserProfileDao;
import com.carbonwise.app.data.remote.AiChatApiService;
import com.carbonwise.app.data.repository.AchievementRepository;
import com.carbonwise.app.data.repository.CarbonRepository;
import com.carbonwise.app.data.repository.ChallengeRepository;
import com.carbonwise.app.data.repository.GoalRepository;
import com.carbonwise.app.data.repository.UserRepository;
import com.carbonwise.app.di.DatabaseModule;
import com.carbonwise.app.di.DatabaseModule_ProvideAchievementDaoFactory;
import com.carbonwise.app.di.DatabaseModule_ProvideCarbonEntryDaoFactory;
import com.carbonwise.app.di.DatabaseModule_ProvideCarbonGoalDaoFactory;
import com.carbonwise.app.di.DatabaseModule_ProvideChallengeDaoFactory;
import com.carbonwise.app.di.DatabaseModule_ProvideDatabaseFactory;
import com.carbonwise.app.di.DatabaseModule_ProvideUserProfileDaoFactory;
import com.carbonwise.app.di.NetworkModule;
import com.carbonwise.app.di.NetworkModule_ProvideAiChatApiServiceFactory;
import com.carbonwise.app.di.NetworkModule_ProvideOkHttpClientFactory;
import com.carbonwise.app.di.NetworkModule_ProvideRetrofitFactory;
import com.carbonwise.app.ui.MainActivity;
import com.carbonwise.app.ui.MainActivity_MembersInjector;
import com.carbonwise.app.ui.analytics.AnalyticsFragment;
import com.carbonwise.app.ui.challenges.ChallengesFragment;
import com.carbonwise.app.ui.chat.AiChatActivity;
import com.carbonwise.app.ui.home.HomeFragment;
import com.carbonwise.app.ui.onboarding.OnboardingActivity;
import com.carbonwise.app.ui.onboarding.OnboardingActivity_MembersInjector;
import com.carbonwise.app.ui.profile.EditProfileFragment;
import com.carbonwise.app.ui.profile.ProfileFragment;
import com.carbonwise.app.ui.profile.SettingsFragment;
import com.carbonwise.app.ui.profile.SettingsFragment_MembersInjector;
import com.carbonwise.app.ui.track.TrackFragment;
import com.carbonwise.app.util.PreferenceManager;
import com.carbonwise.app.viewmodel.AiChatViewModel;
import com.carbonwise.app.viewmodel.AiChatViewModel_HiltModules;
import com.carbonwise.app.viewmodel.AnalyticsViewModel;
import com.carbonwise.app.viewmodel.AnalyticsViewModel_HiltModules;
import com.carbonwise.app.viewmodel.ChallengesViewModel;
import com.carbonwise.app.viewmodel.ChallengesViewModel_HiltModules;
import com.carbonwise.app.viewmodel.HomeViewModel;
import com.carbonwise.app.viewmodel.HomeViewModel_HiltModules;
import com.carbonwise.app.viewmodel.ProfileViewModel;
import com.carbonwise.app.viewmodel.ProfileViewModel_HiltModules;
import com.carbonwise.app.viewmodel.TrackViewModel;
import com.carbonwise.app.viewmodel.TrackViewModel_HiltModules;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.ImmutableSet;
import com.google.errorprone.annotations.CanIgnoreReturnValue;
import dagger.hilt.android.ActivityRetainedLifecycle;
import dagger.hilt.android.ViewModelLifecycle;
import dagger.hilt.android.internal.builders.ActivityComponentBuilder;
import dagger.hilt.android.internal.builders.ActivityRetainedComponentBuilder;
import dagger.hilt.android.internal.builders.FragmentComponentBuilder;
import dagger.hilt.android.internal.builders.ServiceComponentBuilder;
import dagger.hilt.android.internal.builders.ViewComponentBuilder;
import dagger.hilt.android.internal.builders.ViewModelComponentBuilder;
import dagger.hilt.android.internal.builders.ViewWithFragmentComponentBuilder;
import dagger.hilt.android.internal.lifecycle.DefaultViewModelFactories;
import dagger.hilt.android.internal.lifecycle.DefaultViewModelFactories_InternalFactoryFactory_Factory;
import dagger.hilt.android.internal.managers.ActivityRetainedComponentManager_LifecycleModule_ProvideActivityRetainedLifecycleFactory;
import dagger.hilt.android.internal.managers.SavedStateHandleHolder;
import dagger.hilt.android.internal.modules.ApplicationContextModule;
import dagger.hilt.android.internal.modules.ApplicationContextModule_ProvideContextFactory;
import dagger.internal.DaggerGenerated;
import dagger.internal.DoubleCheck;
import dagger.internal.IdentifierNameString;
import dagger.internal.KeepFieldType;
import dagger.internal.LazyClassKeyMap;
import dagger.internal.Preconditions;
import dagger.internal.Provider;
import java.util.Map;
import java.util.Set;
import javax.annotation.processing.Generated;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;

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
public final class DaggerCarbonWiseApplication_HiltComponents_SingletonC {
  private DaggerCarbonWiseApplication_HiltComponents_SingletonC() {
  }

  public static Builder builder() {
    return new Builder();
  }

  public static final class Builder {
    private ApplicationContextModule applicationContextModule;

    private DatabaseModule databaseModule;

    private NetworkModule networkModule;

    private Builder() {
    }

    public Builder applicationContextModule(ApplicationContextModule applicationContextModule) {
      this.applicationContextModule = Preconditions.checkNotNull(applicationContextModule);
      return this;
    }

    public Builder databaseModule(DatabaseModule databaseModule) {
      this.databaseModule = Preconditions.checkNotNull(databaseModule);
      return this;
    }

    public Builder networkModule(NetworkModule networkModule) {
      this.networkModule = Preconditions.checkNotNull(networkModule);
      return this;
    }

    public CarbonWiseApplication_HiltComponents.SingletonC build() {
      Preconditions.checkBuilderRequirement(applicationContextModule, ApplicationContextModule.class);
      if (databaseModule == null) {
        this.databaseModule = new DatabaseModule();
      }
      if (networkModule == null) {
        this.networkModule = new NetworkModule();
      }
      return new SingletonCImpl(applicationContextModule, databaseModule, networkModule);
    }
  }

  private static final class ActivityRetainedCBuilder implements CarbonWiseApplication_HiltComponents.ActivityRetainedC.Builder {
    private final SingletonCImpl singletonCImpl;

    private SavedStateHandleHolder savedStateHandleHolder;

    private ActivityRetainedCBuilder(SingletonCImpl singletonCImpl) {
      this.singletonCImpl = singletonCImpl;
    }

    @Override
    public ActivityRetainedCBuilder savedStateHandleHolder(
        SavedStateHandleHolder savedStateHandleHolder) {
      this.savedStateHandleHolder = Preconditions.checkNotNull(savedStateHandleHolder);
      return this;
    }

    @Override
    public CarbonWiseApplication_HiltComponents.ActivityRetainedC build() {
      Preconditions.checkBuilderRequirement(savedStateHandleHolder, SavedStateHandleHolder.class);
      return new ActivityRetainedCImpl(singletonCImpl, savedStateHandleHolder);
    }
  }

  private static final class ActivityCBuilder implements CarbonWiseApplication_HiltComponents.ActivityC.Builder {
    private final SingletonCImpl singletonCImpl;

    private final ActivityRetainedCImpl activityRetainedCImpl;

    private Activity activity;

    private ActivityCBuilder(SingletonCImpl singletonCImpl,
        ActivityRetainedCImpl activityRetainedCImpl) {
      this.singletonCImpl = singletonCImpl;
      this.activityRetainedCImpl = activityRetainedCImpl;
    }

    @Override
    public ActivityCBuilder activity(Activity activity) {
      this.activity = Preconditions.checkNotNull(activity);
      return this;
    }

    @Override
    public CarbonWiseApplication_HiltComponents.ActivityC build() {
      Preconditions.checkBuilderRequirement(activity, Activity.class);
      return new ActivityCImpl(singletonCImpl, activityRetainedCImpl, activity);
    }
  }

  private static final class FragmentCBuilder implements CarbonWiseApplication_HiltComponents.FragmentC.Builder {
    private final SingletonCImpl singletonCImpl;

    private final ActivityRetainedCImpl activityRetainedCImpl;

    private final ActivityCImpl activityCImpl;

    private Fragment fragment;

    private FragmentCBuilder(SingletonCImpl singletonCImpl,
        ActivityRetainedCImpl activityRetainedCImpl, ActivityCImpl activityCImpl) {
      this.singletonCImpl = singletonCImpl;
      this.activityRetainedCImpl = activityRetainedCImpl;
      this.activityCImpl = activityCImpl;
    }

    @Override
    public FragmentCBuilder fragment(Fragment fragment) {
      this.fragment = Preconditions.checkNotNull(fragment);
      return this;
    }

    @Override
    public CarbonWiseApplication_HiltComponents.FragmentC build() {
      Preconditions.checkBuilderRequirement(fragment, Fragment.class);
      return new FragmentCImpl(singletonCImpl, activityRetainedCImpl, activityCImpl, fragment);
    }
  }

  private static final class ViewWithFragmentCBuilder implements CarbonWiseApplication_HiltComponents.ViewWithFragmentC.Builder {
    private final SingletonCImpl singletonCImpl;

    private final ActivityRetainedCImpl activityRetainedCImpl;

    private final ActivityCImpl activityCImpl;

    private final FragmentCImpl fragmentCImpl;

    private View view;

    private ViewWithFragmentCBuilder(SingletonCImpl singletonCImpl,
        ActivityRetainedCImpl activityRetainedCImpl, ActivityCImpl activityCImpl,
        FragmentCImpl fragmentCImpl) {
      this.singletonCImpl = singletonCImpl;
      this.activityRetainedCImpl = activityRetainedCImpl;
      this.activityCImpl = activityCImpl;
      this.fragmentCImpl = fragmentCImpl;
    }

    @Override
    public ViewWithFragmentCBuilder view(View view) {
      this.view = Preconditions.checkNotNull(view);
      return this;
    }

    @Override
    public CarbonWiseApplication_HiltComponents.ViewWithFragmentC build() {
      Preconditions.checkBuilderRequirement(view, View.class);
      return new ViewWithFragmentCImpl(singletonCImpl, activityRetainedCImpl, activityCImpl, fragmentCImpl, view);
    }
  }

  private static final class ViewCBuilder implements CarbonWiseApplication_HiltComponents.ViewC.Builder {
    private final SingletonCImpl singletonCImpl;

    private final ActivityRetainedCImpl activityRetainedCImpl;

    private final ActivityCImpl activityCImpl;

    private View view;

    private ViewCBuilder(SingletonCImpl singletonCImpl, ActivityRetainedCImpl activityRetainedCImpl,
        ActivityCImpl activityCImpl) {
      this.singletonCImpl = singletonCImpl;
      this.activityRetainedCImpl = activityRetainedCImpl;
      this.activityCImpl = activityCImpl;
    }

    @Override
    public ViewCBuilder view(View view) {
      this.view = Preconditions.checkNotNull(view);
      return this;
    }

    @Override
    public CarbonWiseApplication_HiltComponents.ViewC build() {
      Preconditions.checkBuilderRequirement(view, View.class);
      return new ViewCImpl(singletonCImpl, activityRetainedCImpl, activityCImpl, view);
    }
  }

  private static final class ViewModelCBuilder implements CarbonWiseApplication_HiltComponents.ViewModelC.Builder {
    private final SingletonCImpl singletonCImpl;

    private final ActivityRetainedCImpl activityRetainedCImpl;

    private SavedStateHandle savedStateHandle;

    private ViewModelLifecycle viewModelLifecycle;

    private ViewModelCBuilder(SingletonCImpl singletonCImpl,
        ActivityRetainedCImpl activityRetainedCImpl) {
      this.singletonCImpl = singletonCImpl;
      this.activityRetainedCImpl = activityRetainedCImpl;
    }

    @Override
    public ViewModelCBuilder savedStateHandle(SavedStateHandle handle) {
      this.savedStateHandle = Preconditions.checkNotNull(handle);
      return this;
    }

    @Override
    public ViewModelCBuilder viewModelLifecycle(ViewModelLifecycle viewModelLifecycle) {
      this.viewModelLifecycle = Preconditions.checkNotNull(viewModelLifecycle);
      return this;
    }

    @Override
    public CarbonWiseApplication_HiltComponents.ViewModelC build() {
      Preconditions.checkBuilderRequirement(savedStateHandle, SavedStateHandle.class);
      Preconditions.checkBuilderRequirement(viewModelLifecycle, ViewModelLifecycle.class);
      return new ViewModelCImpl(singletonCImpl, activityRetainedCImpl, savedStateHandle, viewModelLifecycle);
    }
  }

  private static final class ServiceCBuilder implements CarbonWiseApplication_HiltComponents.ServiceC.Builder {
    private final SingletonCImpl singletonCImpl;

    private Service service;

    private ServiceCBuilder(SingletonCImpl singletonCImpl) {
      this.singletonCImpl = singletonCImpl;
    }

    @Override
    public ServiceCBuilder service(Service service) {
      this.service = Preconditions.checkNotNull(service);
      return this;
    }

    @Override
    public CarbonWiseApplication_HiltComponents.ServiceC build() {
      Preconditions.checkBuilderRequirement(service, Service.class);
      return new ServiceCImpl(singletonCImpl, service);
    }
  }

  private static final class ViewWithFragmentCImpl extends CarbonWiseApplication_HiltComponents.ViewWithFragmentC {
    private final SingletonCImpl singletonCImpl;

    private final ActivityRetainedCImpl activityRetainedCImpl;

    private final ActivityCImpl activityCImpl;

    private final FragmentCImpl fragmentCImpl;

    private final ViewWithFragmentCImpl viewWithFragmentCImpl = this;

    private ViewWithFragmentCImpl(SingletonCImpl singletonCImpl,
        ActivityRetainedCImpl activityRetainedCImpl, ActivityCImpl activityCImpl,
        FragmentCImpl fragmentCImpl, View viewParam) {
      this.singletonCImpl = singletonCImpl;
      this.activityRetainedCImpl = activityRetainedCImpl;
      this.activityCImpl = activityCImpl;
      this.fragmentCImpl = fragmentCImpl;


    }
  }

  private static final class FragmentCImpl extends CarbonWiseApplication_HiltComponents.FragmentC {
    private final SingletonCImpl singletonCImpl;

    private final ActivityRetainedCImpl activityRetainedCImpl;

    private final ActivityCImpl activityCImpl;

    private final FragmentCImpl fragmentCImpl = this;

    private FragmentCImpl(SingletonCImpl singletonCImpl,
        ActivityRetainedCImpl activityRetainedCImpl, ActivityCImpl activityCImpl,
        Fragment fragmentParam) {
      this.singletonCImpl = singletonCImpl;
      this.activityRetainedCImpl = activityRetainedCImpl;
      this.activityCImpl = activityCImpl;


    }

    @Override
    public void injectAnalyticsFragment(AnalyticsFragment analyticsFragment) {
    }

    @Override
    public void injectChallengesFragment(ChallengesFragment challengesFragment) {
    }

    @Override
    public void injectHomeFragment(HomeFragment homeFragment) {
    }

    @Override
    public void injectEditProfileFragment(EditProfileFragment editProfileFragment) {
    }

    @Override
    public void injectProfileFragment(ProfileFragment profileFragment) {
    }

    @Override
    public void injectSettingsFragment(SettingsFragment settingsFragment) {
      injectSettingsFragment2(settingsFragment);
    }

    @Override
    public void injectTrackFragment(TrackFragment trackFragment) {
    }

    @Override
    public DefaultViewModelFactories.InternalFactoryFactory getHiltInternalFactoryFactory() {
      return activityCImpl.getHiltInternalFactoryFactory();
    }

    @Override
    public ViewWithFragmentComponentBuilder viewWithFragmentComponentBuilder() {
      return new ViewWithFragmentCBuilder(singletonCImpl, activityRetainedCImpl, activityCImpl, fragmentCImpl);
    }

    @CanIgnoreReturnValue
    private SettingsFragment injectSettingsFragment2(SettingsFragment instance) {
      SettingsFragment_MembersInjector.injectPreferenceManager(instance, singletonCImpl.preferenceManagerProvider.get());
      SettingsFragment_MembersInjector.injectUserRepository(instance, singletonCImpl.userRepositoryProvider.get());
      return instance;
    }
  }

  private static final class ViewCImpl extends CarbonWiseApplication_HiltComponents.ViewC {
    private final SingletonCImpl singletonCImpl;

    private final ActivityRetainedCImpl activityRetainedCImpl;

    private final ActivityCImpl activityCImpl;

    private final ViewCImpl viewCImpl = this;

    private ViewCImpl(SingletonCImpl singletonCImpl, ActivityRetainedCImpl activityRetainedCImpl,
        ActivityCImpl activityCImpl, View viewParam) {
      this.singletonCImpl = singletonCImpl;
      this.activityRetainedCImpl = activityRetainedCImpl;
      this.activityCImpl = activityCImpl;


    }
  }

  private static final class ActivityCImpl extends CarbonWiseApplication_HiltComponents.ActivityC {
    private final SingletonCImpl singletonCImpl;

    private final ActivityRetainedCImpl activityRetainedCImpl;

    private final ActivityCImpl activityCImpl = this;

    private ActivityCImpl(SingletonCImpl singletonCImpl,
        ActivityRetainedCImpl activityRetainedCImpl, Activity activityParam) {
      this.singletonCImpl = singletonCImpl;
      this.activityRetainedCImpl = activityRetainedCImpl;


    }

    @Override
    public void injectMainActivity(MainActivity mainActivity) {
      injectMainActivity2(mainActivity);
    }

    @Override
    public void injectAiChatActivity(AiChatActivity aiChatActivity) {
    }

    @Override
    public void injectOnboardingActivity(OnboardingActivity onboardingActivity) {
      injectOnboardingActivity2(onboardingActivity);
    }

    @Override
    public DefaultViewModelFactories.InternalFactoryFactory getHiltInternalFactoryFactory() {
      return DefaultViewModelFactories_InternalFactoryFactory_Factory.newInstance(getViewModelKeys(), new ViewModelCBuilder(singletonCImpl, activityRetainedCImpl));
    }

    @Override
    public Map<Class<?>, Boolean> getViewModelKeys() {
      return LazyClassKeyMap.<Boolean>of(ImmutableMap.<String, Boolean>builderWithExpectedSize(6).put(LazyClassKeyProvider.com_carbonwise_app_viewmodel_AiChatViewModel, AiChatViewModel_HiltModules.KeyModule.provide()).put(LazyClassKeyProvider.com_carbonwise_app_viewmodel_AnalyticsViewModel, AnalyticsViewModel_HiltModules.KeyModule.provide()).put(LazyClassKeyProvider.com_carbonwise_app_viewmodel_ChallengesViewModel, ChallengesViewModel_HiltModules.KeyModule.provide()).put(LazyClassKeyProvider.com_carbonwise_app_viewmodel_HomeViewModel, HomeViewModel_HiltModules.KeyModule.provide()).put(LazyClassKeyProvider.com_carbonwise_app_viewmodel_ProfileViewModel, ProfileViewModel_HiltModules.KeyModule.provide()).put(LazyClassKeyProvider.com_carbonwise_app_viewmodel_TrackViewModel, TrackViewModel_HiltModules.KeyModule.provide()).build());
    }

    @Override
    public ViewModelComponentBuilder getViewModelComponentBuilder() {
      return new ViewModelCBuilder(singletonCImpl, activityRetainedCImpl);
    }

    @Override
    public FragmentComponentBuilder fragmentComponentBuilder() {
      return new FragmentCBuilder(singletonCImpl, activityRetainedCImpl, activityCImpl);
    }

    @Override
    public ViewComponentBuilder viewComponentBuilder() {
      return new ViewCBuilder(singletonCImpl, activityRetainedCImpl, activityCImpl);
    }

    @CanIgnoreReturnValue
    private MainActivity injectMainActivity2(MainActivity instance) {
      MainActivity_MembersInjector.injectPreferenceManager(instance, singletonCImpl.preferenceManagerProvider.get());
      return instance;
    }

    @CanIgnoreReturnValue
    private OnboardingActivity injectOnboardingActivity2(OnboardingActivity instance) {
      OnboardingActivity_MembersInjector.injectPreferenceManager(instance, singletonCImpl.preferenceManagerProvider.get());
      return instance;
    }

    @IdentifierNameString
    private static final class LazyClassKeyProvider {
      static String com_carbonwise_app_viewmodel_AnalyticsViewModel = "com.carbonwise.app.viewmodel.AnalyticsViewModel";

      static String com_carbonwise_app_viewmodel_ChallengesViewModel = "com.carbonwise.app.viewmodel.ChallengesViewModel";

      static String com_carbonwise_app_viewmodel_ProfileViewModel = "com.carbonwise.app.viewmodel.ProfileViewModel";

      static String com_carbonwise_app_viewmodel_AiChatViewModel = "com.carbonwise.app.viewmodel.AiChatViewModel";

      static String com_carbonwise_app_viewmodel_TrackViewModel = "com.carbonwise.app.viewmodel.TrackViewModel";

      static String com_carbonwise_app_viewmodel_HomeViewModel = "com.carbonwise.app.viewmodel.HomeViewModel";

      @KeepFieldType
      AnalyticsViewModel com_carbonwise_app_viewmodel_AnalyticsViewModel2;

      @KeepFieldType
      ChallengesViewModel com_carbonwise_app_viewmodel_ChallengesViewModel2;

      @KeepFieldType
      ProfileViewModel com_carbonwise_app_viewmodel_ProfileViewModel2;

      @KeepFieldType
      AiChatViewModel com_carbonwise_app_viewmodel_AiChatViewModel2;

      @KeepFieldType
      TrackViewModel com_carbonwise_app_viewmodel_TrackViewModel2;

      @KeepFieldType
      HomeViewModel com_carbonwise_app_viewmodel_HomeViewModel2;
    }
  }

  private static final class ViewModelCImpl extends CarbonWiseApplication_HiltComponents.ViewModelC {
    private final SingletonCImpl singletonCImpl;

    private final ActivityRetainedCImpl activityRetainedCImpl;

    private final ViewModelCImpl viewModelCImpl = this;

    private Provider<AiChatViewModel> aiChatViewModelProvider;

    private Provider<AnalyticsViewModel> analyticsViewModelProvider;

    private Provider<ChallengesViewModel> challengesViewModelProvider;

    private Provider<HomeViewModel> homeViewModelProvider;

    private Provider<ProfileViewModel> profileViewModelProvider;

    private Provider<TrackViewModel> trackViewModelProvider;

    private ViewModelCImpl(SingletonCImpl singletonCImpl,
        ActivityRetainedCImpl activityRetainedCImpl, SavedStateHandle savedStateHandleParam,
        ViewModelLifecycle viewModelLifecycleParam) {
      this.singletonCImpl = singletonCImpl;
      this.activityRetainedCImpl = activityRetainedCImpl;

      initialize(savedStateHandleParam, viewModelLifecycleParam);

    }

    @SuppressWarnings("unchecked")
    private void initialize(final SavedStateHandle savedStateHandleParam,
        final ViewModelLifecycle viewModelLifecycleParam) {
      this.aiChatViewModelProvider = new SwitchingProvider<>(singletonCImpl, activityRetainedCImpl, viewModelCImpl, 0);
      this.analyticsViewModelProvider = new SwitchingProvider<>(singletonCImpl, activityRetainedCImpl, viewModelCImpl, 1);
      this.challengesViewModelProvider = new SwitchingProvider<>(singletonCImpl, activityRetainedCImpl, viewModelCImpl, 2);
      this.homeViewModelProvider = new SwitchingProvider<>(singletonCImpl, activityRetainedCImpl, viewModelCImpl, 3);
      this.profileViewModelProvider = new SwitchingProvider<>(singletonCImpl, activityRetainedCImpl, viewModelCImpl, 4);
      this.trackViewModelProvider = new SwitchingProvider<>(singletonCImpl, activityRetainedCImpl, viewModelCImpl, 5);
    }

    @Override
    public Map<Class<?>, javax.inject.Provider<ViewModel>> getHiltViewModelMap() {
      return LazyClassKeyMap.<javax.inject.Provider<ViewModel>>of(ImmutableMap.<String, javax.inject.Provider<ViewModel>>builderWithExpectedSize(6).put(LazyClassKeyProvider.com_carbonwise_app_viewmodel_AiChatViewModel, ((Provider) aiChatViewModelProvider)).put(LazyClassKeyProvider.com_carbonwise_app_viewmodel_AnalyticsViewModel, ((Provider) analyticsViewModelProvider)).put(LazyClassKeyProvider.com_carbonwise_app_viewmodel_ChallengesViewModel, ((Provider) challengesViewModelProvider)).put(LazyClassKeyProvider.com_carbonwise_app_viewmodel_HomeViewModel, ((Provider) homeViewModelProvider)).put(LazyClassKeyProvider.com_carbonwise_app_viewmodel_ProfileViewModel, ((Provider) profileViewModelProvider)).put(LazyClassKeyProvider.com_carbonwise_app_viewmodel_TrackViewModel, ((Provider) trackViewModelProvider)).build());
    }

    @Override
    public Map<Class<?>, Object> getHiltViewModelAssistedMap() {
      return ImmutableMap.<Class<?>, Object>of();
    }

    @IdentifierNameString
    private static final class LazyClassKeyProvider {
      static String com_carbonwise_app_viewmodel_AiChatViewModel = "com.carbonwise.app.viewmodel.AiChatViewModel";

      static String com_carbonwise_app_viewmodel_HomeViewModel = "com.carbonwise.app.viewmodel.HomeViewModel";

      static String com_carbonwise_app_viewmodel_ChallengesViewModel = "com.carbonwise.app.viewmodel.ChallengesViewModel";

      static String com_carbonwise_app_viewmodel_AnalyticsViewModel = "com.carbonwise.app.viewmodel.AnalyticsViewModel";

      static String com_carbonwise_app_viewmodel_ProfileViewModel = "com.carbonwise.app.viewmodel.ProfileViewModel";

      static String com_carbonwise_app_viewmodel_TrackViewModel = "com.carbonwise.app.viewmodel.TrackViewModel";

      @KeepFieldType
      AiChatViewModel com_carbonwise_app_viewmodel_AiChatViewModel2;

      @KeepFieldType
      HomeViewModel com_carbonwise_app_viewmodel_HomeViewModel2;

      @KeepFieldType
      ChallengesViewModel com_carbonwise_app_viewmodel_ChallengesViewModel2;

      @KeepFieldType
      AnalyticsViewModel com_carbonwise_app_viewmodel_AnalyticsViewModel2;

      @KeepFieldType
      ProfileViewModel com_carbonwise_app_viewmodel_ProfileViewModel2;

      @KeepFieldType
      TrackViewModel com_carbonwise_app_viewmodel_TrackViewModel2;
    }

    private static final class SwitchingProvider<T> implements Provider<T> {
      private final SingletonCImpl singletonCImpl;

      private final ActivityRetainedCImpl activityRetainedCImpl;

      private final ViewModelCImpl viewModelCImpl;

      private final int id;

      SwitchingProvider(SingletonCImpl singletonCImpl, ActivityRetainedCImpl activityRetainedCImpl,
          ViewModelCImpl viewModelCImpl, int id) {
        this.singletonCImpl = singletonCImpl;
        this.activityRetainedCImpl = activityRetainedCImpl;
        this.viewModelCImpl = viewModelCImpl;
        this.id = id;
      }

      @SuppressWarnings("unchecked")
      @Override
      public T get() {
        switch (id) {
          case 0: // com.carbonwise.app.viewmodel.AiChatViewModel 
          return (T) new AiChatViewModel(singletonCImpl.provideAiChatApiServiceProvider.get(), singletonCImpl.carbonRepositoryProvider.get(), singletonCImpl.userRepositoryProvider.get());

          case 1: // com.carbonwise.app.viewmodel.AnalyticsViewModel 
          return (T) new AnalyticsViewModel(singletonCImpl.carbonRepositoryProvider.get());

          case 2: // com.carbonwise.app.viewmodel.ChallengesViewModel 
          return (T) new ChallengesViewModel(singletonCImpl.challengeRepositoryProvider.get(), singletonCImpl.achievementRepositoryProvider.get(), singletonCImpl.userRepositoryProvider.get());

          case 3: // com.carbonwise.app.viewmodel.HomeViewModel 
          return (T) new HomeViewModel(singletonCImpl.carbonRepositoryProvider.get(), singletonCImpl.userRepositoryProvider.get(), singletonCImpl.challengeRepositoryProvider.get());

          case 4: // com.carbonwise.app.viewmodel.ProfileViewModel 
          return (T) new ProfileViewModel(singletonCImpl.userRepositoryProvider.get(), singletonCImpl.goalRepositoryProvider.get());

          case 5: // com.carbonwise.app.viewmodel.TrackViewModel 
          return (T) new TrackViewModel(singletonCImpl.carbonRepositoryProvider.get(), singletonCImpl.userRepositoryProvider.get());

          default: throw new AssertionError(id);
        }
      }
    }
  }

  private static final class ActivityRetainedCImpl extends CarbonWiseApplication_HiltComponents.ActivityRetainedC {
    private final SingletonCImpl singletonCImpl;

    private final ActivityRetainedCImpl activityRetainedCImpl = this;

    private Provider<ActivityRetainedLifecycle> provideActivityRetainedLifecycleProvider;

    private ActivityRetainedCImpl(SingletonCImpl singletonCImpl,
        SavedStateHandleHolder savedStateHandleHolderParam) {
      this.singletonCImpl = singletonCImpl;

      initialize(savedStateHandleHolderParam);

    }

    @SuppressWarnings("unchecked")
    private void initialize(final SavedStateHandleHolder savedStateHandleHolderParam) {
      this.provideActivityRetainedLifecycleProvider = DoubleCheck.provider(new SwitchingProvider<ActivityRetainedLifecycle>(singletonCImpl, activityRetainedCImpl, 0));
    }

    @Override
    public ActivityComponentBuilder activityComponentBuilder() {
      return new ActivityCBuilder(singletonCImpl, activityRetainedCImpl);
    }

    @Override
    public ActivityRetainedLifecycle getActivityRetainedLifecycle() {
      return provideActivityRetainedLifecycleProvider.get();
    }

    private static final class SwitchingProvider<T> implements Provider<T> {
      private final SingletonCImpl singletonCImpl;

      private final ActivityRetainedCImpl activityRetainedCImpl;

      private final int id;

      SwitchingProvider(SingletonCImpl singletonCImpl, ActivityRetainedCImpl activityRetainedCImpl,
          int id) {
        this.singletonCImpl = singletonCImpl;
        this.activityRetainedCImpl = activityRetainedCImpl;
        this.id = id;
      }

      @SuppressWarnings("unchecked")
      @Override
      public T get() {
        switch (id) {
          case 0: // dagger.hilt.android.ActivityRetainedLifecycle 
          return (T) ActivityRetainedComponentManager_LifecycleModule_ProvideActivityRetainedLifecycleFactory.provideActivityRetainedLifecycle();

          default: throw new AssertionError(id);
        }
      }
    }
  }

  private static final class ServiceCImpl extends CarbonWiseApplication_HiltComponents.ServiceC {
    private final SingletonCImpl singletonCImpl;

    private final ServiceCImpl serviceCImpl = this;

    private ServiceCImpl(SingletonCImpl singletonCImpl, Service serviceParam) {
      this.singletonCImpl = singletonCImpl;


    }
  }

  private static final class SingletonCImpl extends CarbonWiseApplication_HiltComponents.SingletonC {
    private final DatabaseModule databaseModule;

    private final ApplicationContextModule applicationContextModule;

    private final NetworkModule networkModule;

    private final SingletonCImpl singletonCImpl = this;

    private Provider<CarbonWiseDatabase> provideDatabaseProvider;

    private Provider<ChallengeRepository> challengeRepositoryProvider;

    private Provider<AchievementRepository> achievementRepositoryProvider;

    private Provider<UserRepository> userRepositoryProvider;

    private Provider<PreferenceManager> preferenceManagerProvider;

    private Provider<OkHttpClient> provideOkHttpClientProvider;

    private Provider<Retrofit> provideRetrofitProvider;

    private Provider<AiChatApiService> provideAiChatApiServiceProvider;

    private Provider<CarbonRepository> carbonRepositoryProvider;

    private Provider<GoalRepository> goalRepositoryProvider;

    private SingletonCImpl(ApplicationContextModule applicationContextModuleParam,
        DatabaseModule databaseModuleParam, NetworkModule networkModuleParam) {
      this.databaseModule = databaseModuleParam;
      this.applicationContextModule = applicationContextModuleParam;
      this.networkModule = networkModuleParam;
      initialize(applicationContextModuleParam, databaseModuleParam, networkModuleParam);

    }

    private HiltWorkerFactory hiltWorkerFactory() {
      return WorkerFactoryModule_ProvideFactoryFactory.provideFactory(ImmutableMap.<String, javax.inject.Provider<WorkerAssistedFactory<? extends ListenableWorker>>>of());
    }

    private ChallengeDao challengeDao() {
      return DatabaseModule_ProvideChallengeDaoFactory.provideChallengeDao(databaseModule, provideDatabaseProvider.get());
    }

    private AchievementDao achievementDao() {
      return DatabaseModule_ProvideAchievementDaoFactory.provideAchievementDao(databaseModule, provideDatabaseProvider.get());
    }

    private UserProfileDao userProfileDao() {
      return DatabaseModule_ProvideUserProfileDaoFactory.provideUserProfileDao(databaseModule, provideDatabaseProvider.get());
    }

    private CarbonEntryDao carbonEntryDao() {
      return DatabaseModule_ProvideCarbonEntryDaoFactory.provideCarbonEntryDao(databaseModule, provideDatabaseProvider.get());
    }

    private CarbonGoalDao carbonGoalDao() {
      return DatabaseModule_ProvideCarbonGoalDaoFactory.provideCarbonGoalDao(databaseModule, provideDatabaseProvider.get());
    }

    @SuppressWarnings("unchecked")
    private void initialize(final ApplicationContextModule applicationContextModuleParam,
        final DatabaseModule databaseModuleParam, final NetworkModule networkModuleParam) {
      this.provideDatabaseProvider = DoubleCheck.provider(new SwitchingProvider<CarbonWiseDatabase>(singletonCImpl, 1));
      this.challengeRepositoryProvider = DoubleCheck.provider(new SwitchingProvider<ChallengeRepository>(singletonCImpl, 0));
      this.achievementRepositoryProvider = DoubleCheck.provider(new SwitchingProvider<AchievementRepository>(singletonCImpl, 2));
      this.userRepositoryProvider = DoubleCheck.provider(new SwitchingProvider<UserRepository>(singletonCImpl, 3));
      this.preferenceManagerProvider = DoubleCheck.provider(new SwitchingProvider<PreferenceManager>(singletonCImpl, 4));
      this.provideOkHttpClientProvider = DoubleCheck.provider(new SwitchingProvider<OkHttpClient>(singletonCImpl, 7));
      this.provideRetrofitProvider = DoubleCheck.provider(new SwitchingProvider<Retrofit>(singletonCImpl, 6));
      this.provideAiChatApiServiceProvider = DoubleCheck.provider(new SwitchingProvider<AiChatApiService>(singletonCImpl, 5));
      this.carbonRepositoryProvider = DoubleCheck.provider(new SwitchingProvider<CarbonRepository>(singletonCImpl, 8));
      this.goalRepositoryProvider = DoubleCheck.provider(new SwitchingProvider<GoalRepository>(singletonCImpl, 9));
    }

    @Override
    public void injectCarbonWiseApplication(CarbonWiseApplication carbonWiseApplication) {
      injectCarbonWiseApplication2(carbonWiseApplication);
    }

    @Override
    public Set<Boolean> getDisableFragmentGetContextFix() {
      return ImmutableSet.<Boolean>of();
    }

    @Override
    public ActivityRetainedComponentBuilder retainedComponentBuilder() {
      return new ActivityRetainedCBuilder(singletonCImpl);
    }

    @Override
    public ServiceComponentBuilder serviceComponentBuilder() {
      return new ServiceCBuilder(singletonCImpl);
    }

    @CanIgnoreReturnValue
    private CarbonWiseApplication injectCarbonWiseApplication2(CarbonWiseApplication instance) {
      CarbonWiseApplication_MembersInjector.injectWorkerFactory(instance, hiltWorkerFactory());
      CarbonWiseApplication_MembersInjector.injectChallengeRepository(instance, challengeRepositoryProvider.get());
      CarbonWiseApplication_MembersInjector.injectAchievementRepository(instance, achievementRepositoryProvider.get());
      CarbonWiseApplication_MembersInjector.injectUserRepository(instance, userRepositoryProvider.get());
      return instance;
    }

    private static final class SwitchingProvider<T> implements Provider<T> {
      private final SingletonCImpl singletonCImpl;

      private final int id;

      SwitchingProvider(SingletonCImpl singletonCImpl, int id) {
        this.singletonCImpl = singletonCImpl;
        this.id = id;
      }

      @SuppressWarnings("unchecked")
      @Override
      public T get() {
        switch (id) {
          case 0: // com.carbonwise.app.data.repository.ChallengeRepository 
          return (T) new ChallengeRepository(singletonCImpl.challengeDao());

          case 1: // com.carbonwise.app.data.local.CarbonWiseDatabase 
          return (T) DatabaseModule_ProvideDatabaseFactory.provideDatabase(singletonCImpl.databaseModule, ApplicationContextModule_ProvideContextFactory.provideContext(singletonCImpl.applicationContextModule));

          case 2: // com.carbonwise.app.data.repository.AchievementRepository 
          return (T) new AchievementRepository(singletonCImpl.achievementDao());

          case 3: // com.carbonwise.app.data.repository.UserRepository 
          return (T) new UserRepository(singletonCImpl.userProfileDao(), singletonCImpl.carbonEntryDao(), singletonCImpl.challengeDao());

          case 4: // com.carbonwise.app.util.PreferenceManager 
          return (T) new PreferenceManager(ApplicationContextModule_ProvideContextFactory.provideContext(singletonCImpl.applicationContextModule));

          case 5: // com.carbonwise.app.data.remote.AiChatApiService 
          return (T) NetworkModule_ProvideAiChatApiServiceFactory.provideAiChatApiService(singletonCImpl.networkModule, singletonCImpl.provideRetrofitProvider.get());

          case 6: // retrofit2.Retrofit 
          return (T) NetworkModule_ProvideRetrofitFactory.provideRetrofit(singletonCImpl.networkModule, singletonCImpl.provideOkHttpClientProvider.get());

          case 7: // okhttp3.OkHttpClient 
          return (T) NetworkModule_ProvideOkHttpClientFactory.provideOkHttpClient(singletonCImpl.networkModule);

          case 8: // com.carbonwise.app.data.repository.CarbonRepository 
          return (T) new CarbonRepository(singletonCImpl.carbonEntryDao());

          case 9: // com.carbonwise.app.data.repository.GoalRepository 
          return (T) new GoalRepository(singletonCImpl.carbonGoalDao());

          default: throw new AssertionError(id);
        }
      }
    }
  }
}
