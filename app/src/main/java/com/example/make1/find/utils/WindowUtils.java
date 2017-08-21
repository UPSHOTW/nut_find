package com.example.make1.find.utils;

import android.app.Activity;
import android.content.Context;
import android.os.CountDownTimer;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.graphics.PixelFormat;
import android.graphics.Rect;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View.OnKeyListener;
import android.view.View.OnTouchListener;
import android.view.View.OnClickListener;
import android.view.WindowManager.LayoutParams;
import android.widget.Button;

import com.example.make1.find.R;

import java.util.Timer;
import java.util.TimerTask;
/**
 * Created by make1 on 2017/8/21.
 */

/**
 * 弹窗辅助类
 */
public class WindowUtils {
    private static final String TAG = "q";
    static View mView = null;
    private static WindowManager mWindowManager = null;
    static Context mContext = null;
    public static Boolean isShown = false;
    static Button mBtnDelete;
    private int T = 10;
    private int COUNT_TIME = 0;
    private CountDownTimer timer;




    public void clickButton(View view){
        mBtnDelete.setClickable(false);
        timer = new CountDownTimer(10000,10) {
            @Override
            public void onTick(long l) {
                mBtnDelete.setText("删除("+l/1000+")");

            }
            @Override
            public void onFinish() {
                mBtnDelete.setText("删除");
                mBtnDelete.setBackgroundResource(R.drawable.button_shape_red_small);
                mBtnDelete.setClickable(true);
            }
        };
        timer.start();
    }
//    private Handler handler = new Handler(){
//        @Override
//        public void handleMessage(Message msg){
//            mBtnDelete.setText("删除("+T+")");
//            if (T<=0){
//                T = 10;
//                mBtnDelete.setText("删除");
//                mBtnDelete.setBackgroundResource(R.drawable.button_shape_red_small);
//                mBtnDelete.setClickable(true);
//
//            }
//            T--;
//            mBtnDelete.setText("删除("+T+")");
//            sendEmptyMessageDelayed(COUNT_TIME,1000);
//        }
//    };
//    public void clickButton(View view){
//        handler.sendEmptyMessage(COUNT_TIME);
//        mBtnDelete.setClickable(false);
//    }

    private static Timer mTimer;
    private  int countDownTime = 10;


    private class MyTimerTask extends TimerTask{
        @Override
        public void run() {
            countDownTime --;
            if(countDownTime ==0){
//                mBtnDelete.setText("删除");
            }else if(countDownTime>0){
//                Message msg = new Message();
//                msg.what = 1;
//                msg.obj = countDownTime;
//                myHandler.handleMessage(msg);
                Log.i("q","countDownTime:"+countDownTime);
            }
        }
    }


    public static void showPopupWindow(final Context context) {
        if (isShown) {
            Log.i(TAG, "return cause already shown");
            return;
        }

        isShown = true;
        Log.i(TAG, "showPopupWindow");
        //获取应用的Context
        mContext = context.getApplicationContext();
        // 获取WindowManager
        mWindowManager = (WindowManager) mContext.getSystemService(Context.WINDOW_SERVICE);
        mView = setUpView(context);
        final WindowManager.LayoutParams params =  new WindowManager.LayoutParams(WindowManager.LayoutParams.MATCH_PARENT,
                WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.TYPE_PHONE,
                WindowManager.LayoutParams.FLAG_LAYOUT_IN_SCREEN | WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS
                        | WindowManager.LayoutParams.FLAG_NOT_TOUCH_MODAL
                        | WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE
                        | WindowManager.LayoutParams.FLAG_ALT_FOCUSABLE_IM | WindowManager.LayoutParams.FLAG_SPLIT_TOUCH
                        | WindowManager.LayoutParams.FLAG_DISMISS_KEYGUARD,
                PixelFormat.RGBA_8888);
        params.gravity = Gravity.CENTER;
        mWindowManager.addView(mView, params);
        Log.i(TAG, "add view");
    }

    /**
     * 隐藏弹出框
     */
    public static void hidePopupWindow() {
        Log.i(TAG, "hide " + isShown + ", " + mView);
        if (isShown && null != mView) {
            Log.i(TAG, "hidePopupWindow");
            mWindowManager.removeView(mView);
            isShown = false;
        }
    }

    private  View setUpView(final Context context) {
        Log.i(TAG, "setUp view");
        View view = LayoutInflater.from(context).inflate(R.layout.dialog_bound_delete,
                null);
        Button mBtnAbrogate = view.findViewById(R.id.mBtnAbrogate);
        mBtnDelete = view.findViewById(R.id.mBtnDelete);

        mBtnDelete.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.i(TAG, "ok on click");
                // 打开安装包
                // 隐藏弹窗
                WindowUtils.hidePopupWindow();
            }
        });
        mBtnAbrogate.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.i(TAG, "cancel on click");
                WindowUtils.hidePopupWindow();
            }
        });
        // 点击窗口外部区域可消除
        // 这点的实现主要将悬浮窗设置为全屏大小，外层有个透明背景，中间一部分视为内容区域
        // 所以点击内容区域外部视为点击悬浮窗外部
        final View popupWindowView = view.findViewById(R.id.deleteDialog);// 非透明的内容区域
        view.setOnTouchListener(new OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                Log.i(TAG, "onTouch");
                int x = (int) event.getX();
                int y = (int) event.getY();
                Rect rect = new Rect();
                popupWindowView.getGlobalVisibleRect(rect);
                if (!rect.contains(x, y)) {
                    WindowUtils.hidePopupWindow();
                }
                Log.i(TAG, "onTouch : " + x + ", " + y + ", rect: "
                        + rect);
                return false;
            }
        });
        // 点击back键可消除
        view.setOnKeyListener(new OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                switch (keyCode) {
                    case KeyEvent.KEYCODE_BACK:
                        WindowUtils.hidePopupWindow();
                        return true;
                    default:
                        return false;
                }
            }
        });
        return view;
    }

}
