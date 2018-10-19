package huang.bling.hackathon.aladdin.baseconfig;

/**
 * Created by 沈东 on 2016/11/9.
 */

public interface BaseView {

    void onRequestStart();

    void onRequestError(String msg);

    void onRequestEnd();

    void onInternetError();

    void onOutDue();

    void onShowMsg(String msg);
}
