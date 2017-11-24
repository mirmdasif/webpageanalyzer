package com.webpageanalyzer.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @since 11/24/17.
 */
@Controller
public class HomeController {

    @RequestMapping("/")
    String home() {
        return "home";
    }
}
