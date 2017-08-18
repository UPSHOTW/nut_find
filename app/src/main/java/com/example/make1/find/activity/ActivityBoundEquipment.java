package com.example.make1.find.activity;


import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.example.make1.find.R;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 选择设备类型
 */

public class ActivityBoundEquipment extends AppCompatActivity implements View.OnClickListener {
    @BindView(R.id.mImgStop)
    ImageButton mImgStop;
    @BindView(R.id.mTxtFinish)
    TextView mTxtFinish;
    @BindView(R.id.mImgGoods)
    ImageView mImgGoods;
    @BindView(R.id.mBtnKey)
    RadioButton mBtnKey;
    @BindView(R.id.mBtnWallet)
    RadioButton mBtnWallet;
    @BindView(R.id.mBtnLaptop)
    RadioButton mBtnLaptop;
    @BindView(R.id.mBtnSuitcase)
    RadioButton mBtnSuitcase;
    @BindView(R.id.mBtnHaversack)
    RadioButton mBtnHaversack;
    @BindView(R.id.mBtnOther)
    RadioButton mBtnOther;
    @BindView(R.id.mEdtArticle)
    EditText mEdtArticle;
    private String equipmentName;
    Intent intent;
    @BindView(R.id.radioGroup)
    RadioGroup radioGroup;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.bound_equipment);
        ButterKnife.bind(this);
        initListener();
        // TODO: 2017/8/18 RadioButton不能实现单选 
    }

    private void initListener() {
        mImgStop.setOnClickListener(this);
        mTxtFinish.setOnClickListener(this);
        mImgGoods.setOnClickListener(this);
        mBtnKey.setOnClickListener(this);
        mBtnWallet.setOnClickListener(this);
        mBtnLaptop.setOnClickListener(this);
        mBtnSuitcase.setOnClickListener(this);
        mBtnHaversack.setOnClickListener(this);
        mBtnOther.setOnClickListener(this);
//        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
//            @Override
//            public void onCheckedChanged(RadioGroup group, int checkedId) {
//                RadioButton rab = (RadioButton)findViewById(radioGroup.getCheckedRadioButtonId());
//                String name = rab.getText().toString();
//                Log.i("q",name);
//            }
//        });
    }

    @Override
    public void onResume() {
        super.onResume();

    }


    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.mImgStop:
                intent = new Intent(ActivityBoundEquipment.this, ActivityBoundFound.class);
                startActivity(intent);
                break;
            case R.id.mBtnKey:
                equipmentName = mBtnKey.getText().toString();
                mEdtArticle.setText(equipmentName);
                break;
            case R.id.mBtnLaptop:
                equipmentName = mBtnLaptop.getText().toString();
                mEdtArticle.setText(equipmentName);
                break;
            case R.id.mBtnWallet:
                equipmentName = mBtnWallet.getText().toString();
                mEdtArticle.setText(equipmentName);
                break;
            case R.id.mBtnSuitcase:
                equipmentName = mBtnSuitcase.getText().toString();
                mEdtArticle.setText(equipmentName);
                break;
            case R.id.mBtnHaversack:
                equipmentName = mBtnHaversack.getText().toString();
                mEdtArticle.setText(equipmentName);
                break;
            case R.id.mBtnOther:
                equipmentName = mBtnOther.getText().toString();
                mEdtArticle.setText(equipmentName);
                break;
            case R.id.mTxtFinish:
                intent = new Intent(ActivityBoundEquipment.this, ActivityEquipmentDetails.class);
                //用Bundle携带数据
                Bundle bundle = new Bundle();
                //传递name参数
                bundle.putString("equipmentName", equipmentName);
                intent.putExtras(bundle);
                startActivity(intent);
                break;

            default:
        }
    }
}