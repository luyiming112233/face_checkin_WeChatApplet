package cn.edu.zjut.qiandao.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Transient;

@Entity
@Data
@JsonIgnoreProperties({"id","session_key"})
public class User {
    @Id
    @GeneratedValue
    private Integer id;
    private String openid;
    private String session_key;
    private String stuid;
    @Transient
    private String token;
}
