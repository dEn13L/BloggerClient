package com.github.den13l.bloggerclient.ui.base.view.mvp;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import com.github.den13l.bloggerclient.ui.base.presenter.MvpPresenter;

public abstract class MvpActivity<V extends MvpView, P extends MvpPresenter<V>>
    extends AppCompatActivity {

  @NonNull protected P presenter;

  public abstract P createPresenter();

  @Override protected void onCreate(@Nullable Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    presenter = createPresenter();
    presenter.attachView((V) this);
  }

  @Override protected void onDestroy() {
    presenter.detachView();
    super.onDestroy();
  }

  @NonNull public P getPresenter() {
    return presenter;
  }
}
