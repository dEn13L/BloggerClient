package com.github.den13l.bloggerclient.model.db;

import android.annotation.SuppressLint;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.field.ForeignCollectionField;
import com.j256.ormlite.table.DatabaseTable;
import java.util.Collection;
import com.github.den13l.bloggerclient.model.Post;

@DatabaseTable(tableName = "posts") public class DbPost {

  public static final String COLUMN_BLOG_ID = "blogId";
  public static final String COLUMN_IMAGES = "images";
  public static final String COLUMN_COMMENTS = "comments";
  public static final String COLUMN_LABEL = "labels";

  @DatabaseField(id = true, useGetSet = true) private String id;
  @DatabaseField(columnName = COLUMN_BLOG_ID) private String blogId;
  @DatabaseField private String published;
  @DatabaseField private String updated;
  @DatabaseField private String url;
  @DatabaseField private String title;
  @DatabaseField private String titleLink;
  @DatabaseField private String content;
  @ForeignCollectionField(eager = true, columnName = COLUMN_IMAGES) private Collection<DbImage>
      images;
  @DatabaseField private String customMetaData;
  @DatabaseField(foreign = true, foreignAutoRefresh = true) private DbAuthor author;
  @DatabaseField private long repliesCount;
  @ForeignCollectionField(eager = true, columnName = COLUMN_COMMENTS) private Collection<DbComment>
      comments;
  @ForeignCollectionField(eager = true, columnName = COLUMN_LABEL) private Collection<DbLabel>
      labels;
  @DatabaseField private String locationName;
  @DatabaseField private double latitude;
  @DatabaseField private double longitude;
  @DatabaseField private String locationSpan;
  @DatabaseField private String status;

  public DbPost() {
    // OrmLite required constructor
  }

  public DbPost(Post post) {
    this.id = post.getId();
    this.blogId = post.getBlogId();
    this.published = post.getPublished();
    this.updated = post.getUpdated();
    this.url = post.getUrl();
    this.title = post.getTitle();
    this.titleLink = post.getTitleLink();
    this.content = post.getContent();
    this.customMetaData = post.getCustomMetaData();
    this.repliesCount = post.getRepliesCount();
    this.locationName = post.getLocationName();
    this.latitude = post.getLatitude();
    this.longitude = post.getLongitude();
    this.locationSpan = post.getLocationSpan();
    this.status = post.getStatus();
  }

  @Override public int hashCode() {
    return id.hashCode();
  }

  @Override public boolean equals(Object obj) {
    if (this == obj) return true;
    if (obj == null || getClass() != obj.getClass()) return false;

    DbPost dbPost = (DbPost) obj;

    return id.equals(dbPost.id);
  }

  @SuppressLint("DefaultLocale") @Override public String toString() {
    return String.format(
        "DbPost {id='%s', blogId='%s', published='%s', updated='%s', url='%s', title='%s', titleLink='%s', images='%s', customMetaData='%s', author='%s', repliesCount='%d', comments='%s', labels='%s', locationName='%s', latitude='%s', longitude='%s', locationSpan='%s', status='%s'}",
        id, blogId, published, updated, url, title, titleLink, images, customMetaData, author,
        repliesCount, comments, labels, locationName, latitude, longitude, locationSpan, status);
  }

  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
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

  public String getUrl() {
    return url;
  }

  public void setUrl(String url) {
    this.url = url;
  }

  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public String getTitleLink() {
    return titleLink;
  }

  public void setTitleLink(String titleLink) {
    this.titleLink = titleLink;
  }

  public String getContent() {
    return content;
  }

  public void setContent(String content) {
    this.content = content;
  }

  public Collection<DbImage> getImages() {
    return images;
  }

  public void setImages(Collection<DbImage> images) {
    this.images = images;
  }

  public void addImages(Collection<DbImage> images) {
    this.images.addAll(images);
  }

  public void addImage(DbImage image) {
    this.images.add(image);
  }

  public String getCustomMetaData() {
    return customMetaData;
  }

  public void setCustomMetaData(String customMetaData) {
    this.customMetaData = customMetaData;
  }

  public DbAuthor getAuthor() {
    return author;
  }

  public void setAuthor(DbAuthor author) {
    this.author = author;
  }

  public long getRepliesCount() {
    return repliesCount;
  }

  public void setRepliesCount(long repliesCount) {
    this.repliesCount = repliesCount;
  }

  public Collection<DbComment> getComments() {
    return comments;
  }

  public void setComments(Collection<DbComment> comments) {
    this.comments = comments;
  }

  public void addComments(Collection<DbComment> comments) {
    this.comments.addAll(comments);
  }

  public void addComment(DbComment comment) {
    this.comments.add(comment);
  }

  public Collection<DbLabel> getLabels() {
    return labels;
  }

  public void setLabels(Collection<DbLabel> labels) {
    this.labels = labels;
  }

  public void addLabels(Collection<DbLabel> labels) {
    this.labels.addAll(labels);
  }

  public void addLabel(DbLabel label) {
    this.labels.add(label);
  }

  public String getLocationName() {
    return locationName;
  }

  public void setLocationName(String locationName) {
    this.locationName = locationName;
  }

  public double getLatitude() {
    return latitude;
  }

  public void setLatitude(double latitude) {
    this.latitude = latitude;
  }

  public double getLongitude() {
    return longitude;
  }

  public void setLongitude(double longitude) {
    this.longitude = longitude;
  }

  public String getLocationSpan() {
    return locationSpan;
  }

  public void setLocationSpan(String locationSpan) {
    this.locationSpan = locationSpan;
  }

  public String getStatus() {
    return status;
  }

  public void setStatus(String status) {
    this.status = status;
  }
}