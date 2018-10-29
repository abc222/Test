package com.example.xiaoxiong.test;

import android.os.Looper;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.widget.TextView;

public class DynamicThemeActivity extends AppCompatActivity {

    private TextView mTextMessage;
    private static int sMessage;
    private static int sTheme;

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.item1:
                    sMessage = R.string.title_blue;
                    //将选中的主题资源id保存到静态变量中
                    sTheme = R.style.AppThemeLightBlue;
                    //重构Activity才能应用新的主题
                    recreate();
                    return true;
                case R.id.item2:
                    sMessage = R.string.title_deep_blue;
                    sTheme = R.style.AppThemeDeepBlue;
                    recreate();
                    return true;
                case R.id.item3:
                    sMessage = R.string.title_green;
                    sTheme = R.style.AppThemeGreen;
                    recreate();
                    return true;
                case R.id.item4:
                    sMessage = R.string.title_pink;
                    sTheme = R.style.AppThemePink;
                    recreate();
                    return true;
            }
            return false;
        }

    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (sTheme != 0) {
            //设置主题
            setTheme(sTheme);
        }
        setContentView(R.layout.activity_dynamic_theme);

        mTextMessage = (TextView) findViewById(R.id.message);
        if (sMessage != 0)
            mTextMessage.setText(sMessage);
        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
    }
}
