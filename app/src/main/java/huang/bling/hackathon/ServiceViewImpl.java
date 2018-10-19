package huang.bling.hackathon;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.app.TaskStackBuilder;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.IBinder;
import android.os.VibrationEffect;
import android.os.Vibrator;
import android.support.annotation.RequiresApi;
import android.support.v4.app.NotificationCompat;

public class ServiceViewImpl<TsunamiInfo> extends Service implements AppContract.ServiceView<TsunamiInfo> {

    NotificationManager myManager;
    NotificationCompat.Builder myBuilder;
    private final int notId=222;
    Vibrator myVibrator;

    TsunamiInfo info;
    private Intent myIntent;
    private int flags;
    private int myId;
    private ServicePresentorImpl myPresentor;

    @Override
    public int onStartCommand(Intent intent,int flags, int startId){
        myIntent=intent;
        this.flags=flags;
        myId=startId;
        myPresentor=new ServicePresentorImpl(this);
        while(true){
            myPresentor.queryCurrentTsunamiInfo();
            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                e.printStackTrace();
                break;
            }
        }


        return Service.START_NOT_STICKY;
    }
    @Override
    public IBinder onBind(Intent intent) {

        return null;
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    public void setTsunamiInfo(Object o) {
        boolean alarm=false;

        //data processing procedure

        alarm=true;

        //data processing procedure ends

        if(alarm==true){
            myManager=(NotificationManager)getSystemService(Context.NOTIFICATION_SERVICE);
            Intent resultIntent=new Intent(this,MainActivity.class);
            Bundle ack=new Bundle();
            ack.putInt("alarm",1);
            resultIntent.putExtras(ack);
            TaskStackBuilder stackBuilder=TaskStackBuilder.create(this);
            stackBuilder.addParentStack(MainActivity.class);
            stackBuilder.addNextIntent(resultIntent);
            PendingIntent resultPendingIntent=
                    stackBuilder.getPendingIntent(0,PendingIntent.FLAG_UPDATE_CURRENT);

            myBuilder=new NotificationCompat.Builder(this)
                    .setSmallIcon(R.drawable.ic_launcher_background)
                    .setContentTitle("Alarm!")
                    .setContentText("A Tsunami is coming!")
                    .setPriority(2);
            myBuilder.setContentIntent(resultPendingIntent);
            myManager.notify(notId,myBuilder.build());

            myVibrator=(Vibrator)getSystemService(Context.VIBRATOR_SERVICE);
            myVibrator.vibrate(VibrationEffect.createOneShot(5000, VibrationEffect.DEFAULT_AMPLITUDE));
        }
    }
}
