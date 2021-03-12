package com.house.demo.model;

import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @author xjj
 */
@ConfigurationProperties(prefix = "file")
public class FileProperty {

    private String filePath;

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }
}
