package com.github.den13l.bloggerclient.ui.base.view;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import butterknife.ButterKnife;
import com.github.den13l.bloggerclient.R;
import com.github.den13l.bloggerclient.ui.base.presenter.MvpPresenter;
import com.github.den13l.bloggerclient.ui.base.view.lce.MvpLceActivity;
import com.github.den13l.bloggerclient.ui.base.view.lce.MvpLceView;

public abstract class BaseMvpLceActivity<CV extends View, M, V extends MvpLceView<M>, P extends MvpPresenter<V>>
    extends MvpLceActivity<CV, M, V, P> {

  protected abstract void injectDependencies();

  protected abstract void clearDependencies();

  @Override protected void onCreate(@Nullable Bundle savedInstanceState) {
    injectDependencies();
    super.onCreate(savedInstanceState);
  }

  @Override public void onContentChanged() {
    super.onContentChanged();
    ButterKnife.bind(this);
  }

  @Override protected void onDestroy() {
    clearDependencies();
    super.onDestroy();
  }

  @Override protected String getErrorMessage(Throwable e, boolean pullToRefresh) {
    if (pullToRefresh) {
      return getString(R.string.error_occurred);
    } else {
      return getString(R.string.error_occurred_retry);
    }
  }
}
