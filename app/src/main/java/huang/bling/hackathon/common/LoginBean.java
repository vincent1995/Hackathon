package huang.bling.hackathon.common;

/**
 * Created by 沈东 on 2016/11/10.
 */

public class LoginBean {

    /**
     * tag : RSP
     * clientSn : 0
     * status : 0
     * statusDesc : 操作成功
     * opid :
     */

    private String tag;
    private String clientSn;
    private int status;
    private String statusDesc;
    private String opid;

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    public String getClientSn() {
        return clientSn;
    }

    public void setClientSn(String clientSn) {
        this.clientSn = clientSn;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getStatusDesc() {
        return statusDesc;
    }

    public void setStatusDesc(String statusDesc) {
        this.statusDesc = statusDesc;
    }

    public String getOpid() {
        return opid;
    }

    public void setOpid(String opid) {
        this.opid = opid;
    }
}
