package huang.bling.hackathon.aladdin.ui.homepage;

import com.sunlandgroup.aladdin.baseconfig.BaseMoudle;
import com.sunlandgroup.aladdin.baseconfig.BasePresenter;
import com.sunlandgroup.aladdin.baseconfig.BaseView;

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
