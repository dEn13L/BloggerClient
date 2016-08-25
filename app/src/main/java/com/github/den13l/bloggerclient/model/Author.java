package com.github.den13l.bloggerclient.model;

import android.annotation.SuppressLint;

public class Author {

  private String id;
  private String displayName;
  private String url;
  private String imageUrl;

  @Override public int hashCode() {
    return id.hashCode();
  }

  @Override public boolean equals(Object obj) {
    if (this == obj) return true;
    if (obj == null || getClass() != obj.getClass()) return false;

    Author author = (Author) obj;

    return id.equals(author.id);
  }

  @SuppressLint("DefaultLocale") @Override public String toString() {
    return String.format("Author {id='%s', displayName='%s', url='%s', imageUrl='%s'}", id,
        displayName, url, imageUrl);
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
}