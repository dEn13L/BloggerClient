package com.github.den13l.bloggerclient.ui.addblog;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import butterknife.BindView;
import butterknife.ButterKnife;
import javax.inject.Inject;
import com.github.den13l.bloggerclient.BloggerApplication;
import com.github.den13l.bloggerclient.R;
import com.github.den13l.bloggerclient.ui.base.view.BaseMvpDialog;

public class AddBlogDialog extends BaseMvpDialog<AddBlogMvp.View, AddBlogMvp.Presenter>
    implements AddBlogMvp.View {

  @Inject AddBlogPresenter presenter;

  @BindView(R.id.editTextBlogUrl) EditText blogUrlEditText;
  @BindView(R.id.viewLoading) View loadingView;
  @BindView(R.id.textSuccess) TextView successTextView;

  private static int dpToPx(Context context, float dp) {
    DisplayMetrics displayMetrics = context.getResources().getDisplayMetrics();
    return (int) ((dp * displayMetrics.density) + 0.5);
  }

  @Override public void injectDependencies() {
    BloggerApplication.get(getActivity()).getFragmentComponent().inject(this);
  }

  @Override protected void clearDependencies() {
    BloggerApplication.get(getActivity()).clearFragmentComponent();
  }

  @Override public AddBlogMvp.Presenter createPresenter() {
    return presenter;
  }

  @Override public void onCreate(@Nullable Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setCancelable(false);
  }

  @NonNull @SuppressLint("InflateParams") @Override
  public Dialog onCreateDialog(Bundle savedInstanceState) {
    LayoutInflater inflater = getActivity().getLayoutInflater();
    View view = inflater.inflate(R.layout.dialog_add_blog, null);
    ButterKnife.bind(this, view);

    AlertDialog dialog = new AlertDialog.Builder(getActivity()).setView(view)
        .setMessage(R.string.enter_blog_url)
        .setPositiveButton(android.R.string.ok, null)
        .setNegativeButton(android.R.string.cancel, null)
        .create();

    dialog.setOnShowListener(dialogInterface -> {
      dialog.getButton(AlertDialog.BUTTON_POSITIVE)
          .setOnClickListener(v -> presenter.addBlog(blogUrlEditText.getText().toString()));
    });

    return dialog;
  }

  @Override public void showLoading() {
    blogUrlEditText.setVisibility(View.GONE);
    blogUrlEditText.setError("");
    loadingView.setVisibility(View.VISIBLE);
    successTextView.setVisibility(View.GONE);
  }

  @Override public void showSuccess() {
    blogUrlEditText.setVisibility(View.GONE);
    blogUrlEditText.setError("");

    int translateDp = 40;
    // Not visible yet, so animate the view in
    AnimatorSet set = new AnimatorSet();
    ObjectAnimator contentFadeIn = ObjectAnimator.ofFloat(successTextView, "alpha", 0f, 1f);
    ObjectAnimator contentTranslateIn = ObjectAnimator.ofFloat(successTextView, "translationY",
        dpToPx(successTextView.getContext(), translateDp), 0);

    ObjectAnimator loadingFadeOut = ObjectAnimator.ofFloat(loadingView, "alpha", 1f, 0f);
    ObjectAnimator loadingTranslateOut = ObjectAnimator.ofFloat(loadingView, "translationY", 0,
        -dpToPx(loadingView.getContext(), translateDp));

    set.playTogether(contentFadeIn, contentTranslateIn, loadingFadeOut, loadingTranslateOut);
    set.setDuration(500);

    set.addListener(new AnimatorListenerAdapter() {

      @Override public void onAnimationStart(Animator animation) {
        successTextView.setTranslationY(0);
        loadingView.setTranslationY(0);
        successTextView.setVisibility(View.VISIBLE);
      }

      @Override public void onAnimationEnd(Animator animation) {
        loadingView.setVisibility(View.GONE);
        loadingView.setAlpha(1f); // For future showLoading calls
        successTextView.setTranslationY(0);
        loadingView.setTranslationY(0);

        detach();
      }
    });

    set.start();
  }

  @Override public void showError() {
    blogUrlEditText.setVisibility(View.VISIBLE);
    blogUrlEditText.setError(getString(R.string.error_add_blog));
    successTextView.setVisibility(View.GONE);
    loadingView.setVisibility(View.GONE);
  }

  @Override public void detach() {
    dismiss();
  }
}