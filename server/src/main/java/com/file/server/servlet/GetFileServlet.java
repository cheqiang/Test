package com.file.server.servlet;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.file.db.FileService;
import com.file.entity.UploadFile;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * 单个文件信息，获取文件的元数据
 */
public class GetFileServlet extends HttpServlet {

    Logger logger = Logger.getLogger(GetFileServlet.class);
    private FileService fileService = new FileService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        logger.info("调用server GetFileServlet请求  ");
        String uuid = req.getParameter("uuid");
        resp.setContentType("text/json; charset=utf-8");
        PrintWriter writer = resp.getWriter();
        UploadFile file = null;
        //查询文件元数据
        try {
            file = fileService.getFile(uuid);
            if(file == null){
                Map map = new HashMap();
                map.put("message","no file!");
                String message = JSON.toJSONString(map);
                writer.print(message);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        logger.info("返回的结果： ");
        logger.info(JSON.toJSONString(file));
        writer.print(JSON.toJSONString(file));
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }
}

