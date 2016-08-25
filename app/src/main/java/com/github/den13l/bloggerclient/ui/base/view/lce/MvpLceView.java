package com.github.den13l.bloggerclient.ui.base.view.lce;

import com.github.den13l.bloggerclient.ui.base.view.mvp.MvpView;

public interface MvpLceView<M> extends MvpView {

  void showLoading(boolean pullToRefresh);

  void showContent();

  void showError(Throwable throwable, boolean pullToRefresh);

  void setData(M data);

  void loadData(boolean pullToRefresh);
}
