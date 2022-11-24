package com.ruoyi.framework.verticle;

import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.extra.spring.SpringUtil;
import com.ruoyi.vertx.base.common.BaseRestEasyController;
import io.vertx.core.AbstractVerticle;
import org.apache.log4j.Logger;
import org.jboss.resteasy.plugins.server.vertx.VertxRegistry;
import org.jboss.resteasy.plugins.server.vertx.VertxRequestHandler;
import org.jboss.resteasy.plugins.server.vertx.VertxResteasyDeployment;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * 统一web处理器，具体的业务交给业务模块的verticle去处理，通过eventbus通信
 *
 * 因为web层的api交给resteasy管控了，
 * 这个webVerticle的作用就是将resteasy管控的controller注册到相关的deployment
 *
 */
@Component("webVerticle")
public class WebVerticle extends AbstractVerticle {

    private final Logger logger = Logger.getLogger(WebVerticle.class);

    @Override
    public void start() throws Exception {
        VertxResteasyDeployment vertxResteasyDeployment = new VertxResteasyDeployment();
        vertxResteasyDeployment.start();
        VertxRegistry registry = vertxResteasyDeployment.getRegistry();
        // 获取到所有的restEasyController,并注册到VertxRegistry
        Map<String, BaseRestEasyController> restEasyControllerMap = SpringUtil.getBeansOfType(BaseRestEasyController.class);
        if (CollectionUtil.isNotEmpty(restEasyControllerMap)) {
            for (BaseRestEasyController controller : restEasyControllerMap.values()) {
                registry.addPerInstanceResource(controller.getClass());
            }
        }
        int serverPort = config().getInteger("serverPort") == null ? 8080 : config().getInteger("serverPort");
        vertx.createHttpServer()
                .requestHandler(new VertxRequestHandler(vertx, vertxResteasyDeployment))
                .listen(serverPort, handler->{
                    if(handler.succeeded()){
                        logger.info("----Web 服务启动成功 ----");
                    } else {
                        logger.error("----Web 服务启动失败 ----", handler.cause());
                        vertx.close();
                        // web服务启动失败直接退出虚拟机
                        System.exit(-1);
                    }
                });
    }
}
