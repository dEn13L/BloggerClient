package com.github.den13l.bloggerclient.ui.base.view;

import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import butterknife.ButterKnife;
import com.github.den13l.bloggerclient.ui.base.presenter.MvpPresenter;
import com.github.den13l.bloggerclient.ui.base.view.mvp.MvpFragment;
import com.github.den13l.bloggerclient.ui.base.view.mvp.MvpView;

public abstract class BaseMvpFragment<V extends MvpView, P extends MvpPresenter<V>>
    extends MvpFragment<V, P> {

  protected abstract void injectDependencies();

  protected abstract void clearDependencies();

  @LayoutRes public abstract int getLayoutRes();

  @Override public void onCreate(@Nullable Bundle savedInstanceState) {
    injectDependencies();
    super.onCreate(savedInstanceState);
  }

  @Nullable @Override
  public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
      @Nullable Bundle savedInstanceState) {
    return inflater.inflate(getLayoutRes(), container, false);
  }

  @Override public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
    super.onViewCreated(view, savedInstanceState);
    ButterKnife.bind(view);
  }

  @Override public void onDestroy() {
    clearDependencies();
    super.onDestroy();
  }
}
