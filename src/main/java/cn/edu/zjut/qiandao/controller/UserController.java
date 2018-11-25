package cn.edu.zjut.qiandao.controller;

import cn.edu.zjut.qiandao.conf.Configuration;
import cn.edu.zjut.qiandao.dao.UserRespository;
import cn.edu.zjut.qiandao.entity.*;
import cn.edu.zjut.qiandao.service.UserService;
import cn.edu.zjut.qiandao.utils.Result;
import cn.edu.zjut.qiandao.utils.Results;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@RestController
public class UserController {
    @Autowired
    UserRespository userRespository;
    @Autowired
    Configuration conf;
    @Autowired
    UserService userService;
    @GetMapping(value = "/user/{id}")
    public User id(@PathVariable("id") Integer id) {
        return userRespository.findById(id);
    }

    @RequestMapping(value = "/onLogin", method = RequestMethod.POST)
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
    public Result bangding(@RequestBody Binding binding, HttpServletRequest request) {
        //  String openid=(String)request.getSession().getAttribute("openid");
        String openid = binding.getOpenid();
        Result result=Results.error(100,"fail");
        if (openid == null)
            return Results.error(100, "用户认证异常");
        try{
             result=userService.bangding(binding,openid);
        }catch (Exception e){
            e.printStackTrace();
            return Results.error(100,"exception",null);
        }
        return result;
    }
    @PostMapping(value = "/studentinfo")
    public Result getstudentinfo(@RequestParam String openid){
        Student student=null;
        try{
                student=userService.getStudentInfo(openid);
        }catch(Exception e){
            e.printStackTrace();
            student=null;
            return Results.error(100,"error");
        }
         return Results.success(student);
    }
    @PostMapping("/suggest")
    public Result addSuggest(@RequestParam String openid,@RequestParam String suggest ){
            if(userService.addSuggest(openid,suggest))
                return Results.success(null);
            else return Results.error(100,"fail");
    }

}
