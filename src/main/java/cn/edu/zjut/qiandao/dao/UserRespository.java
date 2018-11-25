package cn.edu.zjut.qiandao.dao;

import cn.edu.zjut.qiandao.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRespository extends JpaRepository<User,Integer> {
   // User getOne(Integer id);
    User findById(Integer id);
    User findByOpenid(String openid);
}
