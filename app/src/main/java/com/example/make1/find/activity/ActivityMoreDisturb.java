package com.example.make1.find.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.widget.DrawerLayout;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;

import com.example.make1.find.R;
import com.example.make1.find.fragment.FragmentMoreDisturb;
import com.example.make1.find.fragment.FragmentRegister;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ActivityMoreDisturb extends FragmentActivity implements View.OnClickListener {

    private DrawerLayout drawerLayout;
    private ImageView actionbar_img;
    private Button btn_left1, btn_left2;
    @BindView(R.id.mImgBack)
    ImageButton mImgBack;
    Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_more_disturb);
        ButterKnife.bind(this);

        initView();
        initListener();
        initData();
    }

    private void initView() {
        drawerLayout = (DrawerLayout) findViewById(R.id.drawerLayoutMoreDisturb);
    }

    private void initListener() {
        mImgBack.setOnClickListener(this);
    }

    private void initData() {
        addFragmentToStack(new FragmentMoreDisturb());

    }

    private void addFragmentToStack(Fragment fragment) {
        getSupportFragmentManager().beginTransaction().replace(R.id.frameLayoutMoreDisturb, fragment).commit();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.mImgBack:
                setResult(9);
                finish();
                break;
            default:
        }
    }
}
