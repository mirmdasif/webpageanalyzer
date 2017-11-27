package com.webpageanalyzer.sevice.Parser;

import com.webpageanalyzer.WebpageanalyzerApplication;
import com.webpageanalyzer.web.command.WebPageDetailsCmd;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = WebpageanalyzerApplication.class)
public class HtmlVersionParserTest {

    @Autowired
    HtmlVersionParser htmlVersionParser;

    @Test
    public void testHtmlVersionPopulation() {

        WebPageDetailsCmd cmd = new WebPageDetailsCmd();

        Document document = Jsoup.parse("<!DOCTYPE html>\n" +
                "<html>\n" +
                "<head>\n" +
                "<meta charset=\"UTF-8\">\n" +
                "<title>Title of the document</title>\n" +
                "</head>\n" +
                "\n" +
                "<body>\n" +
                "Content of the document......\n" +
                "</body>\n" +
                "\n" +
                "</html>");


        htmlVersionParser.parse(document, cmd);

        assertEquals("HTML 5", cmd.getHtmlVersion());
    }
}