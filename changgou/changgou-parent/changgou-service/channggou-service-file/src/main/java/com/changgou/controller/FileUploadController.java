package com.changgou.controller;


import com.changgou.file.FastDFSFile;
import com.changgou.util.FastDFSUtil;
import entity.Result;
import entity.StatusCode;
import org.csource.common.MyException;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController    ///类同于responBody+controller
@RequestMapping(value="/upload")
@CrossOrigin  //跨域
public class FileUploadController {

    /**
     * 文件上传
     */
    @PostMapping
    public Result upload(@RequestParam(value = "file")MultipartFile file) throws Exception {
//        file.transferTo(); 以前上传文件的方法
          FastDFSFile fastDFSFile = new FastDFSFile(
                  file.getOriginalFilename(), //文件名
                  file.getBytes(),  //文件字节数组
                  StringUtils.getFilenameExtension(file.getOriginalFilename()) //文件拓展名
          );
          String[] uploads = FastDFSUtil.upload(fastDFSFile);
          String url = "http://192.168.10.132:8080/"+uploads[0]+"/"+uploads[1];
//          System.out.println("url:"+url);
        return  new Result(true, StatusCode.OK,"上传成功",url);

    }
}
