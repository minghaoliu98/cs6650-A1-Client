package org.example;

import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.json.JSONObject;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.Random;
import org.apache.commons.httpclient.*;
import org.apache.commons.httpclient.methods.*;
import org.apache.commons.httpclient.params.HttpMethodParams;

public class Main {
    private static String url = "http://localhost:8080/A1_server_war_exploded/swipe/leftorright/";
    private static long baseTime;
    public static void main(String[] args) {
        baseTime = System.currentTimeMillis();
        Runnable test = new Runnable() {
            @Override
            public void run() {
                System.out.println("Hello world!");
                long startTime = System.currentTimeMillis();
                final HttpPost httpPost = new HttpPost(url);
                CloseableHttpClient client = HttpClients.createDefault();
                try {
                    StringEntity entity = new StringEntity(creatJson());
                    httpPost.setEntity(entity);
                    CloseableHttpResponse response = client.execute(httpPost);
                    int code = response.getStatusLine().getStatusCode();
                    System.out.println(code);
                    if (code == 201) {


                    } else {

                    }
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            }
        };

        new Thread(test).start();

    }


    private static String creatJson(){
        JSONObject json = new JSONObject();
        String swipe = "Left";
        if (randInt(0, 1) == 0){
            swipe = "Right";
        }
        json.put("swipe", swipe);
        json.put("swiper", randInt(1, 5000));
        json.put("swipee", randInt(1, 1000000));
        String comment = "";
        for (int i = 0; i < 256; i++){
            char t = (char)((int)'a' + randInt(0,25));
            if (randInt(0, 1) == 0){
                t = Character.toUpperCase(t);
            }
            comment += t;
        }
        json.put("comment", comment);
        return json.toString();
    }
    public static int randInt(int min, int max) {
        Random rand = new Random();
        return rand.nextInt((max - min) + 1) + min;
    }
}