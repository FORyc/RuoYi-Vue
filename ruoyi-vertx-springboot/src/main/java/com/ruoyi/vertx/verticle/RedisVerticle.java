package com.ruoyi.vertx.verticle;

import com.ruoyi.vertx.base.annotation.Verticle;
import com.ruoyi.vertx.base.constant.EventBusAddressEnum;
import com.ruoyi.vertx.base.constant.VerticleName;
import io.vertx.core.AbstractVerticle;
import io.vertx.redis.client.Redis;
import io.vertx.redis.client.RedisAPI;
import io.vertx.redis.client.RedisOptions;
import org.apache.log4j.Logger;

/**
 *  redis操作verticle
 */
@Verticle(value = VerticleName.REDIS_VERTICLE)
public class RedisVerticle extends AbstractVerticle {

    private final Logger logger = Logger.getLogger(RedisVerticle.class);

    @Override
    public void start() throws Exception {
        // TODO 这里的RedisOptions可以交给spring管理
        String host = config().getString("host");
        // Create the redis client
        Redis client = Redis.createClient(vertx, new RedisOptions().addConnectionString(host));
        client.connect()
                .onFailure(handler -> {
                    logger.error("Failed to connect redis", handler);
                    vertx.close();
                    System.exit(1);
                }).onSuccess(handler -> logger.info("redis connection established"));
        RedisAPI redis = RedisAPI.api(client);
        vertx.eventBus().consumer(EventBusAddressEnum.REDIS.value().concat("*"), handler -> {
            // TODO 判断redis操作
        });

    }
}
