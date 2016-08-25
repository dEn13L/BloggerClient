package com.github.den13l.bloggerclient.model.api;

import android.annotation.SuppressLint;
import com.google.gson.annotations.SerializedName;
import java.util.List;

/**
 * https://developers.google.com/blogger/docs/3.0/reference/posts
 *
 * @see ApiPostList
 */
public class ApiPost {

  @SerializedName("id") public String id;
  @SerializedName("blog") public Blog blog;
  @SerializedName("published") public String published;
  @SerializedName("updated") public String updated;
  @SerializedName("url") public String url;
  @SerializedName("title") public String title;
  @SerializedName("titleLink") public String titleLink;
  @SerializedName("content") public String content;
  @SerializedName("images") public List<ApiImage> images;
  @SerializedName("customMetaData") public String customMetaData;
  @SerializedName("author") public ApiAuthor author;
  @SerializedName("replies") public Replies replies;
  @SerializedName("labels") public List<String> labels;
  @SerializedName("location") public Location location;
  @SerializedName("status") public String status;

  @Override public int hashCode() {
    return id.hashCode();
  }

  @Override public boolean equals(Object obj) {
    if (this == obj) return true;
    if (obj == null || getClass() != obj.getClass()) return false;

    ApiPost apiPost = (ApiPost) obj;

    return id.equals(apiPost.id);
  }

  @SuppressLint("DefaultLocale") @Override public String toString() {
    return String.format(
        "ApiPost {id='%s', blog='%s', published='%s', updated='%s', url='%s', title='%s', titleLink='%s', images='%s', customMetaData='%s', author='%s', replies='%s', labels='%s', location='%s', status='%s'}",
        id, blog, published, updated, url, title, titleLink, images, customMetaData,
        author, replies, labels, location, status);
  }

  /**
   * @see ApiPost
   */
  public static class Blog {

    @SerializedName("id") public String id;

    @SuppressLint("DefaultLocale") @Override public String toString() {
      return String.format("ApiPost.Blog {id='%s'}", id);
    }
  }

  /**
   * @see ApiPost
   */
  public static class Replies {

    @SerializedName("totalItems") public long totalItems;
    @SerializedName("items") public List<ApiComment> items;

    @SuppressLint("DefaultLocale") @Override public String toString() {
      return String.format("ApiPost.Replies {totalItems='%s', comments count='%s'}", totalItems,
          items != null ? items.size() : 0);
    }
  }

  /**
   * @see ApiPost
   */
  public static class Location {

    @SerializedName("name") public String name;
    @SerializedName("lat") public double latitude;
    @SerializedName("lng") public double longitude;
    @SerializedName("span") public String span;

    @SuppressLint("DefaultLocale") @Override public String toString() {
      return String.format("ApiPost.Location {name='%s', latitude='%s', longitude='%s', span='%s'}",
          name, latitude, longitude, span);
    }
  }
}