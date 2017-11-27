package com.webpageanalyzer.sevice;

import org.junit.Assert;
import org.junit.Test;

public class JsoupHtmlAnalyzerTest {

    HtmlAnalyzer htmlAnalyzer = new JsoupHtmlAnalyzer();

    @Test
    public void testHtml4Version() {
        String html = "<!DOCTYPE HTML PUBLIC \"-//W3C//DTD HTML 4.01//EN\"\n" +
                "        \"http://www.w3.org/TR/html4/strict.dtd\">\n" +
                "\n" +
                "<html>\n" +
                "\n" +
                "<head>\n" +
                "\n" +
                "<title>Conforming HTML 4.01 Strict Template</title>\n" +
                "\n" +
                "</head>\n" +
                "\n" +
                "<body>\n" +
                "\n" +
                "</body>\n" +
                "</html>";

        Assert.assertEquals("-//W3C//DTD HTML 4.01//EN", htmlAnalyzer.analyzeHtml(html,"example.com").getHtmlVersion());
    }
}