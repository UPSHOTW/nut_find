package com.example.make1.find.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.make1.find.R;
import com.example.make1.find.activity.ActivityFriendsAdd;

import java.util.ArrayList;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * Created by wjx on 2017/8/2.
 */

public class FragmentDisturb extends Fragment  implements View.OnClickListener{
    private TextView mTxtAdd;
    private int imageIds[];
    private String[] titles;
    private ArrayList<ImageView> images;
    private ArrayList<View> dots;
    private TextView title;
    private ViewPager mViewPager;
    private ImageView imageView;
    private ViewPagerAdapter adapter;
    private int oldPosition = 0;//记录上一次点的位置
    private int currentItem; //当前页面
    Intent intent;
    private ScheduledExecutorService scheduledExecutorService;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_disturb, null);
        mTxtAdd = view.findViewById(R.id.mTxtAdd);
        //图片ID
        imageIds = new int[]{
                R.mipmap.a,
                R.mipmap.b
        };
        //图片标题
        titles = new String[]{
                getString(R.string.bound_guide),
                getString(R.string.bound_guide)
        };
        //显示的图片
        images = new ArrayList<ImageView>();
        for (int i = 0; i < imageIds.length; i++) {
            imageView = new ImageView(getActivity());
            imageView.setBackgroundResource(imageIds[i]);
            images.add(imageView);
        }
        //显示的点
        dots = new ArrayList<View>();
        dots.add(view.findViewById(R.id.dot_first));
        dots.add(view.findViewById(R.id.dot_second));
        title = view.findViewById(R.id.mTxtImgTitle);
        title.setText(titles[0]);
        mViewPager = view.findViewById(R.id.vp);
        adapter = new ViewPagerAdapter();
        mViewPager.setAdapter(adapter);
        mViewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageSelected(int position) {
                // TODO Auto-generated method stub
                title.setText(titles[position]);

                dots.get(oldPosition).setBackgroundResource(R.drawable.dot_normal);
                dots.get(position).setBackgroundResource(R.drawable.dot_focused);

                oldPosition = position;
                currentItem = position;
            }

                   @Override
        public void onPageScrolled(int arg0, float arg1, int arg2) {
            // TODO Auto-generated method stub

        }

        @Override
        public void onPageScrollStateChanged(int arg0) {
            // TODO Auto-generated method stub

        }
    });

        return view;


    }

    private class ViewPagerAdapter extends PagerAdapter {

        @Override
        public int getCount() {
            // TODO Auto-generated method stub
            return images.size();
        }

        //是否是同一张图片
        @Override
        public boolean isViewFromObject(View arg0, Object arg1) {
            // TODO Auto-generated method stub
            return arg0 == arg1;
        }

        @Override
        public void destroyItem(ViewGroup view, int position, Object object) {
            // TODO Auto-generated method stub
//            super.destroyItem(container, position, object);
//            view.removeViewAt(position);
            view.removeView(images.get(position));

        }

        @Override
        public Object instantiateItem(ViewGroup view, int position) {
            // TODO Auto-generated method stub
            view.addView(images.get(position));

            return images.get(position);
        }
    }

    @Override
    public void onStart() {
        // TODO Auto-generated method stub
        super.onStart();

        scheduledExecutorService = Executors.newSingleThreadScheduledExecutor();

        //每隔2秒钟切换一张图片
        scheduledExecutorService.scheduleWithFixedDelay(new ViewPagerTask(), 5, 5, TimeUnit.SECONDS);
    }

    //切换图片
    private class ViewPagerTask implements Runnable {

        @Override
        public void run() {
            // TODO Auto-generated method stub
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
        mTxtAdd.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.mTxtAdd:
                intent = new Intent(getActivity(), ActivityFriendsAdd.class);
                startActivity(intent);
                break;

            default:
        }
    }


}

