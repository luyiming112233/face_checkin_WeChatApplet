package cn.edu.zjut.qiandao.mapper;

import cn.edu.zjut.qiandao.domain.Student;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import javax.annotation.security.PermitAll;

public interface UserMapper {
    //@Update()
    @Insert("insert into user_face(stuid) values(#{stuid})")
    void saveFace(@Param("stuid")String stuid);
    @Update("update user_face set feature=#{feature} where stuid=#{stuid}")
    void updatefeature(@Param("stuid")String stuid,@Param("feature")String feature);
    @Update("update user_face set stuid=#{stuid2} where stuid=#{stuid1}")
    void updatestuid(@Param("stuid1")String stuid1,@Param("stuid2")String stuid2);
    @Select("select student.* from user,student where user.stuid=student.stuid and user.openid=#{openid}")
    Student getStudentInfo(@Param("openid") String openid);
    @Insert("insert into suggest(openid,suggest,time) values(#{openid},#{suggest},#{time})")
    void addSuggest(@Param("openid")String openid,@Param("suggest")String suggest,@Param("time")String time);
    @Update("update student set img=#{img} where stuid=#{stuid}")
    void registerface(@Param("img") String img, @Param("stuid")String stuid);
}
