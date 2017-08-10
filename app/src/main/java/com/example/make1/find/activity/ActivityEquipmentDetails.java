package com.example.make1.find.activity;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.widget.DrawerLayout;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.make1.find.R;
import com.example.make1.find.fragment.FragmentLogin;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ActivityEquipmentDetails extends FragmentActivity implements View.OnClickListener {

    private DrawerLayout drawerLayout;
    private ImageView actionbar_img;
    private Button btn_left1, btn_left2;

    Intent intent;
    private boolean isVisible = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_main);
        ButterKnife.bind(this);


        initView();
        initListener();
        initData();
    }

    private void initView() {
        //  drawerLayout = (DrawerLayout) findViewById(R.id.drawerLayoutLogin);
    }

    private void initListener() {

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

            default:
        }
    }
}
