package com.example.make1.find.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.make1.find.R;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 *
 */

public class ActivityFriendsAddBound extends AppCompatActivity implements View.OnClickListener {
    @BindView(R.id.mImgBack)
    ImageView mImgBack;
    @BindView(R.id.mBtnFindNothing)
    Button mBtnFindNothing;

    Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.friends_add_bound);
        ButterKnife.bind(this);
    }

    @Override
    public void onResume() {
        super.onResume();
        mImgBack.setOnClickListener(this);
        mBtnFindNothing.setOnClickListener(this);

    }
    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.mImgBack:
                intent = new Intent(ActivityFriendsAddBound.this,ActivityFriendsAdd.class);
                startActivity(intent);
                break;
            case R.id.mBtnFindNothing:
               intent = new Intent(ActivityFriendsAddBound.this,ActivityBoundExplain.class);
                startActivity(intent);
                break;
            default:
        }
    }

}