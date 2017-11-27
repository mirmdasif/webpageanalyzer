package com.webpageanalyzer.sevice;

import com.webpageanalyzer.utils.UrlUtils;
import com.webpageanalyzer.web.command.WebPageDetailsCmd;
import com.webpageanalyzer.web.enums.Headings;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.DocumentType;
import org.jsoup.nodes.Node;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class JsoupHtmlAnalyzer implements HtmlAnalyzer {

    @Override
    public WebPageDetailsCmd analyzeHtml(String html, String baseUrl) {
        WebPageDetailsCmd cmd = new WebPageDetailsCmd();

        Document document = Jsoup.parse(html, baseUrl);
        cmd.setHtmlVersion(getHtmlVersion(document));
        cmd.setTitle(getTitle(document));
        cmd.setHeaders(getHeadingsCountByGroup(document));
        cmd.setLoginPage(hasLoginPage(document));
        List<String> urls = getHyperMediaLinks(document);
        cmd.setLinks(urls);

        cmd.setSameDomainlinks(urls.
                stream().
                filter(str -> UrlUtils.isSameDomainUrl(str, document.baseUri())).collect(Collectors.toList()));

        cmd.setExternalLinks(urls.
                stream().
                filter(str -> !UrlUtils.isSameDomainUrl(str, document.baseUri())).collect(Collectors.toList()));

        return cmd;
    }

    private String getHtmlVersion(Document document) {
        for (Node node : document.childNodes()) {
            if (node instanceof DocumentType) {
                DocumentType documentType = (DocumentType) node;
                String version = documentType.attr("publicid");

                return version.equals("") ? "HTML 5" : version;
            }
        }

        return "";
    }

    String getTitle(Document document) {
        return document.title();
    }

    Map<Headings, Integer> getHeadingsCountByGroup(Document document) {
        Map<Headings, Integer> headingCounts = new LinkedHashMap<>();
        for(Headings heading : Headings.values()) {
            headingCounts.put(heading, document.select(heading.name()).size());
        }

        return headingCounts;
    }

    boolean hasLoginPage(Document document) {
        return document.select("form")
                .stream()
                .filter(form -> form.select("input[type=text]").size() > 0
                        && form.select("input[type=password]").size() > 0)
                .count() > 0;
    }

    List<String> getHyperMediaLinks(Document document) {
        List<String> links = new LinkedList<>();
        document.select("a[href]").forEach(link -> {
            String href = link.attr("abs:href");
            if(!StringUtils.isEmpty(href)) {
                links.add(href);
            }
        });

        return links;
    }
}