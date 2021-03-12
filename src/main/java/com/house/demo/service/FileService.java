package com.house.demo.service;

import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

/**
 * @author xjj
 */
public interface FileService {

    String storeFile(MultipartFile file);

    Resource loadFileAsResource(String fileName);


}
