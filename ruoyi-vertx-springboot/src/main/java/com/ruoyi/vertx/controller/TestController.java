package com.ruoyi.vertx.controller;

import io.vertx.core.Vertx;
import org.apache.log4j.Logger;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.container.AsyncResponse;
import javax.ws.rs.container.Suspended;
import javax.ws.rs.core.Context;

/**
 * 这里的javax包，在macos的openJDK11上
 */
@Path(value = "/test")
public class TestController {

    private final Logger logger = Logger.getLogger(TestController.class);

    @GET
    @Path(value = "/get")
//    @Produces(value = MediaType.APPLICATION_JSON)
    public String testGet(@Suspended AsyncResponse asyncResponse,
                          @Context Vertx vertx,
                          String name){
        logger.info("name:" + name);
        if(name != null){
            logger.info("namespace");
        }
        return name;
    }

}
