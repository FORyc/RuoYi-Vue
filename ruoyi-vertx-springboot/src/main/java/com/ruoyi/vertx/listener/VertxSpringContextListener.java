package com.ruoyi.vertx.listener;

import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.extra.spring.SpringUtil;
import io.vertx.core.AbstractVerticle;
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
        Map<String, AbstractVerticle> verticleMap = SpringUtil.getBeansOfType(AbstractVerticle.class);
        if (CollectionUtil.isNotEmpty(verticleMap)) {
            for (Map.Entry<String, AbstractVerticle> entry : verticleMap.entrySet()) {
                vertx.deployVerticle(entry.getValue());
            }
        } else {
            logger.warn("未找到spring管理的verticle, vertx关闭...");
            vertx.close();
        }
    }

}
