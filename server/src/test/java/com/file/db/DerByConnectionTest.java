package com.file.db;

import com.file.entity.UploadFile;
import org.junit.Test;

import java.sql.*;
import java.util.List;

public class DerByConnectionTest {

    private FileService fileService = new FileService();

    @Test
    public void testFileList() throws  SQLException {
        List<UploadFile> fileList = fileService.getFileList();
        System.out.println(fileList);
    }

    @Test
    public void testgetFile() throws  SQLException {
        UploadFile file = fileService.getFile("0dc8b4d7-0371-4aa2-89e7-4540a12bb0dd");
        System.out.println(file);
    }

    @Test
    public void insertFile() throws  SQLException {
        UploadFile file = new UploadFile();
        fileService.insertFile(file);
        System.out.println(file);
    }

}
