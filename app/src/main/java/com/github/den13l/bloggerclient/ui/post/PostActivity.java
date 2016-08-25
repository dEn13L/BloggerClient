package com.github.den13l.bloggerclient.ui.post;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.webkit.WebView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import butterknife.BindView;
import javax.inject.Inject;
import com.github.den13l.bloggerclient.BloggerApplication;
import com.github.den13l.bloggerclient.R;
import com.github.den13l.bloggerclient.model.Post;
import com.github.den13l.bloggerclient.ui.base.view.BaseMvpLceActivity;

public class PostActivity
    extends BaseMvpLceActivity<RelativeLayout, Post, PostMvp.View<Post>, PostMvp.Presenter<Post>>
    implements PostMvp.View<Post> {

  public static final String EXTRA_POST_ID = "com.github.den13l.bloggerclient.postId";

  @Inject PostPresenter presenter;

  @BindView(R.id.toolbar) Toolbar toolbar;
  @BindView(R.id.textPostTitle) TextView postTitleTextView;
  @BindView(R.id.webViewPostContent) WebView postContentWebView;

  private String postId;

  @Override protected void injectDependencies() {
    BloggerApplication.get(this).getPostComponent().inject(this);
  }

  @Override protected void clearDependencies() {
    BloggerApplication.get(this).clearPostComponent();
  }

  @Override public PostMvp.Presenter<Post> createPresenter() {
    return presenter;
  }

  @Override protected void onCreate(@Nullable Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_post);
    setupToolbar();

    postId = getIntent().getStringExtra(EXTRA_POST_ID);
    loadData(false);
  }

  private void setupToolbar() {
    setSupportActionBar(toolbar);
    //noinspection ConstantConditions
    getSupportActionBar().setTitle("");
    getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    toolbar.setNavigationOnClickListener(view -> finish());
  }

  @Override public boolean onCreateOptionsMenu(Menu menu) {
    getMenuInflater().inflate(R.menu.menu_post, menu);
    return true;
  }

  @Override public boolean onOptionsItemSelected(MenuItem item) {
    int id = item.getItemId();
    if (id == R.id.action_share) {
      presenter.sharePost();
      return true;
    } else if (id == R.id.action_open_in_browser) {
      presenter.openInBrowser();
      return true;
    }
    return super.onOptionsItemSelected(item);
  }

  @Override public void setData(Post post) {
    postTitleTextView.setText(post.getTitle());
    postContentWebView.loadData(post.getContent(), "text/html", null);
  }

  @Override public void loadData(boolean pullToRefresh) {
    presenter.loadPost(postId, pullToRefresh);
  }

  @Override public void sharePost(Post post) {
    Intent intent = new Intent(Intent.ACTION_SEND);
    String title = post.getTitle();
    String text = String.format("%s (%s)", post.getTitle(), post.getUrl());
    intent.putExtra(Intent.EXTRA_SUBJECT, title);
    intent.putExtra(Intent.EXTRA_TEXT, text);
    intent.setType("text/plain");

    startActivity(Intent.createChooser(intent, getString(R.string.action_share)));
  }

  @Override public void openUrl(String url) {
    startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(url)));
  }
}