package com.github.den13l.bloggerclient;

import android.app.Application;
import android.content.Context;
import com.facebook.stetho.Stetho;
import com.github.den13l.bloggerclient.di.components.AppComponent;
import com.github.den13l.bloggerclient.di.components.DaggerAppComponent;
import com.github.den13l.bloggerclient.di.components.FragmentComponent;
import com.github.den13l.bloggerclient.di.components.MainComponent;
import com.github.den13l.bloggerclient.di.components.PostComponent;
import com.github.den13l.bloggerclient.di.modules.AppModule;
import com.github.den13l.bloggerclient.di.modules.DbModule;
import com.github.den13l.bloggerclient.di.modules.RetrofitModule;
import timber.log.Timber;

public class BloggerApplication extends Application {

  private AppComponent appComponent;
  private MainComponent mainComponent;
  private PostComponent postComponent;
  private FragmentComponent fragmentComponent;

  public static BloggerApplication get(Context context) {
    return (BloggerApplication) context.getApplicationContext();
  }

  @Override public void onCreate() {
    super.onCreate();

    // Инициалуем Timber
    Timber.plant(new Timber.DebugTree());

    // Инициализуем Stecho
    Stetho.initializeWithDefaults(this);

    appComponent = DaggerAppComponent.builder()
        .appModule(new AppModule(this))
        .dbModule(new DbModule())
        .retrofitModule(new RetrofitModule())
        .build();
  }

  public AppComponent getAppComponent() {
    return appComponent;
  }

  public MainComponent getMainComponent() {
    if (mainComponent == null) {
      mainComponent = appComponent.plusMainComponent();
    }
    return mainComponent;
  }

  public void clearMainComponent() {
    mainComponent = null;
  }

  public PostComponent getPostComponent() {
    if (postComponent == null) {
      postComponent = appComponent.plusPostComponent();
    }
    return postComponent;
  }

  public void clearPostComponent() {
    postComponent = null;
  }

  public FragmentComponent getFragmentComponent() {
    if (fragmentComponent == null) {
      fragmentComponent = mainComponent.plusFragmentComponent();
    }
    return fragmentComponent;
  }

  public void clearFragmentComponent() {
    fragmentComponent = null;
  }
}
