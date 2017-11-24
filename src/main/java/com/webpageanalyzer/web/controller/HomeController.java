package com.webpageanalyzer.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @since 11/24/17.
 */
@Controller
public class HomeController {

    @GetMapping("/")
    String home() {
        return "home";
    }
}
