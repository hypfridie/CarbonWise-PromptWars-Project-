package com.carbonwise.app.di;

import android.content.Context;
import com.carbonwise.app.data.local.CarbonWiseDatabase;
import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
import javax.annotation.processing.Generated;
import javax.inject.Provider;

@ScopeMetadata("javax.inject.Singleton")
@QualifierMetadata("dagger.hilt.android.qualifiers.ApplicationContext")
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
public final class DatabaseModule_ProvideDatabaseFactory implements Factory<CarbonWiseDatabase> {
  private final DatabaseModule module;

  private final Provider<Context> contextProvider;

  public DatabaseModule_ProvideDatabaseFactory(DatabaseModule module,
      Provider<Context> contextProvider) {
    this.module = module;
    this.contextProvider = contextProvider;
  }

  @Override
  public CarbonWiseDatabase get() {
    return provideDatabase(module, contextProvider.get());
  }

  public static DatabaseModule_ProvideDatabaseFactory create(DatabaseModule module,
      Provider<Context> contextProvider) {
    return new DatabaseModule_ProvideDatabaseFactory(module, contextProvider);
  }

  public static CarbonWiseDatabase provideDatabase(DatabaseModule instance, Context context) {
    return Preconditions.checkNotNullFromProvides(instance.provideDatabase(context));
  }
}
