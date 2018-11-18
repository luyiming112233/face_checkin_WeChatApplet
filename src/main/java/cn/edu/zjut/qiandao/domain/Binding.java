package cn.edu.zjut.qiandao.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Binding {
    private String openid;
    private String stuid;
    private String password;
    private String name;
}
