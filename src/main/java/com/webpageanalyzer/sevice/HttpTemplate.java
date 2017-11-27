package com.webpageanalyzer.sevice;

import org.apache.http.HttpEntity;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class HttpTemplate {

    public String getHtml(String url) throws IOException {

        HttpGet request = new HttpGet(url);
        HttpClient httpClient = HttpClientBuilder.create().build();
        HttpEntity entity = httpClient.execute(request).getEntity();

        return EntityUtils.toString(entity);
    }
}
