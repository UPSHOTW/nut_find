package com.example.make1.find.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.make1.find.R;
import com.example.make1.find.fragment.FragmentDisturb;

import java.util.ArrayList;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 连接中
 */

public class ActivityBoundConnecting extends AppCompatActivity {
    @BindView(R.id.mImgBack)
    ImageView mImgBack;
    Intent intent;
    private int imageIds[];
    private ArrayList<ImageView> images;
    private ViewPager mViewPager;
    private ImageView imageView;
    private ViewPagerAdapter adapter;
    private int oldPosition = 0;//记录上一次点的位置
    private int currentItem; //当前页面
    private ScheduledExecutorService scheduledExecutorService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.bound_connecting);
        ButterKnife.bind(this);
        imageIds = new int[]{
                R.mipmap.binding_progress_01,
                R.mipmap.binding_progress_11,
                R.mipmap.binding_progress_21,
                R.mipmap.binding_progress_31,
                R.mipmap.binding_progress_41
        };
        //显示的图片
        images = new ArrayList<ImageView>();
        for (int i = 0; i < imageIds.length; i++) {
            imageView = new ImageView(this);
            imageView.setBackgroundResource(imageIds[i]);
            images.add(imageView);
        }
        mViewPager = (ViewPager) findViewById(R.id.vpconnecting);
        adapter = new ViewPagerAdapter();
        mViewPager.setAdapter(adapter);
        mViewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageSelected(int position) {
                oldPosition = position;
                currentItem = position;
            }

            @Override
            public void onPageScrolled(int arg0, float arg1, int arg2) {
            }

            @Override
            public void onPageScrollStateChanged(int arg0) {

            }
        });
    }

    private class ViewPagerAdapter extends PagerAdapter {

        @Override
        public int getCount() {
            return images.size();
        }

        //是否是同一张图片
        @Override
        public boolean isViewFromObject(View arg0, Object arg1) {
            return arg0 == arg1;
        }

        @Override
        public void destroyItem(ViewGroup view, int position, Object object) {
            view.removeView(images.get(position));
        }

        @Override
        public Object instantiateItem(ViewGroup view, int position) {
            view.addView(images.get(position));
            return images.get(position);
        }
    }

    @Override
    public void onStart() {
        super.onStart();
        scheduledExecutorService = Executors.newSingleThreadScheduledExecutor();
        //每隔1秒钟切换一张图片
        scheduledExecutorService.scheduleWithFixedDelay(new ViewPagerTask(), 1, 1, TimeUnit.SECONDS);
    }

    //切换图片
    private class ViewPagerTask implements Runnable {
        @Override
        public void run() {
            currentItem = (currentItem + 1) % imageIds.length;
            //更新界面
//            handler.sendEmptyMessage(0);
            handler.obtainMessage().sendToTarget();
        }
    }

    private Handler handler = new Handler() {

        @Override
        public void handleMessage(Message msg) {
            // TODO Auto-generated method stub
            //设置当前页面
            mViewPager.setCurrentItem(currentItem);
        }

    };

    @Override
    public void onStop() {
        // TODO Auto-generated method stub
        super.onStop();
    }
    @Override
    public void onResume() {
        super.onResume();
    }

}