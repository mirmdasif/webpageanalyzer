package com.webpageanalyzer.web.controller;

import org.junit.Test;
import org.springframework.test.web.servlet.MockMvc;

import static com.webpageanalyzer.web.TestUtils.testGetRequestView;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.standaloneSetup;

/**
 * @since 11/24/17.
 */
public class HomeControllerTest {

    @Test
    public void homeControllerTest() throws Exception {
        MockMvc mockMvc = standaloneSetup(new HomeController()).build();
        testGetRequestView(mockMvc, "/", "home");
    }
}
