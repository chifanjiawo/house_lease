package com.house.demo.common.response;

import lombok.Data;

/**
 * @author xjj
 */

public class UploadFileResponse {

    private String fileName;
    private String fileUrl;

    private String fileTyrpe;
    private long size;

    public UploadFileResponse() {
    }

    public UploadFileResponse(String fileName, String fileUrl, String fileTyrpe, long size) {
        this.fileName = fileName;
        this.fileUrl = fileUrl;
        this.fileTyrpe = fileTyrpe;
        this.size = size;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getFileUrl() {
        return fileUrl;
    }

    public void setFileUrl(String fileUrl) {
        this.fileUrl = fileUrl;
    }

    public String getFileTyrpe() {
        return fileTyrpe;
    }

    public void setFileTyrpe(String fileTyrpe) {
        this.fileTyrpe = fileTyrpe;
    }

    public long getSize() {
        return size;
    }

    public void setSize(long size) {
        this.size = size;
    }
}
