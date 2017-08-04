package com.example.make1.find.activity;




import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;

import android.support.v4.app.FragmentActivity;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;

import com.example.make1.find.R;
import com.example.make1.find.fragment.FragmentDisturbArea;
import com.example.make1.find.fragment.FragmentMoreDisturbTab;


import butterknife.BindView;
import butterknife.ButterKnife;


/**
 * Created by make1 on 2017/8/2.
 */

public class ActivityMoreDisturb extends FragmentActivity implements View.OnClickListener {
    @BindView(R.id.mImgBack)
    ImageButton mImgBack;
    @BindView(R.id.drawerLayout)
    DrawerLayout drawerLayout;
    Intent intent;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_more_disturb);
        ButterKnife.bind(this);
        initData();

    }
    private void initData() {
        addFragmentToStack(new FragmentMoreDisturbTab());

    }
    private void addFragmentToStack(Fragment fragment) {
        getSupportFragmentManager().beginTransaction().replace(R.id.frameLayout, fragment).commit();
    }


    @Override
    public void onResume() {
        super.onResume();
        mImgBack.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.mImgBack:
                setResult(10);
                finish();
                break;

        }
    }
}