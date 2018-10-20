package huang.bling.hackathon.ui.homepage;


import huang.bling.hackathon.baseconfig.BaseMoudle;
import huang.bling.hackathon.baseconfig.BasePresenter;
import huang.bling.hackathon.baseconfig.BaseView;

/**
 * Created by 沈东 on 2017/7/11.
 */

public interface HomePageContract  {
    interface Moudle extends BaseMoudle {

//        Observable<ResponseBody> getCurrentBussiness(String mobile, String clienttype);
    }

    interface View extends BaseView {



    }

    abstract class Presenter extends BasePresenter<Moudle, View> {



    }
}
