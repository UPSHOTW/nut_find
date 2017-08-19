package com.example.make1.find.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.make1.find.R;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by make1 on 2017/8/2.
 *
 */

public class ActivityDialogBoundDelete extends AppCompatActivity implements View.OnClickListener {


    static Button mBtnDelete;
    public static int T = 10;//倒计时长
    private static Handler mHandler = new Handler();
    Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dialog_bound_delete);
       mBtnDelete =(Button) findViewById(R.id.mBtnDelete);
        new Thread(new MyCountDownTimer()).start();
    }

    @Override
    public void onResume() {
        super.onResume();

        mBtnDelete.setOnClickListener(this);
    }
    @Override
    public void onClick(View view) {
        switch (view.getId()) {

            case R.id.mBtnDelete:

                break;
            default:
        }
    }
    /**
     * 自定义倒计时类，实现Runnable接口
     */
    static class MyCountDownTimer implements Runnable {
        @Override
        public void run() {
            while (T > 0) {
                mHandler.post(new Runnable() {
                    @Override
                    public void run() {
                        mBtnDelete.setClickable(false);
                        mBtnDelete.setText("删除(" + T + ")");
                        Log.i("q", String.valueOf(T));
                    }
                });
                try {
                    Thread.sleep(1000);//间隔1秒
                } catch (InterruptedException e) {

                }
                T--;
            }
            //倒计时结束，结束循环
            mHandler.post(new Runnable() {
                @Override
                public void run() {
                    mBtnDelete.setClickable(true);
                    mBtnDelete.setText("删除");
                }
            });
            T = 10;
        }
    }


}