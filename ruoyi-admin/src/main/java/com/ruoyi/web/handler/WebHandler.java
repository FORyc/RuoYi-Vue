package com.ruoyi.web.handler;

import com.ruoyi.common.core.domain.R;
import io.swagger.annotations.ApiModelProperty;
import io.vertx.core.json.JsonObject;
import io.vertx.ext.web.RoutingContext;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@Service
public class WebHandler{

    private Logger logger = Logger.getLogger(WebHandler.class);

    private final static Map<Integer, WebHandler.UserEntity> users = new LinkedHashMap<Integer, WebHandler.UserEntity>();
    {
        users.put(1, new WebHandler.UserEntity(1, "admin", "admin123", "15888888888"));
        users.put(2, new WebHandler.UserEntity(2, "ry", "admin123", "15666666666"));
    }

    public void list(RoutingContext routingContext){
        List<UserEntity> userList = new ArrayList<>(users.values());
        JsonObject jsonObject = JsonObject.mapFrom(R.ok(userList));
        routingContext.response().end(jsonObject.toBuffer());
    }

    public void getUser(RoutingContext routingContext){
    }


    class UserEntity {
        @ApiModelProperty("用户ID")
        private Integer userId;

        @ApiModelProperty("用户名称")
        private String username;

        @ApiModelProperty("用户密码")
        private String password;

        @ApiModelProperty("用户手机")
        private String mobile;

        public UserEntity() {

        }

        public UserEntity(Integer userId, String username, String password, String mobile) {
            this.userId = userId;
            this.username = username;
            this.password = password;
            this.mobile = mobile;
        }

        public Integer getUserId()
        {
            return userId;
        }

        public void setUserId(Integer userId)
        {
            this.userId = userId;
        }

        public String getUsername()
        {
            return username;
        }

        public void setUsername(String username)
        {
            this.username = username;
        }

        public String getPassword()
        {
            return password;
        }

        public void setPassword(String password)
        {
            this.password = password;
        }

        public String getMobile()
        {
            return mobile;
        }

        public void setMobile(String mobile)
        {
            this.mobile = mobile;
        }
    }
}
