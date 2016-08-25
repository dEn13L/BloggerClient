package com.github.den13l.bloggerclient.di.modules;

import android.app.Application;
import android.content.Context;
import dagger.Module;
import dagger.Provides;
import com.github.den13l.bloggerclient.di.PerApplication;

@Module public class AppModule {

  private Context context;

  public AppModule(Application application) {
    this.context = application.getApplicationContext();
  }

  @Provides @PerApplication public Context provideApplicationContext() {
    return context;
  }
}
