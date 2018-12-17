package cn.edu.zjut.qiandao.dao;

import cn.edu.zjut.qiandao.entity.SignInstance;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SignInstanceRespository extends JpaRepository<SignInstance,Integer> {
    SignInstance getOneBySigninstanceId(Integer signInstanceId);
}
