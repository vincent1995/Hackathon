package huang.bling.hackathon;

import android.os.AsyncTask;
import android.os.Build;
import android.support.annotation.RequiresApi;

public class ServiceAsync extends AsyncTask<ServiceViewImpl, Void, Void> {
    private AppContract.MapModule myMap;
    private AppContract.WeatherModule<Object, Object> myWeather;
    private Object myInfo;
    private ServiceViewImpl myView;
    @Override
    protected Void doInBackground(ServiceViewImpl... serviceViews) {
        myView=(ServiceViewImpl) serviceViews[0];
//        Object[] myLocation={myMap.getCurrentLocation()};
//        myInfo=myWeather.getTsunamiInfo(myLocation);
        return null;
    }
    @Override
    protected void onPreExecute(){

    }
    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onPostExecute(Void result){
        myView.setTsunamiInfo(myInfo);
    }
}
