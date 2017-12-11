package com.webpageanalyzer.web.controller;

import com.webpageanalyzer.WebpageanalyzerApplication;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static com.webpageanalyzer.web.TestUtils.testGetRequestView;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;

/**
 * @since 11/24/17.
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = WebpageanalyzerApplication.class)
@AutoConfigureMockMvc
public class HomeControllerIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void onGetRequestReturnHomePage() throws Exception {
        testGetRequestView(mockMvc, "/", "form");
    }

    @Test
    public void onOnExceptionHandledInExceptionHandler() throws Exception {
        mockMvc.perform(post("/").param("url", "http://localhost:6523"))
                .andExpect(model().attributeExists("exception"));
    }
}
