package com.ruoyi.web.controller;

import cn.hutool.core.util.StrUtil;
import com.ruoyi.web.handler.WebHandler;
import io.vertx.core.AbstractVerticle;
import io.vertx.core.http.HttpMethod;
import io.vertx.ext.web.Router;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.web.ServerProperties;
import org.springframework.stereotype.Component;

/**
 * web处理verticle
 */
@Component(value = "webVerticle")
public class WebVerticle extends AbstractVerticle {

    private final Logger logger = Logger.getLogger(WebVerticle.class);

    @Autowired
    private ServerProperties serverProperties;
    @Autowired
    private WebHandler webHandler;

    @Override
    public void start() {
        Router router = Router.router(vertx);
        router.route(HttpMethod.GET, "/test/user/list").handler(webHandler::list);
        router.route(HttpMethod.GET, "/test/user/:userId").handler(webHandler::getUser);
        vertx.createHttpServer()
                .requestHandler(router)
                .listen(serverProperties.getPort())
                .onSuccess(handler -> logger.info("服务启动成功"))
                .onFailure(handler -> {
                    logger.error(StrUtil.format("服务启动失败 msg = [{}]", handler.getCause()));
                    vertx.close();
                    System.exit(1);
                });
    }
}

