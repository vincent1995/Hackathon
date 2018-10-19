package huang.bling.hackathon;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;

import huang.bling.hackathon.aladdin.MyApplication;
import huang.bling.hackathon.aladdin.baseconfig.base.BaseActivity;

public class MainActivity extends BaseActivity {
private static Context context;
private static int LOCREQUEST=1;
@Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        context=MyApplication.getContext();
        checkForPermission();

    }

    private void checkForPermission() {
        if(ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION)!=PackageManager.PERMISSION_GRANTED)
        {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION},LOCREQUEST);
        }
    }
//
//    @Override
//    public void updateMap(Object map) {
//
//    }
//
//    @Override
//    public void setLocationTsunamiInfo(Object location, Object o) {
//
//    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults){
if(requestCode==LOCREQUEST){
    if(grantResults.length==1 && grantResults[0]==PackageManager.PERMISSION_GRANTED){
        System.out.println("应用权限获取成功");
    }else{
        System.out.println("应用权限获取失败");
    }
}
}

}
