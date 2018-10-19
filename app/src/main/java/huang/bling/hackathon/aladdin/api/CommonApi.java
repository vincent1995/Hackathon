package huang.bling.hackathon.aladdin.api;

import com.sunlandgroup.aladdin.bean.common.LoginBean;

import retrofit2.http.GET;
import retrofit2.http.Query;
import rx.Observable;

/**
 * Created by 沈东 on 2016/11/9.
 */

public interface CommonApi {

    @GET("rest/login/verifycode")
    Observable<LoginBean> getLogin(@Query("mobile") String mobile, @Query("code") String code);

    @GET("rest/login/getcode")
    Observable<LoginBean> getCode(@Query("mobile") String mobile);

}
