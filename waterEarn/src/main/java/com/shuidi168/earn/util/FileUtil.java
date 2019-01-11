package com.shuidi168.earn.util;

import java.io.File;

import org.springframework.util.StringUtils;

public class FileUtil {

    public static void mkdirs(String dir){
        if(StringUtils.isEmpty(dir)){
            return;
        }
        
        File file = new File(dir);
        if(file.isDirectory()){
            return;
        } else {
            file.mkdirs();
        }
    }
}