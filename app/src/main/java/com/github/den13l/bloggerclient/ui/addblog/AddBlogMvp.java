package com.github.den13l.bloggerclient.ui.addblog;

import com.github.den13l.bloggerclient.ui.base.presenter.MvpPresenter;
import com.github.den13l.bloggerclient.ui.base.view.mvp.MvpView;
import rx.Observable;

interface AddBlogMvp {

  interface View extends MvpView {

    void showLoading();

    void showSuccess();

    void showError();

    void detach();
  }

  interface Presenter extends MvpPresenter<View> {

    void addBlog(String blogUrl);
  }

  interface Interactor<M> {

    Observable<M> addBlog(String blogUrl, String key);
  }
}
