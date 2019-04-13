package com.redhat.summit2019;

import com.redhat.summit2019.generator.DataGenerator;
import okhttp3.Credentials;
import okhttp3.Headers;
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
        String baseURL = "http://192.168.0.23:8080/kie-server/services/rest/server";
        String containerId = "kafka-jbpm-process_1.0.23-SNAPSHOT";
        String processInstanceid = "kafka-jbpm-process.claimfund-process";
        Headers authHeader = new Headers.Builder()
                .add("Authorization", Credentials.basic("wbadmin", "wbadmin"))
                .build();
        URL url = new URL(baseURL + "/containers/" + containerId + "/processes/" + processInstanceid + "/instances");

        if (args != null && args.length > 0) {
            quantity = Integer.parseInt(args[0]);
        }

        System.out.println("Generating " + quantity + " new task(s).");
        for (int i = 0; i < quantity; i++) {
            System.out.println("Creating task #" + i);
            String json = dataGenerator.generateJsonData();

            RequestBody body = RequestBody.create(MediaType.get("application/json"), json);
            Request request = new Request.Builder()
                    .url(url)
                    .headers(authHeader)
                    .post(body)
                    .build();
            Response response = http.newCall(request).execute();

            System.out.println("POST: " + response.code() + " " + (response.body() != null ? response.body().string() : "null"));
            Thread.sleep(2000);
        }
    }
}