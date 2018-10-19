package huang.bling.hackathon;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationManager;

public class MyMapModule implements AppContract.MapModule<Integer,Location>{
    Context context;
    MyMapModule(Context context){
        this.context = context;
    }
    @Override
    public Integer getMap() {
        return null;
    }

    @Override
    public Location getCurrentLocation() {
        if( context.checkSelfPermission(Manifest.permission.ACCESS_FINE_LOCATION)
            == PackageManager.PERMISSION_GRANTED){
            LocationManager locationManager = (LocationManager) context.getSystemService(Context.LOCATION_SERVICE);
            return locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);
        }
        else{
            return null;
        }
    }
}
