package com.sk.scott.utils;

import redis.clients.jedis.JedisPubSub;

public class snowflakeSub extends JedisPubSub {

    @Override
    public void onMessage(String channel, String message) {
        System.out.printf("channel %s message %s\n",channel,message);
        workAndCenterIdGenHelp.refresh();
    }
}
