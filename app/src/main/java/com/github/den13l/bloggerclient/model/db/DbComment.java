package com.github.den13l.bloggerclient.model.db;

import android.annotation.SuppressLint;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;
import com.github.den13l.bloggerclient.model.Comment;

@DatabaseTable(tableName = "comments") public class DbComment {

  @DatabaseField private String status;
  @DatabaseField(id = true) private String id;
  @DatabaseField private String inReplyToId;
  @DatabaseField private String postId;
  @DatabaseField private String blogId;
  @DatabaseField private String published;
  @DatabaseField private String updated;
  @DatabaseField private String content;
  @DatabaseField(foreign = true, foreignAutoRefresh = true) private DbAuthor author;
  @DatabaseField(foreign = true, foreignAutoRefresh = true) private DbPost post;

  public DbComment() {
    // OrmLite required constructor
  }

  public DbComment(DbPost post) {
    this.post = post;
  }

  public DbComment(Comment comment, DbPost post) {
    this.status = comment.getStatus();
    this.id = comment.getId();
    this.inReplyToId = comment.getInReplyToId();
    this.postId = comment.getPostId();
    this.blogId = comment.getBlogId();
    this.published = comment.getPublished();
    this.updated = comment.getUpdated();
    this.content = comment.getContent();
    this.author = new DbAuthor(comment.getAuthor(), this);
    this.post = post;
  }

  @Override public int hashCode() {
    return id.hashCode();
  }

  @Override public boolean equals(Object obj) {
    if (this == obj) return true;
    if (obj == null || getClass() != obj.getClass()) return false;

    DbComment dbComment = (DbComment) obj;

    return id.equals(dbComment.id);
  }

  @SuppressLint("DefaultLocale") @Override public String toString() {
    return String.format(
        "DbComment {status='%s', id='%s', inReplyToId='%s', postId='%s', blogId='%s', published='%s', updated='%s', content='%s', author='%s'}",
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

  public DbAuthor getAuthor() {
    return author;
  }

  public void setAuthor(DbAuthor author) {
    this.author = author;
  }
}