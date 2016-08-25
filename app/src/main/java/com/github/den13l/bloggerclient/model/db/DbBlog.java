package com.github.den13l.bloggerclient.model.db;

import android.annotation.SuppressLint;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;
import com.github.den13l.bloggerclient.model.Blog;

@DatabaseTable(tableName = "blogs") public class DbBlog {

  @DatabaseField(id = true) private String id;
  @DatabaseField private String name;
  @DatabaseField private String description;
  @DatabaseField private String published;
  @DatabaseField private String updated;
  @DatabaseField private String url;
  @DatabaseField private int postsCount;
  @DatabaseField private int pagesCount;
  @DatabaseField private String language;
  @DatabaseField private String country;
  @DatabaseField private String variant;
  @DatabaseField private String nextPageToken;

  public DbBlog() {
    // OrmLite required constructor
  }

  public DbBlog(Blog blog) {
    this.id = blog.getId();
    this.name = blog.getName();
    this.description = blog.getDescription();
    this.published = blog.getPublished();
    this.updated = blog.getUpdated();
    this.url = blog.getUrl();
    this.postsCount = blog.getPostsCount();
    this.pagesCount = blog.getPagesCount();
    this.language = blog.getLanguage();
    this.country = blog.getCountry();
    this.variant = blog.getVariant();
    this.nextPageToken = blog.getNextPageToken();
  }

  @Override public int hashCode() {
    return id.hashCode();
  }

  @Override public boolean equals(Object obj) {
    if (this == obj) return true;
    if (obj == null || getClass() != obj.getClass()) return false;

    DbBlog dbBlog = (DbBlog) obj;

    return id.equals(dbBlog.id);
  }

  @SuppressLint("DefaultLocale") @Override public String toString() {
    return String.format(
        "DbBlog {id='%s', name='%s', description='%s', published='%s', updated='%s', url='%s', postsCount='%d', pagesCount='%d', language='%s', country='%s', variant='%s', nextPageToken='%s'}",
        id, name, description, published, updated, url, postsCount, pagesCount, language, country,
        variant, nextPageToken);
  }

  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
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

  public String getUrl() {
    return url;
  }

  public void setUrl(String url) {
    this.url = url;
  }

  public int getPostsCount() {
    return postsCount;
  }

  public void setPostsCount(int postsCount) {
    this.postsCount = postsCount;
  }

  public int getPagesCount() {
    return pagesCount;
  }

  public void setPagesCount(int pagesCount) {
    this.pagesCount = pagesCount;
  }

  public String getLanguage() {
    return language;
  }

  public void setLanguage(String language) {
    this.language = language;
  }

  public String getCountry() {
    return country;
  }

  public void setCountry(String country) {
    this.country = country;
  }

  public String getVariant() {
    return variant;
  }

  public void setVariant(String variant) {
    this.variant = variant;
  }

  public String getNextPageToken() {
    return nextPageToken;
  }

  public void setNextPageToken(String nextPageToken) {
    this.nextPageToken = nextPageToken;
  }
}