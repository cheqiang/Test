package com.file.server.servlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * TODO:Responsibility description of this class.
 *
 * @author cheqiang
 * @version 1.0
 * @since 2019/10/26 0:30
 */
public class TestServlet extends HttpServlet {


    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        out.println("<HTML>");
        out.println("<HEAD><TITLE>test</TITLE></HEAD>");
        out.println("<BODY>");
        out.println("GET method");
        out.println("</BODY>");
        out.println("</HTML>");
        out.flush();
        out.close();

    }
    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        out.println("<HTML>");
        out.println("<HEAD><TITLE>test</TITLE></HEAD>");
        out.println("<BODY>");
        out.println("POST method");
        out.println("</BODY>");
        out.println("</HTML>");
        out.flush();
        out.close();
    }

}
