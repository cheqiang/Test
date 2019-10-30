package com.demo.pojo;

import java.util.Date;

/**
 *
 * 对应数据库file
 */
public class UploadFile {

    private Integer id;

    private String uuid;

    /**
     * 文件大小
     */
    private Integer size;

    /**
     * 文件类型
     */
    private String type;

    /**
     * 文件名
     */
    private String name;


    /**
     * 源文件名
     */
    private String oldName;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 文件路径
     */
    private String path;

    public UploadFile() {
    }

    public UploadFile(String uuid, Integer size, String type, String name, String oldName, Date createTime, String path) {
        this.uuid = uuid;
        this.size = size;
        this.type = type;
        this.name = name;
        this.oldName = oldName;
        this.createTime = createTime;
        this.path = path;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public Integer getSize() {
        return size;
    }

    public void setSize(Integer size) {
        this.size = size;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getOldName() {
        return oldName;
    }

    public void setOldName(String oldName) {
        this.oldName = oldName;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }
}
