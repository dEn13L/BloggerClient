package com.github.den13l.bloggerclient.model.event;

public class RefreshBlogEvent {

  private String blogId;

  public RefreshBlogEvent(String blogId) {
    this.blogId = blogId;
  }

  public String getBlogId() {
    return blogId;
  }
}
