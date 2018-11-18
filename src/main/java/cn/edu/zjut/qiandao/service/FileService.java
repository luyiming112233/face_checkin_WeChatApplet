package cn.edu.zjut.qiandao.service;

import cn.edu.zjut.qiandao.conf.Configuration;
import cn.edu.zjut.qiandao.mapper.UserMapper;
import cn.edu.zjut.qiandao.utils.HttpClientUtils;
import cn.edu.zjut.qiandao.utils.Result;
import cn.edu.zjut.qiandao.utils.Results;
import com.alibaba.fastjson.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.util.UUID;
@Service
public class FileService {
    private final Logger log= LoggerFactory.getLogger(FileService.class);
    @Autowired
    Configuration configuration;
    //@Value(value = "")
    @Autowired
    UserMapper userMapper;
    public boolean signByFace(MultipartFile file,String stuid)throws FileNotFoundException,IOException{
        if (!file.isEmpty()) {
            String uuid = UUID.randomUUID().toString().replaceAll("-", "");
            String filename = configuration.getUploadpath() + uuid + ".jpg";
            File saveFile = new File(filename);
        //    File saveFile = new File();

                BufferedOutputStream out = new BufferedOutputStream(new FileOutputStream(saveFile));
                out.write(file.getBytes());
                out.flush();
                out.close();
                log.info("filename="+filename);
                String url=configuration.getGetsimilaryurl()+"?id="+stuid+"&&url="+configuration.getUploadurl()+uuid+".jpg";
              String similary=HttpClientUtils.doGet(url);
            //  System.out.println(similary);
                log.info("stuid="+stuid+" the similary is:"+similary);
              if(Double.parseDouble(similary)>0.8)
                  return true;
              else return false;
               // return Integer.parseInt(similary);
        } else {
            log.error("file empty");
            return false;
           // return Results.error(110,"上传失败，图片为空");
        }
        //return false;

    }
    @Transactional
    public Result registface(MultipartFile file,String stuid){
        if (!file.isEmpty()) {
            String uuid = UUID.randomUUID().toString().replaceAll("-", "");
            String filename = configuration.getRegisterfacepath() + uuid + ".jpg";
            File saveFile = new File(filename);
            //    File saveFile = new File();

            try {
                BufferedOutputStream out = new BufferedOutputStream(new FileOutputStream(saveFile));
                out.write(file.getBytes());
                out.flush();
                out.close();
                userMapper.registerface(stuid,uuid);
                log.info("registerfilename="+filename);
                String url=configuration.getGetfeatureurl()+"url="+configuration.getRegisterfaceurl()+uuid+".jpg";
                String feature=HttpClientUtils.doGet(url);
                log.info("register feature:"+feature);
                userMapper.updatefeature(stuid,feature);
                return Results.success(filename);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
                return Results.error(100,"上传失败");
            } catch (IOException e) {
                e.printStackTrace();
                return Results.error(105,"上传失败");
            }catch (Exception e){
                log.error("发生了异常"+e.getMessage());
                e.printStackTrace();
                return Results.error(100,"未知异常");
            }
        } else {
            return Results.error(110,"上传失败，图片为空");
        }

    }
}
