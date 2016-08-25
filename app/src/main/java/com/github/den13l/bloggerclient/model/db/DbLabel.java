package com.github.den13l.bloggerclient.model.db;

import android.annotation.SuppressLint;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

@DatabaseTable(tableName = "labels") public class DbLabel {

  @DatabaseField(id = true, useGetSet = true) private String id;
  @DatabaseField private String label;
  @DatabaseField(foreign = true) private DbPost post;

  public DbLabel() {
    // OrmLite required constructor
  }

  public DbLabel(String label, DbPost post) {
    this.label = label;
    this.post = post;
    this.id = post.getId() + "_" + label;
  }

  @Override public int hashCode() {
    return id.hashCode();
  }

  @Override public boolean equals(Object obj) {
    if (this == obj) return true;
    if (obj == null || getClass() != obj.getClass()) return false;

    DbLabel dbLabel = (DbLabel) obj;

    return id.equals(dbLabel.id);
  }

  @SuppressLint("DefaultLocale") @Override public String toString() {
    return String.format("DbLabel {label='%s'}", label);
  }

  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public String getLabel() {
    return label;
  }

  public void setLabel(String label) {
    this.label = label;
  }

  public DbPost getPost() {
    return post;
  }

  public void setPost(DbPost post) {
    this.post = post;
  }
}