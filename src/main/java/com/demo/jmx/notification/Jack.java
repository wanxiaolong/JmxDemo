package com.demo.jmx.notification;

import javax.management.Notification;
import javax.management.NotificationBroadcasterSupport;

/**
 * 这个类不仅实现了MBean接口，还继承了NotificationBroadcasterSupport。
 * Jack在这里创建并发送了一个消息包。
 */
public class Jack extends NotificationBroadcasterSupport implements JackMBean {
    private int sequence = 0;
    public void hi(String msg) {
        long time = System.currentTimeMillis();
        //创建一个通知
        Notification notification = new Notification("jack.hi", this, ++sequence, time, msg);
        //发送通知
        System.out.println("Jack发送Notification：" + msg);
        sendNotification(notification);
    }
}