package com.house.demo.controller;

import com.house.demo.common.response.MyResult;
import com.house.demo.common.response.UploadFileResponse;

import com.house.demo.service.HouseOrderService;
import com.house.demo.service.HouseUserService;
import com.house.demo.utils.FileUploadUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * @author xjj
 */
@Slf4j
@RestController
@RequestMapping("file")
public class FileController {

    @Autowired
    private HouseOrderService orderService;

    @Autowired
    private HouseUserService userService;

    @PostMapping("upload")
    public MyResult uploadFile(@RequestParam("file")MultipartFile file,@RequestParam("houseOrderId")long id){


//        String fileName = fileService.storeFile(file);
//
//        String fileDownloadUri = ServletUriComponentsBuilder.fromCurrentContextPath()
//                .path("/downloadFile/")
//                .path(fileName)
//                .toUriString();

            String fileName = StringUtils.cleanPath(file.getOriginalFilename());

            String uploadDir = "order-photos/" + id;

            try {

                FileUploadUtil.saveFile(uploadDir, fileName, file);
                orderService.setUrl(id,"http://localhost:8081/"+uploadDir+"/"+fileName);
                System.out.println("保存成功");
            } catch (IOException e) {
                e.printStackTrace();
            }

//        if(orderService.setUrl(id,list)==0){
//            return MyResult.fail("上传失败");
//        }



        return MyResult.succ("上传成功");

    }


    @PostMapping("avatar")
    public MyResult setUserAvatar(@RequestParam("file")MultipartFile file,@RequestParam("userId")int id){

        String fileName = StringUtils.cleanPath(file.getOriginalFilename());

        String avatarDir  = "avatar-photos/"+id;


        try {
            FileUploadUtil.saveFile(avatarDir,fileName,file);
            userService.setUserAvatar(id,"http://localhost:8081/"+avatarDir+"/"+fileName);
        } catch (IOException e) {
            e.printStackTrace();
            return MyResult.fail("设置头像失败");
        }

        return MyResult.succ("设置成功");




    }

//    @PostMapping("loadMulti")
//    public List<UploadFileResponse> multiupload(@RequestParam("files")MultipartFile[] files){
//
//        List<UploadFileResponse> list = new ArrayList<>();
//        if (files != null) {
//            for (MultipartFile multipartFile:files) {
//                UploadFileResponse uploadFileResponse = uploadFile(files);
//                list.add(uploadFileResponse);
//            }
//        }
//        return list;
//
//    }

//    @GetMapping("download/{fileName:.*}")
//    public ResponseEntity<Resource> downFile(@PathVariable String fileName, HttpServletRequest request){
//        Resource resource = fileService.loadFileAsResource(fileName);
//        String contentType = null;
//        try {
//            request.getServletContext().getMimeType(resource.getFile().getAbsolutePath());
//        } catch (IOException e) {
//            log.info("Could not determine file type.");
//        }
//        if(contentType == null) {
//            contentType = "application/octet-stream";
//        }
//        return ResponseEntity.ok()
//                .contentType(MediaType.parseMediaType(contentType))
//                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + resource.getFilename() + "\"")
//                .body(resource);
//
//
//    }


}
