package com.github.den13l.bloggerclient.model.api;

import android.annotation.SuppressLint;
import com.google.gson.annotations.SerializedName;
import com.github.den13l.bloggerclient.model.db.DbLabel;

/**
 * @see ApiPost
 * @see ApiComment
 */
public class ApiAuthor {

  @SerializedName("id") public String id;
  @SerializedName("displayName") public String displayName;
  @SerializedName("url") public String url;
  @SerializedName("image") public ApiImage image;

  @Override public int hashCode() {
    return id.hashCode();
  }

  @Override public boolean equals(Object obj) {
    if (this == obj) return true;
    if (obj == null || getClass() != obj.getClass()) return false;

    ApiAuthor apiAuthor = (ApiAuthor) obj;

    return id.equals(apiAuthor.id);
  }

  @SuppressLint("DefaultLocale") @Override public String toString() {
    return String.format("ApiAuthor {id='%s', displayName='%s', url='%s', imageUrl='%s'}", id,
        displayName, url, image);
  }
}