package cn.edu.zjut.qiandao.conf;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "weixin")
public class Configuration {
  private String appid;
  private String appsecret;
  private String registerfacepath;
  private String registerfaceurl;
  private String uploadpath;
  private String uploadurl;
  private String faceUrl;
  private String getfeatureurl;
  private String getsimilaryurl;

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

    public String getFaceUrl() {
        return faceUrl;
    }

    public void setFaceUrl(String faceUrl) {
        this.faceUrl = faceUrl;
    }

    public String getGetfeatureurl() {
        return getfeatureurl;
    }

    public void setGetfeatureurl(String getfeatureurl) {
        this.getfeatureurl = getfeatureurl;
    }

    public String getGetsimilaryurl() {
        return getsimilaryurl;
    }

    public void setGetsimilaryurl(String getsimilaryurl) {
        this.getsimilaryurl = getsimilaryurl;
    }

    public String getUploadurl() {
        return uploadurl;
    }

    public void setUploadurl(String uploadurl) {
        this.uploadurl = uploadurl;
    }


    public String getRegisterfaceurl() {
        return registerfaceurl;
    }

    public void setRegisterfaceurl(String registerfaceurl) {
        this.registerfaceurl = registerfaceurl;
    }

    public String getRegisterfacepath() {
        return registerfacepath;
    }

    public void setRegisterfacepath(String registerfacepath) {
        this.registerfacepath = registerfacepath;
    }
}
