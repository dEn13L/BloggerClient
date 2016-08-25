package com.github.den13l.bloggerclient.model.api;

import android.annotation.SuppressLint;
import com.google.gson.annotations.SerializedName;

/**
 * https://developers.google.com/blogger/docs/3.0/reference/blogs
 *
 * Example:
 *
   {
     "kind": "blogger#getBlog",
     "id": "6755709643044947179",
     "name": "Android Developers ApiBlog",
     "description": "",
     "published": "2007-10-22T13:26:05-07:00",
     "updated": "2016-08-15T14:03:38-07:00",
     "url": "http://android-developers.blogspot.com/",
     "selfLink": "https://www.googleapis.com/blogger/v3/blogs/6755709643044947179",
     "posts": {
       "totalItems": 628,
       "selfLink": "https://www.googleapis.com/blogger/v3/blogs/6755709643044947179/posts"
     },
     "pages": {
       "totalItems": 0,
       "selfLink": "https://www.googleapis.com/blogger/v3/blogs/6755709643044947179/pages"
     },
     "locale": {
       "language": "en",
       "country": "",
       "variant": ""
     }
   }

 */
public class ApiBlog {

  @SerializedName("id") public String id;
  @SerializedName("name") public String name;
  @SerializedName("description") public String description;
  @SerializedName("published") public String published;
  @SerializedName("updated") public String updated;
  @SerializedName("url") public String url;
  @SerializedName("posts") public Posts posts;
  @SerializedName("pages") public Pages pages;
  @SerializedName("locale") public Locale locale;

  @Override public int hashCode() {
    return id.hashCode();
  }

  @Override public boolean equals(Object obj) {
    if (this == obj) return true;
    if (obj == null || getClass() != obj.getClass()) return false;

    ApiBlog apiBlog = (ApiBlog) obj;

    return id.equals(apiBlog.id);
  }

  @SuppressLint("DefaultLocale") @Override public String toString() {
    return String.format(
        "ApiBlog {id='%s', name='%s', description='%s', published='%s', updated='%s', url='%s', posts='%s', pages='%s', locale='%s'}",
        id, name, description, published, updated, url, posts, pages, locale);
  }

  /**
   * @see ApiBlog
   */
  public static class Posts {

    @SerializedName("totalItems") public int totalItems;

    @SuppressLint("DefaultLocale") @Override public String toString() {
      return String.format("ApiBlog.Posts {totalItems='%d'}", totalItems);
    }
  }

  /**
   * @see ApiBlog
   */
  public static class Pages {

    @SerializedName("totalItems") public int totalItems;

    @SuppressLint("DefaultLocale") @Override public String toString() {
      return String.format("ApiBlog.Pages {totalItems='%d'}", totalItems);
    }
  }

  /**
   * @see ApiBlog
   */
  public static class Locale {

    @SerializedName("language") public String language;
    @SerializedName("country") public String country;
    @SerializedName("variant") public String variant;

    @SuppressLint("DefaultLocale") @Override public String toString() {
      return String.format("ApiBlog.Locale {language='%s', country='%s', variant='%s'}", language,
          country, variant);
    }
  }
}