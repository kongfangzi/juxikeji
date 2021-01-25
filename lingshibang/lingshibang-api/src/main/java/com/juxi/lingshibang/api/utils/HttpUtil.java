package com.juxi.lingshibang.api.utils;
import org.apache.commons.lang.StringUtils;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClientBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.io.IOException;
import java.nio.charset.Charset;

public class HttpUtil {
    private static final Logger logger = LoggerFactory.getLogger(HttpUtil.class);
    private static String CONENT_TYPE_CONSTANT = "application/json";
    /**
     * 发送json数据
     * @param url
     * @param json
     * @param contentType
     * @return
     */
    public static HttpResponse postJson(String url, String  json,String contentType){
        HttpPost httpPost = new HttpPost(url);
        HttpClient httpClient = HttpClientBuilder.create().build();
        StringEntity stringEntity = new StringEntity(json, Charset.forName("UTF-8"));
        stringEntity.setContentEncoding("UTF-8");
        stringEntity.setContentType(StringUtils.isEmpty(contentType) ? CONENT_TYPE_CONSTANT:contentType);
        httpPost.setEntity(stringEntity);
        logger.info("json请求的数据结果：" + json);
        HttpResponse response=null;
        try {
            response = httpClient.execute(httpPost);
        } catch (IOException e) {
            logger.info("华为人脸探测token请求失败"+e.getMessage());
        }
        return response;
    }
}
