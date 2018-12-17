package cn.edu.zjut.qiandao.entity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Data
@Table(name="student_sign")
public class StudentSign {
    @Id
    Integer autoid;
    Integer signinstanceId;
    Integer studentId;
    String signTime;
    String imgurl;
    Integer status;
    Double similary;
}
