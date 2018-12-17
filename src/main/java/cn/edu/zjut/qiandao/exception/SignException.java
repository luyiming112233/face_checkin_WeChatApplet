package cn.edu.zjut.qiandao.exception;

import cn.edu.zjut.qiandao.constant.ErrorCode;
import org.slf4j.helpers.MessageFormatter;

import org.slf4j.helpers.MessageFormatter;


/**
 * 业务异常
 *
 * @author i@huangdenghe.com
 * @date 2017/12/11
 * @see ch.qos.logback.classic.spi.LoggingEvent#getFormattedMessage()
 */
public class SignException extends RuntimeException {

    private static final long serialVersionUID = 9215327422367136022L;
    private int code;
    private String snapshot;

    public SignException(ErrorCode errorCode, String snapshotFormat, Object... argArray) {
        super(errorCode.getMessage());
        this.code = errorCode.getCode();
        this.snapshot = MessageFormatter.arrayFormat(snapshotFormat, argArray).getMessage();
    }

    public SignException(int code, String message, String snapshotFormat, Object... argArray) {
        super(message);
        this.code = code;
        this.snapshot = MessageFormatter.arrayFormat(snapshotFormat, argArray).getMessage();
    }

    public int getCode() {
        return code;
    }

    public String getSnapshot() {
        return snapshot;
    }

}
