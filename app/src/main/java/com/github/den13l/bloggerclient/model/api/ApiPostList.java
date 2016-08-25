package com.github.den13l.bloggerclient.model.api;

import android.annotation.SuppressLint;
import com.google.gson.annotations.SerializedName;
import java.util.List;

/**
 * https://developers.google.com/blogger/docs/3.0/reference/posts/list
 *
 * Example:
 *

  {
    "kind": "blogger#postList",
    "nextPageToken": "CgkIChjg0MzK4CoQ64mAu4jExuBd",
    "items": [
      {
        "kind": "blogger#post",
        "id": "7925212461884461078",
        "blog": {
          "id": "6755709643044947179"
        },
        "published": "2016-08-10T12:00:00-07:00",
        "updated": "2016-08-10T12:07:25-07:00",
        "etag": "\"GtyIIQmNmmUjEA0nwhSqMElCJ1g/dGltZXN0YW1wOiAxNDcwODU2MDQ1OTYwCm9mZnNldDogLTI1MjAwMDAwCg\"",
        "url": "http://android-developers.blogspot.com/2016/08/new-features-for-reviews-and-experiments.html",
        "selfLink": "https://www.googleapis.com/blogger/v3/blogs/6755709643044947179/posts/7925212461884461078",
        "title": "New features for reviews and experiments in Google Play Developer Console app",
        "content": "<i><p>Posted by Kobi Glick, Google Play team</p></i>\n\n<p>\nWith over one million apps published through the Google Play Developer Console,\nwe know how important it is to publish with confidence, acquire users, learn\nabout them, and manage your business. Whether reacting to a critical performance\nissue or responding to a negative review, checking on your apps when and where\nyou need to is invaluable.\n</p>\n<p>\nThe <a\nhref=\"https://play.google.com/store/apps/details?id=com.google.android.apps.playconsole&hl=en\">Google\nPlay Developer Console app</a>, launched in May, has already helped thousands of\ndevelopers stay informed of crucial business updates on the go.\n</p>\n<p>\nWe’re excited to tell you about new features, available today:\n</p>\n<p>\n<strong>Receive notifications about new reviews </strong>\n</p>\n<p>\n<div class=\"separator\" style=\"clear: both; text-align: center;\"><a href=\"https://3.bp.blogspot.com/-H1M4sj0yDjg/V6tW79OrFqI/AAAAAAAADW4/E2pnnSoDkb8V0ko2cOQp8UOpX3Exmjx8QCLcB/s1600/image02.png\" imageanchor=\"1\" style=\"margin-left: 1em; margin-right: 1em;\"><img border=\"0\" src=\"https://3.bp.blogspot.com/-H1M4sj0yDjg/V6tW79OrFqI/AAAAAAAADW4/E2pnnSoDkb8V0ko2cOQp8UOpX3Exmjx8QCLcB/s400/image02.png\" width=\"225\" height=\"400\" /></a></div>\n</p>\n<p>\n<strong>Use filters to find the reviews you want</strong>\n</p>\n<p>\n<div class=\"separator\" style=\"clear: both; text-align: center;\"><a href=\"https://4.bp.blogspot.com/-XTlkWH9h-x8/V6tXHkWhfDI/AAAAAAAADW8/E2uW1QMTFwAFIBW-2m25OnIh2SsSeltfgCLcB/s1600/image00.png\" imageanchor=\"1\" style=\"margin-left: 1em; margin-right: 1em;\"><img border=\"0\" src=\"https://4.bp.blogspot.com/-XTlkWH9h-x8/V6tXHkWhfDI/AAAAAAAADW8/E2uW1QMTFwAFIBW-2m25OnIh2SsSeltfgCLcB/s400/image00.png\" width=\"225\" height=\"400\" /></a></div>\n</p>\n<p>\n<strong>Review and apply store listing experiment results</strong>\n</p>\n<p>\n<div class=\"separator\" style=\"clear: both; text-align: center;\"><a href=\"https://3.bp.blogspot.com/-5KCspvJo3ao/V6tXVCmGDrI/AAAAAAAADXA/Wv9rpvXckacP7A4Pog2wP3VqS638kyrwACLcB/s1600/image01.png\" imageanchor=\"1\" style=\"margin-left: 1em; margin-right: 1em;\"><img border=\"0\" src=\"https://3.bp.blogspot.com/-5KCspvJo3ao/V6tXVCmGDrI/AAAAAAAADXA/Wv9rpvXckacP7A4Pog2wP3VqS638kyrwACLcB/s400/image01.png\" width=\"225\" height=\"400\" /></a></div>\n</p>\n<p>\n<strong>Increase the percent of a staged rollout or halt a bad staged\nrollout</strong>\n</p>\n<p>\n<div class=\"separator\" style=\"clear: both; text-align: center;\"><a href=\"https://4.bp.blogspot.com/-4fg60nYDnDI/V6ta1-nFXOI/AAAAAAAADXQ/57p6c-B75_8teDPccVsvFWfAYbnp4AhVACLcB/s1600/image03.png\" imageanchor=\"1\" style=\"margin-left: 1em; margin-right: 1em;\"><img border=\"0\" src=\"https://4.bp.blogspot.com/-4fg60nYDnDI/V6ta1-nFXOI/AAAAAAAADXQ/57p6c-B75_8teDPccVsvFWfAYbnp4AhVACLcB/s400/image03.png\" width=\"225\" height=\"400\" /></a></div>\n</p>\n<p>\nDownload the <a\nhref=\"https://play.google.com/store/apps/details?id=com.google.android.apps.playconsole\">Developer\nConsole app on Google Play</a> and stay on top of your apps and games, wherever\nyou are! Also, <a href=\"http://g.co/play/playbook-androiddevblogposts-evergreen\">get the Playbook for Developers app</a> to stay up-to-date with more features and best practices that will help you grow a successful business on Google Play.\n</p>",
        "author": {
          "id": "g113601876824575481275",
          "displayName": "Reto Meier",
          "url": "https://www.blogger.com/profile/08588467489110681140",
          "imageUrl": {
            "url": "//lh6.googleusercontent.com/--nC4_9HC4lI/AAAAAAAAAAI/AAAAAAAACg8/wJiYzkAyzlQ/s35-c/photo.jpg"
          }
        },
        "replies": {
          "totalItems": "0",
          "selfLink": "https://www.googleapis.com/blogger/v3/blogs/6755709643044947179/posts/7925212461884461078/comments"
        },
        "labels": [
          "Android",
          "Android Developer",
          "Google Play"
        ]
      }
    ]
  }

 */
public class ApiPostList {

  @SerializedName("nextPageToken") public String nextPageToken;
  @SerializedName("items") public List<ApiPost> posts;

  @SuppressLint("DefaultLocale") @Override public String toString() {
    return String.format("ApiPostList {nextPageToken='%s', posts='%s'}", nextPageToken, posts);
  }
}