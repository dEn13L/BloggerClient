package com.github.den13l.bloggerclient.ui.addblog;

import javax.inject.Inject;
import com.github.den13l.bloggerclient.BuildConfig;
import com.github.den13l.bloggerclient.model.Blog;
import com.github.den13l.bloggerclient.model.event.AddBlogEvent;
import com.github.den13l.bloggerclient.ui.base.presenter.BasePresenter;
import com.github.den13l.bloggerclient.utils.RxBus;
import rx.Subscription;
import timber.log.Timber;

class AddBlogPresenter extends BasePresenter<AddBlogMvp.View> implements AddBlogMvp.Presenter {

  private AddBlogInteractor interactor;

  @Inject AddBlogPresenter(AddBlogInteractor interactor) {
    this.interactor = interactor;
  }

  @Override public void addBlog(String blogUrl) {
    if (isViewAttached()) {
      //noinspection ConstantConditions
      getView().showLoading();

      Subscription subscription = interactor.addBlog(blogUrl, BuildConfig.API_KEY)
          .compose(applySchedulers())
          .subscribe(this::onNext, this::onError, this::onCompleted);

      addSubscription(subscription);
    }
  }

  private void onNext(Blog blog) {
    Timber.i("Add blog. %s", blog);
    if (isViewAttached()) {
      //noinspection ConstantConditions
      getView().showSuccess();
    }
    RxBus.getInstance().post(new AddBlogEvent(blog));
  }

  private void onError(Throwable throwable) {
    Timber.e(throwable, "Add blog error");
    if (isViewAttached()) {
      //noinspection ConstantConditions
      getView().showError();
    }
  }

  private void onCompleted() {

  }
}
