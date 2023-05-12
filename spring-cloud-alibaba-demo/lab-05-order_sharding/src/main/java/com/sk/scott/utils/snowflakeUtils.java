package com.sk.scott.utils;

import io.etcd.jetcd.options.GetOption;

import java.util.Collection;
import java.util.List;

public interface snowflakeUtils {
    void watchKey(String keyString, Watchable watchable);
    List<kv> getKVByKey(String key);
}
