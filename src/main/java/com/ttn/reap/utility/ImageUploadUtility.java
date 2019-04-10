package com.ttn.reap.utility;

import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.Date;

@Component
public class ImageUploadUtility {

    private static final String absolute="/home/vineet/Downloads/reap/src/main/resources/static";
    private static final String realPath="images/profilePics";
    public String writeImage(MultipartFile multipartFile,String name){

        Path path=null;
        String[] str=multipartFile.getContentType().split("/");
        SimpleDateFormat simpleDateFormat=new SimpleDateFormat("yyMMdd_hhmmss");
        name+=simpleDateFormat.format(new Date());
        switch (str[1]){
            case "png":
                path= Paths.get(absolute,realPath,name+".png");
                break;
            case "jpeg":
                path=Paths.get(absolute,realPath,name+".jpeg");
        }
        try {
            Files.write(path,multipartFile.getBytes());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return realPath+"/"+name+"."+str[1];
    }
}
