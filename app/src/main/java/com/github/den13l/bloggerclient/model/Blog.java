package com.github.den13l.bloggerclient.model;

import android.annotation.SuppressLint;
import com.github.den13l.bloggerclient.model.api.ApiBlog;
import com.github.den13l.bloggerclient.model.db.DbBlog;

public class Blog {

  private String id;
  private String name;
  private String description;
  private String published;
  private String updated;
  private String url;
  private int postsCount;
  private int pagesCount;
  private String language;
  private String country;
  private String variant;
  private String nextPageToken;

  public Blog() {
  }

  public Blog(ApiBlog apiBlog) {
    this.id = apiBlog.id;
    this.name = apiBlog.name;
    this.description = apiBlog.description;
    this.published = apiBlog.published;
    this.updated = apiBlog.updated;
    this.url = apiBlog.url;
    this.postsCount = apiBlog.posts.totalItems;
    this.pagesCount = apiBlog.pages.totalItems;
    this.language = apiBlog.locale.language;
    this.country = apiBlog.locale.country;
    this.variant = apiBlog.locale.variant;
  }

  public Blog(DbBlog dbBlog) {
    this.id = dbBlog.getId();
    this.name = dbBlog.getName();
    this.description = dbBlog.getDescription();
    this.published = dbBlog.getPublished();
    this.updated = dbBlog.getUpdated();
    this.url = dbBlog.getUrl();
    this.postsCount = dbBlog.getPostsCount();
    this.pagesCount = dbBlog.getPagesCount();
    this.language = dbBlog.getLanguage();
    this.country = dbBlog.getCountry();
    this.variant = dbBlog.getVariant();
    this.nextPageToken = dbBlog.getNextPageToken();
  }

  @Override public int hashCode() {
    return id.hashCode();
  }

  @Override public boolean equals(Object obj) {
    if (this == obj) return true;
    if (obj == null || getClass() != obj.getClass()) return false;

    Blog blog = (Blog) obj;

    return id.equals(blog.id);
  }

  @SuppressLint("DefaultLocale") @Override public String toString() {
    return String.format(
        "Blog {id='%s', name='%s', description='%s', published='%s', updated='%s', url='%s', postsCount='%d', pagesCount='%d', language='%s', country='%s', variant='%s', nextPageToken='%s'}",
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