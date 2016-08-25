package com.github.den13l.bloggerclient.db;

import android.content.Context;
import com.j256.ormlite.dao.Dao;
import java.util.ArrayList;
import java.util.List;
import com.github.den13l.bloggerclient.model.Blog;
import com.github.den13l.bloggerclient.model.Comment;
import com.github.den13l.bloggerclient.model.Post;
import com.github.den13l.bloggerclient.model.converters.BlogConverter;
import com.github.den13l.bloggerclient.model.converters.PostConverter;
import com.github.den13l.bloggerclient.model.db.DbAuthor;
import com.github.den13l.bloggerclient.model.db.DbBlog;
import com.github.den13l.bloggerclient.model.db.DbComment;
import com.github.den13l.bloggerclient.model.db.DbImage;
import com.github.den13l.bloggerclient.model.db.DbLabel;
import com.github.den13l.bloggerclient.model.db.DbPost;
import rx.Observable;

public class DbManager {

  private OrmLiteDbHelper ormLiteDbHelper;

  public DbManager(Context context) {
    this.ormLiteDbHelper = new OrmLiteDbHelper(context);
  }

  /**
   * Возвращает все блоги.
   *
   * @return Observable с объектами Blog.
   */
  public Observable<Blog> getBlogs() {
    return Observable.fromCallable(() -> ormLiteDbHelper.getBlogDao().queryForAll())
        .flatMapIterable(dbBlog -> dbBlog)
        .map(BlogConverter::createBlog);
  }

  /**
   * Возвращает блог по идентификатору.
   *
   * @return Observable с объектом Blog.
   */
  public Observable<Blog> getBlog(String blogId) {
    return Observable.fromCallable(() -> ormLiteDbHelper.getBlogDao().queryForId(blogId))
        .map(BlogConverter::createBlog);
  }

  /**
   * Сохраняет блог.
   *
   * @return Observable с количеством измененных столбцов в базе данных.
   */
  public Observable<Integer> saveBlog(Blog blog) {
    return Observable.fromCallable(
        () -> ormLiteDbHelper.getBlogDao().createOrUpdate(new DbBlog(blog)).getNumLinesChanged());
  }

  /**
   * Возвращает все посты.
   *
   * @return Observable с объектами Post.
   */
  public Observable<Post> getPosts() {
    return Observable.fromCallable(() -> ormLiteDbHelper.getPostDao().queryForAll())
        .flatMapIterable(dbPost -> dbPost)
        .map(PostConverter::create);
  }

  /**
   * Возвращает все посты блога.
   *
   * @return Observable с объектами Post.
   */
  public Observable<Post> getPosts(String blogId) {
    return Observable.fromCallable(
        () -> ormLiteDbHelper.getPostDao().queryForEq(DbPost.COLUMN_BLOG_ID, blogId))
        .flatMapIterable(dbPosts -> dbPosts)
        .map(PostConverter::create);
  }

  /**
   * Возвращает пост с определенным идентификатором.
   *
   * @return Observable с объектом Post.
   */
  public Observable<Post> getPost(String postId) {
    return Observable.fromCallable(() -> ormLiteDbHelper.getPostDao().queryForId(postId))
        .map(PostConverter::create);
  }

  /**
   * Сохраняет список постов.
   *
   * @return Observable с количеством измененных столбцов в базе данных.
   */
  public Observable<Integer> savePosts(List<Post> posts) {
    return Observable.from(posts).flatMap(this::savePost);
  }

  /**
   * Сохраняет пост.
   *
   * @return Observable с количеством измененных столбцов в базе данных.
   */
  public Observable<Integer> savePost(Post post) {
    return Observable.fromCallable(() -> {
      Dao<DbPost, String> dbPostDao = ormLiteDbHelper.getPostDao();
      Dao<DbImage, String> dbImageDao = ormLiteDbHelper.getImagesDao();
      Dao<DbComment, String> dbCommentDao = ormLiteDbHelper.getCommentsDao();
      Dao<DbLabel, String> dbLabelDao = ormLiteDbHelper.getLabelsDao();
      Dao<DbAuthor, String> dbAuthorDao = ormLiteDbHelper.getAuthorsDao();

      DbPost dbPost = new DbPost(post);

      DbAuthor dbAuthor = new DbAuthor(post.getAuthor(), dbPost);
      dbAuthorDao.createOrUpdate(dbAuthor);
      dbPost.setAuthor(dbAuthor);

      dbPostDao.assignEmptyForeignCollection(dbPost, DbPost.COLUMN_IMAGES);
      if (post.getImages() != null) {
        List<DbImage> dbImages = new ArrayList<>();
        for (String image : post.getImages()) {
          DbImage dbImage = new DbImage(image, dbPost);
          dbImages.add(dbImage);
          dbImageDao.createOrUpdate(dbImage);
        }
        dbPost.setImages(dbImages);
      }

      dbPostDao.assignEmptyForeignCollection(dbPost, DbPost.COLUMN_COMMENTS);
      if (post.getComments() != null) {
        List<DbComment> dbComments = new ArrayList<>();
        for (Comment comment : post.getComments()) {
          DbComment dbComment = new DbComment(comment, dbPost);
          dbComments.add(dbComment);
          dbCommentDao.createOrUpdate(dbComment);
        }
        dbPost.setComments(dbComments);
      }

      dbPostDao.assignEmptyForeignCollection(dbPost, DbPost.COLUMN_LABEL);
      if (post.getLabels() != null) {
        List<DbLabel> dbLabels = new ArrayList<>();
        for (String label : post.getLabels()) {
          DbLabel dbLabel = new DbLabel(label, dbPost);
          dbLabels.add(dbLabel);
          dbLabelDao.createOrUpdate(dbLabel);
        }
        dbPost.setLabels(dbLabels);
      }

      return dbPostDao.createOrUpdate(dbPost).getNumLinesChanged();
    });
  }
}
