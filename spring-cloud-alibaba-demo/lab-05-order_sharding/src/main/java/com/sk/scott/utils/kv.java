package com.sk.scott.utils;

public class kv {
    private String key;

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getVal(){
        return val;
    }

    public void setVal(String val){
        this.val =val;
    }

    String val;
    public kv(String key,String val){
        this.key = key;
        this.val = val;
    }
}
