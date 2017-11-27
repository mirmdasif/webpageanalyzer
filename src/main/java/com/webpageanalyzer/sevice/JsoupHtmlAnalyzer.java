package com.webpageanalyzer.sevice;

import com.webpageanalyzer.sevice.Parser.JsoupDocumentParser;
import com.webpageanalyzer.utils.UrlUtils;
import com.webpageanalyzer.web.command.LinkDetails;
import com.webpageanalyzer.web.command.WebPageDetailsCmd;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class JsoupHtmlAnalyzer implements HtmlAnalyzer {

    @Autowired
    private HttpTemplate httpTemplate;

    @Autowired
    List<JsoupDocumentParser> parsers;

    @Override
    public WebPageDetailsCmd analyzeHtml(String html, String baseUrl) {
        WebPageDetailsCmd cmd = new WebPageDetailsCmd();

        Document document = Jsoup.parse(html, baseUrl);
        cmd.setUrl(baseUrl);

        for (JsoupDocumentParser parser : parsers) {
            parser.parse(document, cmd);
        }

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
        links.parallelStream().forEach(url -> {
            int code = httpTemplate.getStatusCode(url);

            linkDetails.add(new LinkDetails(url, code >= 200 && code < 400, code));
        });

        return linkDetails;
    }
}
