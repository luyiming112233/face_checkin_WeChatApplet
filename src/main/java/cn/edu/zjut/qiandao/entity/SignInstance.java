package cn.edu.zjut.qiandao.entity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="signinstance")
@Data
public class SignInstance {
    @Id
    Integer signinstanceId;
    Integer signId;
    String starttime;
    String endtime;
    String longitude;
    String latitude;
    Integer radius;
    String placename;
    String date;
}
