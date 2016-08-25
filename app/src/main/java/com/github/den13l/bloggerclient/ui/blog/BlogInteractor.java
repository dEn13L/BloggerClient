package com.github.den13l.bloggerclient.ui.blog;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import javax.inject.Inject;
import com.github.den13l.bloggerclient.BuildConfig;
import com.github.den13l.bloggerclient.api.BloggerApi;
import com.github.den13l.bloggerclient.db.DbManager;
import com.github.den13l.bloggerclient.di.PerFragment;
import com.github.den13l.bloggerclient.model.Post;
import com.github.den13l.bloggerclient.model.api.ApiPostList;
import com.github.den13l.bloggerclient.model.converters.PostConverter;
import rx.Observable;
import timber.log.Timber;

@PerFragment class BlogInteractor implements BlogMvp.Interactor<List<Post>> {

  private BloggerApi bloggerApi;
  private DbManager dbManager;
  private Set<Post> posts;
  private String nextPageToken;

  @Inject BlogInteractor(BloggerApi bloggerApi, DbManager dbManager) {
    this.bloggerApi = bloggerApi;
    this.dbManager = dbManager;
    this.posts = new HashSet<>();
  }

  /**
   * Загружает список постов блога.
   *
   * Сначала загружает данные из базы, потом догружаются данные с сервера.
   *
   * @return Observable с объектами List<Post>.
   */
  @Override public Observable<List<Post>> loadPosts(String blogId) {
    Timber.i("loadPosts");
    Observable<List<Post>> dbPostsObservable = dbManager.getPosts(blogId)
        .toList()
        .doOnNext(this::addPosts)
        .compose(getAllSortedPosts())
        .filter(posts1 -> !posts1.isEmpty());

    Observable<List<Post>> apiPostsObservable =
        getServerPostsObservable(blogId).compose(getAllSortedPosts());

    return Observable.concat(dbPostsObservable, apiPostsObservable);
  }

  /**
   * Загружает список постов блога.
   *
   * Загружает данные с сервера.
   *
   * @return Observable с объектами List<Post>.
   */
  @Override public Observable<List<Post>> refreshPosts(String blogId) {
    Timber.i("refreshPosts");
    return getServerPostsObservable(blogId).compose(getAllSortedPosts());
  }

  /**
   * Загружает список постов блога.
   *
   * Загружает данные с сервера по указанному токену страницы постов.
   *
   * @return Observable с объектами List<Post>.
   */
  @Override public Observable<List<Post>> loadMorePosts(String blogId) {
    Timber.i("loadMorePosts");
    return getServerPostsObservable(blogId, nextPageToken).compose(getAllSortedPosts());
  }

  private void addPosts(List<Post> posts) {
    this.posts.addAll(posts);
  }

  private void addPost(Post post) {
    this.posts.add(post);
  }

  private Set<Post> getPosts() {
    return posts;
  }

  private void savePosts(List<Post> posts) {
    dbManager.savePosts(posts).subscribe(count -> {
    }, throwable -> Timber.e(throwable, "Save posts to db error"));
  }

  private Observable<List<Post>> getServerPostsObservable(String blogId) {
    return bloggerApi.getPostList(blogId, BuildConfig.API_KEY)
        .compose(handleApiPostList())
        .compose(checkPostExisting(blogId));
  }

  private Observable<List<Post>> getServerPostsObservable(String blogId, String nextPageToken) {
    return bloggerApi.getPostList(blogId, BuildConfig.API_KEY, nextPageToken)
        .compose(handleApiPostList())
        .compose(checkPostExisting(blogId));
  }

  private Observable.Transformer<ApiPostList, List<Post>> handleApiPostList() {
    return observable -> observable.doOnNext(
        apiPostList -> setNextPageToken(apiPostList.nextPageToken))
        .map(apiPostList -> apiPostList.posts)
        .flatMapIterable(apiPosts -> apiPosts)
        .map(PostConverter::create)
        .toList()
        .doOnNext(this::addPosts)
        .doOnNext(this::savePosts);
  }

  /**
   * Проверяет, есть ли уже последний полученный пост.
   * Если нету, то продолжает скачивание следующий постов по nextPageToken
   */
  private Observable.Transformer<List<Post>, List<Post>> checkPostExisting(String blogId) {
    return observable -> observable.flatMapIterable(posts1 -> posts1)
        .last()
        .flatMap(post -> Observable.just(posts.contains(post)))
        .flatMap(contains -> {
          if (contains) {
            return Observable.just(null);
          } else {
            return getServerPostsObservable(blogId, nextPageToken);
          }
        });
  }

  private void setNextPageToken(String nextPageToken) {
    this.nextPageToken = nextPageToken;
  }

  private <T> Observable.Transformer<T, List<Post>> getAllSortedPosts() {
    return observable -> observable.flatMap(o -> Observable.from(getPosts()))
        .toSortedList(this::comparePosts);
  }

  private int comparePosts(Post lhs, Post rhs) {
    return rhs.getPublished().compareTo(lhs.getPublished());
  }
}
