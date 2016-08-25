package com.github.den13l.bloggerclient.ui.base.view;

import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import butterknife.ButterKnife;
import com.github.den13l.bloggerclient.ui.base.presenter.MvpPresenter;
import com.github.den13l.bloggerclient.ui.base.view.mvp.MvpActivity;
import com.github.den13l.bloggerclient.ui.base.view.mvp.MvpView;

public abstract class BaseMvpActivity<V extends MvpView, P extends MvpPresenter<V>>
    extends MvpActivity<V, P> {

  protected abstract void injectDependencies();

  protected abstract void clearDependencies();

  @Override protected void onCreate(@Nullable Bundle savedInstanceState) {
    injectDependencies();
    super.onCreate(savedInstanceState);
  }

  @Override public void setContentView(@LayoutRes int layoutResID) {
    super.setContentView(layoutResID);
    ButterKnife.bind(this);
  }

  @Override protected void onDestroy() {
    clearDependencies();
    super.onDestroy();
  }
}
