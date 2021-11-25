package com.demo.jmx.notification;

//该类名称必须与实现的接口的前缀保持一致
public class Doris implements DorisMBean {
    public void print(String str) {
        System.out.println(str);
    }
}