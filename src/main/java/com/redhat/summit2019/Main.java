package com.redhat.summit2019;

import io.vertx.core.Future;

public class Main {
    private static WebServiceVerticle verticle = new WebServiceVerticle();
    private static Future<Void> future = Future.future();

    public static void main(String[] args) {
        Runtime.getRuntime().addShutdownHook(new Thread(() -> verticle.stop(future)));
        verticle.start(future);
    }
}
