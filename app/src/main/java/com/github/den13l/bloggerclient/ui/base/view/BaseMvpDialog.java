package com.github.den13l.bloggerclient.ui.base.view;

import android.os.Bundle;
import android.support.annotation.Nullable;
import com.github.den13l.bloggerclient.ui.base.presenter.MvpPresenter;
import com.github.den13l.bloggerclient.ui.base.view.mvp.MvpDialog;
import com.github.den13l.bloggerclient.ui.base.view.mvp.MvpView;

public abstract class BaseMvpDialog<V extends MvpView, P extends MvpPresenter<V>>
    extends MvpDialog<V, P> {

  protected abstract void injectDependencies();

  protected abstract void clearDependencies();

  @Override public void onCreate(@Nullable Bundle savedInstanceState) {
    injectDependencies();
    super.onCreate(savedInstanceState);
  }

  @Override public void onDestroy() {
    clearDependencies();
    super.onDestroy();
  }
}
