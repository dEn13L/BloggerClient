package com.github.den13l.bloggerclient.model.event;

import com.github.den13l.bloggerclient.model.Blog;

public class AddBlogEvent {

  private Blog blog;

  public AddBlogEvent(Blog blog) {
    this.blog = blog;
  }

  public Blog getBlog() {
    return blog;
  }
}
