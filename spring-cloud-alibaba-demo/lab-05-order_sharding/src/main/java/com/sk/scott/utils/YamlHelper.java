package com.sk.scott.utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class YamlHelper {
    static final String getURI(){
        InputStream inputStream = YamlHelper.class
                .getClassLoader()
                .getResourceAsStream("server.yaml");

        BufferedReader in2=new BufferedReader(new InputStreamReader(inputStream));
        String y;
        while(true){
            try {
                if ((y = in2.readLine()) == null) break;
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            //一行一行读
            if ( y.trim().startsWith("server-lists")){
                if (!y.isEmpty()) {
                    if (y.contains(":")) {

                        String uri =  y.split("#")[0].
                                replaceAll("server-lists:",
                                        "").trim();
                        System.out.println(uri+"snowflake-gen-uri");
                        return uri;
                    }
                }
            }

        }
        return null;
    }

    static final String getType(){
        InputStream inputStream = YamlHelper.class
                .getClassLoader()
                .getResourceAsStream("server.yaml");

        BufferedReader in2=new BufferedReader(new InputStreamReader(inputStream));
        String y;
        while(true){
            try {
                if ((y = in2.readLine()) == null) break;
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            //一行一行读
            if ( y.trim().startsWith("type") && (y.toLowerCase().contains("etcd")||
                    y.toLowerCase().contains("zookeeper"))){
                if (!y.isEmpty()) {
                    if (y.contains(":")) {

                        String uri =  y.split("#")[0].
                                replaceAll("type:",
                                        "").trim();
                        System.out.println(uri);
                        return uri;
                    }
                }
            }

        }
        return null;
    }
}
