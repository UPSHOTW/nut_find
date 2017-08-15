package com.example.make1.find.fragment;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.example.make1.find.R;
import com.example.make1.find.activity.ActivityDisturbExist;
import com.example.make1.find.activity.ActivityEquipmentDetails;
import com.example.make1.find.activity.ActivityFriendsAdd;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import butterknife.BindView;

/**
 * Created by wjx on 2017/8/2.
 */

public class FragmentDisturb extends Fragment implements View.OnClickListener {
    private ImageView mImgAdd;
    private ImageView mImgAdds;
    private int imageIds[];
    private String[] titles;
    private ArrayList<ImageView> images;
    private ArrayList<View> dots;
    private TextView title;
    private ViewPager mViewPager;
    private ImageView imageView;
    private ViewPagerAdapter adapter;
    private ListView list_equipment;
    private ImageButton mImgBotherTime;
    private int oldPosition = 0;//记录上一次点的位置
    private int currentItem; //当前页面
    Intent intent;
    private Context mContext;

    private ScheduledExecutorService scheduledExecutorService;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_disturb, null);
        mContext = getActivity();
        mImgAdd = view.findViewById(R.id.mImgAdd);
        mImgAdds = view.findViewById(R.id.mImgAdds);
        list_equipment = view.findViewById(R.id.list_equipment);
        mImgBotherTime = view.findViewById(R.id.mImgBotherTime);
        EquipmentList();
        onGain();
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
        mImgAdd.setOnClickListener(this);
        mImgAdds.setOnClickListener(this);
        mImgBotherTime.setOnClickListener(this);
        list_equipment.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                intent = new Intent(getActivity(),ActivityEquipmentDetails.class);
                startActivity(intent);
            }
        });
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.mImgAdd:
                intent = new Intent(getActivity(), ActivityFriendsAdd.class);
                startActivity(intent);
                break;
            case R.id.mImgAdds:
                intent = new Intent(getActivity(), ActivityFriendsAdd.class);
                startActivity(intent);
                break;
            case R.id.mImgBotherTime:
                initPopWindow(view);
                break;
            default:
        }
    }

    private void EquipmentList() {
        ArrayList<HashMap<String, Object>> founditem = new ArrayList<HashMap<String, Object>>();
        HashMap<String, Object> map = new HashMap<String, Object>();
        map.put("imgEquipment", R.mipmap.ic_add_nut_logo);
        map.put("txtEquipmentName", "Nut3");
        map.put("mTxtDistance", "附近");
        map.put("mTxtLinkTime", "20分钟前");
        founditem.add(map);
        SimpleAdapter equipmentitemAdapter = new SimpleAdapter(getActivity(), founditem, R.layout.list_equipment, new String[]{
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
                intent = new Intent(getActivity(), ActivityEquipmentDetails.class);
                startActivity(intent);
            }
        });
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
                Toast.makeText(getActivity(), "30分钟", Toast.LENGTH_LONG).show();
                popWindow.dismiss();
            }
        });
        mTxtTimeOneHour.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getActivity(), "1小时", Toast.LENGTH_LONG).show();
                popWindow.dismiss();
            }
        });
        mTxtTimeTwoHour.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Toast.makeText(getActivity(), "2小时", Toast.LENGTH_LONG).show();
                popWindow.dismiss();
            }
        });
        mTxtTimeFourHours.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getActivity(), "4小时", Toast.LENGTH_LONG).show();
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
        WindowManager.LayoutParams lp = getActivity().getWindow()
                .getAttributes();
        lp.alpha = bgAlpha;
        getActivity().getWindow().setAttributes(lp);
    }

}

