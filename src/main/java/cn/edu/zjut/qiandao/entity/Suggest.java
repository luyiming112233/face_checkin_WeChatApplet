package cn.edu.zjut.qiandao.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
@Data
public class Suggest {
    @Id
    @GeneratedValue
    Integer autoid;
    String openid;
    String suggest;
    String time;
    public Suggest(){}
    public Suggest(String openid,String suggest,String time){
        this.openid=openid;
        this.suggest=suggest;
        this.time=time;
    }
}
