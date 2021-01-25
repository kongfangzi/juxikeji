package com.juxi.lingshibang.api.utils.httpUtils;

import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.ParseException;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.LinkedList;
import java.util.List;

public class test {
    public static void main(String[] args) {


            // 获取连接客户端工具
            CloseableHttpClient httpClient = HttpClients.createDefault();

            String entityStr = null;
            CloseableHttpResponse response = null;

            try {
                /*
                 * 由于GET请求的参数都是拼装在URL地址后方，所以我们要构建一个URL，带参数
                 * https://d92a38492f2648658f3342234eeeb7f5-cn-hangzhou.alicloudapi.com/product/search?pageNo=1&pageSize=27
                 */
                URIBuilder uriBuilder = new URIBuilder("https://d92a38492f2648658f3342234eeeb7f5-cn-hangzhou.alicloudapi.com/product/search");
                /** 第一种添加参数的形式 */
        /*uriBuilder.addParameter("name", "root");
        uriBuilder.addParameter("password", "123456");*/
                /** 第二种添加参数的形式 */
                List<NameValuePair> list = new LinkedList<>();
                BasicNameValuePair param1 = new BasicNameValuePair("pageNo", "1");
                BasicNameValuePair param2 = new BasicNameValuePair("pageSize", "27");
                list.add(param1);
                list.add(param2);
                uriBuilder.setParameters(list);

                // 根据带参数的URI对象构建GET请求对象
                HttpGet httpGet = new HttpGet(uriBuilder.build());

                /*
                 * 添加请求头信息
                 */
                // 浏览器表示
                httpGet.addHeader("User-Agent", "Mozilla/5.0 (Windows; U; Windows NT 5.1; en-US; rv:1.7.6)");
                // 传输的类型
                httpGet.addHeader("Content-Type", "application/x-www-form-urlencoded");
                httpGet.addHeader("Authorization","APPCODE 514c8a65625e43dc821f6b030cf9244b");
                // 执行请求
                response = httpClient.execute(httpGet);
                // 获得响应的实体对象
                HttpEntity entity = response.getEntity();
                // 使用Apache提供的工具类进行转换成字符串
                entityStr = EntityUtils.toString(entity, "UTF-8");
            } catch (ClientProtocolException e) {
                System.err.println("Http协议出现问题");
                e.printStackTrace();
            } catch (ParseException e) {
                System.err.println("解析错误");
                e.printStackTrace();
            } catch (URISyntaxException e) {
                System.err.println("URI解析异常");
                e.printStackTrace();
            } catch (IOException e) {
                System.err.println("IO异常");
                e.printStackTrace();
            } finally {
                // 释放连接
                if (null != response) {
                    try {
                        response.close();
                        httpClient.close();
                    } catch (IOException e) {
                        System.err.println("释放连接出错");
                        e.printStackTrace();
                    }
                }
            }

            // 打印响应内容
            System.out.println(entityStr);

        }

    }

