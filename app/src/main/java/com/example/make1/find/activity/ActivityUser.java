package com.example.make1.find.activity;


import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.make1.find.R;


import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by make1 on 2017/8/2.
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
    Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.more_user);
        ButterKnife.bind(this);

    }

    @Override
    public void onResume() {
        super.onResume();
        mImgBack.setOnClickListener(this);
        mRltPhoneNum.setOnClickListener(this);
        mRltEmail.setOnClickListener(this);
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
            default:
        }
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
              intent = new Intent(ActivityUser.this,ActivityLogin.class);
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
