package cn.edu.zjut.qiandao.service.impl;

import cn.edu.zjut.qiandao.conf.Configuration;
import cn.edu.zjut.qiandao.constant.ErrorCode;
import cn.edu.zjut.qiandao.dao.StudentRepository;
import cn.edu.zjut.qiandao.dao.SuggestRepository;
import cn.edu.zjut.qiandao.dao.UserRepository;
import cn.edu.zjut.qiandao.dto.Binding;
import cn.edu.zjut.qiandao.dto.Login;
import cn.edu.zjut.qiandao.dto.UserDTO;
import cn.edu.zjut.qiandao.entity.*;
import cn.edu.zjut.qiandao.exception.SignException;
import cn.edu.zjut.qiandao.mapper.StudentMapper;
import cn.edu.zjut.qiandao.service.UserService;
import cn.edu.zjut.qiandao.utils.HttpClientUtils;
import cn.edu.zjut.qiandao.utils.JWTUtils;
import com.alibaba.fastjson.JSONObject;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;

@Service
@Slf4j
public class UserServiceImpl implements UserService {
    @Autowired
    Configuration conf;
    @Autowired
    UserRepository userRepository;
    @Autowired
    StudentRepository studentRepository;
    @Autowired
    SuggestRepository suggestRepository;
    @Autowired
    StudentMapper studentMapper;
    private SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    public UserDTO login(Login login)throws Exception{
        String url="https://api.weixin.qq.com/sns/jscode2session?appid="+conf.getAppid()+"&secret="+conf.getAppsecret()+"&js_code="+login.getCode()+"&grant_type=authorization_code";
        JSONObject result= HttpClientUtils.httpGet(url);
        System.out.println(result.toJSONString());
        //  Sresult.get("openid");
        // return userRespository.findById(1);
        System.out.println();
        User user=null;
        UserDTO userDTO=new UserDTO();
        if(result.get("openid")!=null){
            user=userRepository.findByOpenid(result.getString("openid"));
            System.out.println("user sid="+user.getStudentId());
            BeanUtils.copyProperties(user,userDTO);
            userDTO.setToken(JWTUtils.createToken(user.getOpenid()));
           if(user!=null){
               return userDTO;
           }else{
               user=new User();
               user.setOpenid(result.getString("openid"));
               user.setSession_key(result.getString("session_key"));
               userRepository.save(user);
               BeanUtils.copyProperties(user,userDTO);
               return userDTO;
           }
        }
        return null;
    }
    public void binding(Binding binding, String openid)throws Exception{
          Student student=studentRepository.getOneByStudentId(binding.getStudentId());
          User user=userRepository.findByOpenid(openid);
        //  log.info("传过来的信息:name"+binding.getName()+"  password:"+binding.getPassword());
         // log.info("数据库查出来的信息:name"+student.getName()+"  password:"+student.getPassword());
          //log.info("相等吗?name:"+binding.getName().equals(student.getName())+"  password:"+binding.getPassword().equals(student.getPassword()));
          if(student==null)
            //  return Results.error(100,"no such student");
              throw new SignException(ErrorCode.STUID_NOT_EXIST,"studentid={}",binding.getStudentId());
          if(binding.getName().equals(student.getName())&&binding.getPassword().equals(student.getPassword())){
             user.setStudentId(binding.getStudentId());
              userRepository.save(user);
          }else
              throw new SignException(ErrorCode.NAME_OR_PASSWORD_ERROR,"name={},password={}",binding.getName(),binding.getPassword());
    }
    /*public StudentDTO getStudentInfo(String openid){
        StudentDTO studentDTO=new StudentDTO();
        if(null==openid)
            throw new SignException(ErrorCode.OPENID_NOT_NULL,"openid={}",openid);
        Student student=studentMapper.getStudentInfo(openid);
        if(null==student)
            throw new SignException(ErrorCode.STUID_NOT_EXIST,"studentid={}",student.getStudentId());
        BeanUtils.copyProperties(student,studentDTO);
        return studentDTO;
    }*/
    public void addSuggest(String openid,String suggest){
        if(null==openid)
            throw new SignException(ErrorCode.OPENID_NOT_NULL,"openid={}",openid);
        if(null==suggest)
            throw new SignException(ErrorCode.OPENID_NOT_NULL,"suggest={}",suggest);
        Suggest asuggest=new Suggest(openid,suggest,df.format(new Date()));
        suggestRepository.save(asuggest);
    }
}
