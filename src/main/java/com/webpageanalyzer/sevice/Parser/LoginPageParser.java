package com.webpageanalyzer.sevice.Parser;

import com.webpageanalyzer.web.command.WebPageDetailsCmd;
import org.jsoup.nodes.Document;
import org.springframework.stereotype.Component;

@Component
public class LoginPageParser implements JsoupDocumentParser {
    @Override
    public void parse(Document document, WebPageDetailsCmd cmd) {
        cmd.setLoginPage(hasLoginPage(document));
    }

    private boolean hasLoginPage(Document document) {
        return document.select("form")
                .stream()
                .filter(form -> (form.select("input[type=password]").size() > 0 &&
                        (form.select("input[type=text]").size() > 0
                                || form.select("input[type=email]").size() > 0)))
                .count() > 0;
    }
}
