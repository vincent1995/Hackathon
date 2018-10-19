package huang.bling.hackathon.aladdin.baseconfig.base;

import android.os.Bundle;

import com.afollestad.materialdialogs.MaterialDialog;
import com.sunlandgroup.aladdin.baseconfig.BaseMoudle;
import com.sunlandgroup.aladdin.baseconfig.BasePresenter;
import com.sunlandgroup.aladdin.baseconfig.BaseView;
import com.sunlandgroup.aladdin.baseconfig.util.TUtil;
import com.sunlandgroup.aladdin.util.SLog;

/**
 * Created by 沈东 on 2016/11/9.
 */

public abstract class BaseFrameActivity<P extends BasePresenter, M extends BaseMoudle> extends BaseActivity implements BaseView {

    public P mPresenter;

    public M mModel;

    private MaterialDialog mFailDialog;

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
