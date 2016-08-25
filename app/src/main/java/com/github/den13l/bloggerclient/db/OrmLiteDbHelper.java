package com.github.den13l.bloggerclient.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import com.j256.ormlite.android.apptools.OrmLiteSqliteOpenHelper;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;
import java.sql.SQLException;
import com.github.den13l.bloggerclient.model.db.DbAuthor;
import com.github.den13l.bloggerclient.model.db.DbBlog;
import com.github.den13l.bloggerclient.model.db.DbComment;
import com.github.den13l.bloggerclient.model.db.DbImage;
import com.github.den13l.bloggerclient.model.db.DbLabel;
import com.github.den13l.bloggerclient.model.db.DbPost;
import timber.log.Timber;

class OrmLiteDbHelper extends OrmLiteSqliteOpenHelper {

  private static final String DATABASE_NAME = "blogger.db";
  private static final int DATABASE_VERSION = 1;

  private Dao<DbBlog, String> blogsDao;
  private Dao<DbPost, String> postsDao;
  private Dao<DbImage, String> imagesDao;
  private Dao<DbComment, String> commentsDao;
  private Dao<DbAuthor, String> authorsDao;
  private Dao<DbLabel, String> labelsDao;

  OrmLiteDbHelper(Context context) {
    super(context, DATABASE_NAME, null, DATABASE_VERSION);
  }

  @Override public void onCreate(SQLiteDatabase database, ConnectionSource connectionSource) {
    try {
      TableUtils.createTable(connectionSource, DbBlog.class);
      TableUtils.createTable(connectionSource, DbPost.class);
      TableUtils.createTable(connectionSource, DbImage.class);
      TableUtils.createTable(connectionSource, DbComment.class);
      TableUtils.createTable(connectionSource, DbAuthor.class);
      TableUtils.createTable(connectionSource, DbLabel.class);
    } catch (SQLException e) {
      Timber.e(e, "Create database tables error");
    }
  }

  @Override
  public void onUpgrade(SQLiteDatabase database, ConnectionSource connectionSource, int oldVersion,
      int newVersion) {
  }

  Dao<DbBlog, String> getBlogDao() throws SQLException {
    if (blogsDao == null) {
      blogsDao = getDao(DbBlog.class);
    }
    return blogsDao;
  }

  Dao<DbPost, String> getPostDao() throws SQLException {
    if (postsDao == null) {
      postsDao = getDao(DbPost.class);
    }
    return postsDao;
  }

  Dao<DbImage, String> getImagesDao() throws SQLException {
    if (imagesDao == null) {
      imagesDao = getDao(DbImage.class);
    }
    return imagesDao;
  }

  Dao<DbComment, String> getCommentsDao() throws SQLException {
    if (commentsDao == null) {
      commentsDao = getDao(DbComment.class);
    }
    return commentsDao;
  }

  Dao<DbAuthor, String> getAuthorsDao() throws SQLException {
    if (authorsDao == null) {
      authorsDao = getDao(DbAuthor.class);
    }
    return authorsDao;
  }

  Dao<DbLabel, String> getLabelsDao() throws SQLException {
    if (labelsDao == null) {
      labelsDao = getDao(DbLabel.class);
    }
    return labelsDao;
  }
}