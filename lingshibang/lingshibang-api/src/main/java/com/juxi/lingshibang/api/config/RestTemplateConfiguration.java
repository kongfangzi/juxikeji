package com.juxi.lingshibang.api.config;

import com.alibaba.fastjson.support.spring.FastJsonHttpMessageConverter;
import org.apache.http.client.HttpClient;
import org.apache.http.impl.client.DefaultConnectionKeepAliveStrategy;
import org.apache.http.impl.client.DefaultHttpRequestRetryHandler;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.web.client.DefaultResponseErrorHandler;
import org.springframework.web.client.RestOperations;
import org.springframework.web.client.RestTemplate;

import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 */
@Configuration
@ConfigurationProperties(prefix = "rest.connection")
public class RestTemplateConfiguration {
    private static final Logger logger = LoggerFactory.getLogger(RestTemplateConfiguration.class);

    @Value("${rest.connection.connectionRequestTimeout}")
    private Integer connectionRequestTimeout;
    @Value("${rest.connection.connectTimeout}")
    private Integer connectTimeout;
    @Value("${rest.connection.socketTimeout}")
    private Integer socketTimeout;

    @Bean
    @ConditionalOnMissingBean({RestOperations.class, RestTemplate.class})
    public RestTemplate customRestTemplate() {

        // 添加内容转换器
        List<HttpMessageConverter<?>> messageConverters = new ArrayList<>();
        FastJsonHttpMessageConverter fastJsonHttpMessageConverter = new FastJsonHttpMessageConverter();
        fastJsonHttpMessageConverter.setSupportedMediaTypes(
                Collections.singletonList(MediaType.APPLICATION_JSON_UTF8));

//        messageConverters.add(new FormHttpMessageConverter());
//        messageConverters.add(new MarshallingHttpMessageConverter());
//        messageConverters.add(new ByteArrayHttpMessageConverter());
        messageConverters.add(fastJsonHttpMessageConverter);
        messageConverters.add(new StringHttpMessageConverter(Charset.forName("UTF-8")));

        RestTemplate restTemplate = new RestTemplate(messageConverters);
        restTemplate.setRequestFactory(customHttpRequestFactory());
        restTemplate.setErrorHandler(new DefaultResponseErrorHandler());
        logger.info("RestClient INIT DONE");
        return restTemplate;
    }

    @Bean
    public HttpComponentsClientHttpRequestFactory customHttpRequestFactory() {
        // 长连接保持30秒
        PoolingHttpClientConnectionManager pollingConnectionManager =
                new PoolingHttpClientConnectionManager(30, TimeUnit.SECONDS);
        // 总连接数
        pollingConnectionManager.setMaxTotal(1000);
        // 同路由的并发数
        pollingConnectionManager.setDefaultMaxPerRoute(1000);

        HttpClientBuilder httpClientBuilder = HttpClients.custom();
        httpClientBuilder.setConnectionManager(pollingConnectionManager);
        // 重试次数，默认是3次，没有开启
        httpClientBuilder.setRetryHandler(new DefaultHttpRequestRetryHandler(2, true));
        // 保持长连接配置，需要在头添加Keep-Alive
        httpClientBuilder.setKeepAliveStrategy(new DefaultConnectionKeepAliveStrategy());

        HttpClient httpClient = httpClientBuilder.build();
        // httpClient连接配置，底层是配置RequestConfig
        HttpComponentsClientHttpRequestFactory clientHttpRequestFactory =
                new HttpComponentsClientHttpRequestFactory(httpClient);
        // 缓冲请求数据，默认值是true。通过POST或者PUT大量发送数据时，建议将此属性更改为false，以免耗尽内存。
        clientHttpRequestFactory.setBufferRequestBody(false);
        clientHttpRequestFactory.setConnectTimeout(connectTimeout);
        clientHttpRequestFactory.setConnectionRequestTimeout(connectionRequestTimeout);
        clientHttpRequestFactory.setReadTimeout(socketTimeout);
        return clientHttpRequestFactory;
    }

    public void setConnectionRequestTimeout(Integer connectionRequestTimeout) {
        this.connectionRequestTimeout = connectionRequestTimeout;
    }

    public void setConnectTimeout(Integer connectTimeout) {
        this.connectTimeout = connectTimeout;
    }

    public void setSocketTimeout(Integer socketTimeout) {
        this.socketTimeout = socketTimeout;
    }
}
