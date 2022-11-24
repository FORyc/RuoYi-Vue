package com.ruoyi.vertx.config;

import io.vertx.core.Vertx;
import io.vertx.core.WorkerExecutor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class VertxConfig {

    @Bean
    public Vertx vertx(){
        return Vertx.vertx();
    }

    @Bean
    public WorkerExecutor workerExecutor(Vertx vertx){
        return vertx.createSharedWorkerExecutor("my-worker-pool", Runtime.getRuntime().availableProcessors());
    }
}
