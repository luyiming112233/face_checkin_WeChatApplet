package cn.edu.zjut.qiandao.constant;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

@AllArgsConstructor
@Getter
@ToString
public enum  ErrorCode {
    FACE_NOT_FOUND(10001,"未识别出人脸"),
    STUID_NOT_EXIST(10002,"学号不存在"),
    STUID_NOT_FOUND(1006,"学号为空"),
    STUID_OR_SIGNINSTANCEID_NULL(100010,"学号或者签到号为空"),
    SIGNSIMILARY_ERROR(100011,"相似度错误"),
    FILE_NOT_FOUND(10004,"上传文件为空"),
    SIGN_FAIL(10005,"识别失败，不是同一个人"),
    OPENID_NOT_NULL(10008,"用户openid为空"),
    SUGGEST_NOT_NULL(10007,"建议不能为空"),
    TOKEN_NOT_EXIST(10009,"认证异常"),
    TOKEN_INVALID(100012,"TOKEN非法"),
    NAME_OR_PASSWORD_ERROR(10003, "姓名或密码错误！");
    private int code;
    private String message;
}
