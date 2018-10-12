package com.example.xiaoxiong.test;

import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.TextView;

public class DynamicThemeActivity extends AppCompatActivity {

    private TextView mTextMessage;
    private static int sMessage;
    private static int sTheme;

//    private BottomNavigationView.OnNavigationItemReselectedListener mOnNavigationItemReselectedListener
//            = new BottomNavigationView.OnNavigationItemReselectedListener() {
//        @Override
//        public void onNavigationItemReselected(@NonNull MenuItem menuItem) {
//            case R.id.navigation_blue:
//            sMessage = R.string.title_blue;
//
//            //将选中的主题资源id保存到静态变量中
//            sTheme = R.style.AppThemeLightBlue;
//
//            //重构Activity才能应用新的主题
//            recreate();
//            return true;
//            case R.id.navigation_deep_blue:
//            sMessage = R.string.title_deep_blue;
//            sTheme = R.style.AppThemeDeepBlue;
//            recreate();
//            return true;
//            case R.id.navigation_green:
//            sMessage = R.string.title_green;
//            sTheme = R.style.AppThemeGreen;
//            recreate();
//            return true;
//            case R.id.navigation_pink:
//            sMessage = R.string.title_pink;
//            sTheme = R.style.AppThemePink;
//            recreate();
//            return true;
//        }
//            return false;
//    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dynamic_theme);
        if (sTheme!=0) {
            //设置主题
            setTheme(sTheme);
        }
        mTextMessage = (TextView) findViewById(R.id.message);
        if (sMessage!=0)
            mTextMessage.setText(sMessage);
//        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
//        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
    }
}
