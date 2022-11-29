package com.ruoyi.vertx.controller;

import com.ruoyi.vertx.base.common.BaseRestEasyController;
import io.vertx.core.http.HttpServerRequest;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.container.AsyncResponse;
import jakarta.ws.rs.container.Suspended;
import jakarta.ws.rs.core.Context;
import jakarta.ws.rs.core.MediaType;
import org.apache.log4j.Logger;

/**
 * 新增的restController需要继承BaseRestEasyController，且加入Spring的管理
 */
//@Component
@Path(value = "/test")
public class TestController extends BaseRestEasyController {

    private final Logger logger = Logger.getLogger(TestController.class);

    @GET
    @Path(value = "/get")
    @Produces(value = {MediaType.APPLICATION_JSON})
    public AsyncResponse testGet(@Context HttpServerRequest request,
                          @Suspended AsyncResponse asyncResponse){
        String name = request.getParam("name");
        logger.info("name:" + name);
        asyncResponse.resume(name);
        return asyncResponse;
    }

}
