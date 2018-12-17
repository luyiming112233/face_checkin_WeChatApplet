package cn.edu.zjut.qiandao.web.controller;

import cn.edu.zjut.qiandao.constant.UserHttpHeader;
import cn.edu.zjut.qiandao.dto.Result;
import cn.edu.zjut.qiandao.dto.SignInstanceDTO;
import cn.edu.zjut.qiandao.entity.Sign;
import cn.edu.zjut.qiandao.service.StudentService;
import cn.edu.zjut.qiandao.utils.JWTUtils;
import cn.edu.zjut.qiandao.utils.Results;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
@RequestMapping("/student")
public class StudentController {

    @Autowired
    StudentService studentService;
    @RequestMapping("/daily/{date}")
    public Result getDailySigns(HttpServletRequest request, @PathVariable String date){
        String openid= JWTUtils.getOpenid(request.getHeader(UserHttpHeader.USER_TOKEN));
      //  String openid="oasdN5e88EgUWsn04BSQhG9r5kUI";
       // System.out.println(openid);
      //  System.out.println(date);
        List<Sign> signList=studentService.getDailySigns(openid,date);
      //  System.out.println("大小？"+signList.size());
        return Results.success(signList);
    }
    @RequestMapping("/signLocation/{signInstanceId}")
    public Result getSignLocation(@PathVariable Integer signInstanceId){
        SignInstanceDTO signInstanceDTO=studentService.getSignLocation(signInstanceId);
         return Results.success(signInstanceDTO);
    }
}
