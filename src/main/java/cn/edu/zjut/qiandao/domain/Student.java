package cn.edu.zjut.qiandao.domain;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
@Data
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
