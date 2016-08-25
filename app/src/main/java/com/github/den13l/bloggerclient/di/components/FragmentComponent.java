package com.github.den13l.bloggerclient.di.components;

import dagger.Subcomponent;
import com.github.den13l.bloggerclient.di.PerFragment;
import com.github.den13l.bloggerclient.ui.addblog.AddBlogDialog;
import com.github.den13l.bloggerclient.ui.blog.BlogFragment;

@Subcomponent @PerFragment public interface FragmentComponent {

  void inject(BlogFragment fragment);

  void inject(AddBlogDialog dialog);
}
