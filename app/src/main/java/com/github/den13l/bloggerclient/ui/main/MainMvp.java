package com.github.den13l.bloggerclient.ui.main;

import java.util.List;
import com.github.den13l.bloggerclient.ui.base.presenter.MvpPresenter;
import com.github.den13l.bloggerclient.ui.base.view.lce.MvpLceView;
import rx.Observable;

interface MainMvp {

  interface View<M> extends MvpLceView<M> {

    void showAddBlogDialog();

    void setCurrentItem(int item);
  }

  interface Presenter<M> extends MvpPresenter<View<M>> {

    void processAddBlog();

    void refresh();

    void loadBlogs(boolean pullToRefresh);
  }

  interface Interactor<M> {

    Observable<List<M>> loadBlogs(String url, String apiKey);

    Observable saveBlog(M blog);
  }
}
