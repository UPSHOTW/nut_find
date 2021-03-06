package com.example.make1.find.activity;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.make1.find.R;
import com.example.make1.find.fragment.FragmentLogin;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ActivityLogin extends FragmentActivity implements View.OnClickListener {
    @BindView(R.id.mTxtRegister)
    TextView mTxtRegister;
    @BindView(R.id.mBtnLogin)
    Button mBtnLogin;
    @BindView(R.id.rltOther)
    RelativeLayout rltOther;
    @BindView(R.id.mRltOtherLogin)
    RelativeLayout mRltOtherLogin;
    @BindView(R.id.imgOtherUp)
    ImageView imgOtherUp;
    @BindView(R.id.mTxtForgot)
    TextView mTxtForgot;
    Intent intent;
    int controlHeight;
    int controlWidth;
    private boolean isVisible = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_main);
        ButterKnife.bind(this);
        initView();
        initListener();
        initData();
        controlHeight();
    }
    //获取控件高度
    private void controlHeight() {
        ViewTreeObserver vto = rltOther.getViewTreeObserver();
        vto.addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                rltOther.getViewTreeObserver().removeGlobalOnLayoutListener(this);
                controlHeight =  rltOther.getHeight();
               controlWidth = rltOther.getWidth();

            }
        });
    }

    private void initView() {
    }

    private void initListener() {
        mTxtRegister.setOnClickListener(this);
        mBtnLogin.setOnClickListener(this);
        rltOther.setOnClickListener(this);
        mRltOtherLogin.setOnClickListener(this);
        mTxtForgot.setOnClickListener(this);
    }

    private void initData() {
        addFragmentToStack(new FragmentLogin());

    }

    private void addFragmentToStack(Fragment fragment) {
        getSupportFragmentManager().beginTransaction().replace(R.id.frameLayoutLogin, fragment).commit();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.mTxtRegister:
                intent = new Intent(ActivityLogin.this, ActivityRegister.class);
                startActivity(intent);
                break;
            case R.id.mBtnLogin:
                intent = new Intent(ActivityLogin.this, MainTab.class);
                startActivity(intent);
                break;
            case R.id.mTxtForgot:
                intent = new Intent(ActivityLogin.this, ActivityForget.class);
                startActivity(intent);
                break;
            case R.id.mRltOtherLogin:
                if (isVisible) {
                    isVisible = false;

                    //动画1
                    ObjectAnimator oa1 = ObjectAnimator.ofFloat(rltOther, "translationY", 0F, -controlHeight);
                    //动画2
                    ObjectAnimator oa2 = ObjectAnimator.ofFloat(mRltOtherLogin, "translationY", 0F, -controlHeight);
                    //创建动画集
                    AnimatorSet animatorSet = new AnimatorSet();
                    //设置同时播放
                    animatorSet.playTogether(oa1, oa2);
                    // animatorSet.playSequentially(oa1, oa2);//按顺序播放动画
                    animatorSet.setDuration(500);
                    //开始动画
                    animatorSet.start();
                    imgOtherUp.setBackgroundResource(R.mipmap.ic_up);
                } else {
                    isVisible = true;
                    //动画1
                    ObjectAnimator oa1 = ObjectAnimator.ofFloat(rltOther, "translationY", -controlHeight, 0F);
                    //动画2
                    ObjectAnimator oa2 = ObjectAnimator.ofFloat(mRltOtherLogin, "translationY", -controlHeight, 0F);
                    //创建动画集
                    AnimatorSet animatorSet = new AnimatorSet();
                    //设置同时播放
                    animatorSet.playTogether(oa1, oa2);
                    // animatorSet.playSequentially(oa1, oa2);//按顺序播放动画
                    animatorSet.setDuration(1000);
                    //开始动画
                    animatorSet.start();
                    imgOtherUp.setBackgroundResource(R.mipmap.ic_textfield_down);
                }
                break;
            default:
        }
    }
}
