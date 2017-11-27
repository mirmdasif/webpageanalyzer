package com.webpageanalyzer.sevice.Parser;

import com.webpageanalyzer.web.command.WebPageDetailsCmd;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.DocumentType;
import org.jsoup.nodes.Node;
import org.springframework.stereotype.Component;

@Component
public class HtmlVersionParser implements JsoupDocumentParser {
    @Override
    public void parse(Document document, WebPageDetailsCmd cmd) {
        cmd.setHtmlVersion(getHtmlVersion(document));
    }

    private String getHtmlVersion(Document document) {
        for (Node node : document.childNodes()) {
            if (node instanceof DocumentType) {
                String version = "";
                DocumentType documentType = (DocumentType) node;
                version = documentType.attr("publicid");
                return version.equals("") ? "HTML 5" : version;
            }
        }

        return "";
    }
}
