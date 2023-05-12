package com.sk.scott.message;

import java.io.Serializable;

public class Demo01Message implements Serializable {
    public static final String QUEUE = "QUEUE_DEMO_01";
    public static final String EXCHANGE = "EXCHANGE_DEMO_01";
    public static final String ROUTING_KEY = "ROUTING_KEY_01";

    private Integer id;

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }
}
