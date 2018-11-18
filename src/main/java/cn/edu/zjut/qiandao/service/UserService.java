package cn.edu.zjut.qiandao.service;

import cn.edu.zjut.qiandao.conf.Configuration;
import cn.edu.zjut.qiandao.domain.*;
import cn.edu.zjut.qiandao.mapper.UserMapper;
import cn.edu.zjut.qiandao.utils.HttpClientUtils;
import cn.edu.zjut.qiandao.utils.Result;
import cn.edu.zjut.qiandao.utils.Results;
import com.alibaba.fastjson.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.SimpleDateFormat;
import java.util.Date;

@Service
public class UserService {
    private Logger log= LoggerFactory.getLogger(UserService.class);
    @Autowired
    Configuration conf;
    @Autowired
    UserRespository userRespository;
    @Autowired
    StudentRespository studentRespository;
    @Autowired
    UserMapper userMapper;
    private SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    public User login(Login login){
        String url="https://api.weixin.qq.com/sns/jscode2session?appid="+conf.getAppid()+"&secret="+conf.getAppsecret()+"&js_code="+login.getCode()+"&grant_type=authorization_code";
        JSONObject result= HttpClientUtils.httpGet(url);
        System.out.println(result.toJSONString());
        //  Sresult.get("openid");
        // return userRespository.findById(1);
        System.out.println();
        if(result.get("openid")!=null){
           User user=userRespository.findByOpenid(result.getString("openid"));
           if(user!=null){
               return user;
           }else{
               user=new User();
               user.setOpenid(result.getString("openid"));
               user.setSession_key(result.getString("session_key"));
               userRespository.save(user);
               return user;
           }
        }
        return null;
    }
    public Result bangding(Binding binding, String openid)throws Exception{
          Student student=studentRespository.getOneByStuid(binding.getStuid());
          User user=userRespository.findByOpenid(openid);
          if(student==null)
              return Results.error(100,"no such student");
          if(binding.getName().equals(student.getName())&&binding.getPassword().equals(student.getPassword())){
             user.setStuid(binding.getStuid());
              userRespository.save(user);
             return Results.success(null);
          }else return  Results.error(100,"name or password wrong");
    }
    public Student getStudentInfo(String openid){
        return userMapper.getStudentInfo(openid);
    }
    public boolean addSuggest(String openid,String suggest){
        try{
            userMapper.addSuggest(openid,suggest,df.format(new Date()));
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
        return true;
    }
}
