package com.github.den13l.bloggerclient.di.modules;

import dagger.Module;
import dagger.Provides;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import com.github.den13l.bloggerclient.api.BloggerApi;
import com.github.den13l.bloggerclient.di.PerApplication;
import rx.schedulers.Schedulers;

@Module public class RetrofitModule {

  @Provides @PerApplication public Retrofit provideRetrofit() {
    return new Retrofit.Builder().baseUrl("https://www.googleapis.com/blogger/v3/")
        .addConverterFactory(GsonConverterFactory.create())
        .addCallAdapterFactory(RxJavaCallAdapterFactory.createWithScheduler(Schedulers.io()))
        .build();
  }

  @Provides @PerApplication public BloggerApi provideBloggerApi(Retrofit retrofit) {
    return retrofit.create(BloggerApi.class);
  }
}
