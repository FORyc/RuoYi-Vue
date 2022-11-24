package com.ruoyi.framework.verticle;

import com.ruoyi.vertx.base.constant.VerticleName;
import io.vertx.core.AbstractVerticle;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;

@Component(value = VerticleName.WORK_VERTICLE)
public class WorkerVerticle  extends AbstractVerticle {

    private final Logger logger = Logger.getLogger(WorkerVerticle.class);

    @Override
    public void start() throws Exception {
        // TODO 主要是执行一些耗时操作,通过订阅eventbus来消费数据

    }


}
