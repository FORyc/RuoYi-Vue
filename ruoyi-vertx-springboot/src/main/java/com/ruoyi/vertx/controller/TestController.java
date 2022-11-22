package com.ruoyi.vertx.controller;

import io.vertx.core.http.HttpServerRequest;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.Context;
import jakarta.ws.rs.core.MediaType;
import org.apache.log4j.Logger;

@Path(value = "/test")
public class TestController {

    private final Logger logger = Logger.getLogger(TestController.class);

    @GET
    @Path(value = "/get")
    @Produces(value = {MediaType.APPLICATION_JSON})
    public String testGet(@Context HttpServerRequest request){
        String name = request.getParam("name");
        logger.info("name:" + name);
        if(name != null){
            logger.info("namespace");
        }
        return name;
    }

}
