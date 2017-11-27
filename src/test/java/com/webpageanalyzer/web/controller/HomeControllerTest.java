package com.webpageanalyzer.web.controller;

import com.webpageanalyzer.exceptions.UrlProcessingException;
import com.webpageanalyzer.sevice.HtmlAnalyzer;
import com.webpageanalyzer.sevice.HttpTemplate;
import com.webpageanalyzer.web.command.WebPageDetailsCmd;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.internal.matchers.InstanceOf;
import org.springframework.test.web.servlet.MockMvc;

import java.io.IOException;

import static com.webpageanalyzer.web.TestUtils.testGetRequestView;
import static com.webpageanalyzer.web.controller.HomeController.CMD_NAME;
import static com.webpageanalyzer.web.controller.HomeController.VIEW_NAME;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.standaloneSetup;

/**
 * @since 11/24/17.
 */
public class HomeControllerTest {

    MockMvc mockMvc;

    @InjectMocks
    HomeController controller;

    @Mock
    HttpTemplate template;

    @Mock
    private HtmlAnalyzer htmlAnalyzer;

    @Before
    public void setup() throws IOException {
        MockitoAnnotations.initMocks(this);
        mockMvc = standaloneSetup(controller).build();
        when(template.getHtml(any(String.class))).thenReturn("<h1>Abc<h2>");
        when(htmlAnalyzer.analyzeHtml(any(String.class), any(String.class))).thenReturn(new WebPageDetailsCmd());
    }


    @Test
    public void homeControllerTest() throws Exception {

        testGetRequestView(mockMvc, "/", VIEW_NAME);
        mockMvc.perform(post("/").requestAttr(CMD_NAME, new WebPageDetailsCmd()))
                .andExpect(view().name(VIEW_NAME))
                .andExpect(model().attribute(CMD_NAME, new InstanceOf(WebPageDetailsCmd.class)));
    }

    @Test
    public void onEmptyUrlSubmitShowError() throws Exception {
        mockMvc.perform(post("/"))
                .andExpect(model().hasErrors());
    }

    @Test
    public void onInvalidUrlShowError() throws Exception {
        mockMvc.perform(post("/").param("url", "abc.com"))
                .andExpect(model().errorCount(1));
    }
}
