package com.example.make1.find.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;

import com.example.make1.find.R;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 设备绑定说明
 */

public class ActivityBoundExplain extends AppCompatActivity implements View.OnClickListener {
    @BindView(R.id.mImgBack)
    ImageView mImgBack;
    Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.bound_explain);
        ButterKnife.bind(this);
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
                intent = new Intent(ActivityBoundExplain.this, ActivityFriendsAddBound.class);
                startActivity(intent);
                break;
            default:
        }
    }
}