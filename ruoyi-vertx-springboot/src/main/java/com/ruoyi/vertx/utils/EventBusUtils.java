package com.ruoyi.vertx.utils;

import io.vertx.core.Future;
import io.vertx.core.Vertx;
import io.vertx.core.eventbus.EventBus;
import io.vertx.core.eventbus.Message;
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

    /**
     * 请求数据，带返回值
     * @param address eventbus注册的地址
     * @param message 消息
     * @return 异步响应
     */
    public Future<Message<Object>> request(String address, Object message) {
        return eventBus.request(address, message);
    }

    public void publishMessage(String address, Object message){
        eventBus.publish(address, message);
    }

    // TODO 需要一个对eventBus的address 验证的方法

}
