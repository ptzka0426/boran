package com.boran.Util;

import com.alibaba.fastjson.JSONObject;

import java.io.*;
import java.net.ConnectException;
import java.net.HttpURLConnection;
import java.net.URL;


/**
 * @Author LT
 * @create 2020-10-27 16:12
 */
public class HttpRequestUtil {
    /**
     * 发起http请求并获取结果
     *
     * @param requestUrl    请求地址
     * @param requestMethod 请求方式（GET、POST）
     * @param outputStr     提交的数据
     * @return JSONObject(通过JSONObject.get ( key)的方式获取json对象的属性值)
     */
    public static JSONObject httpRequest(String requestUrl, String requestMethod, String outputStr) throws Exception {
        JSONObject jsonObject = null;
        StringBuffer buffer = new StringBuffer();
        InputStream inputStream = null;
        try {
            URL url = new URL(requestUrl);
            HttpURLConnection httpUrlConn = (HttpURLConnection) url.openConnection();
            httpUrlConn.addRequestProperty("User-Agent","Mozilla / 4.76");
            /*httpUrlConn.setConnectTimeout(10000);
            httpUrlConn.setReadTimeout(10000);*/
            httpUrlConn.setDoOutput(true);//允许输入流，即允许下载
            httpUrlConn.setDoInput(true);//允许输出流，即允许上传
            httpUrlConn.setUseCaches(false);//不使用缓冲
            httpUrlConn.setConnectTimeout(3000);//设置连接主机超时
            httpUrlConn.setReadTimeout(3000);//设置从主机读取数据超时
            System.setProperty("sun.net.client.defaultConnectTimeout", String.valueOf(3000));// （单位：毫秒）
            System.setProperty("sun.net.client.defaultReadTimeout", String.valueOf(3000)); // （单位：毫秒）
            // 设置请求方式（GET/POST）
            httpUrlConn.setRequestMethod(requestMethod);
            if ("GET".equalsIgnoreCase(requestMethod)) {
                httpUrlConn.connect();
            }
            // 当有数据需要提交时
            if (null != outputStr) {
                OutputStream outputStream = httpUrlConn.getOutputStream();
                // 注意编码格式，防止中文乱码
                outputStream.write(outputStr.getBytes("UTF-8"));
                outputStream.close();
            }
            //将返回的输入流转换成字符串
            inputStream = httpUrlConn.getInputStream();
            InputStreamReader inputStreamReader = new InputStreamReader(inputStream, "utf-8");
            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);

            String str = null;
            while ((str = bufferedReader.readLine()) != null) {
                buffer.append(str);
            }
            bufferedReader.close();
            inputStreamReader.close();
            // 释放资源
            inputStream.close();
            inputStream = null;
            httpUrlConn.disconnect();
            if(buffer.toString().substring(0,1).equals("{")){
                jsonObject = JSONObject.parseObject(buffer.toString());
            }else{
                jsonObject = JSONObject.parseObject(buffer.toString().substring(1, buffer.toString().length() - 1));
            }
        } catch (ConnectException ce) {
            ce.printStackTrace();
            throw new ConnectException("连接超时..............");
            //System.out.println("Weixin server connection timed out");
        } catch (Exception e) {
            e.printStackTrace();
            //System.out.println("http request error:{}");
            throw new Exception("连接超时.....................................................");
        } finally {
            try {
                if (inputStream != null) {
                    inputStream.close();
                }
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
        return jsonObject;
    }
}
