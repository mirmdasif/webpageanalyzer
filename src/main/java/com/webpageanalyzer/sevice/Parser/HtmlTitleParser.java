package com.webpageanalyzer.sevice.Parser;

import com.webpageanalyzer.web.command.WebPageDetailsCmd;
import org.jsoup.nodes.Document;
import org.springframework.stereotype.Component;

@Component
public class HtmlTitleParser implements JsoupDocumentParser {

    @Override
    public void parse(Document document, WebPageDetailsCmd cmd) {
        cmd.setTitle(getTitle(document));
    }

    private String getTitle(Document document) {
        return document.title();
    }
}
