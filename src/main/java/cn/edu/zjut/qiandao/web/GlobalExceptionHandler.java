package cn.edu.zjut.qiandao.web;

import cn.edu.zjut.qiandao.exception.SignException;
import cn.edu.zjut.qiandao.utils.Results;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
@ControllerAdvice
@Slf4j
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    /**
     * 处理服务器内部错误500
     * @param e
     * @param body
     * @param headers
     * @param status
     * @param request
     * @return
     */
    @Override
    protected ResponseEntity<Object> handleExceptionInternal(Exception e, Object body, HttpHeaders headers,
             HttpStatus status, WebRequest request) {
        log.warn(e.getMessage(), e);
        return super.handleExceptionInternal(e, body, headers, status, request);
    }

    /**
     * 处理自定义SignException
     * @param e
     * @return
     */
    @ExceptionHandler
    public ResponseEntity<Object> handleSignException(SignException e){
        log.warn(e.getSnapshot(),e);
        return new ResponseEntity<>(Results.error(e.getCode(), e.getMessage()), HttpStatus.OK);
    }

    /**
     * 处理RunTimeException
     * @param e
     * @return
     */
    @ExceptionHandler
    public ResponseEntity<Object> handleRuntimeException(RuntimeException e) {
        log.error(e.getMessage(), e);
        return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase(),
                HttpStatus.INTERNAL_SERVER_ERROR);
    }

    /**
     * 处理异常
     * @param e
     * @return
     */
    @ExceptionHandler
    public ResponseEntity<Object> handleException(Exception e) {
        log.error(e.getMessage(), e);
        return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase(),
                HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
