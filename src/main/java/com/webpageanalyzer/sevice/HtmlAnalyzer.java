package com.webpageanalyzer.sevice;

import com.webpageanalyzer.web.command.WebPageDetailsCmd;

public interface HtmlAnalyzer {
    WebPageDetailsCmd analyzeHtml(String html, String baseUrl);
}
