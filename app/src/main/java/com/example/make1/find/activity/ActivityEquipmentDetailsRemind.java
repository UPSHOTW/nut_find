package com.example.make1.find.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import com.example.make1.find.R;


import butterknife.BindView;
import butterknife.ButterKnife;

/**
 *手机提醒页面
 */

public class ActivityEquipmentDetailsRemind extends AppCompatActivity implements View.OnClickListener {
    @BindView(R.id.mImgBack)
    ImageView mImgBack;

    Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.equipment_details_remind);
        ButterKnife.bind(this);

    }

    @Override
    public void onResume() {
        super.onResume();
        mImgBack.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.mImgBack:
                intent = new Intent(ActivityEquipmentDetailsRemind.this, ActivityEquipmentDetails.class);
                startActivity(intent);
                break;
            default:
        }
    }

    /**
     * 选择断开提醒时长弹出框
     */
    public void WarnTime(View v) {
        Log.i("t","点击");

        }



}