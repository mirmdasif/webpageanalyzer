package com.webpageanalyzer.web.controller;

import com.webpageanalyzer.sevice.HtmlAnalyzer;
import com.webpageanalyzer.sevice.HttpTemplate;
import com.webpageanalyzer.exceptions.UrlProcessingException;
import com.webpageanalyzer.web.command.WebPageDetailsCmd;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.io.IOException;

/**
 * @since 11/24/17.
 */
@Controller
public class HomeController {

    static final String CMD_NAME = "webPageDetailsCmd";

    static final String VIEW_NAME = "form";

    @Autowired
    private HttpTemplate httpTemplate;

    @Autowired
    private HtmlAnalyzer htmlAnalyzer;

    @GetMapping("/")
    public String form(WebPageDetailsCmd webPageDetailsCmd) {
        return VIEW_NAME;
    }

    @PostMapping("/")
    public String processUrl(@Valid @ModelAttribute(CMD_NAME) WebPageDetailsCmd webPageDetailsCmd,
                             BindingResult result,
                             ModelMap model)

            throws IOException, UrlProcessingException {

        if(!result.hasErrors()) {
            String html = httpTemplate.getHtml(webPageDetailsCmd.getUrl());
            model.put(CMD_NAME, htmlAnalyzer.analyzeHtml(html, webPageDetailsCmd.getUrl()));
        }

        return VIEW_NAME;
    }

    @ExceptionHandler(Exception.class)
    public ModelAndView error(Exception ex) {
        System.out.println("Forwarding error to " + VIEW_NAME );

        ModelAndView model = new ModelAndView(VIEW_NAME);
        model.addObject(CMD_NAME, new WebPageDetailsCmd());
        model.addObject("exception", ex.getMessage());

        return model;
    }
}
