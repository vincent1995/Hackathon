package huang.bling.hackathon.aladdin.baseconfig.base;

import android.os.Bundle;

import com.afollestad.materialdialogs.MaterialDialog;

import huang.bling.hackathon.aladdin.baseconfig.BaseMoudle;
import huang.bling.hackathon.aladdin.baseconfig.BasePresenter;
import huang.bling.hackathon.aladdin.baseconfig.BaseView;
import huang.bling.hackathon.aladdin.baseconfig.util.TUtil;
import huang.bling.hackathon.aladdin.util.SLog;


/**
 * Created by quan on 16/9/20.
 */

public abstract  class BaseFrameFragment<P extends BasePresenter, M extends BaseMoudle> extends BaseFragment implements BaseView {

    public P mPresenter;

    public M mModel;

    private MaterialDialog mFailDialog;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mPresenter = TUtil.getT(this, 0);
        mModel = TUtil.getT(this, 1);
        if (this instanceof BaseView) {
            mPresenter.setVM(this,mModel);
        }

    }

    @Override
    public void onDestroy() {
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
