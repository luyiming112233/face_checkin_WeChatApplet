package cn.edu.zjut.qiandao.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
@Data
@JsonIgnoreProperties({"id","session_key","password"})
public class User {
    @Id
    @GeneratedValue
    private Integer id;
    private String name;
    private String openid;
    private String session_key;
    private String stuid;
    private String password;
    private String token;
}
