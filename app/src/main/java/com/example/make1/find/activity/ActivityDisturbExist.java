package com.example.make1.find.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;

import com.example.make1.find.R;
import com.example.make1.find.adapter.EquipmentAdapter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.StringTokenizer;

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
        //去掉标题栏（继承自AppCompatActivity时）
        if (getSupportActionBar() != null) {
            getSupportActionBar().hide();
        }
        setContentView(R.layout.disturb_exist);
        ButterKnife.bind(this);
        //添加数据
        List<Map<String, Object>> list = getData();
        //适配器
        EquipmentAdapter equipmentAdapter = new EquipmentAdapter(this, list);
        //设置适配器
        list_equipment.setAdapter(equipmentAdapter);
        return;


    }

    public List<Map<String, Object>> getData() {
        List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
        for (int i = 0; i < 10; i++) {
            Map<String, Object> map = new HashMap<String, Object>();
            map.put("image", R.mipmap.img_logo_null);
            map.put("imgEquipment", "笔记本电脑");
            map.put("mTxtEquipment", "40分钟之前");
            list.add(map);
        }
        return list;
    }
}


































