package com.ruoyi.vertx.verticle;

import io.vertx.core.AbstractVerticle;


import org.apache.log4j.Logger;
import org.jboss.resteasy.plugins.server.vertx.VertxRequestHandler;
import org.jboss.resteasy.plugins.server.vertx.VertxResteasyDeployment;
import org.springframework.stereotype.Component;

@Component
public class ResteasyVerticle extends AbstractVerticle {

    private final Logger logger = Logger.getLogger(ResteasyVerticle.class);

    @Override
    public void start() throws Exception {
        VertxResteasyDeployment deployment = new VertxResteasyDeployment();
        deployment.start();


        vertx.createHttpServer()
                .requestHandler(new VertxRequestHandler(vertx, deployment))
                .listen(8080, ar -> {
                    System.out.println("Server started on port "+ ar.result().actualPort());
                });
    }
}
