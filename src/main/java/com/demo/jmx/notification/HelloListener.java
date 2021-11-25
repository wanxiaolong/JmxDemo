package com.demo.jmx.notification;

import javax.management.Notification;
import javax.management.NotificationListener;

public class HelloListener implements NotificationListener {
    @Override
    public void handleNotification(Notification notification, Object handback) {
        //如果是由Doris来处理Notification
        if (handback instanceof Doris) {
            Doris doris = (Doris) handback;
            doris.print("Doris处理Notification：" + notification.getMessage());
        }
    }
}
