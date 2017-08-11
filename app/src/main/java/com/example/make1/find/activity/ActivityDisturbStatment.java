package com.example.make1.find.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.SimpleAdapter;

import com.example.make1.find.R;

import java.util.ArrayList;
import java.util.HashMap;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 *
 */

public class ActivityDisturbStatment extends AppCompatActivity implements View.OnClickListener {
    @BindView(R.id.mImgBack)
    ImageView mImgBack;
    @BindView(R.id.mRltDisturbArea)
    RelativeLayout mRltDisturbArea;
    Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.equipment_disturb_statement);
        ButterKnife.bind(this);
    }

    @Override
    public void onResume() {
        super.onResume();
        mImgBack.setOnClickListener(this);
        mRltDisturbArea.setOnClickListener(this);

    }
    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.mImgBack:
                intent = new Intent(ActivityDisturbStatment.this,ActivityEquipmentDetails.class);
                startActivity(intent);
                break;
            case R.id.mRltDisturbArea:
                break;
            default:
        }
    }

}