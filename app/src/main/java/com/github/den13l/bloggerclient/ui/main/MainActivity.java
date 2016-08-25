package com.github.den13l.bloggerclient.ui.main;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import butterknife.BindView;
import java.util.List;
import javax.inject.Inject;
import com.github.den13l.bloggerclient.BloggerApplication;
import com.github.den13l.bloggerclient.R;
import com.github.den13l.bloggerclient.model.Blog;
import com.github.den13l.bloggerclient.ui.addblog.AddBlogDialog;
import com.github.den13l.bloggerclient.ui.base.view.BaseMvpLceActivity;

public class MainActivity extends
    BaseMvpLceActivity<ViewPager, List<Blog>, MainMvp.View<List<Blog>>, MainMvp.Presenter<List<Blog>>>
    implements MainMvp.View<List<Blog>> {

  private static final String ADD_BLOG_DIALOG_TAG = "addDialogTag";

  @Inject MainPresenter presenter;
  @BindView(R.id.toolbar) Toolbar toolbar;
  @BindView(R.id.tabs) TabLayout tabLayout;
  @BindView(R.id.viewEmpty) View emptyView;
  private MainPagerAdapter pagerAdapter;

  @Override public void injectDependencies() {
    BloggerApplication.get(this).getMainComponent().inject(this);
  }

  @Override protected void clearDependencies() {
    BloggerApplication.get(this).clearMainComponent();
  }

  @Override public MainMvp.Presenter<List<Blog>> createPresenter() {
    return presenter;
  }

  @Override protected void onCreate(@Nullable Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);
    setupToolbar();
    setupViewPager();
    loadData(false);
  }

  private void setupToolbar() {
    setSupportActionBar(toolbar);
    //noinspection ConstantConditions
    getSupportActionBar().setTitle("");
  }

  private void setupViewPager() {
    pagerAdapter = new MainPagerAdapter(getSupportFragmentManager());
    contentView.setAdapter(pagerAdapter);
    tabLayout.setupWithViewPager(contentView);
  }

  @Override public boolean onCreateOptionsMenu(Menu menu) {
    getMenuInflater().inflate(R.menu.menu_main, menu);
    return true;
  }

  @Override public boolean onOptionsItemSelected(MenuItem item) {
    int id = item.getItemId();
    switch (id) {
      case R.id.action_add_blog:
        presenter.processAddBlog();
        return true;
      case R.id.action_update:
        presenter.refresh();
        return true;
    }
    return super.onOptionsItemSelected(item);
  }

  @Override public void setData(List<Blog> blogs) {
    if (blogs != null && !blogs.isEmpty()) {
      pagerAdapter.setBlogs(blogs);
      pagerAdapter.notifyDataSetChanged();
    }
  }

  @Override public void loadData(boolean pullToRefresh) {
    presenter.loadBlogs(pullToRefresh);
  }

  @Override public void showLoading(boolean pullToRefresh) {
    super.showLoading(pullToRefresh);
    if (!pullToRefresh) {
      emptyView.setVisibility(View.GONE);
    }
  }

  @Override public void showContent() {
    super.showContent();
    if (pagerAdapter.getBlogs() == null || pagerAdapter.getBlogs().isEmpty()) {
      emptyView.setVisibility(View.VISIBLE);
    } else {
      emptyView.setVisibility(View.GONE);
    }
  }

  @Override public void showError(Throwable e, boolean pullToRefresh) {
    super.showError(e, pullToRefresh);
    if (!pullToRefresh) {
      emptyView.setVisibility(View.GONE);
    }
  }

  @Override public void showAddBlogDialog() {
    new AddBlogDialog().show(getSupportFragmentManager(), ADD_BLOG_DIALOG_TAG);
  }

  @Override public void setCurrentItem(int item) {
    contentView.setCurrentItem(item);
  }
}
