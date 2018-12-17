package cn.edu.zjut.qiandao.dao;

import cn.edu.zjut.qiandao.entity.StudentFace;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentFaceRepository extends JpaRepository<StudentFace,Integer> {
StudentFace getOneByStudentId(int sid);
}
