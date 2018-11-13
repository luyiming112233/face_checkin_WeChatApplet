package cn.edu.zjut.qiandao.controller;

import cn.edu.zjut.qiandao.service.FileService;
import cn.edu.zjut.qiandao.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
@Controller
public class FileController {
    @Autowired
    FileService fileService;
    @RequestMapping(value = "/upload", method = RequestMethod.POST)
    @ResponseBody
    public Result upload(@RequestParam("file") MultipartFile file) {
        return fileService.dofileupload(file);
    }
    @RequestMapping(value = "/testupload", method = RequestMethod.GET)
    public String testupload()
    {
        // ModelAndView mv=new ModelAndView("uploadtest");
        // return mv;
        return "uploadtest";
    }
}
