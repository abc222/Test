package com.example.xiaoxiong.test;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.RatingBar;

import java.sql.SQLOutput;

public class RatingBarTestActivity extends AppCompatActivity {

    public static final String TAG = "RatingBarTestActivity";

    //可交互的两个RatingBar
    private RatingBar ratingBar1 = null;
    private RatingBar ratingBar2 = null;

    //不可交互的两个RatingBar
    private RatingBar ratingBarOne = null;
    private RatingBar ratingBarTwo = null;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rating_bar_test);
        initView();
    }

    //初始化函数
    public void initView(){

        //获得控件
        ratingBar1 = (RatingBar)findViewById(R.id.ratingBar1);
        ratingBar2 = (RatingBar)findViewById(R.id.ratingBar2);
        ratingBarOne = (RatingBar)findViewById(R.id.ratingBarOne);
        ratingBarTwo = (RatingBar)findViewById(R.id.ratingBarTwo);

        //为ratingBar1添加 OnRatingBarChangeListener
        //当用户交互改变分值时，触发该事件
        ratingBar1.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
            @Override
            public void onRatingChanged(RatingBar ratingBar, float rating, boolean fromUser) {
                //第一个参数，当前评分修改的 ratingBar
                Log.d(TAG,"ratingBar: " + ratingBar);
                //第二个参数，当前评分分手，范围 0~星星数量
                Log.d(TAG,"rating: " + rating);
                //第三个参数，如果评分改变是由用户触摸手势或方向键轨迹球移动触发的，则返回true
                Log.d(TAG,"fromUser:" + fromUser);

                //设置不可交互的展示型ratingBarOne的评分分数
                ratingBarOne.setRating(rating);
            }
        });

        //为ratingBar2添加 OnRatingBarChangeListener
        //当用户交互改变分值时，触发该事件
        ratingBar2.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
            @Override
            public void onRatingChanged(RatingBar ratingBar, float rating, boolean fromUser) {
                ratingBarTwo.setRating(rating);
            }
        });
    }
}
