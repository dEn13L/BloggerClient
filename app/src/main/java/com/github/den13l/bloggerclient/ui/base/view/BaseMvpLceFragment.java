package com.github.den13l.bloggerclient.ui.base.view;

import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import butterknife.ButterKnife;
import com.github.den13l.bloggerclient.R;
import com.github.den13l.bloggerclient.ui.base.presenter.MvpPresenter;
import com.github.den13l.bloggerclient.ui.base.view.lce.MvpLceFragment;
import com.github.den13l.bloggerclient.ui.base.view.lce.MvpLceView;

public abstract class BaseMvpLceFragment<CV extends View, M, V extends MvpLceView<M>, P extends MvpPresenter<V>>
    extends MvpLceFragment<CV, M, V, P> {

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
    View view = inflater.inflate(getLayoutRes(), container, false);
    ButterKnife.bind(this, view);
    return view;
  }

  @Override public void onDestroy() {
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
