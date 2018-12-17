package cn.edu.zjut.qiandao.service;

import cn.edu.zjut.qiandao.dto.SignInstanceDTO;
import cn.edu.zjut.qiandao.dto.StudentDTO;
import cn.edu.zjut.qiandao.entity.Sign;
import cn.edu.zjut.qiandao.entity.SignInstance;

import java.util.HashMap;
import java.util.List;

public interface StudentService {
    StudentDTO getStudentInfo(String openid);
    List<Sign> getDailySigns(String openid, String date);
    HashMap<String,Sign> getMonthSigns(String openid, String month);
    SignInstanceDTO getSignLocation(Integer signInstanceId);
}
