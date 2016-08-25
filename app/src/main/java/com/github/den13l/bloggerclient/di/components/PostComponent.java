package com.github.den13l.bloggerclient.di.components;

import dagger.Subcomponent;
import com.github.den13l.bloggerclient.di.PerActivity;
import com.github.den13l.bloggerclient.ui.post.PostActivity;

@Subcomponent @PerActivity public interface PostComponent {

  void inject(PostActivity activity);
}
