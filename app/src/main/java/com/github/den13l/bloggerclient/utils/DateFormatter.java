package com.github.den13l.bloggerclient.utils;

import android.annotation.SuppressLint;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@SuppressLint("SimpleDateFormat") public class DateFormatter {

  private static final SimpleDateFormat DATE_FORMAT =
      new SimpleDateFormat("EEE, dd MMM yyyy, HH:mm");
  private static final SimpleDateFormat RFC3399_FORMAT_1 =
      new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'");
  private static final SimpleDateFormat RFC3399_FORMAT_2 =
      new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSSSS'Z'");
  private static final SimpleDateFormat RFC3399_FORMAT_3 =
      new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssZ");
  private static final SimpleDateFormat RFC3399_FORMAT_4 =
      new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSSSSZ");

  public static String getFormattedDate(String rfc3399Date) throws ParseException {
    return getFormattedDate(parseRFC3339Date(rfc3399Date));
  }

  private static String getFormattedDate(Date date) {
    return DATE_FORMAT.format(date.getTime());
  }

  private static Date parseRFC3339Date(String rfc3399Date) throws ParseException {
    Date date;
    if (rfc3399Date.endsWith("Z")) {
      try {
        date = RFC3399_FORMAT_1.parse(rfc3399Date);
      } catch (java.text.ParseException pe) {
        RFC3399_FORMAT_2.setLenient(true);
        date = RFC3399_FORMAT_2.parse(rfc3399Date);
      }
      return date;
    }

    // Step one, split off the timezone.
    String firstPart = rfc3399Date.substring(0, rfc3399Date.lastIndexOf('-'));
    String secondPart = rfc3399Date.substring(rfc3399Date.lastIndexOf('-'));

    // Step two, remove the colon from the timezone offset
    secondPart = secondPart.substring(0, secondPart.indexOf(':')) + secondPart.substring(
        secondPart.indexOf(':') + 1);
    rfc3399Date = firstPart + secondPart;
    try {
      date = RFC3399_FORMAT_3.parse(rfc3399Date);
    } catch (java.text.ParseException pe) {
      RFC3399_FORMAT_4.setLenient(true);
      date = RFC3399_FORMAT_4.parse(rfc3399Date);
    }
    return date;
  }
}
