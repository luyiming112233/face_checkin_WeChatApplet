package cn.edu.zjut.qiandao.dto;

public class Login {
    private String code;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {

        this.code = code;
    }
    public Login(){}
    public Login(String code){
        this.code=code;
    }
}
