package com.sk.scott.message;

import java.io.Serializable;

public class ManualAckMessage implements Serializable {
    public static final String QUEUE = "QUEUE_MANUAL_ACK_01";
    public static final String EXCHANGE = "EXCHANGE_MANUAL_ACK_01";
    public static final String ROUTING_KEY = "ROUTING_MANUAL_ACK_01";

    private Integer id;

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }
}
