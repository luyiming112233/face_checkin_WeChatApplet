package cn.edu.zjut.qiandao.service.impl;

import cn.edu.zjut.qiandao.conf.Configuration;
import cn.edu.zjut.qiandao.constant.ErrorCode;
import cn.edu.zjut.qiandao.constant.SignResult;
import cn.edu.zjut.qiandao.dao.StudentRepository;
import cn.edu.zjut.qiandao.dao.StudentFaceRepository;
import cn.edu.zjut.qiandao.dao.StudentSignRepository;
import cn.edu.zjut.qiandao.dao.UserRepository;
import cn.edu.zjut.qiandao.entity.Student;
import cn.edu.zjut.qiandao.entity.StudentFace;
import cn.edu.zjut.qiandao.entity.StudentSign;
import cn.edu.zjut.qiandao.exception.SignException;
import cn.edu.zjut.qiandao.mapper.StudentMapper;
import cn.edu.zjut.qiandao.service.FileService;
import cn.edu.zjut.qiandao.utils.HttpClientUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;
@Service
@Slf4j
public class FileServiceImpl implements FileService {
    @Autowired
    Configuration configuration;
    //@Value(value = "")
    @Autowired
    StudentMapper userMapper;
    @Autowired
    UserRepository userRepository;
    @Autowired
    StudentFaceRepository studentFaceRepository;
    @Autowired
    StudentRepository studentRepository;
    @Autowired
    StudentSignRepository studentSignRepository;
    public void signByFace(MultipartFile file,String openid,Integer signInstanceId)throws FileNotFoundException,IOException,Exception{
        Integer sid=userRepository.findByOpenid(openid).getStudentId();
        if(null==sid||null==signInstanceId)
            throw new SignException(ErrorCode.STUID_NOT_FOUND,"stuid={},signInstanceId={}",sid,signInstanceId);
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
                String imgurl=configuration.getUploadurl()+uuid+".jpg";
                String url=configuration.getGetsimilaryurl()+"?id="+sid+"&&url="+imgurl;
              String similarity=HttpClientUtils.doGet(url);
            //  System.out.println(similarity);
                log.info("stuid="+sid+" the similarity is:"+similarity);
                Double mysimilarity;
                try{
                    mysimilarity = Double.parseDouble(similarity);
                }catch (Exception e){
                    throw new SignException(ErrorCode.SIGNSIMILARY_ERROR,"={}",similarity);
                }
              if(mysimilarity<0.7)
                  throw new SignException(ErrorCode.SIGN_FAIL,"stuid={}",sid);
            StudentSign studentSign=studentSignRepository.findBySigninstanceIdAndStudentId(signInstanceId,sid);
            studentSign.setImgurl(imgurl);
            studentSign.setStatus(SignResult.SIGN_SUCCESS);
            studentSign.setSimilary(mysimilarity);
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            studentSign.setSignTime(format.format(new Date()));
                studentSignRepository.save(studentSign);
        } else {
            log.error("file empty");
          throw new SignException(ErrorCode.FILE_NOT_FOUND,"filename={}",file.getName());
           // return Results.error(110,"上传失败，图片为空");
        }
        //return false;

    }

    public void registFace(MultipartFile file,String openid)throws FileNotFoundException,IOException,Exception{
        Integer sid= userRepository.findByOpenid(openid).getStudentId();
       // log.info("我被执行了, i am alive");
        if (!file.isEmpty()) {
            String uuid = UUID.randomUUID().toString().replaceAll("-", "");
            String filename = configuration.getRegisterfacepath() + uuid + ".jpg";
        //    log.info("the file register path is"+filename);
            File saveFile = new File(filename);
            //    File saveFile = new File();
                BufferedOutputStream out = new BufferedOutputStream(new FileOutputStream(saveFile));
                out.write(file.getBytes());
                out.flush();
                out.close();
            Student student=studentRepository.getOneByStudentId(sid);
            student.setImg(uuid);
            studentRepository.save(student);
            // userMapper.registerface(stuid,uuid);
                log.info("registerfilename="+filename);
                String url=configuration.getGetfeatureurl()+"?url="+configuration.getRegisterfaceurl()+"/"+uuid+".jpg";
                String feature=HttpClientUtils.doGet(url);
                log.info("register feature:"+feature);
            StudentFace studentFace=studentFaceRepository.getOneByStudentId(sid);
            studentFace.setFeature(feature);
            studentFaceRepository.save(studentFace);
            //userMapper.updatefeature(stuid,feature);
        } else {
            throw new SignException(ErrorCode.FILE_NOT_FOUND,"filename={}",file.getName());
        }

    }
}
