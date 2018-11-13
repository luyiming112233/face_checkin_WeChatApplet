package cn.edu.zjut.qiandao.service;

import cn.edu.zjut.qiandao.conf.Configuration;
import cn.edu.zjut.qiandao.domain.Bangding;
import cn.edu.zjut.qiandao.domain.Login;
import cn.edu.zjut.qiandao.domain.User;
import cn.edu.zjut.qiandao.domain.UserRespository;
import cn.edu.zjut.qiandao.utils.HttpClientUtils;
import cn.edu.zjut.qiandao.utils.Result;
import cn.edu.zjut.qiandao.utils.Results;
import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    @Autowired
    Configuration conf;
    @Autowired
    UserRespository userRespository;
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
               user.setName(null);
               user.setOpenid(result.getString("openid"));
               user.setPassword("123456");
               user.setSession_key(result.getString("session_key"));
               userRespository.save(user);
               return user;
           }
        }
        return null;
    }
    public Result bangding(Bangding bangding, String openid){
          User user=userRespository.findByOpenid(openid);
         if(!bangding.getPassword().equals(user.getPassword()))
             return Results.error(100,"passworderror",null);
          user.setName(bangding.getName());
          user.setStuid(bangding.getStuid());
       //   user.setPassword(bangding.getPassword());
         try{
          userRespository.save(user);
         }catch (Exception e){
             e.printStackTrace();
             return Results.error(100,"fail",null);
         }
        return Results.success(null);
    }
}
