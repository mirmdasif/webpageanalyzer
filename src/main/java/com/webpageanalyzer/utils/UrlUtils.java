package com.webpageanalyzer.utils;

import com.google.common.net.InternetDomainName;

import java.net.URI;

public class UrlUtils {

    public static boolean isSameDomainUrl(String  url1, String url2) {
        try {
            URI uri = new URI(url1);
            URI uri2 = new URI(url2);

           return InternetDomainName.from(uri.getHost()).topPrivateDomain()
                    .equals(InternetDomainName.from(uri2.getHost()).topPrivateDomain());

        } catch (Exception e) {
            e.printStackTrace();
        }

        return false;
    }
}
