package com.example.make1.find.activity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.example.make1.find.R;
import com.example.make1.find.utils.PickerView;


import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 *手机提醒页面
 */

public class ActivityEquipmentDetailsRemind extends AppCompatActivity implements View.OnClickListener {
    @BindView(R.id.mImgBack)
    ImageView mImgBack;
    @BindView(R.id.mRltWarnTime)
    RelativeLayout mRltWarnTime;
    Intent intent;
    public PickerView pickerView;

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

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.mImgBack:
                intent = new Intent(ActivityEquipmentDetailsRemind.this, ActivityEquipmentDetails.class);
                startActivity(intent);
                break;
            case R.id.mRltWarnTime:
                dialog();
                break;
            default:
        }
    }

    /**
     * 选择断开提醒时长弹出框
     */
    public void dialog() {
        final AlertDialog.Builder builder = new AlertDialog.Builder(this);
        //初始化自定义布局参数
        LayoutInflater layoutInflater = getLayoutInflater();
        final View customLayout = layoutInflater.inflate(R.layout.dialog_warn_time, (ViewGroup)findViewById(R.id.customDialog));
        //为对话框设置视图
        builder.setView(customLayout);
        pickerView = (PickerView)customLayout.findViewById(R.id.timePicker);
        //定义滚动选择器的数据项
        ArrayList<String> grade = new ArrayList<>();
        for(int i=0;i<10;i++){
            grade.add(i+"");
        }
        //为滚动选择器设置数据
        pickerView.setData(grade);
        pickerView.setOnSelectListener(new PickerView.onSelectListener() {
            @Override
            public void onSelect(String text) {
                Log.i("tag","选择了"+text);
            }
        });
        //对话框的确定按钮
        builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {


            }
        });
        //对话框的取消按钮
        builder.setNegativeButton("取消",null);
        //显示对话框
        builder.show();

    }

}