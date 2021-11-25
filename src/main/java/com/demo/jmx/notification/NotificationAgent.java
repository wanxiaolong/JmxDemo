package com.demo.jmx.notification;

import javax.management.MBeanServer;
import javax.management.ObjectName;
import java.lang.management.ManagementFactory;

public class NotificationAgent {
    public static void main(String[] args) throws Exception {
        MBeanServer server = ManagementFactory.getPlatformMBeanServer();

        //将DorisMBean注册到MBeanServer中
        Doris doris = new Doris();
        server.registerMBean(doris, new ObjectName("com.demo.jmx:name=Doris"));

        //将JackMBean注册到MBeanServer中
        Jack jack = new Jack();
        server.registerMBean(jack, new ObjectName("com.demo.jmx:name=Jack"));

        //Jack用HelloListener监听Notification。
        //这里会把doris这个对象发给HelloListener，因此在HelloListener里面可以获取到doris这个对象
        jack.addNotificationListener(new HelloListener(), null, doris);

        System.out.println("Application Started.");
        Thread.sleep(60 * 60 * 1000);
    }
}
