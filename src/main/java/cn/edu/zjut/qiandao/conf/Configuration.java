package cn.edu.zjut.qiandao.conf;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "weixin")
public class Configuration {
  private String appid;
  private String appsecret;
  private String uploadpath;

    public String getAppid() {
        return appid;
    }

    public void setAppid(String appid) {
        this.appid = appid;
    }

    public String getAppsecret() {
        return appsecret;
    }

    public void setAppsecret(String appsecret) {
        this.appsecret = appsecret;
    }

    public String getUploadpath() {
        return uploadpath;
    }

    public void setUploadpath(String uploadpath) {
        this.uploadpath = uploadpath;
    }
}
