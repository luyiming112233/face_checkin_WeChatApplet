package cn.edu.zjut.qiandao.dao;

import cn.edu.zjut.qiandao.entity.StudentSign;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentSignRepository extends JpaRepository<StudentSign,Integer> {
    StudentSign findBySigninstanceIdAndStudentId(Integer signinstanceid,Integer sid);
}
