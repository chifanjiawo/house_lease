package com.house.demo.service.impl;

import com.house.demo.common.exception.FileException;
import com.house.demo.service.FileService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.Collection;
import java.util.Stack;

/**
 * @author xjj
 */
@Service
public class FileServiceImpl implements FileService {

    private Path fileStorageLocation;


    public FileServiceImpl(@Value("${file.filePath}")String path) {


        StringBuilder builder;

        this.fileStorageLocation = Paths.get(path).toAbsolutePath().normalize();

//        System.out.println(path);
//        try {
//            Files.createDirectories(this.fileStorageLocation);
//        } catch (IOException e) {
//            throw new FileException("could not create directory",e);
//        }

    }

    @Override
    public String storeFile(MultipartFile file) {
        String fileName = StringUtils.cleanPath(file.getOriginalFilename());

        try {
            // Check if the file's name contains invalid characters
            if(fileName.contains("..")) {
                throw new FileException("Sorry! Filename contains invalid path sequence " + fileName);
            }

            // Copy file to the target location (Replacing existing file with the same name)
            Path targetLocation = this.fileStorageLocation.resolve(fileName);

            Files.copy(file.getInputStream(), targetLocation, StandardCopyOption.REPLACE_EXISTING);

            return fileName;
        } catch (IOException ex) {
            throw new FileException("Could not store file " + fileName + ". Please try again!", ex);
        }

    }

    @Override
    public Resource loadFileAsResource(String fileName) {

        Stack stack = new Stack();

        try {
            Path filePath = this.fileStorageLocation.resolve(fileName).normalize();
            Resource resource = new UrlResource(filePath.toUri());
            if(resource.exists()) {
                return resource;
            } else {
                throw new FileException("File not found " + fileName);
            }
        } catch (MalformedURLException ex) {
            throw new FileException("File not found " + fileName, ex);
        }

    }
}
