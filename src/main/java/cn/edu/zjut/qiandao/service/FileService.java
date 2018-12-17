package cn.edu.zjut.qiandao.service;

import org.springframework.web.multipart.MultipartFile;

import java.io.FileNotFoundException;
import java.io.IOException;

public interface FileService {
     void signByFace(MultipartFile file, String openid,Integer signInstanceId)throws FileNotFoundException, IOException,Exception;
     void registFace(MultipartFile file,String openid)throws FileNotFoundException,IOException,Exception;
}
