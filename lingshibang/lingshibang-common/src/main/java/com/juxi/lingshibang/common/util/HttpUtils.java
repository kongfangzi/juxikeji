package com.juxi.lingshibang.common.util;

import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Map;

@Slf4j
public class HttpUtils {

    public static String get(String httpUrl){
        try {
            while (true){
                URL url = new URL(httpUrl);
                HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                httpURLConnection.setConnectTimeout(10000);
                httpURLConnection.setReadTimeout(30000);
                httpURLConnection.connect();
                int responseCode = httpURLConnection.getResponseCode();
                if(responseCode==200){
                    InputStream inputStream = httpURLConnection.getInputStream();
                    return stream2String(inputStream);
                }else if(responseCode==302){
                    httpUrl = httpURLConnection.getHeaderField("Location");
                }else {
                    System.out.println("httpError:"+httpURLConnection.getResponseMessage());
                    break;
                }
            }
        } catch (Exception e) {
            log.error("http请求异常",e);
        }
        return  null;
    }

    public static String post(String httpUrl, Map<String,String> params){
        OutputStream paramStream=null;
        InputStream is = null;
        try {
            log.info("httpClient:url:{}:param:{}",httpUrl, JSON.toJSONString(params));
            URL url = new URL(httpUrl);
            HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
            httpURLConnection.setConnectTimeout(10000);
            httpURLConnection.setReadTimeout(10000);
            httpURLConnection.setDoInput(true);
            httpURLConnection.setDoOutput(true);
            if(params!=null && params.size()>0){
                paramStream = httpURLConnection.getOutputStream();
                String composeParams = composeParams(params);
                paramStream.write(composeParams.getBytes("utf-8"));
            }
            is=httpURLConnection.getInputStream();
            String resultTxt = stream2String(is);
            log.info("httpClient:url:",resultTxt);
            return resultTxt;
        } catch (Exception e) {
            log.error("http请求异常",e);
        } finally {
            if(paramStream!=null){
                try {
                    paramStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if(is!=null){
                try {
                    is.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return null;
    }

    private static String composeParams(Map<String,String> params){
        if(params!=null){
            StringBuilder builder = new StringBuilder();
            for(Map.Entry<String,String> entry : params.entrySet()){
                builder.append(entry.getKey());
                builder.append("=");
                builder.append(entry.getValue());
                builder.append("&");
            }
            if(builder.length()>0){
                builder.deleteCharAt(builder.length()-1);
            }
            return builder.toString();
        }
        return null;
    }

    private static String stream2String(InputStream inputStream) throws IOException {
        ByteArrayOutputStream bos = null;
        try {
            byte[] buff = new byte[1024];
            int len = -1;
            bos = new ByteArrayOutputStream();
            while ((len=inputStream.read(buff))!=-1){
                //文本内容不大于10M
                if(bos.size()+len>1024*1024*10){
                    throw new IOException("body is too large");
                }
                bos.write(buff,0,len);
            }
            return new String(bos.toByteArray(),"utf-8");
        } finally {
            if(inputStream!=null){
                try {
                    inputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if(bos!=null){
                try {
                    bos.reset();
                    bos.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }

}
