package com.file.server.servlet;


import com.file.db.FileService;
import com.file.entity.UploadFile;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import java.io.*;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.UUID;

/**
 * 文件管理servlet
 */
public class FileServlet extends HttpServlet {

    Logger logger = Logger.getLogger(FileServlet.class);


    private static String location = "D:/file-server";

    private FileService fileService = new FileService();

    /**
     * 文件下载
     *
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String uuid = req.getParameter("uuid"); //客户端传递的需要下载的文件名
        logger.info("调用server FileServlet请求 下载文件 ：uuid"+uuid);
        UploadFile file = null;
        try {
            file = fileService.getFile(uuid);
            if(file==null){
                //如果没有返回值
                resp.setStatus(410);
                resp.getOutputStream().print("no file!");
                return;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        String path = file.getPath(); //默认认为文件在当前项目的根目录
        FileInputStream fis = new FileInputStream(path);
        resp.setCharacterEncoding("utf-8");
        resp.setHeader("Content-Disposition", "attachment; filename="+file.getName());
        ServletOutputStream out = resp.getOutputStream();
        byte[] bt = new byte[1024];
        int length = 0;
        while((length=fis.read(bt))!=-1){
            out.write(bt,0,length);
        }
        out.close();
    }


    /**
     * 文件上传
     *
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        logger.info("调用server FileServlet请求 上传文件");
        Part part = req.getPart("file");
        String disposition = part.getHeader("Content-Disposition");
        System.out.println(disposition);
        String suffix = disposition.substring(disposition.lastIndexOf("."), disposition.length() - 1);
        //原始文件名
        String oldName = disposition.replaceAll( ".*filename=\"|\"","");
        logger.info(oldName);
        //文件名   uuid + 文件类型   123132.png
        String uuid = UUID.randomUUID().toString();
        //uuid
        String filename = uuid + suffix;
        LocalDateTime dateTime = LocalDateTime.now();
        String dateFormat = dateTime.format(DateTimeFormatter.ofPattern("yyyyMMdd"));
        //判断路径是否存在
        File dir = new File(location+"/"+dateFormat);
        if(!dir.exists()){
            dir.mkdir();
        }
        //文件全路径
        String filePath = location + "/" + dateFormat + "/" +filename;
        System.out.println(filePath);

        //获取上传的文件输入流
        InputStream is = part.getInputStream();
        //文件大小
        int size = is.available();
        System.out.println(size);
        FileOutputStream fos = new FileOutputStream(filePath);

        byte[] bty = new byte[1024];
        int length = 0;
        while ((length = is.read(bty)) != -1) {
            fos.write(bty, 0, length);
        }
        //将上传文件数据存入数据库
        UploadFile uploadFile = new UploadFile(uuid,size,suffix,filename,oldName,new Date(),filePath);
        try {
            //存入derby数据库
            fileService.insertFile(uploadFile);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        //res返回数据
        resp.getOutputStream().print(filename);
        fos.close();
        is.close();
    }

}
