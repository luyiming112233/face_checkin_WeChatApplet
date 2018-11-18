package cn.edu.zjut.qiandao.domain;

import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentRespository extends JpaRepository<Student,String> {
    // User getOne(Integer id);
   Student getOneByStuid(String stuid);
}