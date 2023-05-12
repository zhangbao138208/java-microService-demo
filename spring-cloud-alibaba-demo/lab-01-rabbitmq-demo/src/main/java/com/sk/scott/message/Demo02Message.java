package com.sk.scott.message;

import java.io.Serializable;

public class Demo02Message implements Serializable {
    public static final String QUEUE1 = "QUEUE01_DEMO_02";
    public static final String QUEUE2 = "QUEUE02_DEMO_02";

    public static final String EXCHANGE = "EXCHANGE_DEMO_02";
    public static final String ROUTING_KEY = "ROUTING_KEY_02";

    private Integer id;

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }
}
