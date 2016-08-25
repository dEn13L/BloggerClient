package com.github.den13l.bloggerclient.ui.base.presenter;

import com.github.den13l.bloggerclient.ui.base.view.mvp.MvpView;
import rx.Observable;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
import rx.subscriptions.CompositeSubscription;

public class BasePresenter<V extends MvpView> extends MvpBasePresenter<V> {

  private CompositeSubscription compositeSubscription;

  public BasePresenter() {
    this.compositeSubscription = new CompositeSubscription();
  }

  @Override public void detachView() {
    super.detachView();
    compositeSubscription.clear();
  }

  protected void addSubscription(Subscription subscription) {
    compositeSubscription.add(subscription);
  }

  protected void removeSubscription(Subscription subscription) {
    compositeSubscription.remove(subscription);
  }

  protected <T> Observable.Transformer<T, T> applySchedulers() {
    return observable -> observable.subscribeOn(Schedulers.io())
        .observeOn(AndroidSchedulers.mainThread());
  }
}
