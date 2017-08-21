package com.example.make1.find.activity;

import android.content.Context;
import android.content.Intent;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.os.StrictMode;
import android.provider.MediaStore;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;

import android.support.v7.app.AlertDialog;
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
import android.widget.PopupWindow;

import android.widget.TextView;
import android.widget.Toast;

import com.example.make1.find.R;
import com.example.make1.find.fragment.FragmentEquipmentDetails;
import com.example.make1.find.utils.PickerView;
import com.example.make1.find.utils.ShearRoundness;
import com.example.make1.find.utils.WindowUtils;


import java.io.ByteArrayOutputStream;
import java.io.File;
import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.example.make1.find.R.id.mBtnAffirm;
import static com.example.make1.find.R.id.mBtnAffirmName;
import static com.example.make1.find.R.string.warntime;
import static com.example.make1.find.constant.PhotographParameter.IMAGE_UNSPECIFIED;
import static com.example.make1.find.constant.PhotographParameter.NONE;
import static com.example.make1.find.constant.PhotographParameter.PHOTO_GRAPH;
import static com.example.make1.find.constant.PhotographParameter.PHOTO_RESOULT;
import static com.example.make1.find.constant.PhotographParameter.PHOTO_ZOOM;
import static com.example.make1.find.constant.PhotographParameter.change_path;


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
    @BindView(R.id.mImgUser)
    ImageView mImgUser;
    private Context mContext;
    Intent intent;
    boolean isfre = true;
    private Handler mHandler = new Handler();
    private Timer mMyTimer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.equipment_details);
        ButterKnife.bind(this);
        mContext = ActivityEquipmentDetails.this;
        //新页面接收数据
        Bundle bundle = this.getIntent().getExtras();
        //接收name值
        String equipmentName = bundle.getString("equipmentName");
        Log.i("获取到的name值为", equipmentName);
        mTxtEquipmentname.setText(equipmentName);
        //解决7.0版本的拍照问题
        StrictMode.VmPolicy.Builder Vbuilder = new StrictMode.VmPolicy.Builder();
        StrictMode.setVmPolicy(Vbuilder.build());
        Vbuilder.detectFileUriExposure();
        initView();
        initListener();
        initData();
        fre();
        mMyTimer = new Timer();

    }

    private void fre() {
        if (isfre) {
            View view = LayoutInflater.from(ActivityEquipmentDetails.this).inflate(R.layout.dialog_bound_equipment, null);
            final Button mBtnAbrogate = view.findViewById(R.id.mBtnAbrogate);
            final Button mBtnAffirm = view.findViewById(R.id.mBtnAffirmOpen);
            final AlertDialog.Builder builder = new AlertDialog.Builder(ActivityEquipmentDetails.this).setView(view);
            builder.setView(view);
            final AlertDialog alertDialog = builder.create();
            view.setBackgroundResource(R.drawable.button_shape_white);
            mBtnAffirm.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    intent = new Intent(ActivityEquipmentDetails.this, ActivityMoreQuestion.class);
                    startActivity(intent);
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
                intent = new Intent(ActivityEquipmentDetails.this, ActivityDisturbStatment.class);
                startActivity(intent);
                break;
            case R.id.ImgDistance:
                intent = new Intent(ActivityEquipmentDetails.this, ActivityEquipmentLocation.class);
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
                popWindow.dismiss();
                initPopWindowPicture(v);
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
                final Button mBtnAffirm = v.findViewById(mBtnAffirmName);//"确认"按钮
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
                        Toast.makeText(ActivityEquipmentDetails.this, "修改成功", Toast.LENGTH_LONG).show();
                    }
                });
                mBtnAbrogate.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        alertDialog.dismiss();
                    }
                });
                alertDialog.show();
                popWindow.dismiss();
            }
        });
        /**
         * 删除操作
         */
