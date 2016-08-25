package com.github.den13l.bloggerclient.ui.base.view;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.View;
import com.github.den13l.bloggerclient.R;
import com.github.den13l.bloggerclient.ui.base.presenter.MvpPresenter;
import com.github.den13l.bloggerclient.ui.base.view.lce.MvpLceView;

public abstract class BaseRefreshFragment<M, V extends MvpLceView<M>, P extends MvpPresenter<V>>
    extends BaseMvpLceFragment<SwipeRefreshLayout, M, V, P>
    implements SwipeRefreshLayout.OnRefreshListener {

  @Override public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
    super.onViewCreated(view, savedInstanceState);
    contentView.setOnRefreshListener(this);
    int[] colors = new int[] { ContextCompat.getColor(getContext(), R.color.accent_color) };
    contentView.setColorSchemeColors(colors);
  }

  @Override public void onRefresh() {
    loadData(true);
  }

  @Override public void showContent() {
    super.showContent();
    contentView.setRefreshing(false);
  }

  @Override public void showError(Throwable e, boolean pullToRefresh) {
    super.showError(e, pullToRefresh);
    contentView.setRefreshing(false);
  }
}
