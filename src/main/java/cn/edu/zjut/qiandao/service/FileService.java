package cn.edu.zjut.qiandao.service;

import cn.edu.zjut.qiandao.conf.Configuration;
import cn.edu.zjut.qiandao.utils.Result;
import cn.edu.zjut.qiandao.utils.Results;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.util.UUID;
@Service
public class FileService {
    @Autowired
    Configuration configuration;
    public Result dofileupload(MultipartFile file){
        if (!file.isEmpty()) {
            String media = UUID.randomUUID().toString().replaceAll("-", "");
            String filename = configuration.getUploadpath() + media + ".jpg";
            File saveFile = new File(filename);
        //    File saveFile = new File();

            try {
                BufferedOutputStream out = new BufferedOutputStream(new FileOutputStream(saveFile));
                out.write(file.getBytes());
                out.flush();
                out.close();
                return Results.success(null);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
                return Results.error(100,"上传失败");
            } catch (IOException e) {
                e.printStackTrace();
                return Results.error(105,"上传失败");
            }
        } else {
            return Results.error(110,"上传失败，图片为空");
        }

    }
}
