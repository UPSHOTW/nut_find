package com.example.make1.find.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.make1.find.R;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 选择设备类型
 */

public class ActivityBoundEquipment extends AppCompatActivity implements View.OnClickListener {
    @BindView(R.id.mImgStop)
    ImageView mImgStop;
    @BindView(R.id.mTxtFinish)
    Button mTxtFinish;
    @BindView(R.id.mImgGoods)
    ImageView mImgGoods;
    @BindView(R.id.mBtnKey)
    Button mBtnKey;
    @BindView(R.id.mBtnWallet)
    Button mBtnWallet;
    @BindView(R.id.mBtnLaptop)
    Button mBtnLaptop;
    @BindView(R.id.mBtnSuitcase)
    Button mBtnSuitcase;
    @BindView(R.id.mBtnHaversack)
    Button mBtnHaversack;
    @BindView(R.id.mBtnOther)
    Button mBtnOther;
    @BindView(R.id.mEdtArticle)
    EditText mEdtArticle;
    private String equipmentName;
    Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.bound_equipment);
        ButterKnife.bind(this);
        initListener();
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
        mEdtArticle.setOnClickListener(this);
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
            case R.id.TxtFinish:
                setResult(20);
                finish();
                intent = new Intent(ActivityBoundEquipment.this, ActivityEquipmentDetails.class);
                //用Bundle携带数据
                Bundle bundle = new Bundle();
                //传递name参数为tinyphp
                bundle.putString("equipmentName", equipmentName);
                intent.putExtras(bundle);
                startActivity(intent);
                Log.i("q", "点击");
                break;
            case R.id.mEdtArticle:
                equipmentName = mEdtArticle.getText().toString();
                break;
            case R.id.mBtnKey:
                equipmentName = mBtnKey.getText().toString();
                Log.i("q", equipmentName);
                break;
            case R.id.mBtnLaptop:
                equipmentName = mBtnLaptop.getText().toString();
                break;
            case R.id.mBtnWallet:
                equipmentName = mBtnWallet.getText().toString();
                break;
            case R.id.mBtnSuitcase:
                equipmentName = mBtnSuitcase.getText().toString();
                break;
            case R.id.mBtnHaversack:
                equipmentName = mBtnHaversack.getText().toString();
                break;
            case R.id.mBtnOther:
                equipmentName = mBtnOther.getText().toString();
                break;
            default:
        }
    }

}