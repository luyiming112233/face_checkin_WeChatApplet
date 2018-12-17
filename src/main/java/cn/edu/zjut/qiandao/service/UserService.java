package cn.edu.zjut.qiandao.service;

import cn.edu.zjut.qiandao.dto.StudentDTO;
import cn.edu.zjut.qiandao.dto.UserDTO;
import cn.edu.zjut.qiandao.dto.Binding;
import cn.edu.zjut.qiandao.dto.Login;

public interface UserService {
     UserDTO login(Login login)throws Exception;
     void binding(Binding binding, String openid)throws Exception;
     StudentDTO getStudentInfo(String openid);
     void addSuggest(String openid,String suggest);
}
