package com.house.demo.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * @author xjj
 */
@Configuration
public class MvcConfig implements WebMvcConfigurer {


    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {

        exposeDirectory("order-photos", registry);
        exposeDirectory("avatar-photos", registry);
    }

    private void exposeDirectory(String dirName, ResourceHandlerRegistry registry) {
        Path uploadDir = Paths.get(dirName);
        String uploadPath = uploadDir.toFile().getAbsolutePath();
        System.out.println(uploadPath);
        if (dirName.startsWith("../")) {dirName = dirName.replace("../", "");}

        registry.addResourceHandler("/" + dirName + "/**").addResourceLocations("file:"+ uploadPath + "/");
    }
}
