package com.example.make1.find.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.example.make1.find.R;

import java.util.ArrayList;
import java.util.HashMap;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by make1 on 2017/8/11
 */

public class ActivityDisturbExist extends AppCompatActivity implements View.OnClickListener {
    @BindView(R.id.list_equipment)
    ListView list_equipment;
    @BindView(R.id.mImgBotherTime)
    ImageButton mImgBotherTime;
    private Context mContext;
    Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.disturb_exist);
        mContext = ActivityDisturbExist.this;
        ButterKnife.bind(this);
        EquipmentList();
        // TimeList();
        onGain();
    }

//    private void TimeList() {
//        ArrayList<HashMap<String, Object>> timeitem = new ArrayList<HashMap<String, Object>>();
//        HashMap<String, Object> map = new HashMap<String, Object>();
//        map.put("mTxtTime", "60分钟");
//        timeitem.add(map);
//        SimpleAdapter BotherTimeAdapter = new SimpleAdapter(this, timeitem, R.layout.list_time, new String[]{
//                "mTxtTime"}, new int[]{R.id.mTxtTime});
//        //添加并显示
//        time_list.setAdapter(BotherTimeAdapter);
//    }

    private void EquipmentList() {
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

    private void onGain() {
        View view = LayoutInflater.from(mContext).inflate(R.layout.list_equipment, null, false);
        // ImageView imgEquipment = view.findViewById(R.id.imgEquipment);
        LinearLayout mLytEquipmentDetails = view.findViewById(R.id.mLytEquipmentDetails);
        mLytEquipmentDetails.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                intent = new Intent(ActivityDisturbExist.this, ActivityEquipmentDetails.class);
                startActivity(intent);
            }
        });
    }

    @Override
    public void onResume() {
        mImgBotherTime.setOnClickListener(this);
        super.onResume();
    }

    /**
     * 使用PopWindow实现菜单从屏幕底部弹出
     *
     * @param v
     */
    private void initPopWindow(View v) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.disturb_bothertime, null, false);
        TextView mTxtThirtyMinute = view.findViewById(R.id.mTxtThirtyMinute);
        TextView mTxtTimeOneHour = view.findViewById(R.id.mTxtTimeOneHour);
        TextView mTxtTimeTwoHour = view.findViewById(R.id.mTxtTimeTwoHour);
        TextView mTxtTimeFourHours = view.findViewById(R.id.mTxtTimeFourHours);
        //1.构造一个PopupWindow，参数依次是加载的View，宽高
        final PopupWindow popWindow = new PopupWindow(view,
                ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        popWindow.setTouchable(true);
        popWindow.setTouchInterceptor(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                return false;
                // 这里如果返回true的话，touch事件将被拦截
                // 拦截后 PopupWindow的onTouchEvent不被调用，这样点击外部区域无法dismiss
            }
        });
        setBackgroundAlpha(0.5f);//设置屏幕透明度
        // popWindow.setBackgroundDrawable(new BitmapDrawable());    //要为popWindow设置一个背景才有效
        //设置popupWindow显示的位置，参数依次是参照View，x轴的偏移量，y轴的偏移量
        // popWindow.showAsDropDown(v, 0,820);
        //显示在根布局的底部
        popWindow.showAtLocation(view, Gravity.BOTTOM | Gravity.LEFT, 0, 0);
        //在退出时popupWindow时，需要恢复屏幕原有透明度
        popWindow.setOnDismissListener(new PopupWindow.OnDismissListener() {
            @Override
            public void onDismiss() {
                //隐藏时回复屏幕正常透明度
                setBackgroundAlpha(1.0f);
            }
        });
        //设置popupWindow里的按钮的事件
        mTxtThirtyMinute.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(ActivityDisturbExist.this, "30分钟", Toast.LENGTH_LONG).show();
                popWindow.dismiss();
            }
        });
        mTxtTimeOneHour.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(ActivityDisturbExist.this, "1小时", Toast.LENGTH_LONG).show();
                popWindow.dismiss();
            }
        });
        mTxtTimeTwoHour.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Toast.makeText(ActivityDisturbExist.this, "2小时", Toast.LENGTH_LONG).show();
                popWindow.dismiss();
            }
        });
        mTxtTimeFourHours.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(ActivityDisturbExist.this, "4小时", Toast.LENGTH_LONG).show();
                popWindow.dismiss();
            }
        });
    }

    /**
     * 设置添加屏幕的背景透明度
     *
     * @param bgAlpha 屏幕透明度0.0-1.0 1表示完全不透明
     */
    private void setBackgroundAlpha(float bgAlpha) {
        WindowManager.LayoutParams lp = ((ActivityDisturbExist) mContext).getWindow()
                .getAttributes();
        lp.alpha = bgAlpha;
        ((ActivityDisturbExist) mContext).getWindow().setAttributes(lp);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.mImgBotherTime:
                initPopWindow(view);
                break;
            default:
        }

    }
}




