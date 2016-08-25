package com.github.den13l.bloggerclient.model.converters;

import java.util.ArrayList;
import java.util.List;
import com.github.den13l.bloggerclient.model.Comment;
import com.github.den13l.bloggerclient.model.Post;
import com.github.den13l.bloggerclient.model.api.ApiComment;
import com.github.den13l.bloggerclient.model.api.ApiImage;
import com.github.den13l.bloggerclient.model.api.ApiPost;
import com.github.den13l.bloggerclient.model.db.DbComment;
import com.github.den13l.bloggerclient.model.db.DbImage;
import com.github.den13l.bloggerclient.model.db.DbLabel;
import com.github.den13l.bloggerclient.model.db.DbPost;

public class PostConverter {

  public static Post create(DbPost dbPost) {
    Post post = new Post();

    post.setId(dbPost.getId());
    post.setBlogId(dbPost.getBlogId());
    post.setPublished(dbPost.getPublished());
    post.setUpdated(dbPost.getUpdated());
    post.setUrl(dbPost.getUrl());
    post.setTitle(dbPost.getTitle());
    post.setTitleLink(dbPost.getTitleLink());
    post.setContent(dbPost.getContent());

    if (dbPost.getImages() != null) {
      List<String> images = new ArrayList<>();
      for (DbImage dbImage : dbPost.getImages()) {
        images.add(dbImage.getUrl());
      }
      post.setImages(images);
    }

    post.setCustomMetaData(dbPost.getCustomMetaData());
    post.setAuthor(AuthorConverter.create(dbPost.getAuthor()));
    post.setRepliesCount(dbPost.getRepliesCount());

    if (dbPost.getComments() != null) {
      List<Comment> comments = new ArrayList<>();
      for (DbComment dbComment : dbPost.getComments()) {
        Comment comment = CommentConverter.create(dbComment);
        comments.add(comment);
      }
      post.setComments(comments);
    }

    if (dbPost.getLabels() != null) {
      List<String> labels = new ArrayList<>();
      for (DbLabel dbLabel : dbPost.getLabels()) {
        labels.add(dbLabel.getLabel());
      }
      post.setLabels(labels);
    }

    post.setLocationName(dbPost.getLocationName());
    post.setLatitude(dbPost.getLatitude());
    post.setLongitude(dbPost.getLongitude());
    post.setLocationSpan(dbPost.getLocationSpan());
    post.setStatus(dbPost.getStatus());

    return post;
  }

  public static Post create(ApiPost apiPost) {
    Post post = new Post();

    post.setId(apiPost.id);
    if (apiPost.blog != null) {
      post.setBlogId(apiPost.blog.id);
    }
    post.setPublished(apiPost.published);
    post.setUpdated(apiPost.updated);
    post.setUrl(apiPost.url);
    post.setTitle(apiPost.title);
    post.setTitleLink(apiPost.titleLink);
    post.setContent(apiPost.content);

    if (apiPost.images != null) {
      List<String> images = new ArrayList<>();
      for (ApiImage image : apiPost.images) {
        images.add(image.url);
      }
      post.setImages(images);
    }

    post.setCustomMetaData(apiPost.customMetaData);
    post.setAuthor(AuthorConverter.create(apiPost.author));
    if (apiPost.replies != null) {
      post.setRepliesCount(apiPost.replies.totalItems);
      if (apiPost.replies.items != null) {
        List<Comment> comments = new ArrayList<>();
        for (ApiComment item : apiPost.replies.items) {
          comments.add(CommentConverter.create(item));
        }
        post.setComments(comments);
      }
    }

    post.setLabels(apiPost.labels);
    if (apiPost.location != null) {
      post.setLocationName(apiPost.location.name);
      post.setLatitude(apiPost.location.latitude);
      post.setLongitude(apiPost.location.longitude);
      post.setLocationSpan(apiPost.location.span);
    }
    post.setStatus(apiPost.status);

    return post;
  }
}
