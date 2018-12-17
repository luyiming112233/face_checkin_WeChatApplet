package cn.edu.zjut.qiandao.entity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Data
@Table(name = "student_face")
public class StudentFace {
    @Id
    @GeneratedValue
    Integer autoid;
    Integer studentId;
    String feature;
}
