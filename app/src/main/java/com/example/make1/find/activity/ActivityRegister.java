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
import com.example.make1.find.fragment.FragmentLogin;
import com.example.make1.find.fragment.FragmentRegister;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ActivityRegister extends FragmentActivity implements View.OnClickListener {

    private DrawerLayout drawerLayout;
    private ImageView actionbar_img;
    private Button btn_left1, btn_left2;
    @BindView(R.id.mImgBack)
    ImageButton mImgBack;
    Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_main);
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
        drawerLayout = (DrawerLayout) findViewById(R.id.drawerLayoutRegister);
//        drawerLayout = (DrawerLayout) findViewById(R.id.drawerLayout);
//        actionbar_img = (ImageView) findViewById(R.id.actionbar_img);
//        btn_left1 = (Button) findViewById(R.id.btn_left1);
//        btn_left2 = (Button) findViewById(R.id.btn_left2);
    }

    private void initListener() {
//        actionbar_img.setOnClickListener(this);
//        btn_left1.setOnClickListener(this);
//        btn_left2.setOnClickListener(this);
        mImgBack.setOnClickListener(this);
    }

    private void initData() {
//        manager = getSupportFragmentManager();
        addFragmentToStack(new FragmentRegister());

    }

    private void addFragmentToStack(Fragment fragment) {
        getSupportFragmentManager().beginTransaction().replace(R.id.frameLayoutRegister, fragment).commit();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
//            case R.id.actionbar_img:
//                drawerLayout.openDrawer(Gravity.LEFT);
//                break;
//            case R.id.btn_left1:
//                drawerLayout.closeDrawer(Gravity.LEFT);
//                addFragmentToStack(new FragmentLoginPhone());
//                break;
//            case R.id.btn_left2:
//                drawerLayout.closeDrawer(Gravity.LEFT);
//                addFragmentToStack(new FragmentLoginEmail());
//                break;
            case R.id.mImgBack:
                intent = new Intent(ActivityRegister.this, ActivityLogin.class);
                startActivity(intent);
                break;
            default:
        }
    }
}
