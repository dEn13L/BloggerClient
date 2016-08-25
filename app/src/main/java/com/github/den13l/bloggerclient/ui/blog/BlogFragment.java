package com.github.den13l.bloggerclient.ui.blog;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import butterknife.BindView;
import java.util.List;
import javax.inject.Inject;
import com.github.den13l.bloggerclient.BloggerApplication;
import com.github.den13l.bloggerclient.R;
import com.github.den13l.bloggerclient.model.Blog;
import com.github.den13l.bloggerclient.model.Post;
import com.github.den13l.bloggerclient.ui.base.view.BaseRefreshFragment;
import com.github.den13l.bloggerclient.ui.post.PostActivity;
import timber.log.Timber;

public class BlogFragment
    extends BaseRefreshFragment<List<Post>, BlogMvp.View<List<Post>>, BlogMvp.Presenter<List<Post>>>
    implements BlogMvp.View<List<Post>>, BlogAdapter.PostClickListener {

  public static final String EXTRA_BLOG_ID = "com.github.den13l.bloggerclient.blogId";

  @Inject BlogPresenter presenter;

  @BindView(R.id.viewEmpty) View emptyView;
  @BindView(R.id.recyclerView) RecyclerView recyclerView;

  private BlogAdapter adapter;

  public static BlogFragment newInstance(Blog blog) {
    BlogFragment fragment = new BlogFragment();
    Bundle bundle = new Bundle();
    bundle.putString(EXTRA_BLOG_ID, blog.getId());
    fragment.setArguments(bundle);

    return fragment;
  }

  @Override public void injectDependencies() {
    BloggerApplication.get(getContext()).getFragmentComponent().inject(this);
  }

  @Override protected void clearDependencies() {
    BloggerApplication.get(getContext()).clearFragmentComponent();
  }

  @Override public BlogMvp.Presenter<List<Post>> createPresenter() {
    return presenter;
  }

  @Override public int getLayoutRes() {
    return R.layout.fragment_main;
  }

  @Override public void onCreate(@Nullable Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    adapter = new BlogAdapter(this);
    initBlogId();
  }

  private void initBlogId() {
    String blogId = getArguments().getString(EXTRA_BLOG_ID);
    presenter.setBlogId(blogId);
  }

  @Override public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
    super.onViewCreated(view, savedInstanceState);
    LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
    recyclerView.setLayoutManager(layoutManager);
    recyclerView.setAdapter(adapter);
    recyclerView.addOnScrollListener(new EndlessRecyclerViewScrollListener(layoutManager) {
      @Override public void onLoadMore(int page, int totalItemsCount) {
        Timber.i("Load more");
        loadMore();
      }
    });
  }

  @Override public void onResume() {
    super.onResume();
    loadData(false);
  }

  private void loadMore() {
    if (!adapter.isShowLoadMoreProgress()) {
      Timber.i("setShowLoadMoreProgress");
      adapter.setShowLoadMoreProgress(true);
      adapter.notifyItemInserted(adapter.getPosts().size());
      presenter.loadMorePosts();
    }
  }

  @Override public void setData(List<Post> posts) {
    adapter.setShowLoadMoreProgress(false);
    adapter.setPosts(posts);
    adapter.notifyDataSetChanged();
  }

  @Override public void loadData(boolean pullToRefresh) {
    presenter.loadPosts(pullToRefresh);
  }

  @Override public void showPullToRefreshLoading() {
    contentView.setRefreshing(true);
  }

  @Override public void showLoading(boolean pullToRefresh) {
    super.showLoading(pullToRefresh);
    if (!pullToRefresh) {
      emptyView.setVisibility(View.GONE);
    }
  }

  @Override public void showContent() {
    super.showContent();
    if (adapter.getPosts() == null || adapter.getPosts().isEmpty()) {
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

  @Override public void onPostClicked(String postId) {
    Intent intent = new Intent(getContext(), PostActivity.class);
    intent.putExtra(PostActivity.EXTRA_POST_ID, postId);
    getContext().startActivity(intent);
  }
}
