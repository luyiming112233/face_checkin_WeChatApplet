package cn.edu.zjut.qiandao.service;

import org.springframework.web.multipart.MultipartFile;

import java.io.FileNotFoundException;
import java.io.IOException;

public interface FileService {
     /**
      * 根据上传的人脸签到
      * @param file
      * @param openid
      * @param signInstanceId
      * @throws FileNotFoundException
      * @throws IOException
      * @throws Exception
      */
     void signByFace(MultipartFile file, String openid,Integer signInstanceId)throws FileNotFoundException, IOException,Exception;

     /**
      * 注册人脸
      * @param file
      * @param openid
      * @throws FileNotFoundException
      * @throws IOException
      * @throws Exception
      */
     void registFace(MultipartFile file,String openid)throws FileNotFoundException,IOException,Exception;
}
