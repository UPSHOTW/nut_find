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
import android.widget.Toast;

import com.example.make1.find.R;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by make1 on 2017/8/2.
 */

public class ActivityDialogBoundDelete extends AppCompatActivity implements View.OnClickListener {


    @BindView(R.id.mBtnDelete)
    Button mBtnDelete;
    @BindView(R.id.mBtnAbrogate)
    Button mBtnAbrogate;
    public static int T = 10;//倒计时长
    private static Handler mHandler = new Handler();
    Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dialog_bound_delete);
       ButterKnife.bind(this);
        new Thread(new MyCountDownTimer()).start();
    }

    @Override
    public void onResume() {
        super.onResume();
        mBtnDelete.setOnClickListener(this);
        mBtnAbrogate.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.mBtnDelete:
                // TODO: 2017/8/22 删除设备 
                Toast.makeText(ActivityDialogBoundDelete.this, R.string.delete_success, Toast.LENGTH_LONG).show();
                intent = new Intent(ActivityDialogBoundDelete.this,ActivityEquipmentDetails.class);
                startActivity(intent);
                break;
            case R.id.mBtnAbrogate:
                intent = new Intent(ActivityDialogBoundDelete.this,ActivityEquipmentDetails.class);
                startActivity(intent);
            default:
        }
    }

    /**
     * 自定义倒计时类，实现Runnable接口
     */
    class MyCountDownTimer implements Runnable {
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
                    mBtnDelete.setBackgroundResource(R.drawable.button_shape_red_small);
                }
            });
            T = 10;
        }
    }
}