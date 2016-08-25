package com.github.den13l.bloggerclient.api;

import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;
import com.github.den13l.bloggerclient.model.api.ApiBlog;
import com.github.den13l.bloggerclient.model.api.ApiPost;
import com.github.den13l.bloggerclient.model.api.ApiPostList;
import rx.Observable;

/**
 * https://developers.google.com/blogger/docs/3.0/reference/
 */
public interface BloggerApi {

  @GET("blogs/byurl") Observable<ApiBlog> getBlog(@Query("url") String url, @Query("key") String key);

  @GET("blogs/{blogId}/posts") Observable<ApiPostList> getPostList(@Path("blogId") String blogId,
      @Query("key") String key);

  @GET("blogs/{blogId}/posts") Observable<ApiPostList> getPostList(@Path("blogId") String blogId,
      @Query("key") String key, @Query("pageToken") String pageToken);

  @GET("blogs/{blogId}/posts/{postId}") Observable<ApiPost> getPost(@Path("blogId") String blogId,
      @Path("postId") String postId, @Query("key") String key);
}