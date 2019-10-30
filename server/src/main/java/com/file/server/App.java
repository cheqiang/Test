package com.file.server;


import com.file.server.filter.PermissionFilter;
import com.file.server.servlet.FileListServlet;
import com.file.server.servlet.FileServlet;
import com.file.server.servlet.GetFileServlet;
import com.file.server.servlet.TestServlet;
import org.eclipse.jetty.server.Connector;
import org.eclipse.jetty.server.Handler;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.handler.DefaultHandler;
import org.eclipse.jetty.server.handler.HandlerCollection;
import org.eclipse.jetty.server.nio.SelectChannelConnector;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;

import javax.servlet.DispatcherType;
import javax.servlet.MultipartConfigElement;
import java.util.EnumSet;

/**
 *
 *  jetty嵌入式开发
 *
 */
public class App {

    public static void main(String[] args) throws Exception {
        initServer(8080,"/server");
    }

    public static void initServer(int port,String contextPath) throws Exception {

        Server server = new Server();
        server.setStopAtShutdown(true);
        SelectChannelConnector connector = new SelectChannelConnector();
        connector.setPort(port);
        connector.setReuseAddress(true);
        server.setConnectors(new Connector[] { connector });
        ServletContextHandler context = new ServletContextHandler();
        context.setContextPath(contextPath);
        addServletAndFilter(context);
        HandlerCollection handlers = new HandlerCollection();
        handlers.setHandlers(new Handler[] { context, new DefaultHandler() });
        server.setHandler(handlers);

        server.start();
        server.join();
    }

    /**
     * 加载servlet
     * @param context
     */
    private static void addServletAndFilter(ServletContextHandler context) {
        context.addServlet(TestServlet.class,"/test");
        //文件servlet设置multipartConfig
        ServletHolder fileUploadServletHolder = new ServletHolder(new FileServlet());
        fileUploadServletHolder.getRegistration().setMultipartConfig(new MultipartConfigElement("d:/file-server"));
        context.addServlet(fileUploadServletHolder,"/file");
        context.addServlet(GetFileServlet.class,"/getFile");
        context.addServlet(FileListServlet.class,"/fileList");
        //权限验证过滤器
        context.addFilter(PermissionFilter.class,"/*", EnumSet.of(DispatcherType.REQUEST));
    }
}
