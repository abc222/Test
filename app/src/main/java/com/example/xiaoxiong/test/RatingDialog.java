package com.example.xiaoxiong.test;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

public class RatingDialog extends Dialog {

    private ImageView img_back;
    private RatingBar ratingBar;
    private TextView textView;
    private Button encourage;

    public RatingDialog(Context context) {
        super(context, R.style.Dialog);
    }

    public RatingDialog(Context context, int themeResId) {
        super(context, themeResId);
    }

    protected RatingDialog(Context context, boolean cancelable, OnCancelListener cancelListener) {
        super(context, cancelable, cancelListener);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

//        在dialog中的oncreate方法中，将
//        setContentView(R.layout.link_type)改为
//        View view = this.getLayoutInflater().inflate(R.layout.link_type, null);
//        setContentView(view);
//        而控件则由view.findViewById(R.id.d)得到

        this.setContentView(R.layout.dialog_rate);

        initView();

        //点击空白区域是否消失
        //setCanceledOnTouchOutside(false);
    }

    public void initView(){
        img_back = (ImageView) findViewById(R.id.close);
        ratingBar = (RatingBar) findViewById(R.id.ratingBar);
        textView = (TextView) findViewById(R.id.textview);
        encourage = (Button) findViewById(R.id.encourage_button);
        img_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                RatingDialog.this.dismiss();
            }
        });
        ratingBar.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
            @Override
            public void onRatingChanged(RatingBar ratingBar, float rating, boolean fromUser) {
                //第一个参数，当前评分修改的 ratingBar
                //第二个参数，当前评分分手，范围 0~星星数量
                //第三个参数，如果评分改变是由用户触摸手势或方向键轨迹球移动触发的，则返回true
                textView.setVisibility(View.INVISIBLE);
                encourage.setVisibility(View.VISIBLE);
            }
        });
    }


}
