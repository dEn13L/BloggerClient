package com.github.den13l.bloggerclient.ui.post;

import javax.inject.Inject;
import com.github.den13l.bloggerclient.db.DbManager;
import com.github.den13l.bloggerclient.di.PerActivity;
import com.github.den13l.bloggerclient.model.Post;
import rx.Observable;

@PerActivity
class PostInteractor implements PostMvp.Interactor<Post> {

  private DbManager dbManager;

  @Inject PostInteractor(DbManager dbManager) {
    this.dbManager = dbManager;
  }

  @Override public Observable<Post> loadPost(String postId) {
    return dbManager.getPost(postId);
  }
}
