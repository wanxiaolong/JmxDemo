package com.demo.jmx;

import javax.management.Attribute;
import javax.management.MBeanServerConnection;
import javax.management.MBeanServerInvocationHandler;
import javax.management.ObjectName;
import javax.management.remote.JMXConnector;
import javax.management.remote.JMXConnectorFactory;
import javax.management.remote.JMXServiceURL;
import java.util.Arrays;

public class Client {
    public static void main(String[] args) throws Exception {
        JMXServiceURL url = new JMXServiceURL(HelloAgent2.JMX_URL);
        JMXConnector connector = JMXConnectorFactory.connect(url);
        MBeanServerConnection connection = connector.getMBeanServerConnection();

        String[] domains = connection.getDomains();
        System.out.println("Domains: " + Arrays.toString(domains));
        System.out.println("MBean count: " + connection.getMBeanCount());

        //ObjectName的名称与前面注册时候的保持一致
        ObjectName mbeanName = new ObjectName(HelloAgent.MBEAN_NAME);
        //设置属性。注意这里的属性名首字母大写，否则会报找不到属性的异常。
        connection.setAttribute(mbeanName, new Attribute("Name", "北京"));
        connection.setAttribute(mbeanName, new Attribute("Age", 1600));
        //获取属性
        String name = (String)connection.getAttribute(mbeanName, "Name");
        int age = (int)connection.getAttribute(mbeanName, "Age");
        System.out.println("Name=" + name + ", Age=" + age);

        //方法调用方式一：通过Java反射注入
        connection.invoke(mbeanName, "helloWorld",
                new String[]{"Boy"}, new String[]{"java.lang.String"});

        //方法调用方式二：通过代理对象直接调用方法
        HelloMBean proxy = MBeanServerInvocationHandler
                .newProxyInstance(connection, mbeanName, HelloMBean.class, false);
        proxy.helloWorld();
        proxy.helloWorld("Dog");
        proxy.print();
    }
}