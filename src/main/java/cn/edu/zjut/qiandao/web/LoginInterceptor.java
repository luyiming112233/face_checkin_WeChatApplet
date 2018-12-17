package cn.edu.zjut.qiandao.web;

import cn.edu.zjut.qiandao.constant.ErrorCode;
import cn.edu.zjut.qiandao.constant.UserHttpHeader;
import cn.edu.zjut.qiandao.exception.SignException;
import cn.edu.zjut.qiandao.utils.JWTUtils;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class LoginInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o) throws Exception {
       String token=httpServletRequest.getHeader(UserHttpHeader.USER_TOKEN);
       String ip=httpServletRequest.getHeader("X-Forwarded-For");
          if(null==token)
              throw new SignException(ErrorCode.TOKEN_NOT_EXIST,"TOKEN={},ip={}",token,ip);
          String openid= JWTUtils.getOpenid(token);
          if(null==openid)
              throw new SignException(ErrorCode.OPENID_NOT_NULL,"openid={},ip={}",openid,ip);
        return true;

    }

    @Override
    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) throws Exception {

    }
}
