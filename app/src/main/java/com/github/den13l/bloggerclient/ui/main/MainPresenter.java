package com.github.den13l.bloggerclient.ui.main;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import javax.inject.Inject;
import com.github.den13l.bloggerclient.BuildConfig;
import com.github.den13l.bloggerclient.di.PerActivity;
import com.github.den13l.bloggerclient.model.Blog;
import com.github.den13l.bloggerclient.model.Constants;
import com.github.den13l.bloggerclient.model.event.AddBlogEvent;
import com.github.den13l.bloggerclient.model.event.RefreshBlogEvent;
import com.github.den13l.bloggerclient.ui.base.presenter.BasePresenter;
import com.github.den13l.bloggerclient.utils.RxBus;
import rx.Subscription;
import timber.log.Timber;

@PerActivity public class MainPresenter extends BasePresenter<MainMvp.View<List<Blog>>>
    implements MainMvp.Presenter<List<Blog>> {

  private MainInteractor mainInteractor;
  private String currentBlogId;
  private Set<Blog> blogs;

  @Inject MainPresenter(MainInteractor mainInteractor) {
    this.mainInteractor = mainInteractor;
    this.blogs = new HashSet<>();
    RxBus.getInstance().register(AddBlogEvent.class, this::onAddBlogEvent);
  }

  @Override public void processAddBlog() {
    if (isViewAttached()) {
      //noinspection ConstantConditions
      getView().showAddBlogDialog();
    }
  }

  @Override public void refresh() {
    if (currentBlogId != null) {
      RxBus.getInstance().post(new RefreshBlogEvent(currentBlogId));
    }
  }

  @Override public void loadBlogs(boolean pullToRefresh) {
    if (isViewAttached()) {
      //noinspection ConstantConditions
      getView().showLoading(pullToRefresh);

      Subscription subscription =
          mainInteractor.loadBlogs(Constants.DEFAULT_BLOG_URL, BuildConfig.API_KEY)
              .compose(applySchedulers())
              .subscribe(this::onNext, throwable -> onError(throwable, pullToRefresh),
                  this::onCompleted);

      addSubscription(subscription);
    }
  }

  private void onNext(List<Blog> data) {
    Timber.i("Set blogs. %s", data);
    if (isViewAttached()) {
      addBlogs(data);
      setCurrentBlogId(data);

      //noinspection ConstantConditions
      getView().setData(getBlogs());
    }
  }

  private void onError(Throwable throwable, boolean pullToRefresh) {
    Timber.e(throwable, "Loading blogs error");
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

  private void onAddBlogEvent(AddBlogEvent event) {
    if (isViewAttached()) {
      Blog blog = event.getBlog();
      setCurrentBlogId(blog.getId());
      addBlog(blog);
      saveBlog(blog);

      //noinspection ConstantConditions
      getView().setData(getBlogs());

      int position = getBlogPosition(blog);
      if (position != -1) {
        getView().setCurrentItem(position);
      }
    }
  }

  /**
   * Метод ищет блог в списке блогов.
   *
   * @param blog Блог для поиска.
   * @return Возвращает позицию блога в списке блогов или -1, если блог не найден.
   */
  private int getBlogPosition(Blog blog) {
    List<Blog> blogs = getBlogs();
    for (int i = 0; i < blogs.size(); i++) {
      if (blogs.get(i).equals(blog)) return i;
    }
    return -1;
  }

  private void addBlog(Blog blog) {
    this.blogs.add(blog);
  }

  private void addBlogs(List<Blog> blogs) {
    this.blogs.addAll(blogs);
  }

  private List<Blog> getBlogs() {
    return new ArrayList<>(blogs);
  }

  private void setCurrentBlogId(List<Blog> data) {
    if (data != null && !data.isEmpty()) {
      setCurrentBlogId(data.get(0).getId());
    }
  }

  private void setCurrentBlogId(String blogId) {
    currentBlogId = blogId;
  }

  private void saveBlog(Blog blog) {
    mainInteractor.saveBlog(blog).subscribe(count -> {
    }, throwable -> Timber.e(throwable, "Save blog to db error"));
  }
}
