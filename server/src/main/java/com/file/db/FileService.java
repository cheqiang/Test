package com.file.db;

import com.file.entity.UploadFile;

import java.sql.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 *
 * derby file表数据库操作
 *
 */
public class FileService {

    private Connection connection = DerByConnection.getConnection();

    /**
     * 插入文件数据
     */
    public boolean insertFile(UploadFile file) throws SQLException {
        String sql = "INSERT INTO file(uuid,size,type,name,old_name,create_time,path) VALUES (?,?,?,?,?,?,?)";
        PreparedStatement psm = connection.prepareStatement(sql);
        psm.setString(1,file.getUuid());
        psm.setInt(2,file.getSize());
        psm.setString(3,file.getType());
        psm.setString(4,file.getName());
        psm.setString(5,file.getOldName());
        psm.setTimestamp(6,new Timestamp(file.getCreateTime().getTime()));
        psm.setString(7,file.getPath());
        boolean execute = psm.execute();
        return execute;
    }


    /**
     * 文件列表(前10个)
     */
    public List<UploadFile> getFileList() throws SQLException {
        List<UploadFile> uploadFiles = new ArrayList<>();
        Statement sm = connection.createStatement();
        ResultSet rs=sm.executeQuery("SELECT * FROM file ORDER BY create_time DESC ");
        while(rs.next()){
            UploadFile uploadFile = new UploadFile();
            uploadFile.setId(rs.getInt("ID"));
            uploadFile.setSize(rs.getInt("size"));
            uploadFile.setUuid(rs.getString("uuid"));
            uploadFile.setType(rs.getString("type"));
            uploadFile.setName(rs.getString("name"));
            uploadFile.setOldName(rs.getString("old_name"));
            uploadFile.setCreateTime(new Date(rs.getTimestamp("create_time").getTime()));
            uploadFile.setPath(rs.getString("path"));
            uploadFiles.add(uploadFile);
        }
        return uploadFiles;
    }


    /**
     * 查询文件(单个)
     */
    public UploadFile getFile(String uuid) throws SQLException {
        UploadFile uploadFile = null;
        Statement sm = connection.createStatement();
        ResultSet rs=sm.executeQuery("SELECT * FROM file WHERE uuid="+"'"+uuid+"'");
        while(rs.next()){
            uploadFile = new UploadFile();
            uploadFile.setId(rs.getInt("ID"));
            uploadFile.setSize(rs.getInt("size"));
            uploadFile.setUuid(rs.getString("uuid"));
            uploadFile.setType(rs.getString("type"));
            uploadFile.setName(rs.getString("name"));
            uploadFile.setOldName(rs.getString("old_name"));
            uploadFile.setCreateTime(new Date(rs.getTimestamp("create_time").getTime()));
            uploadFile.setPath(rs.getString("path"));
        }
        return uploadFile;
    }


}
