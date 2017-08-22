package com.example.make1.find.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.make1.find.R;
import com.example.make1.find.utils.PickerView;


import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.example.make1.find.R.string.can;
import static com.example.make1.find.R.string.warntime;

/**
 * 手机提醒页面
 */

public class ActivityEquipmentDetailsRemind extends AppCompatActivity implements View.OnClickListener {
    @BindView(R.id.mImgBack)
    ImageView mImgBack;
    @BindView(R.id.mRltWarnTime)
    RelativeLayout mRltWarnTime;
    @BindView(R.id.mTxtTime)
    TextView mTxtTime;
    @BindView(R.id.mRltNotifySound)
    RelativeLayout mRltNotifySound;
    @BindView(R.id.mTxtVoice)
    TextView mTxtVoice;
    String time;
    PickerView pickerView;
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
        mRltWarnTime.setOnClickListener(this);
        mRltNotifySound.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.mImgBack:
                intent = new Intent(ActivityEquipmentDetailsRemind.this, ActivityEquipmentDetails.class);
                startActivity(intent);
                break;
            case R.id.mRltWarnTime:
                WarnTime();
                break;
            case R.id.mRltNotifySound:
                intent = new Intent(ActivityEquipmentDetailsRemind.this, ActivityRemindSound.class);
                startActivity(intent);
                Log.i("q", " mRltNotifySound.setOnClickListener-------->");
                break;
            default:
        }
    }

    /**
     * 选择断开提醒时长弹出框
     */
    public void WarnTime() {
        final AlertDialog.Builder builder = new AlertDialog.Builder(this);
        //初始化自定义布局参数
        LayoutInflater layoutInflater = getLayoutInflater();
        final View customLayout = layoutInflater.inflate(R.layout.dialog_warn_time, (ViewGroup) findViewById(R.id.customDialog));
        final Button mBtnAbrogate = customLayout.findViewById(R.id.mBtnAbrogate);
        final Button mBtnAffirm = customLayout.findViewById(R.id.mBtnAffirm);
        //为对话框设置视图
        builder.setView(customLayout);
        final AlertDialog alertDialog = builder.create();
        customLayout.setBackgroundResource(R.drawable.button_shape_white);
        pickerView = customLayout.findViewById(R.id.timePicker);
        //定义滚动选择器的数据项
        ArrayList<String> minute = new ArrayList<>();
        for (int i = 5; i < 60; i++) {
            minute.add(i + "");
        }
        //为滚动选择器设置数据
        pickerView.setData(minute);
        pickerView.setOnSelectListener(new PickerView.onSelectListener() {
            @Override
            public void onSelect(String seltime) {
                time = seltime.toString();
            }
        });
        mBtnAffirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mTxtTime.setText(time.toString() + "分钟");
                alertDialog.dismiss();
            }
        });
        mBtnAbrogate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mTxtTime.setText(warntime);
                alertDialog.dismiss();
            }
        });
        alertDialog.show();
    }


}

