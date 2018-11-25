package cn.edu.zjut.qiandao.dao;

import cn.edu.zjut.qiandao.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentRespository extends JpaRepository<Student,String> {
    // User getOne(Integer id);
   Student getOneByStuid(String stuid);
}