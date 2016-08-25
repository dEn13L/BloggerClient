package com.github.den13l.bloggerclient.ui.post;

import javax.inject.Inject;
import com.github.den13l.bloggerclient.di.PerActivity;
import com.github.den13l.bloggerclient.model.Post;
import com.github.den13l.bloggerclient.ui.base.presenter.BasePresenter;
import timber.log.Timber;

@PerActivity class PostPresenter extends BasePresenter<PostMvp.View<Post>>
    implements PostMvp.Presenter<Post> {

  private PostInteractor postInteractor;
  private Post post;

  @Inject PostPresenter(PostInteractor postInteractor) {
    this.postInteractor = postInteractor;
  }

  @Override public void loadPost(String postId, boolean pullToRefresh) {
    Timber.i("loadPost");
    if (isViewAttached()) {
      //noinspection ConstantConditions
      getView().showLoading(pullToRefresh);

      postInteractor.loadPost(postId)
          .subscribe(this::onNext, throwable -> onError(throwable, pullToRefresh),
              this::onCompleted);
    }
  }

  private void onNext(Post post) {
    Timber.i("Set post. %s", post);
    this.post = post;
    if (isViewAttached()) {
      //noinspection ConstantConditions
      getView().setData(post);
    }
  }

  private void onError(Throwable throwable, boolean pullToRefresh) {
    Timber.e(throwable, "Loading post error");
    if (isViewAttached()) {
      //noinspection ConstantConditions
      getView().showError(throwable, pullToRefresh);
    }
  }

  private void onCompleted() {
    if (isViewAttached()) {
      //noinspection ConstantConditions
      getView().showContent();
    }
  }

  @Override public void sharePost() {
    if (isViewAttached() && post != null) {
      //noinspection ConstantConditions
      getView().sharePost(post);
    }
  }

  @Override public void openInBrowser() {
    if (isViewAttached() && post != null) {
      //noinspection ConstantConditions
      getView().openUrl(post.getUrl());
    }
  }
}
