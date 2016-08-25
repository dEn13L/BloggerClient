package com.github.den13l.bloggerclient.ui.base.presenter;

import com.github.den13l.bloggerclient.ui.base.view.mvp.MvpView;

public interface MvpPresenter<V extends MvpView> {

  void attachView(V mvpView);

  void detachView();
}