package com.github.den13l.bloggerclient.ui.blog;

import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import butterknife.BindView;
import butterknife.ButterKnife;
import java.text.ParseException;
import java.util.List;
import com.github.den13l.bloggerclient.R;
import com.github.den13l.bloggerclient.model.Post;
import com.github.den13l.bloggerclient.utils.DateFormatter;

class BlogAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

  private static final int TYPE_ITEM = 0;
  private static final int TYPE_LOAD_MORE_PROGRESS = 1;

  private List<Post> posts;
  private PostClickListener postClickListener;
  private boolean showLoadMoreProgress;

  BlogAdapter(PostClickListener postClickListener, List<Post> posts) {
    this.postClickListener = postClickListener;
    this.posts = posts;
  }

  BlogAdapter(PostClickListener postClickListener) {
    this.postClickListener = postClickListener;
  }

  List<Post> getPosts() {
    return posts;
  }

  void setPosts(List<Post> posts) {
    this.posts = posts;
  }

  public boolean isShowLoadMoreProgress() {
    return showLoadMoreProgress;
  }

  public void setShowLoadMoreProgress(boolean showLoadMoreProgress) {
    this.showLoadMoreProgress = showLoadMoreProgress;
  }

  @Override public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
    LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
    if (viewType == TYPE_ITEM) {
      View view = layoutInflater.inflate(R.layout.item_post, parent, false);
      return new PostViewHolder(view);
    } else if (viewType == TYPE_LOAD_MORE_PROGRESS) {
      View view = layoutInflater.inflate(R.layout.item_load_more, parent, false);
      return new LoadMoreViewHolder(view);
    }
    return null;
  }

  @Override public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
    if (holder instanceof PostViewHolder) {
      PostViewHolder postViewHolder = ((PostViewHolder) holder);
      Post post = posts.get(position);
      postViewHolder.titleTextView.setText(post.getTitle());
      String content = post.getContent();
      content = content.replaceAll("(?i)<img[^>]*>|</img>", "");
      postViewHolder.contentTextView.setText(Html.fromHtml(content));
      try {
        postViewHolder.dateTextView.setText(DateFormatter.getFormattedDate(post.getPublished()));
      } catch (ParseException ignored) {
      }
      postViewHolder.postAuthorTextView.setText(post.getAuthor().getDisplayName());
      int commentsCount = post.getComments() != null ? post.getComments().size() : 0;
      postViewHolder.postCommentsCountTextView.setText(String.valueOf(commentsCount));

      holder.itemView.setOnClickListener(view -> postClickListener.onPostClicked(post.getId()));
    }
  }

  @Override public int getItemCount() {
    int count = 0;
    if (showLoadMoreProgress) {
      count++;
    }
    if (posts != null) {
      count += posts.size();
    }
    return count;
  }

  @Override public int getItemViewType(int position) {
    int type = TYPE_ITEM;
    if (showLoadMoreProgress && (posts != null && position >= posts.size())) {
      type = TYPE_LOAD_MORE_PROGRESS;
    }
    return type;
  }

  interface PostClickListener {
    void onPostClicked(String postId);
  }

  class PostViewHolder extends RecyclerView.ViewHolder {

    @BindView(R.id.textPostTitle) TextView titleTextView;
    @BindView(R.id.textPostContent) TextView contentTextView;
    @BindView(R.id.textPostDate) TextView dateTextView;
    @BindView(R.id.textPostAuthor) TextView postAuthorTextView;
    @BindView(R.id.textPostCommentsCount) TextView postCommentsCountTextView;

    PostViewHolder(View itemView) {
      super(itemView);
      ButterKnife.bind(this, itemView);
    }
  }

  class LoadMoreViewHolder extends RecyclerView.ViewHolder {

    public LoadMoreViewHolder(View itemView) {
      super(itemView);
    }
  }
}
