package com.learning.mvc.controller;
 
 
import com.alibaba.fastjson.JSONObject;
import com.learning.mvc.util.FileManager;
import com.learning.mvc.util.PlatPropUtil;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.util.HashMap;
import java.util.Map;

@RestController
public class FileController {

    //上传老师要发布的毕设的要求
    @RequestMapping("fileUpload")
    public JSONObject fileUpload(MultipartFile file){
        JSONObject json = new JSONObject();
        if(file.isEmpty()){
            json.put("state","文件为空");
        }
        else json.put("state","上传成功");
        System.out.println("后台接受到的上传文件名及大小:"+file.getOriginalFilename()+","+file.getSize());
        FileManager filemanager=new FileManager();
        String parentpath = PlatPropUtil.getDynamicPropertyValue("application.properties","fiel.path");
        //先将文件缓冲区里的文件清空
        if(filemanager.delFiles(parentpath))
            System.out.println("文件删除成功");
        else
            System.out.println("文件删除失败或者不存在该路径");
        //再保存上传的文件
        if(filemanager.uploadFile(file.getOriginalFilename(),parentpath,file)==1)
            System.out.println("文件保存成功");
        else
            System.out.println("文件保存失败");


        return json;
    }



}