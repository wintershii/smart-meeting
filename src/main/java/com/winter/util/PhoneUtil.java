package com.winter.util;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.squareup.okhttp.*;
import com.winter.common.PhoneNumber;
import com.winter.common.PhoneRequest;

import java.io.IOException;

public class PhoneUtil {

    private final static ObjectMapper MAPPER = new ObjectMapper();

    public static final MediaType JSON = MediaType.parse("application/json; charset=utf-8");
    /**
     * 获取手机验证码
     * @param phoneNumber
     * @return
     */
    public static String getVerificationCode(String phoneNumber) {
        System.out.println(phoneNumber);
        String url = "https://api2.bmob.cn/1/requestSmsCode";
        OkHttpClient client = new OkHttpClient();

        PhoneRequest phoneRequest = new PhoneRequest(phoneNumber,"AppModel");


        Gson gson = new Gson();
        String json = gson.toJson(phoneRequest);
        System.out.println(json);
        RequestBody  jsonBody = RequestBody.create(JSON,json);


        Request request = new Request.Builder()
                .url(url)
                .addHeader("X-Bmob-Application-Id","4527189a38e9393bebbaaf3940982580")
                .addHeader("X-Bmob-REST-API-Key","6449d52f810bf605698ff865f5017929")
                .addHeader("Content-Type","application/json")
                .post(jsonBody)
                .build();

        try {
            Response response = client.newCall(request).execute();
            String result = response.body().string();
            System.out.println("获取到响应:" + result);
            System.out.println("开始请求....");
            if ( !response.isSuccessful()) {
                System.out.println("请求失败...");
                return null;
            }
            System.out.println("请求成功...");
            System.out.println("获取到响应:" + result);
            return bodyToJson(result);

        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }


    /**
     * 验证用户验证码是否正确
     * @param code
     * @return
     */
    public static boolean judgeCodeIsTrue(String code,String mobilePhoneNumber) {
        String url = "https://api2.bmob.cn/1/verifySmsCode/" + code;
        OkHttpClient client = new OkHttpClient();


        PhoneNumber phoneNumber = new PhoneNumber(mobilePhoneNumber);

        Gson gson = new Gson();
        String json = gson.toJson(phoneNumber);
        System.out.println(json);
        RequestBody  jsonBody = RequestBody.create(JSON,json);


        Request request = new Request.Builder()
                .url(url)
                .addHeader("X-Bmob-Application-Id","4527189a38e9393bebbaaf3940982580")
                .addHeader("X-Bmob-REST-API-Key","6449d52f810bf605698ff865f5017929")
                .addHeader("Content-Type","application/json")
                .post(jsonBody)
                .build();

        try {
            Response response = client.newCall(request).execute();
            String result = response.body().string();
            System.out.println("获取到响应:" + result);
            System.out.println("开始请求....");
            if ( !response.isSuccessful()) {
                System.out.println("请求失败...");
                return false;
            }
            System.out.println("请求成功...");
            System.out.println("获取到响应:" + result);
            return true;

        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }

    /**
     * 请求体转Json
     * @param result
     * @return
     */
    private static String bodyToJson(String result) {

        JsonNode root = null;
        try {
            root = MAPPER.readTree(result);
        } catch (IOException e) {
            e.printStackTrace();
        }

        JsonNode code = root.path("smsId");
        System.out.println("验证码:"+code);
        return code.toString();
    }

}
