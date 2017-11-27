package com.webpageanalyzer.web.command;

import com.webpageanalyzer.web.enums.Headings;
import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.URL;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class WebPageDetailsCmd {

    @NotBlank
    @URL
    private String url;

    private String htmlVersion;
    private String title;
    private Map<Headings, Integer> headers;
    private boolean loginPage;
    private List<String> links;
    private List<String> sameDomainLinks = new ArrayList<>();
    private List<String> externalLinks = new ArrayList<>();

    private List<LinkDetails> linkDetails = new ArrayList<>();

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getHtmlVersion() {
        return htmlVersion;
    }

    public void setHtmlVersion(String htmlVersion) {
        this.htmlVersion = htmlVersion;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Map<Headings, Integer> getHeaders() {
        return headers;
    }

    public void setHeaders(Map<Headings, Integer> headers) {
        this.headers = headers;
    }

    public boolean isLoginPage() {
        return loginPage;
    }

    public void setLoginPage(boolean loginPage) {
        this.loginPage = loginPage;
    }

    public List<String> getLinks() {
        return links;
    }

    public void setLinks(List<String> links) {
        this.links = links;
    }

    public List<String> getSameDomainLinks() {
        return sameDomainLinks;
    }

    public void setSameDomainLinks(List<String> sameDomainLinks) {
        this.sameDomainLinks = sameDomainLinks;
    }

    public List<String> getExternalLinks() {
        return externalLinks;
    }

    public void setExternalLinks(List<String> externalLinks) {
        this.externalLinks = externalLinks;
    }

    public List<LinkDetails> getLinkDetails() {
        return linkDetails;
    }

    public void setLinkDetails(List<LinkDetails> linkDetails) {
        this.linkDetails = linkDetails;
    }

    @Override
    public String toString() {
        return "WebPageDetailsCmd{" +
                "url='" + url + '\'' +
                ", htmlVersion='" + htmlVersion + '\'' +
                ", title='" + title + '\'' +
                '}';
    }
}
