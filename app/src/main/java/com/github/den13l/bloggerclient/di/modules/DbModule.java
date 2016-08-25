package com.github.den13l.bloggerclient.di.modules;

import android.content.Context;
import dagger.Module;
import dagger.Provides;
import com.github.den13l.bloggerclient.db.DbManager;
import com.github.den13l.bloggerclient.di.PerApplication;

@Module public class DbModule {

  @Provides @PerApplication public DbManager provideDbManager(Context context) {
    return new DbManager(context);
  }
}
