package com.webpageanalyzer.config;

import org.apache.http.client.config.RequestConfig;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Although Jsoup can fetch html but will using HTTP Client for it's connection pooling feature
 */
@Configuration
public class HttpConfig {

    @Bean
    public CloseableHttpClient closeableHttpClient() {

        return HttpClientBuilder.create()
                .setConnectionManager(new PoolingHttpClientConnectionManager())
                .setDefaultRequestConfig(RequestConfig.custom()
                        .setConnectionRequestTimeout(2000).build())
                .build();
    }
}
