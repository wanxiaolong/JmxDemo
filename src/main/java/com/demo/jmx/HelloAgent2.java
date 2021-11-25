package com.demo.jmx;

import javax.management.JMException;
import javax.management.MBeanServer;
import javax.management.ObjectName;
import javax.management.remote.JMXConnectorServer;
import javax.management.remote.JMXConnectorServerFactory;
import javax.management.remote.JMXServiceURL;
import java.io.IOException;
import java.lang.management.ManagementFactory;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;

public class HelloAgent2 {

    public static final String JMX_URL = "service:jmx:rmi:///jndi/rmi://localhost:9999/jmxrmi";
    public static void main(String[] arges) throws JMException {
        //通过工厂类获取MBeanServer，用来做MBean的容器。
        MBeanServer server = ManagementFactory.getPlatformMBeanServer();
        //ObjectName中的取名是有一定规范的，格式为：“域名:name=MBean名称”，
        //其中域名和MBean的名称可以任意取。这样定义后，就可以唯一标识我们定义的这个MBean的实现类了。
        ObjectName helloName = new ObjectName("com.demo.jmx:name=Hello");
        //将MBean注册到MBeanServer中
        server.registerMBean(new Hello(), helloName);

        try {
            //这个步骤很重要，注册一个端口，绑定url后用于客户端通过rmi方式连接JMXConnectorServer
            LocateRegistry.createRegistry(9999);
            //URL路径的结尾可以随意指定，但如果需要用Jconsole来进行连接，则必须使用jmxrmi
            JMXServiceURL url = new JMXServiceURL(JMX_URL);
            JMXConnectorServer jcs = JMXConnectorServerFactory.newJMXConnectorServer(url, null, server);
            jcs.start();
            System.out.println("JMX server started.");
        } catch (RemoteException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}