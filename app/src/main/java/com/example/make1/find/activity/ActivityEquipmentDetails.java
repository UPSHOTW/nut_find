package com.example.make1.find.activity;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AlertDialog;
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
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.make1.find.R;
import com.example.make1.find.fragment.FragmentEquipmentDetails;


import butterknife.BindView;
import butterknife.ButterKnife;

public class ActivityEquipmentDetails extends FragmentActivity implements View.OnClickListener {
    @BindView(R.id.mImgDetailsMore)
    ImageView mImgDetailsMore;
    @BindView(R.id.mImgBack)
    ImageButton mImgBack;
    @BindView(R.id.lytEquipmentDetails)
    LinearLayout lytEquipmentDetails;
    @BindView(R.id.mTxtEquipmentname)
    TextView mTxtEquipmentname;
    @BindView(R.id.mBtnLossStatement)
    Button mBtnLossStatement;
    @BindView(R.id.ImgDistance)
    ImageView ImgDistance;
    private Context mContext;
    Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.equipment_details);
        ButterKnife.bind(this);
        mContext = ActivityEquipmentDetails.this;
        initView();
        initListener();
        initData();
    }

    private void initView() {

    }

    private void initListener() {
        mImgDetailsMore.setOnClickListener(this);
        mImgBack.setOnClickListener(this);
        mBtnLossStatement.setOnClickListener(this);
        ImgDistance.setOnClickListener(this);

    }

    private void initData() {
        addFragmentToStack(new FragmentEquipmentDetails());

    }

    private void addFragmentToStack(Fragment fragment) {
        getSupportFragmentManager().beginTransaction().replace(R.id.frameLayoutEquipment, fragment).commit();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.mImgDetailsMore:
                initPopWindow(v);
                break;
            case R.id.mImgBack:
                intent = new Intent(ActivityEquipmentDetails.this, ActivityDisturbExist.class);
                startActivity(intent);
                break;
            case R.id.mBtnLossStatement:
                intent = new Intent(ActivityEquipmentDetails.this,ActivityDisturbStatment.class);
                startActivity(intent);
                break;
            case R.id.ImgDistance:
                intent = new Intent(ActivityEquipmentDetails.this,ActivityEquipmentLocation.class);
                startActivity(intent);
                break;
            default:
        }
    }

    /**
     * 使用PopWindow实现菜单从屏幕底部弹出
     *
     * @param v
     */
    private void initPopWindow(View v) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.equipment_details_more_item, null, false);//菜单具体内容布局文件
        //内容布局控件
        TextView mTxtAlterImg = view.findViewById(R.id.mTxtAlterImg);
        TextView mTxtAlterName = view.findViewById(R.id.mTxtAlterName);
        TextView mTxtDelete = view.findViewById(R.id.mTxtDelete);
        TextView mTxtCancel = view.findViewById(R.id.mTxtCancel);
        //1.构造一个PopupWindow，参数依次是加载的View，宽高
        final PopupWindow popWindow = new PopupWindow(view,
                ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        // popWindow.setAnimationStyle(R.anim.more_anim_pop);  //设置加载动画

        //这些为了点击非PopupWindow区域，PopupWindow会消失的，如果没有下面的
        //代码的话，你会发现，当你把PopupWindow显示出来了，无论你按多少次后退键
        //PopupWindow并不会关闭，而且退不出程序，加上下述代码可以解决这个问题
        popWindow.setTouchable(true);
        popWindow.setTouchInterceptor(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                // lytEquipmentDetails.setBackgroundDrawable(new ColorDrawable(0x7DC0C0C0));
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
        /**
         * 修改头像操作
         */
        mTxtAlterImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        /**
         * 修改设备名称操作
         */
        mTxtAlterName.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                View v = LayoutInflater.from(ActivityEquipmentDetails.this).inflate(R.layout.dialog_equipment_details_altername, null);
                final EditText mEdtAlterName = v.findViewById(R.id.mEdtAlterName);
                final Button mBtnAbrogate = v.findViewById(R.id.mBtnAbrogate);//"取消"按钮
                final Button mBtnAffirm = v.findViewById(R.id.mBtnAffirm);//"确认"按钮
                mEdtAlterName.setText(mTxtEquipmentname.getText().toString());
                final AlertDialog.Builder builder = new AlertDialog.Builder(ActivityEquipmentDetails.this).setView(view);
                builder.setView(v);
                final AlertDialog alertDialog = builder.create();
                v.setBackgroundResource(R.drawable.button_shape_white);
                mBtnAffirm.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        mTxtEquipmentname.setText(mEdtAlterName.getText().toString());
                         alertDialog.dismiss();
                        Toast.makeText(ActivityEquipmentDetails.this,"修改成功",Toast.LENGTH_LONG).show();
                    }
                });
                mBtnAbrogate.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        alertDialog.dismiss();
                    }
                });
                alertDialog.show();
            }
        });
        /**
         * 删除操作
         */
        mTxtDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                View v = LayoutInflater.from(ActivityEquipmentDetails.this).inflate(R.layout.dialog_equipment_details_delete, null);
                final Button mBtnAbrogate = v.findViewById(R.id.mBtnAbrogate);//"取消"按钮
                final Button mBtnDelete = v.findViewById(R.id.mBtnDelete);//"删除"按钮
                final AlertDialog.Builder builder = new AlertDialog.Builder(ActivityEquipmentDetails.this).setView(view);
                builder.setView(v);
                final AlertDialog alertDialog = builder.create();
                v.setBackgroundResource(R.drawable.button_shape_white);
                mBtnDelete.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                       //删除操作

                        alertDialog.dismiss();
                        Toast.makeText(ActivityEquipmentDetails.this,"删除成功",Toast.LENGTH_LONG).show();
                    }
                });
                mBtnAbrogate.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        alertDialog.dismiss();
                    }
                });
                alertDialog.show();
            }
        });
        /**
         * 取消操作
         */
        mTxtCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
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
        WindowManager.LayoutParams lp = ((ActivityEquipmentDetails) mContext).getWindow()
                .getAttributes();
        lp.alpha = bgAlpha;
        ((ActivityEquipmentDetails) mContext).getWindow().setAttributes(lp);
    }
}

