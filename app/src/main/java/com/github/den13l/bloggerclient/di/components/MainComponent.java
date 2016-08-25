package com.github.den13l.bloggerclient.di.components;

import dagger.Subcomponent;
import com.github.den13l.bloggerclient.di.PerActivity;
import com.github.den13l.bloggerclient.ui.main.MainActivity;

@Subcomponent @PerActivity public interface MainComponent {

  FragmentComponent plusFragmentComponent();

  void inject(MainActivity activity);
}
