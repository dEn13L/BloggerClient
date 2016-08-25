package com.github.den13l.bloggerclient.ui.main;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import java.util.List;
import com.github.den13l.bloggerclient.model.Blog;
import com.github.den13l.bloggerclient.ui.blog.BlogFragment;

class MainPagerAdapter extends FragmentStatePagerAdapter {

  private List<Blog> blogs;

  MainPagerAdapter(FragmentManager fm) {
    super(fm);
  }

  MainPagerAdapter(FragmentManager fm, List<Blog> blogs) {
    super(fm);
    this.blogs = blogs;
  }

  @Override public int getCount() {
    return blogs != null ? blogs.size() : 0;
  }

  @Override public Fragment getItem(int position) {
    return BlogFragment.newInstance(blogs.get(position));
  }

  @Override public CharSequence getPageTitle(int position) {
    return blogs.get(position).getName();
  }

  List<Blog> getBlogs() {
    return blogs;
  }

  void setBlogs(List<Blog> blogs) {
    this.blogs = blogs;
  }
}
