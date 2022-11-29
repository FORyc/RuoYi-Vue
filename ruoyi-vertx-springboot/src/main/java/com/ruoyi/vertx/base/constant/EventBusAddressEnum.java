package com.ruoyi.vertx.base.constant;

/**
 * event bus 地址枚举
 */
public enum EventBusAddressEnum {
    WORK("Work."),
    REDIS("Redis."),
    DB("DB."),
    ;

    /**
     * 地址值
     */
    private String value;

    EventBusAddressEnum(String value) {
        this.value = value;
    }

    public String value() {
        return value;
    }
}