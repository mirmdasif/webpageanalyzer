package com.webpageanalyzer.sevice;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.IOException;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class HttpTemplateTest {

    @Autowired
    private HttpTemplate httpTemplate;

    @Test
    public void tetGetHtml() throws IOException {
        assertNotNull(httpTemplate.getHtml("http://wwww.github.com"));
    }
}