//        mTxtDelete.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                View v = LayoutInflater.from(ActivityEquipmentDetails.this).inflate(R.layout.dialog_equipment_details_delete, null);
//                final Button mBtnAbrogate = v.findViewById(R.id.mBtnAbrogate);//"取消"按钮
//                final Button mBtnDelete = v.findViewById(R.id.mBtnDelete);//"删除"按钮
//                final AlertDialog.Builder builder = new AlertDialog.Builder(ActivityEquipmentDetails.this).setView(view);
//                builder.setView(v);
//                final AlertDialog alertDialog = builder.create();
//                v.setBackgroundResource(R.drawable.button_shape_white);
//                mBtnDelete.setOnClickListener(new View.OnClickListener() {
//                    @Override
//                    public void onClick(View view) {
//                        //删除操作
//
//                        alertDialog.dismiss();
//                        Toast.makeText(ActivityEquipmentDetails.this, "删除成功", Toast.LENGTH_LONG).show();
//                    }
//                });
//                mBtnAbrogate.setOnClickListener(new View.OnClickListener() {
//                    @Override
//                    public void onClick(View view) {
//                        alertDialog.dismiss();
//                    }
//                });
//                alertDialog.show();
//                popWindow.dismiss();
//            }
//        });
        mTxtDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mHandler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        WindowUtils.showPopupWindow(ActivityEquipmentDetails.this);

                        popWindow.dismiss();
                    }
                },100);
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

    /**
     * 使用PopWindow实现菜单从屏幕底部弹出
     *
     * @param v
     */
    private void initPopWindowPicture(View v) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.user_change_picture_item, null, false);//菜单具体内容布局文件
        //内容布局控件
        TextView mTxtPhotograph = view.findViewById(R.id.mTxtPhotograph);//拍照
        TextView mTxtAlbum = view.findViewById(R.id.mTxtAlbum);//从相册中选取
        TextView mTxtCancel = view.findViewById(R.id.mTxtCancel);
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
        /**
         * 从相册中选取
         */
        mTxtAlbum.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                intent = new Intent(Intent.ACTION_PICK, null);
                intent.setDataAndType(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, IMAGE_UNSPECIFIED);
                startActivityForResult(intent, PHOTO_ZOOM);
                popWindow.dismiss();
            }
        });
        /**
         * 拍照
         */
        mTxtPhotograph.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String filePath = Environment.getExternalStorageDirectory() + change_path;
                File localFile = new File(filePath);
                if (!localFile.exists()) {
                    localFile.mkdir();
                }
                intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                intent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(new File(Environment.getExternalStorageDirectory() + change_path, "temp.jpg")));
                startActivityForResult(intent, PHOTO_GRAPH);
                popWindow.dismiss();
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

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == NONE)
            return;
        //拍照
        if (requestCode == PHOTO_GRAPH) {
            //设置文件保存路径
            String filePath = Environment.getExternalStorageDirectory() + change_path;
            File locaFile = new File(filePath);
            if (!locaFile.exists()) {
                locaFile.mkdir();
            }
            File picture = new File(Environment.getExternalStorageDirectory() + change_path + "/temp.jpg");
            startPhotoZoom(Uri.fromFile(picture));
        }
        if (data == null)
            return;
        //读取相册缩放图片
        if (requestCode == PHOTO_ZOOM) {
            startPhotoZoom(data.getData());
        }
        //处理结果
        if (requestCode == PHOTO_RESOULT) {
            Bundle extras = data.getExtras();
            if (extras != null) {
                Bitmap photo = extras.getParcelable("data");
                //调用ShearRoundness工具类中的toRoundBitmap方法，将图片剪切为圆形
                Bitmap bmBitmap = new ShearRoundness().toRoundBitmap(photo);
                ByteArrayOutputStream stream = new ByteArrayOutputStream();
                bmBitmap.compress(Bitmap.CompressFormat.JPEG, 80, stream);
                //此处可以把Bitmap保存到sd卡中
                mImgUser.setImageBitmap(bmBitmap);//把图片显示在控件上
            }
        }
        super.onActivityResult(requestCode, resultCode, data);
    }

    /**
     * @param uri
     */
    public void startPhotoZoom(Uri uri) {
        intent = new Intent("com.android.camera.action.CROP");
        intent.setDataAndType(uri, IMAGE_UNSPECIFIED);
        intent.putExtra("crop", "true");
        //宽高比例
        intent.putExtra("aspectX", 1);
        intent.putExtra("aspectY", 1);
        //剪裁图片宽高
        intent.putExtra("outputX", 80);
        intent.putExtra("outputY", 80);
        intent.putExtra("return-data", true);
        startActivityForResult(intent, PHOTO_RESOULT);
    }
}


