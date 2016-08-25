package com.github.den13l.bloggerclient.model.api;

import android.annotation.SuppressLint;
import com.google.gson.annotations.SerializedName;

/**
 * https://developers.google.com/blogger/docs/3.0/reference/comments
 *
 * TODO: add Example
 *
 * Example:
 *



 */
public class ApiComment {

  @SerializedName("status") public String status;
  @SerializedName("id") public String id;
  @SerializedName("inReplyTo") public InReplyTo inReplyTo;
  @SerializedName("post") public Post post;
  @SerializedName("blog") public Blog blog;
  @SerializedName("published") public String published;
  @SerializedName("updated") public String updated;
  @SerializedName("content") public String content;
  @SerializedName("author") public ApiAuthor author;

  @Override public int hashCode() {
    return id.hashCode();
  }

  @Override public boolean equals(Object obj) {
    if (this == obj) return true;
    if (obj == null || getClass() != obj.getClass()) return false;

    ApiComment apiComment = (ApiComment) obj;

    return id.equals(apiComment.id);
  }

  @SuppressLint("DefaultLocale") @Override public String toString() {
    return String.format("ApiComment {status='%s', id='%s', inReplyTo='%s', post='%s', blog='%s', published='%s', updated='%s', content='%s', author='%s'}",
        status, id, inReplyTo, post, blog, published, updated, content, author);
  }

  /**
   * @see ApiComment
   */
  public static class InReplyTo {

    @SerializedName("id") public String id;

    @SuppressLint("DefaultLocale") @Override public String toString() {
      return String.format("ApiComment.InReplyTo {id='%s'}", id);
    }
  }

  /**
   * @see ApiComment
   */
  public static class Post {

    @SerializedName("id") public String id;

    @SuppressLint("DefaultLocale") @Override public String toString() {
      return String.format("ApiComment.ApiPost {id='%s'}", id);
    }
  }

  /**
   * @see ApiComment
   */
  public static class Blog {

    @SerializedName("id") public String id;

    @SuppressLint("DefaultLocale") @Override public String toString() {
      return String.format("ApiComment.ApiBlog {id='%s'}", id);
    }
  }
}