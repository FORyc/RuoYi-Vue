package com.ruoyi.vertx.utils;

import io.vertx.core.Vertx;
import io.vertx.core.eventbus.EventBus;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;

import javax.validation.constraints.NotNull;

/**
 * eventBus 工具类
 * 主要给其他模块来将消息发送到eventBus，然后让verticle来处理
 */
@Component
public class EventBusUtils {

    private final Logger logger = Logger.getLogger(EventBusUtils.class);

    private final EventBus eventBus;

    public EventBusUtils(@NotNull(message = "vertx not null") Vertx vertx) {
        this.eventBus = vertx.eventBus();
    }

    public void request(String address, Object message) {
        eventBus.request(address, message);
    }

    public void publishMessage(String address, Object message){
        eventBus.publish(address, message);
    }

}
