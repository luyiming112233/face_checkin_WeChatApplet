package cn.edu.zjut.qiandao.web.controller;

import cn.edu.zjut.qiandao.conf.Configuration;
import cn.edu.zjut.qiandao.constant.UserHttpHeader;
import cn.edu.zjut.qiandao.dao.UserRepository;
import cn.edu.zjut.qiandao.dto.*;
import cn.edu.zjut.qiandao.entity.*;
import cn.edu.zjut.qiandao.service.StudentService;
import cn.edu.zjut.qiandao.service.UserService;
import cn.edu.zjut.qiandao.utils.JWTUtils;
import cn.edu.zjut.qiandao.utils.Results;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@RestController
public class UserController {
    @Autowired
    UserRepository userRepository;
    @Autowired
    Configuration conf;
    @Autowired
    UserService userService;
    @Autowired
    StudentService studentService;
    @GetMapping(value = "/user/{id}")
    public User id(@PathVariable("id") Integer id) {
        return userRepository.findByAutoid(id);
    }

    @RequestMapping(value = "/onLogin", method = RequestMethod.POST)
    public Result login(@RequestBody Login login)throws Exception {
        // System.out.println("Loginsessionid="+request.getSession().getId());
        UserDTO userDTO = userService.login(login);
            return Results.success(userDTO);
        }

    @RequestMapping(value = "/bangding", method = RequestMethod.POST)
    public Result bangding(@RequestBody Binding binding, HttpServletRequest request)throws Exception {
          String token=request.getHeader(UserHttpHeader.USER_TOKEN);
        String openid = JWTUtils.getOpenid(token);
        Result result=Results.error(100,"fail");
             userService.binding(binding,openid);
        return Results.success(null);
    }
    @PostMapping(value = "/studentinfo")
    public Result getstudentinfo(HttpServletRequest request){
        String token=request.getHeader(UserHttpHeader.USER_TOKEN);
        String openid=JWTUtils.getOpenid(token);
        StudentDTO studentDTO= studentService.getStudentInfo(openid);
         return Results.success(studentDTO);
    }
    @PostMapping("/suggest")
    public Result addSuggest(@RequestParam String suggest,HttpServletRequest request ){
        String token=request.getHeader(UserHttpHeader.USER_TOKEN);
        String openid=JWTUtils.getOpenid(token);
            userService.addSuggest(openid,suggest);
                return Results.success(null);
    }

}
