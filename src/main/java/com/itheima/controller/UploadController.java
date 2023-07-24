package com.itheima.controller;

import com.itheima.pojo.Result;
import com.itheima.utils.AliOSSUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

/**
 * ClassName: UploadController
 * Package: com.itheima.controller
 * Description:
 *
 * @Author dhf
 * @Create 2023/7/21 0:48
 * @Version 17.0.6
 */
@Slf4j
@RestController
public class UploadController {
    @Autowired
    private AliOSSUtils aliOSSUtils;
    //本地存储文件
   /* @PostMapping("/upload")
    public Result upload(String username, Integer age, MultipartFile image) throws IOException {
        log.info("文件上传：{},{},{}",username,age,image);
        String originalFilename = image.getOriginalFilename();
        int index = originalFilename.lastIndexOf(".");
        String substring = originalFilename.substring(index);
        String newFileName = UUID.randomUUID().toString() + substring;
        log.info("新的文件：{}",newFileName);
        image.transferTo(new File("D:\\image\\"+newFileName));

        return Result.success();
    }*/

    @PostMapping("/upload")
    public Result upload(MultipartFile image) throws IOException {
        log.info("文件上传，文件名：{}",image.getOriginalFilename());
        //将图片上传到阿里云并且返回访问url
        String url = aliOSSUtils.upload(image);
        log.info("文件访问：{}",url);
        return Result.success(url);
    }

}
