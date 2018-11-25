package cn.edu.zjut.qiandao.constant;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

@AllArgsConstructor
@Getter
@ToString
public enum  ErrorCode {
    SDK_WRONG(10000,"sdk异常"),
    FACE_NOT_FOUND(10001,"未识别出人脸"),
    STUID_NOT_EXIST(10002,"学号不存在");
    private int code;
    private String message;
}
