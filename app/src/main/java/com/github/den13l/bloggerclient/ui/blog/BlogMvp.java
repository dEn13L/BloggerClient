package com.github.den13l.bloggerclient.ui.blog;

import com.github.den13l.bloggerclient.ui.base.presenter.MvpPresenter;
import com.github.den13l.bloggerclient.ui.base.view.lce.MvpLceView;
import rx.Observable;

interface BlogMvp {

  interface View<M> extends MvpLceView<M> {

    void showPullToRefreshLoading();
  }

  interface Presenter<M> extends MvpPresenter<View<M>> {

    void setBlogId(String blogId);

    void loadPosts(boolean pullToRefresh);

    void loadMorePosts();
  }

  interface Interactor<M> {

    Observable<M> loadPosts(String blogId);

    Observable<M> refreshPosts(String blogId);

    Observable<M> loadMorePosts(String blogId);
  }
}
