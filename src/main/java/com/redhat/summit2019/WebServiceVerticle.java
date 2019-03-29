package com.redhat.summit2019;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.redhat.summit2019.generator.DataGenerator;
import io.vertx.core.AbstractVerticle;
import io.vertx.core.Future;
import io.vertx.core.Vertx;
import io.vertx.core.http.HttpServer;
import io.vertx.core.http.HttpServerResponse;
import io.vertx.ext.web.Router;

import java.text.DateFormat;
import java.text.SimpleDateFormat;

public class WebServiceVerticle extends AbstractVerticle {
    private Vertx vertx = Vertx.vertx();
    private HttpServer httpServer;
    private Router router;
    private DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    @Override
    public void start(Future<Void> future) {
        DataGenerator dataGenerator = new DataGenerator();
        httpServer = vertx.createHttpServer();
        router = Router.router(vertx);


        router.route().handler(routingContext -> {
            System.out.println(
                    df.format(System.currentTimeMillis()) +
                            " " +
                            routingContext.request().remoteAddress().host() +
                            " " +
                            routingContext.request().method().toString() +
                            " " +
                            routingContext.request().path());

            routingContext.next();
        });

        router.route().path("/get").produces("application/json").handler(routingContext -> {
            HttpServerResponse response = routingContext.response();
            response.putHeader("content-type", "application/json");
            try {
                response.end(dataGenerator.generateJsonData());
            } catch (JsonProcessingException e) {
                e.printStackTrace();
            }
        });

        router.route().path("/get/:quantity").produces("application/json").handler(routingContext -> {
            HttpServerResponse response = routingContext.response();
            response.putHeader("content-type", "application/json");
            int quantity = Integer.parseInt(routingContext.request().getParam("quantity"));
            try {
                response.end(dataGenerator.generateJsonData(quantity));
            } catch (JsonProcessingException e) {
                e.printStackTrace();
            }
        });

        router.errorHandler(404, routingContext -> {
            HttpServerResponse response = routingContext.response();
            response.putHeader("content-type", "text/plain");
            response.setStatusCode(404).end("Nothing to see here...");
        });

        router.route().failureHandler(routingContext -> {
            HttpServerResponse response = routingContext.response();
            response.setStatusCode(500);
            response.end(routingContext.failure().toString());
        });

        System.out.println("Starting server. Listening on port 8282...");
        httpServer.requestHandler(router).listen(8282, "0.0.0.0");
        future.complete();
    }

    @Override
    public void stop(Future<Void> future) {
        System.out.println("Shutting server down...");
        router.clear();
        httpServer.close();
        vertx.close();
    }
}
