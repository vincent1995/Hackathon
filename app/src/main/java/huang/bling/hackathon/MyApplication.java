package huang.bling.hackathon;

import android.app.Application;
import android.content.Context;


import huang.bling.hackathon.util.Utils;


/**
 * Created by 沈东 on 2016/11/9.
 */

public class MyApplication extends Application {

    private static Context context;

    @Override
    public void onCreate() {
        super.onCreate();
        context = getApplicationContext();
        Utils.init(this);

    }

    public static Context getContext() {
        return context;
    }

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);

    }

}
