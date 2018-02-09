package com.java.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.*;

@Controller
public class UploadController {

    @GetMapping("/upload")
    public String upload(){
        return "upload";
    }

    @PostMapping("/hello")
    @ResponseBody
    public String hello(@RequestParam CommonsMultipartFile file, HttpServletRequest request) {
        Long t1 = System.currentTimeMillis();
        if (file == null)
            return null;
        String filename = file.getOriginalFilename();
        File uploadFile = new File(request.getSession().getServletContext().getRealPath("/springUpload"));
        if (!uploadFile.exists() || !uploadFile.isDirectory())
            uploadFile.mkdir();
        File photo = new File(uploadFile, filename);
        try {
            file.transferTo(photo);
        } catch (IOException e) {
            e.printStackTrace();
        }
        Long t2 = System.currentTimeMillis();
        System.out.println(t2-t1);
        return "success";
    }

    /**
     * spring mvc流的方式上传图片
     * @param ioFile
     * @param request
     * @return
     */
    @PostMapping("/springIoUpload")
    @ResponseBody
    public String springIoUpload(@RequestParam CommonsMultipartFile ioFile, HttpServletRequest request) {
        Long start = System.currentTimeMillis();
        OutputStream outputStream;
        File uploadFile = new File(request.getSession().getServletContext().getRealPath("/springUpload"));
        if (!uploadFile.exists()) {
            uploadFile.mkdir();
        }
        try {
            outputStream = new FileOutputStream(request.getSession().getServletContext().getRealPath("/springUpload") + "/" + ioFile.getOriginalFilename());
            InputStream inputStream = ioFile.getInputStream();
            int temp;
            while ((temp = inputStream.read()) != -1) {
                outputStream.write(temp);
            }
            outputStream.flush();
            outputStream.close();
            inputStream.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        Long end = System.currentTimeMillis();
        System.out.println(end - start);
        return "success";
    }
}
