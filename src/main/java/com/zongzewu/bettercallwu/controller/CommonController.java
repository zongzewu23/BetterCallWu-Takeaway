package com.zongzewu.bettercallwu.controller;

import com.zongzewu.bettercallwu.common.R;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.UUID;

/**
 * file upload and download
 */
@RestController
@RequestMapping("/common")
@Slf4j
public class CommonController {
    @Value("${BCW.path}")
    private String basePath;
    @PostMapping("/upload")
        public R<String> upload(MultipartFile file){
        //file is a temporary file, need to transfer to somewhere
        log.info(file.toString());
        //get original file name
        String originalFilename = file.getOriginalFilename();
       String suffix = originalFilename.substring(originalFilename.lastIndexOf("."));


        // use UUID to prevent file name duplicate
        String fileName = UUID.randomUUID().toString() + suffix;
        //create a directory object
        File dir = new File(basePath);
        if(!dir.exists()){
            dir.mkdirs();
        }
        try {
            //transfer this temporary file to a new directory
            file.transferTo(new File((basePath + fileName)));
        } catch (IOException e) {
           e.printStackTrace();
        }

            return R.success(fileName);
        }

    /**
     * download file
     * @param name
     * @param response
     */
    @GetMapping("/download")
    public void download(String name, HttpServletResponse response){

        try {
            //input stream, read the file content
            FileInputStream fileInputStream = new FileInputStream(new File(basePath + name));

            //output stream, write back this file to browser
            ServletOutputStream outputStream = response.getOutputStream();

            response.setContentType("image/jpeg");

            byte[] bytes = new byte[1024];
            int len = 0;
           while ((len = fileInputStream.read(bytes)) != -1){
               outputStream.write(bytes, 0, len);
               outputStream.flush();
           }
           outputStream.close();
           fileInputStream.close();
        } catch (Exception e) {
            e.printStackTrace();
        }



    }
}
