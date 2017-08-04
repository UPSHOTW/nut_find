package com.example.make1.find.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.widget.BaseAdapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.make1.find.R;

import java.util.List;
import java.util.Map;

/**
 * Created by make1 on 2017/8/3.
 */

public class EquipmentAdapter extends BaseAdapter {
    private List<Map<String, Object>> data;
    private LayoutInflater layoutInflater;
    private Context context;

    public EquipmentAdapter(Context context, List<Map<String, Object>> data) {

//传入的data,就是我们要在listView中显示的内容
        this.context = context;
        this.data = data;
        this.layoutInflater = LayoutInflater.from(context);
    }

    //这里定义一个类，用力啊表示一个item里面包含的东西
    public class Info {
        public ImageView image;
        public TextView mTxtEquipment;
        public TextView mTxtmTxtLinkTime;

    }

    //所有要返回的信息，都在data里面，从data里面取
    @Override
    public int getCount() {
        return data.size();
    }

    @Override
    public Object getItem(int position) {
        return data.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    //跟actvity中的oncreat()差不多，目的就是给item布局中的各个控件对应好，并添加数据
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // TODO Auto-generated method stub
        Info info = new Info();
        convertView = layoutInflater.inflate(R.layout.list_equipment, null);
        info.image = (ImageView) convertView.findViewById(R.id.imgEquipment);
        info.mTxtEquipment = (TextView) convertView.findViewById(R.id.mTxtEquipment);
        info.mTxtmTxtLinkTime = (TextView) convertView.findViewById(R.id.mTxtLinkTime);
        info.image.setImageResource((Integer) data.get(position).get("img_logo_null"));
        info.mTxtEquipment.setText((String) data.get(position).get("笔记本电脑"));
        info.mTxtmTxtLinkTime.setText((String) data.get(position).get("25分钟前"));
        return convertView;
    }

}











