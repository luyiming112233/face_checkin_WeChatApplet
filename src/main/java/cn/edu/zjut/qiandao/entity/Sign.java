package cn.edu.zjut.qiandao.entity;


import lombok.Data;

@Data
public class Sign {
    Integer signId;
    Integer signInstanceId;
    String  signName;
    String signDescription;
    String placeName;
    String startTime;
    String endTime;
    String longitude;
    String latitude;
    String date;
    Integer status;
    Integer teacherId;
}
