package com.webpageanalyzer.web.command;

public class LinkDetails {
    private String url;
    private boolean reachable;
    private int statusCode;

    public LinkDetails(String url, boolean reachable, int statusCode) {
        this.url = url;
        this.statusCode = statusCode;
        this.reachable = reachable;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public int getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(int statusCode) {
        this.statusCode = statusCode;
    }

    public boolean isReachable() {
        return reachable;
    }

    public void setReachable(boolean reachable) {
        this.reachable = reachable;
    }
}
