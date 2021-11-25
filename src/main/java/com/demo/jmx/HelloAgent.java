package com.demo.jmx;

import javax.management.JMException;
import javax.management.MBeanServer;
import javax.management.ObjectName;
import java.lang.management.ManagementFactory;

public class HelloAgent {

    public static final String MBEAN_NAME = "com.demo.jmx:name=Hello";

    public static void main(String[] arges) throws JMException, InterruptedException {
        //通过工厂类获取MBeanServer，用来做MBean的容器。
        MBeanServer server = ManagementFactory.getPlatformMBeanServer();
        //ObjectName中的取名是有一定规范的，格式为：“域名:name=MBean名称”，
        //其中域名和MBean的名称可以任意取。这样定义后，就可以唯一标识我们定义的这个MBean的实现类了。
        ObjectName helloName = new ObjectName(MBEAN_NAME);
        //将MBean注册到MBeanServer中
        server.registerMBean(new Hello(), helloName);
        System.out.println("Application Started.");
        Thread.sleep(60 * 60 * 1000);
    }
}