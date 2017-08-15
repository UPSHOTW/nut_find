package com.example.make1.find.fragment;


import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTabHost;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;


import com.baidu.location.BDLocation;
import com.baidu.location.BDLocationListener;
import com.baidu.location.LocationClient;
import com.baidu.location.LocationClientOption;
import com.baidu.mapapi.map.BaiduMap;
import com.baidu.mapapi.map.BitmapDescriptor;
import com.baidu.mapapi.map.BitmapDescriptorFactory;
import com.baidu.mapapi.map.MapStatusUpdate;
import com.baidu.mapapi.map.MapStatusUpdateFactory;
import com.baidu.mapapi.map.MapView;
import com.baidu.mapapi.map.MarkerOptions;
import com.baidu.mapapi.map.MyLocationData;
import com.baidu.mapapi.map.OverlayOptions;
import com.baidu.mapapi.model.LatLng;
import com.example.make1.find.R;

import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.make1.find.activity.ActivityFriends;
import com.example.make1.find.activity.ActivityFriendsAdd;
import com.example.make1.find.activity.ActivityUser;
import com.example.make1.find.activity.MainTab;


/**
 * Created by wjx on 2017/8/2.
 * 定位
 */

public class FragmentMap extends Fragment implements View.OnClickListener {
    public LocationClient mLocationClient;
    BDLocation location;
    private MapView mMapView;
    private ImageView mBtnMapLocation;
    private Button mBtnAddFriends;
    private BaiduMap mBaiduMap;
    private ImageView mImgToBound;
    private RelativeLayout rltRecord;
    private RelativeLayout rltBut;
    boolean isVisible = true;

