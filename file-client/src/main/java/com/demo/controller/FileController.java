package com.demo.controller;

import com.alibaba.fastjson.JSONObject;
import com.demo.pojo.UploadFile;
import com.demo.util.FileUtil;
import com.demo.util.OkHttpClientUtil;
import com.demo.util.RSAHeadersUtil;
import okhttp3.Response;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 文件管理
 */
@Controller
public class FileController {

    Logger logger = Logger.getLogger(FileController.class);

    @RequestMapping("fileList")
    public String fileList(HttpServletRequest request, Map map) throws Exception {
        String url = "http://localhost:8080/server/fileList";
        Map headers = RSAHeadersUtil.getRSAHeaders();
        logger.info("访问服务端接口（文件列表）："+url);
        Response response = OkHttpClientUtil.get(url, headers);
        String respString = response.body().string();
        List<UploadFile> uploadFiles = JSONObject.parseArray(respString,UploadFile.class);
        map.put("list",uploadFiles);
        return "file_list";
    }


    @RequestMapping("uploadFile")
    public String uploadFile(@RequestParam("fileName") MultipartFile file) throws Exception {
        Map<String,String> headers = RSAHeadersUtil.getRSAHeaders();
        String url = "http://localhost:8080/server/file";
        String originalFilename = file.getOriginalFilename();
        String fileName = originalFilename.substring(0, originalFilename.lastIndexOf("."));
        String suffix = originalFilename.substring(originalFilename.lastIndexOf("."));
        InputStream inputStream = file.getInputStream();
        //创建临时文件进行传输
        File tempFile = File.createTempFile(fileName+"abc",suffix);
        FileUtil.inputStreamToFile(inputStream, tempFile);
        inputStream.close();
        System.out.println(originalFilename);
        headers.put("Content-Disposition","form-data; name=\"file\"; filename='"+originalFilename+"'");
        System.out.println(headers.get("Content-Disposition"));
        logger.info("访问服务端接口（上传文件）："+url);
        OkHttpClientUtil.postFileRequest(url,tempFile,originalFilename,null,headers);


        //删除
        tempFile.deleteOnExit();
        return "redirect:/fileList";
    }

    @RequestMapping("download")
    public String download(String uuid, HttpServletResponse response) throws Exception {
        Map<String,String> headers = RSAHeadersUtil.getRSAHeaders();
        String downloadUrl = "http://localhost:8080/server/file?uuid="+uuid;
        String getFileUrl = "http://localhost:8080/server/getFile?uuid="+uuid;
        logger.info("访问服务端接口（下载文件）："+downloadUrl);
        Response fileresponse = OkHttpClientUtil.get(getFileUrl, headers);
        UploadFile uploadFile = JSONObject.parseObject(fileresponse.body().string(), UploadFile.class);
        logger.info("访问服务端接口（获得文件）："+getFileUrl);
        Response downloadFileResponse = OkHttpClientUtil.get(downloadUrl, headers);
        InputStream inputStream = downloadFileResponse.body().byteStream();
        response.setHeader("Content-Disposition", "attachment; filename="+uploadFile.getOldName());


        ServletOutputStream out = response.getOutputStream();
        byte[] bt = new byte[1024];
        int length = 0;
        while((length=inputStream.read(bt))!=-1){
            out.write(bt,0,length);
        }
        return null;
    }

}
