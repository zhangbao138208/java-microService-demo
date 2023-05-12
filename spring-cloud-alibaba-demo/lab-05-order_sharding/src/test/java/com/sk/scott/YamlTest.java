package com.sk.scott;

import org.junit.Test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;



public class YamlTest {


    @Test
    public void TestYaml2(){
        InputStream inputStream = this.getClass()
                .getClassLoader()
                .getResourceAsStream("server.yaml");

//        Map<String, Object> obj = yaml.load(inputStream);
//        System.out.println(obj);
        BufferedReader in2=new BufferedReader(new InputStreamReader(inputStream));
        String y="";
        while(true){
            try {
                if (!((y=in2.readLine())!=null)) break;
            } catch (IOException e) {
                throw new RuntimeException(e);
            }//一行一行读
           if ( y.trim().startsWith("server-lists")){
               if (!y.isEmpty()) {
                   if (y.contains(":")) {

                       System.out.println( y.split("#")[0].
                               replaceAll("server-lists:",
                               "").trim());
                   }
               }
               System.out.println(y);
           }

        }
    }
}
