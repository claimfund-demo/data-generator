package com.redhat.summit2019;

import com.redhat.summit2019.generator.PersonGenerator;
import com.redhat.summit2019.model.Person;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

import java.net.URL;
import java.util.concurrent.ThreadLocalRandom;

public class Main {
    private static OkHttpClient http = new OkHttpClient();

    public static void main(String[] args) throws Exception {
        int quantity = 10;
        if (args != null && args.length > 0) {
            quantity = Integer.parseInt(args[0]);
        }

        String baseURI = "http://127.0.0.1:8080/greeting/";

        if (args != null && args.length > 1) {
            baseURI = args[1];
        }

        URL url;

        for (int i = 0; i < quantity; i++) {
            int random = ThreadLocalRandom.current().nextInt(3);
            Person person;
            if (random == 0) {
                person = PersonGenerator.getPerson("f");
            } else if (random == 1) {
                person = PersonGenerator.getPerson("nb");
            } else {
                person = PersonGenerator.getPerson("m");
            }
            url = new URL(baseURI + i);
            RequestBody body = RequestBody.create(MediaType.get("application/json"), person.toJSON());
            Request request = new Request.Builder().url(url).post(body).build();
            Response response = http.newCall(request).execute();

            System.out.println("POST: " + response.code() + " " + person.toJSON());
        }
    }
}