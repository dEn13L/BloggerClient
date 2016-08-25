package com.github.den13l.bloggerclient.model;

import android.annotation.SuppressLint;
import java.util.List;

public class Post {

  private String id;
  private String blogId;
  private String published;
  private String updated;
  private String url;
  private String title;
  private String titleLink;
  private String content;
  private List<String> images;
  private String customMetaData;
  private Author author;
  private long repliesCount;
  private List<Comment> comments;
  private List<String> labels;
  private String locationName;
  private double latitude;
  private double longitude;
  private String locationSpan;
  private String status;

  @Override public int hashCode() {
    return id.hashCode();
  }

  @Override public boolean equals(Object obj) {
    if (this == obj) return true;
    if (obj == null || getClass() != obj.getClass()) return false;

    Post post = (Post) obj;

    return id.equals(post.id);
  }

  @SuppressLint("DefaultLocale") @Override public String toString() {
    return String.format(
        "Post {id='%s', blogId='%s', published='%s', updated='%s', url='%s', title='%s', titleLink='%s', images='%s', customMetaData='%s', author='%s', repliesCount='%d', comments='%s', labels='%s', locationName='%s', latitude='%s', longitude='%s', locationSpan='%s', status='%s'}",
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

  public List<String> getImages() {
    return images;
  }

  public void setImages(List<String> images) {
    this.images = images;
  }

  public String getCustomMetaData() {
    return customMetaData;
  }

  public void setCustomMetaData(String customMetaData) {
    this.customMetaData = customMetaData;
  }

  public Author getAuthor() {
    return author;
  }

  public void setAuthor(Author author) {
    this.author = author;
  }

  public long getRepliesCount() {
    return repliesCount;
  }

  public void setRepliesCount(long repliesCount) {
    this.repliesCount = repliesCount;
  }

  public List<Comment> getComments() {
    return comments;
  }

  public void setComments(List<Comment> comments) {
    this.comments = comments;
  }

  public List<String> getLabels() {
    return labels;
  }

  public void setLabels(List<String> labels) {
    this.labels = labels;
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