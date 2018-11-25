package cn.edu.zjut.qiandao.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
@Data
@JsonIgnoreProperties({"password"})
public class Student
{
    @Id
    private String stuid;
    private String name;
    private String password;
    private String classname;
    private String departname;
    private String email;

}
