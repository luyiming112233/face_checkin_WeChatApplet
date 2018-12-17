package cn.edu.zjut.qiandao.web.controller;

import cn.edu.zjut.qiandao.constant.ErrorCode;
import cn.edu.zjut.qiandao.constant.UserHttpHeader;
import cn.edu.zjut.qiandao.exception.SignException;
import cn.edu.zjut.qiandao.service.FileService;
import cn.edu.zjut.qiandao.dto.Result;
import cn.edu.zjut.qiandao.utils.JWTUtils;
import cn.edu.zjut.qiandao.utils.Results;
import lombok.extern.slf4j.Slf4j;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.FileNotFoundException;
import java.io.IOException;

@Controller
@RequestMapping("/file")
@Slf4j
public class FileController {
    @Autowired
    FileService fileService;
    @RequestMapping(value = "/upload/{signInstanceId}", method = RequestMethod.POST)
    @ResponseBody
    public Result upload(@RequestParam("file") MultipartFile file,HttpServletRequest request,@PathVariable Integer signInstanceId)throws FileNotFoundException,IOException, Exception {
         String openid= JWTUtils.getOpenid(request.getHeader(UserHttpHeader.USER_TOKEN));
            if(null!=openid){
                    fileService.signByFace(file, openid,signInstanceId);
                        return Results.success(null);
     }
     else throw new SignException(ErrorCode.OPENID_NOT_NULL,"openid={}",openid);
    }
    @RequestMapping(value = "/testupload", method = RequestMethod.GET)
    public String testupload()
    {
        // ModelAndView mv=new ModelAndView("uploadtest");
        // return mv;
        return "uploadtest";
    }
    @PostMapping(value = "/register")
    @ResponseBody
    public Result registerface(@RequestParam("file") MultipartFile file, HttpServletRequest request)throws FileNotFoundException,Exception{
        String openid= JWTUtils.getOpenid(request.getHeader(UserHttpHeader.USER_TOKEN));
      //  log.info("我也被执行了,i am alive2");
        fileService.registFace(file,openid);
        return Results.success(null);
    }
}
