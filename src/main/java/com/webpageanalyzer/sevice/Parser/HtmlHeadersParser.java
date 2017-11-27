package com.webpageanalyzer.sevice.Parser;

import com.webpageanalyzer.web.command.WebPageDetailsCmd;
import com.webpageanalyzer.web.enums.Headings;
import org.jsoup.nodes.Document;
import org.springframework.stereotype.Component;

import java.util.LinkedHashMap;
import java.util.Map;

@Component
public class HtmlHeadersParser implements JsoupDocumentParser {

    @Override
    public void parse(Document document, WebPageDetailsCmd cmd) {
        cmd.setHeaders(getHeadingsCountByGroup(document));
    }

    private Map<Headings, Integer> getHeadingsCountByGroup(Document document) {
        Map<Headings, Integer> headingCounts = new LinkedHashMap<>();
        for (Headings heading : Headings.values()) {
            headingCounts.put(heading, document.select(heading.name()).size());
        }

        return headingCounts;
    }
}
