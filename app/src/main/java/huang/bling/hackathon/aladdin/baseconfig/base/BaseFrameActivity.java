package huang.bling.hackathon.aladdin.baseconfig.base;

import android.os.Bundle;

import huang.bling.hackathon.aladdin.baseconfig.BaseMoudle;
import huang.bling.hackathon.aladdin.baseconfig.BasePresenter;
import huang.bling.hackathon.aladdin.baseconfig.BaseView;
import huang.bling.hackathon.aladdin.baseconfig.util.TUtil;
import huang.bling.hackathon.aladdin.util.SLog;


/**
 * Created by 沈东 on 2016/11/9.
 */

public abstract class BaseFrameActivity<P extends BasePresenter, M extends BaseMoudle> extends BaseActivity implements BaseView {

    public P mPresenter;

    public M mModel;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mPresenter = TUtil.getT(this, 0);
        mModel = TUtil.getT(this, 1);
        if (this instanceof BaseView) {
            mPresenter.setVM(this, mModel);
        }
    }

    @Override
    protected void onDestroy() {
        if (mPresenter != null) mPresenter.onDestroy();
        super.onDestroy();
    }

    @Override
    public void onInternetError() {
    }

    @Override
    public void onRequestError(String msg) {
        SLog.e("REQUEST_ERROR ==== ", msg);
    }

    @Override
    public void onOutDue() {

    }

    @Override
    public void onShowMsg(String msg) {
        showShortToast(msg);
    }


}
