package com.juxi.lingshibang.common.util;

import com.juxi.lingshibang.common.base.Constants;
import com.juxi.lingshibang.common.base.Result;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.core.MediaType;
import java.util.*;

/**
 * @Description: 获取请求
 */
public abstract class RequestUtil {

    public static HttpServletRequest getRequest() {
        if (RequestContextHolder.getRequestAttributes() != null) {
            return ((ServletRequestAttributes) (RequestContextHolder.getRequestAttributes()))
                    .getRequest();
        }
        return null;
    }

    public static void postUtil(RestTemplate restTemplate, String url, String jsonStr){
        HttpHeaders headers= buildHearder();
        HttpEntity requestEntity=new HttpEntity(jsonStr,headers);
        ResponseEntity<Result> resultResponseEntity = restTemplate.exchange(url, HttpMethod.POST, requestEntity, Result.class);
    }

    public static HttpHeaders buildHearder(){
        HttpHeaders headers=new HttpHeaders();
        List<String> contentTypes = new ArrayList<>();
        contentTypes.add(MediaType.APPLICATION_JSON.concat(";charset=UTF-8"));
        headers.put("Content-Type",contentTypes);
        return headers;
    }

    public static HttpHeaders buildHearders(HttpServletRequest request){
        HttpHeaders headers=new HttpHeaders();
        Map<String, String> headersInfo=getHeadersInfo(request);
        for (Map.Entry<String, String> entry : headersInfo.entrySet()) {
            headers.add(entry.getKey(),entry.getValue());
        }
        return headers;
    }

    /**
     * 获取登录请求header信息
     * @param request
     * @return
     */
    public static String getLoginRequestHeaderInfo(HttpServletRequest request, String key) {
        String loginToken = null;
        if (request.getMethod().equals(Constants.HTTP_POST_METHOD)) {
            loginToken = request.getHeader(key);
        } else if (request.getMethod().equals(Constants.HTTP_GET_METHOD)) {
            loginToken = request.getHeader(key);
            if (loginToken == null) {
                loginToken = request.getParameter(key);
            }
        }
        return loginToken;
    }

    /**
     * 获取请求header
     * @param request
     * @return
     */
    public static Map<String, String> getHeadersInfo(HttpServletRequest request) {
        Map<String, String> map = new HashMap<String, String>();
        Enumeration headerNames = request.getHeaderNames();
        while (headerNames.hasMoreElements()) {
            String key = (String) headerNames.nextElement();
            String value = request.getHeader(key);
            map.put(key, value);
        }
        return map;
    }


}
