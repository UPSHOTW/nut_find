package com.example.make1.find.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import com.example.make1.find.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Objects;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 *
 */

public class ActivityBoundFound extends AppCompatActivity implements View.OnClickListener {
    @BindView(R.id.mImgBack)
    ImageView mImgBack;
    @BindView(R.id.list_found)
    ListView list_found;

    Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.bound_found);
        ButterKnife.bind(this);
        ArrayList<HashMap<String,Object>> founditem = new ArrayList<HashMap<String,Object>>();
        HashMap<String,Object> map = new HashMap<String,Object>();
        map.put("imgEquipment",R.mipmap.ic_add_nut_logo);
        map.put("txtEquipmentName","Nut3");
        map.put("txtSign","较强");
        map.put("imgNext",R.mipmap.ic_web_next);
        founditem.add(map);
        SimpleAdapter founditemAdapter = new SimpleAdapter(this,founditem,R.layout.found_item,new  String[]{
                "imgEquipment","txtEquipmentName","txtSign","imgNext"},new int[]{R.id.imgEquipment,R.id.txtEquipmentName,R.id.txtSign,R.id.imgNext
                });
        //添加并显示
        list_found.setAdapter(founditemAdapter);
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
                intent = new Intent(ActivityBoundFound.this,ActivityFriendsAddBound.class);
                startActivity(intent);
                break;
            default:
        }
    }

}