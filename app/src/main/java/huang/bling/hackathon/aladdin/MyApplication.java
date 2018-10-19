package huang.bling.hackathon.aladdin;

import android.app.Application;
import android.content.Context;
import android.support.multidex.MultiDex;

import com.facebook.drawee.backends.pipeline.Fresco;
import com.sunlandgroup.aladdin.dao.GreenDaoManager;
import com.sunlandgroup.aladdin.util.Utils;

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
        GreenDaoManager.getInstance();
        Fresco.initialize(this);
    }

    public static Context getContext() {
        return context;
    }

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        MultiDex.install(this);
    }

}
