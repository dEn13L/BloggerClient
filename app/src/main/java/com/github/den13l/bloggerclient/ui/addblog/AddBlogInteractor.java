package com.github.den13l.bloggerclient.ui.addblog;

import javax.inject.Inject;
import com.github.den13l.bloggerclient.api.BloggerApi;
import com.github.den13l.bloggerclient.model.Blog;
import com.github.den13l.bloggerclient.model.converters.BlogConverter;
import rx.Observable;

class AddBlogInteractor implements AddBlogMvp.Interactor<Blog> {

  private BloggerApi bloggerApi;

  @Inject AddBlogInteractor(BloggerApi bloggerApi) {
    this.bloggerApi = bloggerApi;
  }

  @Override public Observable<Blog> addBlog(String blogUrl, String key) {
    return bloggerApi.getBlog(blogUrl, key).map(BlogConverter::createBlog);
  }
}