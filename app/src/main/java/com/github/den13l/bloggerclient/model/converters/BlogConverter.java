package com.github.den13l.bloggerclient.model.converters;

import com.github.den13l.bloggerclient.model.Blog;
import com.github.den13l.bloggerclient.model.api.ApiBlog;
import com.github.den13l.bloggerclient.model.api.ApiPostList;
import com.github.den13l.bloggerclient.model.db.DbBlog;

public class BlogConverter {

  public static Blog createBlog(DbBlog dbBlog) {
    Blog blog = new Blog();

    blog.setId(dbBlog.getId());
    blog.setName(dbBlog.getName());
    blog.setDescription(dbBlog.getDescription());
    blog.setPublished(dbBlog.getPublished());
    blog.setUpdated(dbBlog.getUpdated());
    blog.setUrl(dbBlog.getUrl());
    blog.setPostsCount(dbBlog.getPostsCount());
    blog.setPagesCount(dbBlog.getPagesCount());
    blog.setLanguage(dbBlog.getLanguage());
    blog.setCountry(dbBlog.getCountry());
    blog.setVariant(dbBlog.getVariant());
    blog.setNextPageToken(dbBlog.getNextPageToken());

    return blog;
  }

  public static Blog createBlog(ApiBlog apiBlog, ApiPostList apiPostList) {
    Blog blog = createBlog(apiBlog);
    blog.setNextPageToken(apiPostList.nextPageToken);

    return blog;
  }

  public static Blog createBlog(ApiBlog apiBlog) {
    Blog blog = new Blog();

    blog.setId(apiBlog.id);
    blog.setName(apiBlog.name);
    blog.setDescription(apiBlog.description);
    blog.setPublished(apiBlog.published);
    blog.setUpdated(apiBlog.updated);
    blog.setUrl(apiBlog.url);
    if (apiBlog.posts != null) {
      blog.setPostsCount(apiBlog.posts.totalItems);
    }
    if (apiBlog.pages != null) {
      blog.setPagesCount(apiBlog.pages.totalItems);
    }
    if (apiBlog.locale != null) {
      blog.setLanguage(apiBlog.locale.language);
      blog.setCountry(apiBlog.locale.country);
      blog.setVariant(apiBlog.locale.variant);
    }

    return blog;
  }
}
