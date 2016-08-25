package com.github.den13l.bloggerclient.ui.post;

import com.github.den13l.bloggerclient.model.Post;
import com.github.den13l.bloggerclient.ui.base.presenter.MvpPresenter;
import com.github.den13l.bloggerclient.ui.base.view.lce.MvpLceView;
import rx.Observable;

public interface PostMvp {

  interface View<M> extends MvpLceView<M> {

    void sharePost(Post post);

    void openUrl(String url);
  }

  interface Presenter<M> extends MvpPresenter<View<M>> {

    void loadPost(String postId, boolean pullToRefresh);

    void sharePost();

    void openInBrowser();
  }

  interface Interactor<M> {

    Observable<M> loadPost(String postId);
  }
}
