package com.ruoyi.vertx.listener;

import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.extra.spring.SpringUtil;
import com.ruoyi.vertx.base.annotation.Verticle;
import com.ruoyi.vertx.base.constant.VerticleName;
import io.vertx.core.AbstractVerticle;
import io.vertx.core.DeploymentOptions;
import io.vertx.core.Vertx;
import org.apache.log4j.Logger;
import org.springframework.boot.context.event.ApplicationStartedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * 监听 spring context的启动，以部署verticle
 */
@Component
public class VertxSpringContextListener {

    private final Logger logger = Logger.getLogger(VertxSpringContextListener.class);

    private final Vertx vertx;

    public VertxSpringContextListener(Vertx vertx) {
        this.vertx = vertx;
    }

    /**
     * 监听spring context的启动，当context启动完成后才开始部署verticle
     */
    @EventListener(classes = ApplicationStartedEvent.class)
    public void deploy(){
        logger.info("-------verticle 开始部署------");
        Map<String, Object> verticleMap = SpringUtil.getApplicationContext().getBeansWithAnnotation(Verticle.class);
        if (CollectionUtil.isNotEmpty(verticleMap)) {
            for (Map.Entry<String, Object> entry : verticleMap.entrySet()) {
                try {
                    if (entry.getValue() instanceof AbstractVerticle){
                        AbstractVerticle verticle = (AbstractVerticle) entry.getValue();
                        if (entry.getKey().equals(VerticleName.WORK_VERTICLE)) {
                            DeploymentOptions deploymentOptions = new DeploymentOptions();
                            deploymentOptions.setWorker(true);
                            vertx.deployVerticle(verticle, deploymentOptions, handler -> {
                                if (handler.succeeded()) {
                                    logger.info(StrUtil.format("verticle[{}] deployed", entry.getKey()));
                                } else {
                                    logger.error(StrUtil.format("error deploying {}", entry.getKey()), handler.cause());
                                }
                            });
                        } else {
                            vertx.deployVerticle(verticle, handler ->{
                                if (handler.succeeded()) {
                                    logger.info(StrUtil.format("verticle[{}] deployed", entry.getKey()));
                                } else {
                                    logger.error(StrUtil.format("error deploying {}", entry.getKey()), handler.cause());
                                }
                            });
                        }
                    } else {
                        logger.warn(StrUtil.format("bean = [{}] 不是 AbstractVerticle，暂时不支持直接部署", entry.getKey()));
                    }
                } catch (Exception e){
                    logger.warn(StrUtil.format("[{}]部署失败，msg = {}", entry.getValue(), e));
                }
            }
        } else {
            logger.warn("未找到spring管理的verticle, vertx关闭...");
            vertx.close();
        }
    }

}
