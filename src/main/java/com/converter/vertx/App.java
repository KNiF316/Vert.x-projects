package com.converter.vertx;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.Vertx;
import io.vertx.core.http.HttpServer;
import io.vertx.core.http.HttpServerRequest;
import io.vertx.core.http.HttpServerResponse;
import io.vertx.ext.web.Route;
import io.vertx.ext.web.Router;
import io.vertx.ext.web.handler.BodyHandler;

public class App extends AbstractVerticle {
    public static void main( String[] args ) {

        final String[] weight = new String[1];
        final String[] length = new String[1];
        final String[] distance = new String[1];
        final String[] temperature= new String[1];

        Vertx vertx = Vertx.vertx();

        HttpServer httpServer = vertx.createHttpServer();

        Router router = Router.router(vertx);

        router.route().handler(BodyHandler.create());

       Route handler1 = router
                .get("/weight")
               .handler(routingContext -> {

                  HttpServerResponse response = routingContext.response();
                    response.setChunked(true);
                    response.sendFile("webroot/weight.html");

                });

        Route handler2 = router
               .get("/length")

                .handler(routingContext -> {

                    HttpServerResponse response = routingContext.response();

                   response.sendFile("webroot/length.html");
                });

        Route handler3 = router
                .get("/distance")
                .handler(routingContext -> {

                   HttpServerResponse response = routingContext.response();

                   response.sendFile("webroot/distance.html");

               });

        Route handler4 = router
                .get("/temperature")
                .handler(routingContext -> {

                    HttpServerResponse response = routingContext.response();

                    response.sendFile("webroot/temperature.html");

                });

        Route handler5 = router
               .post("/")
               .handler(routingContext -> {
                   HttpServerRequest request = routingContext.request();
                    HttpServerResponse response = routingContext.response();

                    System.out.println(request.getFormAttribute("weight"));
                  weight[0] = request.getFormAttribute("weight");
                   //  convert into float
                  float formWeight = Float.parseFloat(weight[0]);
                  final float kg;
                    //   lbs-kg Formula
                  kg = (float) (formWeight*0.45);
                       response.send("<p style=\"text-align: center; font-size: 30px;\" >Weight in Kilograms is :" + kg + " </p>");
                });


        Route handler6 = router
                .post("/")
              .handler(routingContext -> {
                   HttpServerRequest request = routingContext.request();
                    HttpServerResponse response = routingContext.response();

                   System.out.println(request.getFormAttribute("weight"));
                   length[0] = request.getFormAttribute("length");
                   //  convert into float
                   float formLength = Float.parseFloat(length[0]);
                    final float feet;
                    // cm-feet Formula
                    feet = (float) (formLength*0.032);
                   response.end("<p style=\"text-align: center; font-size: 30px;\" >Length in Feet is :" + feet + "</p>");
                });



        Route handler7 = router
                .post("/")
                .handler(routingContext -> {
                    HttpServerRequest request = routingContext.request();
                    HttpServerResponse response = routingContext.response();

                     System.out.println(request.getFormAttribute("weight"));
                    distance[0] = request.getFormAttribute("distance");
                    // convert into float
                    float formDistance = Float.parseFloat(distance[0]);
                    final float miles;
                   // km-miles Formula
                    miles = (float) (formDistance*0.62);
                    response.end("<p style=\"text-align: center; font-size: 30px;\" >Distance in Miles is : " + miles+ " </p>");
                });



        Route handler8 = router
                .post("/")
                .handler(routingContext -> {
                    HttpServerRequest request = routingContext.request();
                    HttpServerResponse response = routingContext.response();

                    // System.out.println(request.getFormAttribute("weight"));
                    temperature[0] = request.getFormAttribute("temperature");
                    // convert into float
                    float formTemperature = Float.parseFloat(temperature[0]);
                    final float cel;
                    //fahrenheit-celsius Formula
                    cel = (float) (((formTemperature-32)*5)/9);
                    response.end("<p style=\"text-align: center; font-size: 20px;\" >Temperature in Celsius is : " + cel + " </p>");
                });

        httpServer
                .requestHandler(router)
                .listen(2000);
    }
}