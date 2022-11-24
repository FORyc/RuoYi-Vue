package com.ruoyi.vertx.verticle;

import com.ruoyi.vertx.controller.TestController;
import io.vertx.core.AbstractVerticle;
import org.apache.log4j.Logger;
import org.jboss.resteasy.plugins.server.vertx.VertxRequestHandler;
import org.jboss.resteasy.plugins.server.vertx.VertxResteasyDeployment;

//@Component
public class ResteasyVerticle extends AbstractVerticle {

    private final Logger logger = Logger.getLogger(ResteasyVerticle.class);

    @Override
    public void start() throws Exception {
        VertxResteasyDeployment deployment = new VertxResteasyDeployment();
        deployment.start();
        deployment.getRegistry().addPerInstanceResource(TestController.class);
        vertx.createHttpServer()
                .requestHandler(new VertxRequestHandler(vertx, deployment))
                .listen(8080, ar -> {
                    if (ar.succeeded()) {
                        logger.info("RESTEasy HTTP server started on port 8080");
                    } else {
                        logger.error(ar.cause().getMessage(), ar.cause());
                    }
                });
    }
}
