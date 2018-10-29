package com.example.xiaoxiong.test;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.Toast;

public class MyBroadcastReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {

        String referrer = intent.getStringExtra("referrer");
        Log.d("MyBroadcastReceiver","referrer: " + referrer);
        Toast.makeText(context, "receive in MyBroadcastReceiver", Toast.LENGTH_SHORT).show();
        //abortBroadcast();
    }

}
