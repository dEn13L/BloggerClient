package com.github.den13l.bloggerclient.model.converters;

import com.github.den13l.bloggerclient.model.Author;
import com.github.den13l.bloggerclient.model.api.ApiAuthor;
import com.github.den13l.bloggerclient.model.db.DbAuthor;

public class AuthorConverter {

  public static Author create(DbAuthor dbAuthor) {
    Author author = new Author();

    author.setId(dbAuthor.getId());
    author.setDisplayName(dbAuthor.getDisplayName());
    author.setUrl(dbAuthor.getUrl());
    author.setImageUrl(dbAuthor.getImageUrl());

    return author;
  }

  public static Author create(ApiAuthor apiAuthor) {
    Author author = new Author();

    author.setId(apiAuthor.id);
    author.setDisplayName(apiAuthor.displayName);
    author.setUrl(apiAuthor.url);
    if (apiAuthor.image != null) {
      author.setImageUrl(apiAuthor.image.url);
    }

    return author;
  }
}
