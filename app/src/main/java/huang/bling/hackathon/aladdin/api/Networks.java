package huang.bling.hackathon.aladdin.api;

import com.sunlandgroup.aladdin.BuildConfig;
import com.sunlandgroup.aladdin.global.Constants;
import com.sunlandgroup.aladdin.util.SLog;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.nio.charset.Charset;
import java.util.concurrent.TimeUnit;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;
import okio.Buffer;
import okio.BufferedSource;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by 沈东 on 2016/11/9.
 */

public class Networks {

    private static final int DEFAULT_TIMEOUT = 20;

    private static Retrofit retrofit;

    private static CommonApi commonApi;

    private static Networks mNetworks;

    public static Networks getInstance() {
        if (mNetworks == null) {
            mNetworks = new Networks();
        }
        return mNetworks;
    }

    public CommonApi getCommonApi() {
        return commonApi == null ? configRetrofit(CommonApi.class) : commonApi;
    }



    private <T> T configRetrofitPort(Class<T> service) {
        retrofit = new Retrofit.Builder()
                .baseUrl(Constants.HOSTURL)
                .client(configClient())
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .build();

        return retrofit.create(service);

    }

    private <T> T configRetrofit(Class<T> service) {
        retrofit = new Retrofit.Builder()
                .baseUrl(Constants.APP_BASE_URL)
                .client(configClient())
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .build();

        return retrofit.create(service);

    }

    private <T> T configOtherServiceRetrofit(Class<T> service,String address) {
        retrofit = new Retrofit.Builder()
                .baseUrl(address)
                .client(configClient())
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .build();

        return retrofit.create(service);
    }

    public OkHttpClient configClient() {
        OkHttpClient.Builder okHttpClient = new OkHttpClient.Builder();

        //为所有请求添加头部 Header 配置的拦截器
        Interceptor headerIntercept = new Interceptor() {
            @Override
            public Response intercept(Chain chain) throws IOException {

                Request request = chain.request()
                        .newBuilder()
                       /* .addHeader("Content-Type", "text/html; charset=UTF-8")
                        .addHeader("Accept-Encoding", "*")
                        .addHeader("Connection", "keep-alive")*/
                        //   .addHeader("Accept", "*/*")
                       /* .addHeader("Access-Control-Allow-Origin", "*")
                        .addHeader("Access-Control-Allow-Headers", "X-Requested-With")
                        .addHeader("Vary", "Accept-Encoding")
                        .addHeader("Cookie", "add cookies here")*/
                        .build();

                return chain.proceed(request);
            }
        };

        // Log信息拦截器
        if (BuildConfig.DEBUG) {
            Interceptor loggingIntercept = new Interceptor() {
                @Override
                public Response intercept(Chain chain) throws IOException {
                    Request request = chain.request();
                    Response response = chain.proceed(request);
                    ResponseBody responseBody = response.body();
                    BufferedSource source = responseBody.source();
                    source.request(Long.MAX_VALUE); // Buffer the entire body.
                    Buffer buffer = source.buffer();
                    Charset UTF8 = Charset.forName("UTF-8");

                    SLog.logJ("REQUEST_JSON", buffer.clone().readString(UTF8));
                    SLog.logi("REQUEST_URL", request.toString());
                    return response;
                }
            };
            //使用拦截器
            okHttpClient.addInterceptor(loggingIntercept);
        }


        Interceptor loggingIntercept = new Interceptor() {
            @Override
            public Response intercept(Chain chain) throws IOException {
                Request request = chain.request();
                Response response = chain.proceed(request);
                ResponseBody responseBody = response.body();
                BufferedSource source = responseBody.source();
                source.request(Long.MAX_VALUE); // Buffer the entire body.
                Buffer buffer = source.buffer();
                Charset UTF8 = Charset.forName("UTF-8");
                String result =  buffer.clone().readString(UTF8);
                if (!result.equals("[]")) {
                    try {
                        JSONObject jsonobject = new JSONObject(result);
                        if (jsonobject.getString("status").equals("-1")) {
                            //EventBus.getDefault().post(new OutDueEvent("outdue"));
                           // throw new IOException("验证码过期");
                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
                return response;
            }
        };
        //使用拦截器
      //  okHttpClient.addInterceptor(loggingIntercept);


        //设置超时时间
        okHttpClient.connectTimeout(DEFAULT_TIMEOUT, TimeUnit.SECONDS);
        //使用网络拦截器
        okHttpClient.addNetworkInterceptor(headerIntercept);

        return okHttpClient.build();
    }
}
