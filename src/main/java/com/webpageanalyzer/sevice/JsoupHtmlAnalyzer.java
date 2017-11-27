package com.webpageanalyzer.sevice;

import com.webpageanalyzer.sevice.Parser.HtmlTitleParser;
import com.webpageanalyzer.sevice.Parser.HtmlVersionParser;
import com.webpageanalyzer.utils.UrlUtils;
import com.webpageanalyzer.web.command.LinkDetails;
import com.webpageanalyzer.web.command.WebPageDetailsCmd;
import com.webpageanalyzer.web.enums.Headings;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.*;
import java.util.function.Consumer;
import java.util.stream.Collectors;

@Service
public class JsoupHtmlAnalyzer implements HtmlAnalyzer {

    @Autowired
    private HttpTemplate httpTemplate;

    @Autowired
    private HtmlVersionParser versionParser;

    @Autowired
    private HtmlTitleParser titleParser;

    @Override
    public WebPageDetailsCmd analyzeHtml(String html, String baseUrl) {
        WebPageDetailsCmd cmd = new WebPageDetailsCmd();

        Document document = Jsoup.parse(html, baseUrl);
        cmd.setUrl(baseUrl);
        versionParser.parse(document, cmd);
        titleParser.parse(document, cmd);
        cmd.setHeaders(getHeadingsCountByGroup(document));
        cmd.setLoginPage(hasLoginPage(document));
        List<String> urls = getHyperMediaLinks(document);
        cmd.setLinks(urls);

        cmd.setSameDomainLinks(urls.
                stream().
                filter(str -> UrlUtils.isSameDomainUrl(str, document.baseUri())).collect(Collectors.toList()));

        cmd.setExternalLinks(urls.
                stream().
                filter(str -> !UrlUtils.isSameDomainUrl(str, document.baseUri())).collect(Collectors.toList()));

        cmd.setLinkDetails(getLinkDetails(urls));

        return cmd;
    }

    Map<Headings, Integer> getHeadingsCountByGroup(Document document) {
        Map<Headings, Integer> headingCounts = new LinkedHashMap<>();
        for (Headings heading : Headings.values()) {
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
            if (!StringUtils.isEmpty(href)) {
                links.add(href);
            }
        });

        return links;
    }

    List<LinkDetails> getLinkDetails(List<String> links) {
        List<LinkDetails> linkDetails = new ArrayList<>(links.size());
        links.parallelStream().forEach(new Consumer<String>() {

            @Override
            public void accept(String url) {
                int code = httpTemplate.getStatusCode(url);

                linkDetails.add(new LinkDetails(url, code >= 200 && code < 400 , code));
            }
        });

        return linkDetails;
    }
}
