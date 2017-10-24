package com.imooc.web.controller;

import com.imooc.dto.FileInfo;
import org.apache.catalina.servlet4preview.http.HttpServletRequest;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.Date;

/**
 * Created by zkr on 2017/10/23.
 */
@RestController
@RequestMapping("/file")
public class FileController {

    @Value("${spring.upload.path}")
    public String ROOT;

    @PostMapping
    public FileInfo upload(MultipartFile file) throws IOException {

        System.out.println(file.getName());
        System.out.println(file.getOriginalFilename());
        System.out.println(file.getSize());
        System.out.println("上传文件的profile路径：" + ROOT);

        String classpath = "G:/ideaWorkspace/workspace/springboot/imooc-security/imooc-security-demo/src/main/resources";
        System.out.println("上传文件的real路径：" + classpath + "/upload");

        File localFile = new File(classpath,new Date().getTime()+".txt");

        file.transferTo(localFile);

        FileInfo fileInfo = new FileInfo(localFile.getAbsolutePath());
        return fileInfo;
    }

    @GetMapping("/{id}")
    public void download(@PathVariable String id, HttpServletRequest request, HttpServletResponse response) throws Exception {

        String classpath = "G:/ideaWorkspace/workspace/springboot/imooc-security/imooc-security-demo/src/main/resources";

        try(InputStream inputStream = new FileInputStream(new File(classpath,id + ".txt"));
             OutputStream outputSteam = response.getOutputStream()) {
            response.setContentType("application/x-download");
            response.addHeader("Content-Disposition","attachment;filename=test.txt");

            IOUtils.copy(inputStream,outputSteam);
            outputSteam.flush();
        }

    }


}
