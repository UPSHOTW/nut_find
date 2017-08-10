package com.example.make1.find.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import com.example.make1.find.R;

import java.util.ArrayList;
import java.util.HashMap;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by make1 on 2017/8/2.
 */

public class ActivityDisturbExist extends AppCompatActivity {
    @BindView(R.id.list_equipment)
    ListView list_equipment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.disturb_exist);
        ButterKnife.bind(this);
        ArrayList<HashMap<String, Object>> founditem = new ArrayList<HashMap<String, Object>>();
        HashMap<String, Object> map = new HashMap<String, Object>();
        map.put("imgEquipment", R.mipmap.ic_add_nut_logo);
        map.put("txtEquipmentName", "Nut3");
        map.put("mTxtDistance", "附近");
        map.put("mTxtLinkTime", "20分钟前");
        founditem.add(map);
        SimpleAdapter equipmentitemAdapter = new SimpleAdapter(this, founditem, R.layout.list_equipment, new String[]{
                "imgEquipment", "txtEquipmentName", "mTxtDistance", "mTxtLinkTime"}, new int[]{R.id.imgEquipment, R.id.txtEquipmentName, R.id.mTxtDistance, R.id.mTxtLinkTime
        });
        //添加并显示
        list_equipment.setAdapter(equipmentitemAdapter);

    }

    @Override
    public void onResume() {
        super.onResume();
    }
}


