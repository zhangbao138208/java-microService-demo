package com.sk.scott.utils;

import io.etcd.jetcd.watch.WatchEvent;

@FunctionalInterface
public interface Watchable {
    void handle(WatchEvent.EventType type, String key, String value);
}
