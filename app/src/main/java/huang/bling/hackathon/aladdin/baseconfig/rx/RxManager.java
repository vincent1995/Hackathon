package huang.bling.hackathon.aladdin.baseconfig.rx;

import java.util.HashMap;
import java.util.Map;

import rx.Observable;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.subscriptions.CompositeSubscription;

/**
 * 用于管理RxBus的事件和Rxjava相关代码的生命周期处理
 * Created by 沈东 on 2016/11/9.
 */

public class RxManager {

    public RxBus mRxBus = RxBus.$();
    private Map<String, Observable<?>> mObservables = new HashMap<>();// 管理观察源
    private CompositeSubscription mCompositeSubscription = new CompositeSubscription();// 管理订阅者者

    private Map<String,Subscription> mSubscriptionMap;


    public void saveSubscription(String key,Subscription s) {
        if (mSubscriptionMap == null) {
            mSubscriptionMap = new HashMap<>();
        }
        mSubscriptionMap.put(key,s);
    }

    public void unSubscribe(String key) {
        if (mSubscriptionMap != null) {
            Subscription subscription = mSubscriptionMap.get(key);
            if(subscription != null){
                subscription.unsubscribe();
                mSubscriptionMap.remove(key);
            }
        }
    }

    public void on(String eventName, Action1<Object> action1) {
        Observable<?> mObservable = mRxBus.register(eventName);
        mObservables.put(eventName, mObservable);
        mCompositeSubscription
                .add(mObservable.observeOn(AndroidSchedulers.mainThread())
                        .subscribe(action1, new Action1<Throwable>() {
                            @Override
                            public void call(Throwable throwable) {
                                throwable.printStackTrace();
                            }
                        }));
    }

    public void add(String key,Subscription m){
        mCompositeSubscription.add(m);
        saveSubscription(key,m);
    }

    public void add(Subscription m) {
        mCompositeSubscription.add(m);
    }


    public void clear() {
        mCompositeSubscription.unsubscribe();// 取消订阅
        for (Map.Entry<String, Observable<?>> entry : mObservables.entrySet()){
            mRxBus.unregister(entry.getKey(), entry.getValue());// 移除观察
        }
    }


    public void post(Object tag, Object content) {
        mRxBus.post(tag, content);
    }
}
