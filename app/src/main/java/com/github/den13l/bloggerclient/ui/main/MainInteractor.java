package com.github.den13l.bloggerclient.ui.main;

import java.util.List;
import javax.inject.Inject;
import com.github.den13l.bloggerclient.api.BloggerApi;
import com.github.den13l.bloggerclient.db.DbManager;
import com.github.den13l.bloggerclient.di.PerActivity;
import com.github.den13l.bloggerclient.model.Blog;
import com.github.den13l.bloggerclient.model.api.ApiBlog;
import com.github.den13l.bloggerclient.model.converters.BlogConverter;
import rx.Observable;
import timber.log.Timber;

@PerActivity public class MainInteractor implements MainMvp.Interactor<Blog> {

  private BloggerApi bloggerApi;
  private DbManager dbManager;

  @Inject MainInteractor(BloggerApi bloggerApi, DbManager dbManager) {
    this.bloggerApi = bloggerApi;
    this.dbManager = dbManager;
  }

  /**
   * Загружает список блогов.
   *
   * Сначала загружаются данные из базы, если там нет ни одного блока, то загружается дефолтный
   * блог с сервера и сохраняется в базу.
   *
   * @return Observable с объектами List<Blog>.
   */
  @Override public Observable<List<Blog>> loadBlogs(String url, String apiKey) {
    Timber.i("loadBlogs");
    Observable<List<Blog>> dbBlogsObservable =
        dbManager.getBlogs().toList().filter(blogs -> !blogs.isEmpty());

    Observable<List<Blog>> apiBlogsObservable =
        bloggerApi.getBlog(url, apiKey).map(this::createBlog).doOnNext(this::saveBlogToDb).toList();

    return Observable.concat(dbBlogsObservable, apiBlogsObservable).first();
  }

  @Override public Observable<Integer> saveBlog(Blog blog) {
    return dbManager.saveBlog(blog);
  }

  private Blog createBlog(ApiBlog apiBlog) {
    return BlogConverter.createBlog(apiBlog);
  }

  private void saveBlogToDb(Blog blog) {
    saveBlog(blog).subscribe(count -> {
    }, throwable -> Timber.e(throwable, "Save blog to db error"));
  }
}
