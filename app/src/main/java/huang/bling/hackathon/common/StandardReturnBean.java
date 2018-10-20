package huang.bling.hackathon.common;

/**
 * Created by zjl on 2016/11/8.
 */

//一般的返回结果实体类
public class StandardReturnBean {

    /**
     * tag : RSP
     * clientSn : 0
     * status : -999
     * statusDesc : 操作太频繁,已被拒绝-MemberServiceImpl.setMemberUserNameByMobile
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
