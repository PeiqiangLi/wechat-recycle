package com.wechat.recycle.common.httpclient;

import com.alibaba.fastjson.JSONObject;
import org.apache.commons.collections.MapUtils;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.StringUtils;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Map;

/**
 * @Author: Roylion
 * @Description: HttpUrlConnection 工具类
 * @Date: Created in 14:34 2018/9/30
 */
public class HttpUtils {

    private HttpUtils() {
    }

    private static final String CHARSET = "UTF-8";

    public static String doGet(String url, Map<String, String> params) throws IOException {

        if (MapUtils.isNotEmpty(params)) {
            url = url + "?" + getURLParams(params);
        }

        HttpURLConnection con = getDefaultConnection(url);
        con.setRequestMethod("GET");

        try (InputStream ins = con.getInputStream()) {
            int responseCode = con.getResponseCode();
            String response = IOUtils.toString(ins, CHARSET);
            return response;
        }
    }

    public static String doGet(String url) throws IOException {
        return doGet(url, null);
    }

    public static String doPost(String url, Map<String, String> params, Map<String, String> headers) throws IOException {
        HttpURLConnection con = getDefaultConnection(url);
        con.setRequestMethod("POST");
        con.setDoOutput(true);

        String contentType = null;


        // 设置头部
        if (MapUtils.isNotEmpty(headers)) {
            for (Map.Entry<String, String> entry : headers.entrySet()) {
                if ("content-type".equalsIgnoreCase(entry.getKey())) {
                    contentType = entry.getValue();
                }
                con.setRequestProperty(entry.getKey(), entry.getValue());
            }
        }

        con.getHeaderField("content-type");
        // 设置请求体参数
        if (MapUtils.isNotEmpty(params)) {
            try (OutputStream ous = con.getOutputStream()) {
                String param = "";
                if (contentType == null) {
                    param = getURLParams(params);
                } else if ("application/json".equals(contentType)) {
                    param = JSONObject.toJSONString(params);
                }
                IOUtils.write(param, ous, CHARSET);
            }
        }

        try (InputStream ins = con.getInputStream()) {
            int responseCode = con.getResponseCode();
            String response = IOUtils.toString(ins, CHARSET);
            return response;
        }
    }

    public static String doPostJson(String url, String json, Map<String, String> headers) throws IOException {
        HttpURLConnection con = getDefaultConnection(url);
        con.setRequestMethod("POST");
        con.setDoOutput(true);

        // 设置头部
        if (MapUtils.isNotEmpty(headers)) {
            for (Map.Entry<String, String> entry : headers.entrySet()) {
                con.setRequestProperty(entry.getKey(), entry.getValue());
            }
        }
        con.setRequestProperty("content-type", "application/json");

        // 设置请求体参数
        if (StringUtils.isNotEmpty(json)) {
            try (OutputStream ous = con.getOutputStream()) {
                IOUtils.write(json, ous, CHARSET);
            }
        }

        // 获取响应
        try (InputStream ins = con.getInputStream()) {
            int responseCode = con.getResponseCode();
            String response = IOUtils.toString(ins, CHARSET);
            return response;
        }
    }

    public static String getURLParams(Map<String, String> params) {
        StringBuilder sb = new StringBuilder();
        for (Map.Entry<String, String> entry : params.entrySet()) {
            sb.append(entry.getKey()).append("=").append(entry.getValue()).append("&");
        }
        return sb.substring(0, sb.length() - 1);
    }

    // 设置默认配置
    public static HttpURLConnection getDefaultConnection(String url) throws IOException {
        HttpURLConnection con = (HttpURLConnection) new URL(url).openConnection();
        con.setConnectTimeout(5000);// 设置连接超时
        con.setReadTimeout(30000); // 设置数据传输时间
        con.setUseCaches(false);
        return con;
    }

    public static void main(String[] args) throws IOException {
        System.out.println(doGet("http://www.baidu.com"));
    }
}
