package con.ruoyi.vert;

import com.ruoyi.vertx.VertxApplication;
import com.ruoyi.vertx.base.constant.EventBusAddressEnum;
import com.ruoyi.vertx.utils.EventBusUtils;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest(classes = VertxApplication.class)
//@ExtendWith(VertxExtension.class)
public class Test5Verticle {

    @Autowired
    private EventBusUtils eventBusUtils;

    @Test
    public void testVerticle(){
        System.out.println("dddddddd");
        eventBusUtils.request(EventBusAddressEnum.REDIS.value().concat("test"), "dddddddd")
                .onSuccess(result -> System.out.println(result.body()));
    }

}
