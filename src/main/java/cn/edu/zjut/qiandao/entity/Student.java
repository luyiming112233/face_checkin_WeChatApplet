package cn.edu.zjut.qiandao.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
@Data
@JsonIgnoreProperties({"password"})
public class Student
{
    @Id
    private Integer studentId;
    @Column(name = "student_name")
    private String name;
    @Column(name = "student_password")
    private String password;
    @Column(name="student_classname")
    private String classname;
    @Column(name = "student_departname")
    private String departname;
    @Column(name = "student_email")
    private String email;
    @Column(name = "student_img")
    private String img;

}
