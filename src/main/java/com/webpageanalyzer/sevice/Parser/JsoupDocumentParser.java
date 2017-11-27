package com.webpageanalyzer.sevice.Parser;

import com.webpageanalyzer.web.command.WebPageDetailsCmd;
import org.jsoup.nodes.Document;

public interface JsoupDocumentParser {
    void parse(Document document, WebPageDetailsCmd cmd);
}
