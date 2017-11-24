package com.webpageanalyzer.web.controller;

import com.webpageanalyzer.WebpageanalyzerApplication;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

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
        mockMvc.perform(get("/"))
                .andExpect(view().name("home"));
    }
}
