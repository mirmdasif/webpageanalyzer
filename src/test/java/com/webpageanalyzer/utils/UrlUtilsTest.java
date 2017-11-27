package com.webpageanalyzer.utils;

import org.junit.Assert;
import org.junit.Test;


public class UrlUtilsTest {

    @Test
    public void testSameDomain() {

        Assert.assertTrue(UrlUtils.isSameDomainUrl("https://stackoverflow.com/jobs?med=site-ui&ref=jobs-tab",
                "https://stackoverflow.com/users"));
        Assert.assertTrue(UrlUtils.isSameDomainUrl("https://stackoverflow.com/jobs?med=site-ui&ref=jobs-tab",
                "https://chat.stackoverflow.com"));

    }

    @Test
    void testDifferentDomain() {
        Assert.assertFalse(UrlUtils.isSameDomainUrl("https://stackoverflow.com/jobs?med=site-ui&ref=jobs-tab",
                "https://chat.stackexchange.com"));
    }
}