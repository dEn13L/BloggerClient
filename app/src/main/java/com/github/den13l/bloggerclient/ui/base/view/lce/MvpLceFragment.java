package com.github.den13l.bloggerclient.ui.base.view.lce;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;
import com.github.den13l.bloggerclient.R;
import com.github.den13l.bloggerclient.ui.base.presenter.MvpPresenter;
import com.github.den13l.bloggerclient.ui.base.view.mvp.MvpFragment;

public abstract class MvpLceFragment<CV extends View, M, V extends MvpLceView<M>, P extends MvpPresenter<V>>
    extends MvpFragment<V, P> implements MvpLceView<M> {

  protected View loadingView;
  protected CV contentView;
  protected TextView errorView;

  @Override public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
    super.onViewCreated(view, savedInstanceState);

    loadingView = view.findViewById(R.id.viewLoading);
    //noinspection unchecked
    contentView = (CV) view.findViewById(R.id.viewContent);
    errorView = (TextView) view.findViewById(R.id.viewError);

    if (loadingView == null) {
      throw new NullPointerException(
          "Loading view is null! Loading View id should be R.id.viewLoading");
    }

    if (contentView == null) {
      throw new NullPointerException(
          "Content view is null! Content View id should be R.id.viewContent");
    }

    if (errorView == null) {
      throw new NullPointerException("Error view is null! Error View id should be R.id.viewError");
    }

    errorView.setOnClickListener(v -> onErrorViewClicked());
  }

  @Override public void showLoading(boolean pullToRefresh) {
    if (!pullToRefresh) {
      animateLoadingViewIn();
    }
  }

  protected void animateLoadingViewIn() {
    LceAnimator.showLoading(loadingView, contentView, errorView);
  }

  @Override public void showContent() {
    animateContentViewIn();
  }

  protected void animateContentViewIn() {
    LceAnimator.showContent(loadingView, contentView, errorView);
  }

  protected abstract String getErrorMessage(Throwable e, boolean pullToRefresh);

  protected void showLightError(String msg) {
    if (getActivity() != null) {
      Toast.makeText(getActivity(), msg, Toast.LENGTH_SHORT).show();
    }
  }

  protected void onErrorViewClicked() {
    loadData(false);
  }

  @Override public void showError(Throwable e, boolean pullToRefresh) {
    String errorMsg = getErrorMessage(e, pullToRefresh);
    if (pullToRefresh) {
      showLightError(errorMsg);
    } else {
      errorView.setText(errorMsg);
      animateErrorViewIn();
    }
  }

  protected void animateErrorViewIn() {
    LceAnimator.showErrorView(loadingView, contentView, errorView);
  }

  @Override public void onDestroyView() {
    super.onDestroyView();
    loadingView = null;
    contentView = null;
    errorView = null;
  }
}
