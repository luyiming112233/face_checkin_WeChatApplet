package cn.edu.zjut.qiandao.controller;

import cn.edu.zjut.qiandao.dao.UserRespository;
import cn.edu.zjut.qiandao.service.FileService;
import cn.edu.zjut.qiandao.utils.Result;
import cn.edu.zjut.qiandao.utils.Results;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.FileNotFoundException;
import java.io.IOException;

@Controller
@RequestMapping("/file")
public class FileController {
    @Autowired
    FileService fileService;
    @Autowired
    UserRespository userRespository;
    @RequestMapping(value = "/upload", method = RequestMethod.POST)
    @ResponseBody
    public Result upload(@RequestParam("file") MultipartFile file, @RequestParam String stuid ) {
          //  String stuid=userRespository.findByOpenid(user.getOpenid()).getStuid();
            if(null!=stuid){
                try {
                    if (fileService.signByFace(file, stuid))
                        return Results.success(null);
                    else
                        return Results.error(100, "fail");
                }catch(FileNotFoundException e1){
                    e1.printStackTrace();
                    return Results.error(101,"filenotfound");
                }catch (IOException e2){
                    e2.printStackTrace();
                    return Results.error(102,"ioexception");
                }
                catch (Exception e){
                    e.printStackTrace();
                    return Results.error(100,"error");
                }
     }
     return Results.error(100,"stuid=null");
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
    public Result registerface(@RequestParam("file") MultipartFile file, @RequestParam String stuid ){
       Result result=Results.error(100,"fail");
        try{
         result=fileService.registface(file,stuid);
       }catch (FileNotFoundException e){
           e.printStackTrace();
           return Results.error(100,"file not found");
       }catch (Exception e1){
           return Results.error(100,"exception");
       }
        return result;
    }
}
