package cn.edu.zjut.qiandao.controller;

import cn.edu.zjut.qiandao.conf.Configuration;
import cn.edu.zjut.qiandao.domain.Bangding;
import cn.edu.zjut.qiandao.domain.Login;
import cn.edu.zjut.qiandao.domain.User;
import cn.edu.zjut.qiandao.domain.UserRespository;
import cn.edu.zjut.qiandao.service.FileService;
import cn.edu.zjut.qiandao.service.UserService;
import cn.edu.zjut.qiandao.utils.HttpClientUtils;
import cn.edu.zjut.qiandao.utils.Result;
import cn.edu.zjut.qiandao.utils.Results;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.hibernate.annotations.GeneratorType;
import org.omg.CORBA.Request;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;

@Controller
public class UserController {
    @Autowired
    UserRespository userRespository;
    @Autowired
    Configuration conf;
    @Autowired
    UserService userService;
    @GetMapping(value = "/user/{id}")
    @ResponseBody
    public User id(@PathVariable("id") Integer id) {
        return userRespository.findById(id);
    }

    @RequestMapping(value = "/onLogin", method = RequestMethod.POST)
    @ResponseBody
    public Result login(@RequestBody Login login) {
        // System.out.println("Loginsessionid="+request.getSession().getId());
        User user = userService.login(login);
        if (user == null)
            return Results.error(100, "登录失败");
        else {
            //   request.getSession().setAttribute("openid",user.getOpenid());
            return Results.success(user);
        }
    }

    @RequestMapping(value = "/bangding", method = RequestMethod.POST)
    @ResponseBody
    public Result bangding(@RequestBody Bangding bangding, HttpServletRequest request) {
        //  String openid=(String)request.getSession().getAttribute("openid");
        String openid = bangding.getOpenid();
        // System.out.println("Bangdingsessionid="+request.getSession().getId());
        if (openid == null)
            return Results.error(100, "用户认证异常");
        return userService.bangding(bangding, openid);
    }

}
