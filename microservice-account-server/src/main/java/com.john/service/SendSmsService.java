package com.john.service;

import org.apache.http.HttpEntity;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.client.methods.RequestBuilder;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.IOException;

/**
 * Created by cjl20 on 6/4/2017.
 */
@Service
public class SendSmsService {

    @Value("${sms.url}")
    private String smsurl;
    @Value("${sms.key}")
    private String smskey;
    @Value("${sms.secret}")
    private String smssecret;
    @Value("${sms.sms_template_code}")
    private String sms_template_code;
    @Value("${sms.sms_type}")
    private String sms_type;

    public String Sms(String phone, String checkcode) {
        String result = "successful";
        CloseableHttpClient httpClient = HttpClients.createDefault();
        try {
            //	请求地址
            HttpUriRequest httpGet = RequestBuilder
                    .get()
                    .setUri(smsurl)
                    .addHeader("X-Ca-Key", smskey)
                    .addHeader("X-Ca-Secret", smssecret)
                    .addParameter("rec_num", phone)
                    .addParameter("sms_template_code", sms_template_code)
                    .addParameter("sms_free_sign_name", "验证码")
                    .addParameter("sms_type", sms_type)
                    .addParameter("extend", "1234")
                    .addParameter("sms_param", "{'code':'" + checkcode + "','product':'商品分析系统'}")
                    .build();
            //	TODO	设置请求超时时间
            //	处理请求结果
            ResponseHandler<String> responseHandler = response -> {
                int status = response.getStatusLine().getStatusCode();
                //System.out.println(status);
                HttpEntity entity = response.getEntity();
                return entity != null ? EntityUtils.toString(entity) : null;
            };
            //	发起 API 调用
            String responseBody = httpClient.execute(httpGet, responseHandler);
            result = responseBody.contains("true") ? "successful" : "error";
        } catch (Exception e) {
//            e.printStackTrace();
            result = e.getMessage();
        } finally {
            try {
                httpClient.close();
            } catch (IOException e) {
//                e.printStackTrace();
                result = e.getMessage();
            }
        }
        return result;
    }
}

