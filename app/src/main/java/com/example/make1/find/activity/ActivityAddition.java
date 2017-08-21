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
import com.example.make1.find.fragment.FragmentAddition;
import com.example.make1.find.fragment.FragmentRegister;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ActivityAddition extends FragmentActivity implements View.OnClickListener {


    @BindView(R.id.mImgBack)
    ImageButton mImgBack;
    @BindView(R.id.drawerLayoutAddition)
    DrawerLayout drawerLayout;
    Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.more_friends_add_addition);
        ButterKnife.bind(this);
        initListener();
        initData();
    }

    private void initListener() {
        mImgBack.setOnClickListener(this);
    }

    private void initData() {
        addFragmentToStack(new FragmentAddition());

    }

    private void addFragmentToStack(Fragment fragment) {
        getSupportFragmentManager().beginTransaction().replace(R.id.frameLayoutAddition, fragment).commit();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.mImgBack:
                intent = new Intent(ActivityAddition.this, ActivityFriendsAdd.class);
                startActivity(intent);
                break;
            default:
        }
    }
}
