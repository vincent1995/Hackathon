package huang.bling.hackathon.aladdin.baseconfig;

import android.content.Context;

import com.sunlandgroup.aladdin.baseconfig.rx.RxManager;

/**
 * Created by 沈东 on 2016/11/9.
 */

public abstract class BasePresenter<M,V> {
    public Context context;
    public M mModel;
    public V mView;
    public RxManager mRxManager = new RxManager();

    public void setVM(V v, M m) {
        this.mView = v;
        this.mModel = m;
    }


    public void onDestroy() {
        mRxManager.clear();
    }

    public void stopNetworkRequest() {
        mRxManager.clear();
    }

}
