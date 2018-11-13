package cn.edu.zjut.qiandao.domain;

import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRespository extends JpaRepository<User,Integer> {
   // User getOne(Integer id);
    User findById(Integer id);
    User findByOpenid(String openid);
  //  void countBy
}
