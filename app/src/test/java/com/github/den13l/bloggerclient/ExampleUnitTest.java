package com.github.den13l.bloggerclient;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ExampleUnitTest {
    @Test
    public void addition_isCorrect() throws Exception {
        assertEquals(4, 2 + 2);
    }

    @Test
    public void replaceText() {
        String content = "<p>Paragraph</p><img src='img1'/><h1>Test</h1><img src='img2'></img>";
        String result = content.replaceAll("(?i)<img[^>]*>|</img>", "");
        System.out.println("result: " + result);
    }
}