package com.example.make1.find.activity;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.widget.DrawerLayout;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TableLayout;
import android.widget.TextView;

import com.example.make1.find.R;
import com.example.make1.find.fragment.FragmentLogin;
import com.example.make1.find.fragment.FragmentLoginPhone;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ActivityLogin extends FragmentActivity implements View.OnClickListener {

    private DrawerLayout drawerLayout;
    private ImageView actionbar_img;
    private Button btn_left1, btn_left2;
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
    private boolean isVisible = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_main);
        ButterKnife.bind(this);

//        actionBar = getActionBar();
//        actionBar.hide();
     /*   actionBar.setCustomView(R.layout.title);
        actionBar.setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);*/

        initView();
        initListener();
        initData();
    }

    private void initView() {
        //  drawerLayout = (DrawerLayout) findViewById(R.id.drawerLayoutLogin);
    }

    private void initListener() {
        mTxtRegister.setOnClickListener(this);
        mBtnLogin.setOnClickListener(this);
        rltOther.setOnClickListener(this);
        mRltOtherLogin.setOnClickListener(this);
        mTxtForgot.setOnClickListener(this);
    }

    private void initData() {
//        manager = getSupportFragmentManager();
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
                intent = new Intent(ActivityLogin.this,ActivityForget.class);
                startActivity(intent);
                break;
            case R.id.mRltOtherLogin:
                if (isVisible) {
                    isVisible = false;
                    //动画1
                    ObjectAnimator oa1 = ObjectAnimator.ofFloat(rltOther, "translationY", 0F, -150F);
                    //动画2
                    ObjectAnimator oa2 = ObjectAnimator.ofFloat(mRltOtherLogin, "translationY", 0F, -160F);
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
                    ObjectAnimator oa1 = ObjectAnimator.ofFloat(rltOther, "translationY", -150F, 0F);
                    //动画2
                    ObjectAnimator oa2 = ObjectAnimator.ofFloat(mRltOtherLogin, "translationY", -160F, 0F);
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
