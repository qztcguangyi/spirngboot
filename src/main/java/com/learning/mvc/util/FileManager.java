package com.learning.mvc.util;

import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

//处理文件上传下载的工具类
public class FileManager {

	
    //上传文件
    public int uploadFile(String fileName, String parentpath, MultipartFile uploadfile){
        //String fileName = uploadfile.getOriginalFilename();
        File dest = new File(parentpath + "/" + fileName);
        System.out.println(parentpath + "/" + fileName);
        if(!dest.getParentFile().exists()){ //判断文件父目录是否存在
            dest.getParentFile().mkdirs();
        }
        System.out.println("指导文件名："+fileName);
        System.out.println("文件大小："+uploadfile.getSize());
        try {
            uploadfile.transferTo(dest); //保存文件
            return 1;

        } catch (IllegalStateException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            return 0;
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            return 0;
        }

    }

    public boolean delFiles(String parentpath) {
        return true;
    }
}
