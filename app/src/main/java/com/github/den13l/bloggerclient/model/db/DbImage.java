package com.github.den13l.bloggerclient.model.db;

import android.annotation.SuppressLint;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

@DatabaseTable(tableName = "images") public class DbImage {

  @DatabaseField(id = true) private String url;
  @DatabaseField(foreign = true) private DbPost post;

  public DbImage() {
    // OrmLite required constructor
  }

  public DbImage(String url, DbPost post) {
    this.url = url;
    this.post = post;
  }

  @Override public int hashCode() {
    return url.hashCode();
  }

  @Override public boolean equals(Object obj) {
    if (this == obj) return true;
    if (obj == null || getClass() != obj.getClass()) return false;

    DbImage dbImage = (DbImage) obj;

    return url.equals(dbImage.url);
  }

  @SuppressLint("DefaultLocale") @Override public String toString() {
    return String.format("DbImage {url='%s'}", url);
  }

  public String getUrl() {
    return url;
  }

  public void setUrl(String url) {
    this.url = url;
  }

  public DbPost getPost() {
    return post;
  }

  public void setPost(DbPost post) {
    this.post = post;
  }
}