package com.example.make1.find.activity;


import android.accessibilityservice.AccessibilityService;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.ViewTreeObserver;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.example.make1.find.R;
import com.example.make1.find.utils.DensityDpToPx;

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
    private Context context;
    WindowManager vm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.bound_equipment);
        context = ActivityBoundEquipment.this;
        ButterKnife.bind(this);
        initListener();
        screen();
    }

    private void screen() {
        vm = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        int width = vm.getDefaultDisplay().getWidth();//左侧设置的间距
        int height = DensityDpToPx.dpToPx(context, 18);//处于第二个的高度间距，这个高度需要自己进行调试，找到一个合适的高度

        LinearLayout.LayoutParams paramsLaptop = (LinearLayout.LayoutParams) mBtnLaptop.getLayoutParams();
        paramsLaptop.setMargins(width / 2, -height, 10, 10);//宽度设置为屏幕的一半，高度为合适的高度值
        mBtnLaptop.setLayoutParams(paramsLaptop);
        LinearLayout.LayoutParams paramsSuitcase = (LinearLayout.LayoutParams) mBtnSuitcase.getLayoutParams();
        paramsSuitcase.setMargins(width / 2, -height, 10, 10);//宽度设置为屏幕的一半，高度为合适的高度值
        mBtnSuitcase.setLayoutParams(paramsSuitcase);
        LinearLayout.LayoutParams paramsOther = (LinearLayout.LayoutParams) mBtnOther.getLayoutParams();
        paramsOther.setMargins(width / 2, -height, 10, 10);//宽度设置为屏幕的一半，高度为合适的高度值
        mBtnOther.setLayoutParams(paramsOther);
    }

    private void initListener() {
        mImgStop.setOnClickListener(this);
        mTxtFinish.setOnClickListener(this);
        mImgGoods.setOnClickListener(this);
        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                RadioButton rab = group.findViewById(checkedId);
                equipmentName = rab.getText().toString();
                mEdtArticle.setText(equipmentName);
            }
        });
    }

    @Override
    public void onResume() {
        super.onResume();

    }


    @Override
    public void onClick(View view) {
        switch (view.getId()) {
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