package cn.edu.zjut.qiandao.mapper;

import cn.edu.zjut.qiandao.entity.Student;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface StudentMapper {
    /**
     *
     * @param openid 微信号
     * @return Student 具体信息
     */
    Student getStudentInfo(@Param("openid") String openid);

}
