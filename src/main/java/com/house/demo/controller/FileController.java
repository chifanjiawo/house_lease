package com.house.demo.controller;

import com.house.demo.common.response.UploadFileResponse;
import com.house.demo.service.FileService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author xjj
 */
@Slf4j
@RestController
@RequestMapping("file")
public class FileController {

    @Autowired
    private FileService fileService;

    @PostMapping("upload")
    public UploadFileResponse uploadFile(@RequestParam("file")MultipartFile file){



        String fileName = fileService.storeFile(file);

        String fileDownloadUri = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path("/downloadFile/")
                .path(fileName)
                .toUriString();

        return new UploadFileResponse(fileName, fileDownloadUri,
                file.getContentType(), file.getSize());

    }

    @PostMapping("loadMulti")
    public List<UploadFileResponse> multiupload(@RequestParam("files")MultipartFile[] files){

        List<UploadFileResponse> list = new ArrayList<>();
        if (files != null) {
            for (MultipartFile multipartFile:files) {
                UploadFileResponse uploadFileResponse = uploadFile(multipartFile);
                list.add(uploadFileResponse);
            }
        }
        return list;

    }

    @GetMapping("download/{fileName:.*}")
    public ResponseEntity<Resource> downFile(@PathVariable String fileName, HttpServletRequest request){
        Resource resource = fileService.loadFileAsResource(fileName);
        String contentType = null;
        try {
            request.getServletContext().getMimeType(resource.getFile().getAbsolutePath());
        } catch (IOException e) {
            log.info("Could not determine file type.");
        }
        if(contentType == null) {
            contentType = "application/octet-stream";
        }
        return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType(contentType))
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + resource.getFilename() + "\"")
                .body(resource);


    }


}
