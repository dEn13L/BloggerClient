package com.github.den13l.bloggerclient.model.api;

import android.annotation.SuppressLint;
import com.google.gson.annotations.SerializedName;

/**
 * @see ApiPost
 */
public class ApiImage {

  @SerializedName("url") public String url;

  @Override public int hashCode() {
    return url.hashCode();
  }

  @Override public boolean equals(Object obj) {
    if (this == obj) return true;
    if (obj == null || getClass() != obj.getClass()) return false;

    ApiImage apiImage = (ApiImage) obj;

    return url.equals(apiImage.url);
  }

  @SuppressLint("DefaultLocale") @Override public String toString() {
    return String.format("ApiImage {url='%s'}", url);
  }
}