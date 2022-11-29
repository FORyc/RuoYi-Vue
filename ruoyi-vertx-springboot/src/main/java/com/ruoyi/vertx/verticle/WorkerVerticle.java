package com.ruoyi.vertx.verticle;

import com.ruoyi.vertx.base.annotation.Verticle;
import com.ruoyi.vertx.base.constant.VerticleName;
import io.vertx.core.AbstractVerticle;
import org.apache.log4j.Logger;

@Verticle(value = VerticleName.WORK_VERTICLE)
public class WorkerVerticle  extends AbstractVerticle {

    private final Logger logger = Logger.getLogger(WorkerVerticle.class);

    @Override
    public void start() throws Exception {
        // TODO 主要是执行一些耗时操作,通过订阅eventbus来消费数据

    }


}
