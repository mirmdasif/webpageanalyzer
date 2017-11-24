package com.webpageanalyzer.web.controller;

import org.junit.Test;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.standaloneSetup;

/**
 * @since 11/24/17.
 */
public class HomeControllerTest {

    @Test
    public void homeControllerTest() throws Exception {
        MockMvc mockMvc = standaloneSetup(new HomeController()).build();
        mockMvc.perform(get("/")).andExpect(view().name("home"));
    }
}
