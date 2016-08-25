package com.github.den13l.bloggerclient.model.db;

import android.annotation.SuppressLint;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;
import com.github.den13l.bloggerclient.model.Author;

@DatabaseTable(tableName = "authors") public class DbAuthor {

  @DatabaseField(id = true, useGetSet = true) private String authorId;
  @DatabaseField private String id;
  @DatabaseField private String displayName;
  @DatabaseField private String url;
  @DatabaseField private String imageUrl;
  @DatabaseField(foreign = true) private DbPost post;
  @DatabaseField(foreign = true) private DbComment comment;

  public DbAuthor() {
    // OrmLite required constructor
  }

  public DbAuthor(Author author, DbPost post) {
    this.id = author.getId();
    this.displayName = author.getDisplayName();
    this.url = author.getUrl();
    this.imageUrl = author.getImageUrl();
    this.post = post;
    this.authorId = id + "_" + post.getId();
  }

  public DbAuthor(Author author, DbComment comment) {
    this.id = author.getId();
    this.displayName = author.getDisplayName();
    this.url = author.getUrl();
    this.imageUrl = author.getImageUrl();
    this.comment = comment;
    this.authorId = id + "_" + comment.getId();
  }

  @Override public int hashCode() {
    return authorId.hashCode();
  }

  @Override public boolean equals(Object obj) {
    if (this == obj) return true;
    if (obj == null || getClass() != obj.getClass()) return false;

    DbAuthor dbAuthor = (DbAuthor) obj;

    return authorId.equals(dbAuthor.authorId);
  }

  @SuppressLint("DefaultLocale") @Override public String toString() {
    return String.format("DbAuthor {id='%s', displayName='%s', url='%s', imageUrl='%s'}", id,
        displayName, url, imageUrl);
  }

  public String getAuthorId() {
    return authorId;
  }

  public void setAuthorId(String authorId) {
    this.authorId = authorId;
  }

  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public String getDisplayName() {
    return displayName;
  }

  public void setDisplayName(String displayName) {
    this.displayName = displayName;
  }

  public String getUrl() {
    return url;
  }

  public void setUrl(String url) {
    this.url = url;
  }

  public String getImageUrl() {
    return imageUrl;
  }

  public void setImageUrl(String imageUrl) {
    this.imageUrl = imageUrl;
  }

  public DbPost getPost() {
    return post;
  }

  public void setPost(DbPost post) {
    this.post = post;
  }

  public DbComment getComment() {
    return comment;
  }

  public void setComment(DbComment comment) {
    this.comment = comment;
  }
}