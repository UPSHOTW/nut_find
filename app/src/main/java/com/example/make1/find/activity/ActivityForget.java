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
import com.example.make1.find.fragment.FragmentForget;
import com.example.make1.find.fragment.FragmentRegister;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ActivityForget extends FragmentActivity implements View.OnClickListener {

    private DrawerLayout drawerLayout;
    @BindView(R.id.mImgBack)
    ImageButton mImgBack;
    Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forget_main);
        ButterKnife.bind(this);

        initView();
        initListener();
        initData();
    }

    private void initView() {
        drawerLayout = (DrawerLayout) findViewById(R.id.drawerLayoutRegister);

    }

    private void initListener() {

        mImgBack.setOnClickListener(this);
    }

    private void initData() {
        addFragmentToStack(new FragmentForget());

    }

    private void addFragmentToStack(Fragment fragment) {
        getSupportFragmentManager().beginTransaction().replace(R.id.frameLayoutForget, fragment).commit();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.mImgBack:
                intent = new Intent(ActivityForget.this, ActivityLogin.class);
                startActivity(intent);
                break;
            default:
        }
    }
}
