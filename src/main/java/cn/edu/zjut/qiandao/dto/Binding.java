package cn.edu.zjut.qiandao.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Binding {
    private String openid;
    private Integer studentId;
    private String password;
    private String name;
}
