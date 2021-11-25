package com.demo.jmx;

//该类名称必须与实现的接口的前缀保持一致
public class Hello implements HelloMBean {
    private String name;
    private int age;
    public String getName() {return name;}
    public void setName(String name) {this.name = name;}
    public int getAge() {return age;}
    public void setAge(int age) {this.age = age;}
    public void helloWorld() {
        System.out.println("Hello World");
    }
    public void helloWorld(String str) {
        System.out.println("Hello " + str);
    }
    public void print() {
        System.out.println("name=" + name + ", age=" + age);
    }
}