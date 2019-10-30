package com.file.server.servlet;

import com.alibaba.fastjson.JSON;
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
import java.util.List;

/**
 * 获取文件列表
 */
public class FileListServlet extends HttpServlet {

    Logger logger = Logger.getLogger(FileListServlet.class);
    private FileService fileService = new FileService();

    /**
     * 获取10个文件json
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        logger.info("调用server FileList请求");
        resp.setContentType("text/json; charset=utf-8");
        PrintWriter writer = resp.getWriter();
        List<UploadFile> fileList = null;
        try {
            fileList = fileService.getFileList();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        writer.print(JSON.toJSONString(fileList));
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }
}
