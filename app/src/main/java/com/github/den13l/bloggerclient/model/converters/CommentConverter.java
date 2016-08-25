package com.github.den13l.bloggerclient.model.converters;

import com.github.den13l.bloggerclient.model.Comment;
import com.github.den13l.bloggerclient.model.api.ApiComment;
import com.github.den13l.bloggerclient.model.db.DbComment;

public class CommentConverter {

  public static Comment create(DbComment dbComment) {
    Comment comment = new Comment();

    comment.setStatus(dbComment.getStatus());
    comment.setId(dbComment.getId());
    comment.setInReplyToId(dbComment.getInReplyToId());
    comment.setPostId(dbComment.getPostId());
    comment.setBlogId(dbComment.getBlogId());
    comment.setPublished(dbComment.getPublished());
    comment.setUpdated(dbComment.getUpdated());
    comment.setContent(dbComment.getContent());
    comment.setAuthor(AuthorConverter.create(dbComment.getAuthor()));

    return comment;
  }

  public static Comment create(ApiComment apiComment) {
    Comment comment = new Comment();

    comment.setStatus(apiComment.status);
    comment.setId(apiComment.id);
    if (apiComment.inReplyTo != null) {
      comment.setInReplyToId(apiComment.inReplyTo.id);
    }
    if (apiComment.post != null) {
      comment.setPostId(apiComment.post.id);
    }
    if (apiComment.blog != null) {
      comment.setBlogId(apiComment.blog.id);
    }
    comment.setPublished(apiComment.published);
    comment.setUpdated(apiComment.updated);
    comment.setContent(apiComment.content);
    comment.setAuthor(AuthorConverter.create(apiComment.author));

    return comment;
  }
}