    boolean isFirstLoc = true;// 是否首次定位
    BitmapDescriptor mCurMarker;
    LatLng ll;
    Intent intent;
    public MyLocationListener myListener = new MyLocationListener();
    LocationClientOption option = new LocationClientOption();
    FragmentTabHost mTabHost = null;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle bundle = getArguments();
        if (null != bundle) {
        }
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_map, null);
        mMapView = view.findViewById(R.id.bmapView);
        mBtnAddFriends = view.findViewById(R.id.mBtnAddFriends);
        mBtnMapLocation = view.findViewById(R.id.mBtnMapLocation);
        rltBut = view.findViewById(R.id.rltBut);
        rltRecord = view.findViewById(R.id.rltRecord);
        mImgToBound = view.findViewById(R.id.mImgToBound);
        mBaiduMap = mMapView.getMap();
        mBaiduMap.setMyLocationEnabled(true);
        mLocationClient = new LocationClient(getActivity());//发起定位，添加监听
        initLocation();//初始化LocationClient选项
        mLocationClient.registerLocationListener(myListener);
        option.setOpenGps(true);
        mLocationClient.setLocOption(option);
        myListener.onReceiveLocation(location);
        Log.i("nut", "option" + option);
        mLocationClient.start();
        return view;

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mMapView.onDestroy();
    }

    @Override
    public void onResume() {
        super.onResume();
        mBtnAddFriends.setOnClickListener(this);
        mBtnMapLocation.setOnClickListener(this);
        mImgToBound.setOnClickListener(this);
        mMapView.onResume();
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.mBtnAddFriends:
                intent = new Intent(getActivity(), ActivityFriendsAdd.class);
                startActivity(intent);
                break;
            case R.id.mImgToBound:
                intent = new Intent(getActivity(), ActivityFriendsAdd.class);
                startActivity(intent);
                break;
            case R.id.mBtnMapLocation:
                if (isVisible){
                    isVisible = false;
                    //动画1
                    ObjectAnimator oa1 = ObjectAnimator.ofFloat(rltRecord, "translationY", 0F, -180F);
                    //动画2
                    ObjectAnimator oa2 = ObjectAnimator.ofFloat(rltBut, "translationY", 0F, -200F);
                    //创建动画集
                    AnimatorSet animatorSet = new AnimatorSet();
                    //设置同时播放
                    animatorSet.playTogether(oa1, oa2);
                    // animatorSet.playSequentially(oa1, oa2);//按顺序播放动画
                    animatorSet.setDuration(1000);
                    //开始动画
                    animatorSet.start();
                    //更换按钮背景
                    mBtnMapLocation.setBackgroundResource(R.drawable.attrs_reseda);
                }else {
                    isVisible = true;
                    //动画1
                    ObjectAnimator oa1 = ObjectAnimator.ofFloat(rltRecord, "translationY", -180F, 0F);
                    //动画2
                    ObjectAnimator oa2 = ObjectAnimator.ofFloat(rltBut, "translationY", -200F, 0F);
                    //创建动画集
                    AnimatorSet animatorSet = new AnimatorSet();
                    //设置同时播放
                    animatorSet.playTogether(oa1, oa2);
                    // animatorSet.playSequentially(oa1, oa2);//按顺序播放动画
                    animatorSet.setDuration(1000);
                    //开始动画
                    animatorSet.start();
                    //更换按钮背景
                    mBtnMapLocation.setBackgroundResource(R.drawable.attrs_white);
                }

            default:
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == 0) { //判断请求码
            ActivityFriendsAdd activityFriendsAdd = (ActivityFriendsAdd) getActivity();
            android.app.FragmentManager fragmentManager = activityFriendsAdd.getFragmentManager();
            android.app.FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            fragmentTransaction.commit();
        }
    }

    @Override
    public void onPause() {
        super.onPause();
        mMapView.onPause();
    }

    private void initLocation() {
        option.setLocationMode(LocationClientOption.LocationMode.Hight_Accuracy);
        //可选，默认高精度，设置定位模式，高精度，低功耗，仅设备
        option.setCoorType("bd09ll");
        //可选，默认gcj02，设置返回的定位结果坐标系
        int span = 5000 * 60;
        option.setScanSpan(span);
        //可选，默认0，即仅定位一次，设置发起定位请求的间隔需要大于等于1000ms才是有效的
        option.setIsNeedAddress(true);
        //可选，设置是否需要地址信息，默认不需要
        option.setOpenGps(true);
        //可选，默认false,设置是否使用gps
        option.setLocationNotify(true);
        //可选，默认false，设置是否当GPS有效时按照1S/1次频率输出GPS结果
        option.setIsNeedLocationDescribe(true);
        //可选，默认false，设置是否需要位置语义化结果，可以在BDLocation.getLocationDescribe里得到，结果类似于“在北京天安门附近”
        option.setIsNeedLocationPoiList(true);
        //可选，默认false，设置是否需要POI结果，可以在BDLocation.getPoiList里得到
        option.setIgnoreKillProcess(false);
        //可选，默认true，定位SDK内部是一个SERVICE，并放到了独立进程，设置是否在stop的时候杀死这个进程，默认不杀死
        option.SetIgnoreCacheException(false);
        //可选，默认false，设置是否收集CRASH信息，默认收集
        option.setEnableSimulateGps(false);
        //可选，默认false，设置是否需要过滤GPS仿真结果，默认需要
        mCurMarker = BitmapDescriptorFactory.fromResource(R.mipmap.ic_tab_location_selected);
        mLocationClient.setLocOption(option);

    }

    private class MyLocationListener implements BDLocationListener {
        /**
         * 获取定位
         *
         * @param location
         */
        @Override
        public void onReceiveLocation(BDLocation location) {
            // map view 销毁后不在处理新接收的位置
            if (location == null)
                return;
            MyLocationData locData = new MyLocationData.Builder()
                    .accuracy(location.getRadius())
                    // 此处设置开发者获取到的方向信息，顺时针0-360
                    .direction(100).latitude(location.getLatitude())
                    .longitude(location.getLongitude()).build();
            mBaiduMap.setMyLocationData(locData);
            if (isFirstLoc) {
                isFirstLoc = false;
                ll = new LatLng(location.getLatitude(), location.getLongitude());
                MapStatusUpdate u = MapStatusUpdateFactory.newLatLngZoom(ll, 20);
                // 设置地图最大以及最小缩放级别，地图支持的最大最小级别分别为[3-20]
                mBaiduMap.animateMapStatus(u);
            }
            OverlayOptions overlay = new MarkerOptions().position(ll).icon(mCurMarker);
            mBaiduMap.addOverlay(overlay);
        }


        public void onReceivePoi(BDLocation poiLocation) {
        }
    }
}
