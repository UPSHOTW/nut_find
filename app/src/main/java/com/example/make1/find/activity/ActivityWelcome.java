package com.example.make1.find.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;

import com.example.make1.find.R;

import butterknife.ButterKnife;

/**
 * Created by make1 on 2017/8/2.
 * 欢迎界面
 */

public class ActivityWelcome extends AppCompatActivity {
    Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.welcome);
        ButterKnife.bind(this);
        //休眠3秒
        handler.sendEmptyMessageDelayed(0, 3000);
    }

    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            getHome();
            super.handleMessage(msg);
        }
    };

    public void getHome() {
        Intent intent = new Intent(ActivityWelcome.this, ActivityLogin.class);
        startActivity(intent);
        finish();
    }
}