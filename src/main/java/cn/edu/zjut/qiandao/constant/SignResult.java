package cn.edu.zjut.qiandao.constant;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class SignResult {
    public static final int SIGN_SUCCESS=1;//签到成功
    public static final int SIGN_LEAVE=2;//请假
    public static final int SIGN_FAIL=0;//签到失败
}
