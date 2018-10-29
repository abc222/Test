package com.example.xiaoxiong.test;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.Toast;

public class ScreeOnBroadcastReceiver extends BroadcastReceiver {

    private static final String TAG = "ScreeBroadcastReceiver";

    @Override
    public void onReceive(Context context, Intent intent) {
        Log.d(TAG, "接收到了系统屏幕变亮的广播数据");
        Toast.makeText(context, "接收到了系统屏幕变亮的广播数据", Toast.LENGTH_SHORT).show();
    }
}
