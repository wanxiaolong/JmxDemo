package com.demo.jmx;

//MBean的接口名必须以"MBean"作为结尾
public interface HelloMBean{
    String getName();
    void setName(String name);
    int getAge();
    void setAge(int age);
    void helloWorld();
    void helloWorld(String str);
    void print();
}