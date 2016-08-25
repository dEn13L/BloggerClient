package com.github.den13l.bloggerclient.di.components;

import dagger.Component;
import com.github.den13l.bloggerclient.di.PerApplication;
import com.github.den13l.bloggerclient.di.modules.AppModule;
import com.github.den13l.bloggerclient.di.modules.DbModule;
import com.github.den13l.bloggerclient.di.modules.RetrofitModule;

@Component(modules = { AppModule.class, DbModule.class, RetrofitModule.class }) @PerApplication
public interface AppComponent {

  MainComponent plusMainComponent();

  PostComponent plusPostComponent();
}
