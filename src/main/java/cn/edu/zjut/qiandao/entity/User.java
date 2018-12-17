package cn.edu.zjut.qiandao.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Transient;
import javax.persistence.criteria.CriteriaBuilder;

@Entity
@Data
public class User {
    @Id
    @GeneratedValue
    private Integer autoid;
    private String openid;
    private String session_key;
    private Integer studentId;
    @Transient
    private String token;
}
