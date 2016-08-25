package com.github.den13l.bloggerclient.ui.base.presenter;

import android.support.annotation.Nullable;
import java.lang.ref.WeakReference;
import com.github.den13l.bloggerclient.ui.base.view.mvp.MvpView;

public class MvpBasePresenter<V extends MvpView> implements MvpPresenter<V> {

  private WeakReference<V> viewRef;

  @Override public void attachView(V view) {
    this.viewRef = new WeakReference<>(view);
  }

  @Override public void detachView() {
    if (viewRef != null) {
      viewRef.clear();
      viewRef = null;
    }
  }

  @Nullable public V getView() {
    return viewRef != null ? viewRef.get() : null;
  }

  public boolean isViewAttached() {
    return viewRef != null && viewRef.get() != null;
  }
}