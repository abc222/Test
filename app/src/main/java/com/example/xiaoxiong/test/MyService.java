package com.example.xiaoxiong.test;

import android.app.Notification;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.os.Binder;
import android.os.IBinder;
import android.support.v4.app.NotificationCompat;
import android.util.Log;

public class MyService extends Service {

    public static final String TAG = "MyService";

    private DownloadBinder myBinder = new DownloadBinder();

    public MyService() {
    }

    @Override
    public IBinder onBind(Intent intent) {
        return myBinder;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        Log.d(TAG,"onCreate executed");
        Intent intent = new Intent(this,MainActivity.class);
        PendingIntent pi = PendingIntent.getActivity(this,0, intent,0);
        Notification notification = new NotificationCompat.Builder(this)
                .setContentTitle("This is title.")
                .setContentText("This is text")
                .setWhen(System.currentTimeMillis())
                .setSmallIcon(R.mipmap.ic_launcher)
                .setLargeIcon(BitmapFactory.decodeResource(getResources(), R.mipmap.ic_launcher))
                .setContentIntent(pi)
                .build();
        startForeground(1, notification);
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.d(TAG,"onStartCommand executed");
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void onDestroy() {
        Log.d(TAG,"onDestroy executed");
        super.onDestroy();
    }

    class DownloadBinder extends Binder {

        public void startDownload() {
            Log.d(TAG,"startDownload executed");
        }

        public int getProcess() {
            Log.d(TAG,"getProcess executed");
            return 0;
        }
    }
}
