package cn.edu.zjut.qiandao.service;

import cn.edu.zjut.qiandao.dto.SignInstanceDTO;
import cn.edu.zjut.qiandao.dto.StudentDTO;
import cn.edu.zjut.qiandao.entity.Sign;
import cn.edu.zjut.qiandao.entity.SignInstance;

import java.util.HashMap;
import java.util.List;

public interface StudentService {
    /**
     * 根据openid查询绑定的学生信息
     * @param openid
     * @return
     */
    StudentDTO getStudentInfo(String openid);

    /**
     * 根据openid查询该学生日期为date的签到信息
     * @param openid
     * @param date
     * @return
     */
    List<Sign> getDailySigns(String openid, String date);

    /**
     * 查询学生某个月的签到信息
     * @param openid
     * @param month
     * @return
     */
    HashMap<String,Sign> getMonthSigns(String openid, String month);

    /**
     * 查询签到地理位置
     * @param signInstanceId
     * @return
     */
    SignInstanceDTO getSignLocation(Integer signInstanceId);
}
