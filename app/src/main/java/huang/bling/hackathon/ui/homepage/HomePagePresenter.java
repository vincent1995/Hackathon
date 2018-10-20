package huang.bling.hackathon.ui.homepage;

/**
 * Created by 沈东 on 2017/7/11.
 */

public class HomePagePresenter extends HomePageContract.Presenter {
//    @Override
//    public void getCurrentBussiness(String mobile, String clienttype) {
//        mRxManager.add(mModel.getCurrentBussiness(mobile, clienttype)
//                .subscribeOn(Schedulers.io())
//                .doOnSubscribe(new Action0() {
//                    @Override
//                    public void call() {
//                        mView.onRequestStart();
//                    }
//                })
//                .subscribeOn(AndroidSchedulers.mainThread())
//                .observeOn(AndroidSchedulers.mainThread())
//                .retry(new Func2<Integer, Throwable, Boolean>() {
//                    @Override
//                    public Boolean call(Integer integer, Throwable throwable) {
//                        mView.onInternetError();
//                        //此处也可以通过判断throwable来控制不同的错误不同处理
//                        return true;
//                    }
//                })
//                .subscribe(new Action1<ResponseBody>() {
//                    @Override
//                    public void call(ResponseBody responseBody) {
//                        try {
//                            mView.onRequestEnd();
//                            String result = responseBody.string();
//                            SLog.d("zjl", "当前订单列表---" + result);
//
//                        } catch (IOException e) {
//                            e.printStackTrace();
//                            mView.onInternetError();
//                        }
//                    }
//                }, new Action1<Throwable>() {
//                    @Override
//                    public void call(Throwable throwable) {
//                        mView.onInternetError();
//                    }
//                }));
//    }
}
