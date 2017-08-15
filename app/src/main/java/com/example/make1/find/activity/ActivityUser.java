package com.example.make1.find.activity;


import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.os.StrictMode;
import android.provider.MediaStore;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.example.make1.find.R;
import com.example.make1.find.utils.ShearRoundness;
import java.io.ByteArrayOutputStream;
import java.io.File;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.example.make1.find.constant.PhotographParameter.IMAGE_UNSPECIFIED;
import static com.example.make1.find.constant.PhotographParameter.NONE;
import static com.example.make1.find.constant.PhotographParameter.PHOTO_GRAPH;
import static com.example.make1.find.constant.PhotographParameter.PHOTO_RESOULT;
import static com.example.make1.find.constant.PhotographParameter.PHOTO_ZOOM;
import static com.example.make1.find.constant.PhotographParameter.change_path;

/**
 * Created by make1 on 2017/8/2.
 * 个人资料页面
 */

public class ActivityUser extends AppCompatActivity implements View.OnClickListener {
    @BindView(R.id.mImgBack)
    ImageView mImgBack;
    @BindView(R.id.Sex)
    TextView Sex;
    @BindView(R.id.mTxtUsername)
    TextView mTxtUsername;
    @BindView(R.id.mRltPhoneNum)
    RelativeLayout mRltPhoneNum;
    @BindView(R.id.mRltEmail)
    RelativeLayout mRltEmail;
    @BindView(R.id.mBtnEsc)
    Button mBtnEsc;
    @BindView(R.id.mImgUser)
    ImageView mImgUser;
    Intent intent;
    private Context mContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.more_user);
        ButterKnife.bind(this);
        mContext = ActivityUser.this;
        StrictMode.VmPolicy.Builder Vbuilder = new StrictMode.VmPolicy.Builder();
        StrictMode.setVmPolicy(Vbuilder.build());
        Vbuilder.detectFileUriExposure();

    }

    @Override
    public void onResume() {
        super.onResume();
        mImgBack.setOnClickListener(this);
        mRltPhoneNum.setOnClickListener(this);
        mRltEmail.setOnClickListener(this);
        mImgUser.setOnClickListener(this);
    }


    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.mImgBack:
                setResult(1);
                finish();
                break;
            case R.id.mRltPhoneNum:
                intent = new Intent(ActivityUser.this, ActivityMoreUserPhone.class);
                startActivity(intent);
                break;
            case R.id.mRltEmail:
                intent = new Intent(ActivityUser.this, ActivityMoreUserEmail.class);
                startActivity(intent);
                break;
            case R.id.mImgUser:
                initPopWindow(view);
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
                bmBitmap.compress(Bitmap.CompressFormat.JPEG, 100, stream);
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
        intent.putExtra("outputX", 100);
        intent.putExtra("outputY", 100);
        intent.putExtra("return-data", true);
        startActivityForResult(intent, PHOTO_RESOULT);
    }

    /**
     * 设置添加屏幕的背景透明度
     *
     * @param bgAlpha 屏幕透明度0.0-1.0 1表示完全不透明
     */

    private void setBackgroundAlpha(float bgAlpha) {
        WindowManager.LayoutParams lp = ((ActivityUser) mContext).getWindow()
                .getAttributes();
        lp.alpha = bgAlpha;
        ((ActivityUser) mContext).getWindow().setAttributes(lp);
    }


    /**
     * 性别选择对话框
     *
     * @param v
     */

    public void ChooseSex(View v) {
        final AlertDialog.Builder builder = new AlertDialog.Builder(this);
        final String[] items = new String[]{"男", "女"};
        builder.setSingleChoiceItems(items, 2, new DialogInterface.OnClickListener() {/*设置单选条件的点击事件*/
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Sex.setText(items[which]);
                //退出对话框
                dialog.dismiss();
            }
        });
        builder.setCancelable(false);
        builder.show();
    }

    /**
     * 修改用户名弹出框
     *
     * @param v
     */
    public void ChangeUserName(View v) {
        View view = LayoutInflater.from(ActivityUser.this).inflate(R.layout.dialog_changusername, null);
        final EditText mEdtName = view.findViewById(R.id.mEdtName);
        final Button mBtnAbrogate = view.findViewById(R.id.mBtnAbrogate);
        final Button mBtnAffirm = view.findViewById(R.id.mBtnAffirm);
        mEdtName.setText(mTxtUsername.getText().toString());
        final AlertDialog.Builder builder = new AlertDialog.Builder(ActivityUser.this).setView(view);
        builder.setView(view);
        final AlertDialog alertDialog = builder.create();
        view.setBackgroundResource(R.drawable.button_shape_white);
        mTxtUsername.setText(mEdtName.getText().toString());
        mBtnAffirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mTxtUsername.setText(mEdtName.getText().toString());
                alertDialog.dismiss();
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

    /**
     * 退出登录确认弹出框
     */
    public void Esc(View v) {
        View view = LayoutInflater.from(ActivityUser.this).inflate(R.layout.dialog_esc, null);
        final Button mBtnAbrogate = view.findViewById(R.id.mBtnAbrogate);//"取消"按钮
        final Button mBtnAffirm = view.findViewById(R.id.mBtnAffirm);//"确认"按钮
        final AlertDialog.Builder builder = new AlertDialog.Builder(ActivityUser.this).setView(view);
        builder.setView(view);
        final AlertDialog alertDialog = builder.create();
        view.setBackgroundResource(R.drawable.button_shape_white);
        mBtnAffirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                intent = new Intent(ActivityUser.this, ActivityLogin.class);
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
