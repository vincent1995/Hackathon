package huang.bling.hackathon.aladdin.bean.common;

/**
 * Created by 沈东 on 2016/11/9.
 */

public class CheckVersionBean {

    /**
     * version : 1.0.7
     * url_download : http://download.yongxt.com:8088/95128.apk
     * description : 【95128出行.大众版】有新版本,请及时更新
     * url_description : http://download.yongxt.com:8088/95128.version.xml
     */

    private String version;
    private String url_download;
    private String description;
    private String url_description;

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public String getUrl_download() {
        return url_download;
    }

    public void setUrl_download(String url_download) {
        this.url_download = url_download;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getUrl_description() {
        return url_description;
    }

    public void setUrl_description(String url_description) {
        this.url_description = url_description;
    }
}
