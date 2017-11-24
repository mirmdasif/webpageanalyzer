package com.webpageanalyzer.web;

import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

/**
 * @since 11/24/17.
 */
public class TestUtils {
    public static void testGetRequestView(MockMvc mockMvc,
                                          String requestUrl,
                                          String viewName) throws Exception {
        mockMvc.perform(get(requestUrl))
                .andExpect(view().name(viewName));
    }
}
