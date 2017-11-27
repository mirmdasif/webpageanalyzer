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

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = WebpageanalyzerApplication.class)
public class LoginPageParserTest {

    @Autowired
    private LoginPageParser loginPageParser;

    @Test
    public void testLoginPage() {
        WebPageDetailsCmd cmd = new WebPageDetailsCmd();

        Document document = Jsoup.parse("<form id=\"login-form\" action=\"/users/login?ssrc=head&amp;returnurl=https%3a%2f%2fstackoverflow.com%2f\" method=\"POST\">\n" +
                "                    <input type=\"email\" name=\"email\" id=\"email\" size=\"30\" maxlength=\"100\" placeholder=\"you@example.org\"><br>\n" +
                "                    <label for=\"password\">Password</label><br>\n" +
                "                    <input type=\"password\" name=\"password\" id=\"password\" placeholder=\"********\"><br>\n" +
                "                </div>\n" +
                "            </form>");

        loginPageParser.parse(document, cmd);

        assertTrue(cmd.isLoginPage());
    }
}