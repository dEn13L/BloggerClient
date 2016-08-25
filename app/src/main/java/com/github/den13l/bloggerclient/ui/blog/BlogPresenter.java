package com.github.den13l.bloggerclient.ui.blog;

import java.util.List;
import javax.inject.Inject;
import com.github.den13l.bloggerclient.di.PerFragment;
import com.github.den13l.bloggerclient.model.Post;
import com.github.den13l.bloggerclient.model.event.RefreshBlogEvent;
import com.github.den13l.bloggerclient.ui.base.presenter.BasePresenter;
import com.github.den13l.bloggerclient.utils.RxBus;
import rx.Observable;
import rx.Subscription;
import timber.log.Timber;

@PerFragment class BlogPresenter extends BasePresenter<BlogMvp.View<List<Post>>>
    implements BlogMvp.Presenter<List<Post>> {

  private BlogInteractor blogInteractor;
  private String blogId;

  @Inject BlogPresenter(BlogInteractor blogInteractor) {
    this.blogInteractor = blogInteractor;
    RxBus.getInstance().register(RefreshBlogEvent.class, this::onRefreshBlogEvent);
  }

  private void refreshPosts() {
    if (isViewAttached()) {
      //noinspection ConstantConditions
      getView().showPullToRefreshLoading();
      loadPosts(true);
    }
  }

  @Override public void setBlogId(String blogId) {
    this.blogId = blogId;
  }

  @Override public void loadPosts(boolean pullToRefresh) {
    if (isViewAttached()) {
      Timber.i("loadPosts. %s", blogId);

      //noinspection ConstantConditions
      getView().showLoading(pullToRefresh);

      Observable<List<Post>> postsObservable;
      if (pullToRefresh) {
        postsObservable = blogInteractor.refreshPosts(blogId);
      } else {
        postsObservable = blogInteractor.loadPosts(blogId);
      }
      Subscription subscription = postsObservable.compose(applySchedulers())
          .subscribe(this::onNext, throwable -> onError(throwable, pullToRefresh),
              this::onCompleted);

      addSubscription(subscription);
    }
  }

  @Override public void loadMorePosts() {
    if (isViewAttached()) {
      Observable<List<Post>> postsObservable = blogInteractor.loadMorePosts(blogId);
      Subscription subscription = postsObservable.compose(applySchedulers())
          .subscribe(this::onNext, throwable -> onError(throwable, true), this::onCompleted);

      addSubscription(subscription);
    }
  }

  private void onNext(List<Post> data) {
    Timber.i("Set posts. %s", data);
    if (isViewAttached()) {
      //noinspection ConstantConditions
      getView().setData(data);
      getView().showContent();
    }
  }

  private void onError(Throwable throwable, boolean pullToRefresh) {
    Timber.e(throwable, "Loading posts error");
    if (isViewAttached()) {
      //noinspection ConstantConditions
      getView().showError(throwable, pullToRefresh);
    }
  }

  private void onCompleted() {
  }

  private void onRefreshBlogEvent(RefreshBlogEvent event) {
    if (event.getBlogId().equals(blogId)) {
      refreshPosts();
    }
  }
}
