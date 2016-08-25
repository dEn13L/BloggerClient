package com.github.den13l.bloggerclient.model;

import android.annotation.SuppressLint;

public class Comment {

  private String status;
  private String id;
  private String inReplyToId;
  private String postId;
  private String blogId;
  private String published;
  private String updated;
  private String content;
  private Author author;

  @Override public int hashCode() {
    return id.hashCode();
  }

  @Override public boolean equals(Object obj) {
    if (this == obj) return true;
    if (obj == null || getClass() != obj.getClass()) return false;

    Comment comment = (Comment) obj;

    return id.equals(comment.id);
  }

  @SuppressLint("DefaultLocale") @Override public String toString() {
    return String.format(
        "Comment {status='%s', id='%s', inReplyToId='%s', postId='%s', blogId='%s', published='%s', updated='%s', content='%s', author='%s'}",
        status, id, inReplyToId, postId, blogId, published, updated, content, author);
  }

  public String getStatus() {
    return status;
  }

  public void setStatus(String status) {
    this.status = status;
  }

  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public String getInReplyToId() {
    return inReplyToId;
  }

  public void setInReplyToId(String inReplyToId) {
    this.inReplyToId = inReplyToId;
  }

  public String getPostId() {
    return postId;
  }

  public void setPostId(String postId) {
    this.postId = postId;
  }

  public String getBlogId() {
    return blogId;
  }

  public void setBlogId(String blogId) {
    this.blogId = blogId;
  }

  public String getPublished() {
    return published;
  }

  public void setPublished(String published) {
    this.published = published;
  }

  public String getUpdated() {
    return updated;
  }

  public void setUpdated(String updated) {
    this.updated = updated;
  }

  public String getContent() {
    return content;
  }

  public void setContent(String content) {
    this.content = content;
  }

  public Author getAuthor() {
    return author;
  }

  public void setAuthor(Author author) {
    this.author = author;
  }
}