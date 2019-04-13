package com.redhat.summit2019;

import com.redhat.summit2019.generator.DataGenerator;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

import java.net.URL;

public class Main {

    private static final OkHttpClient http = new OkHttpClient();
    private static final DataGenerator dataGenerator = new DataGenerator();

    public static void main(String[] args) throws Exception {
        int quantity = 10;
        String baseURI = "http://127.0.0.1:8080/greeting/";
        URL url;

        if (args != null && args.length > 0) {
            quantity = Integer.parseInt(args[0]);
        }

        if (args != null && args.length > 1) {
            baseURI = args[1];
        }

        for (int i = 0; i < quantity; i++) {
            url = new URL(baseURI);
            String json = dataGenerator.generateJsonData();
            RequestBody body = RequestBody.create(MediaType.get("application/json"), json);
            Request request = new Request.Builder().url(url).post(body).build();
            Response response = http.newCall(request).execute();

            System.out.println("POST: " + response.code() + " " + (response.body() != null ? response.body().string() : "null"));
        }
    }
}