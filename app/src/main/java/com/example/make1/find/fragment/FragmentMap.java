package com.example.make1.find.fragment;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTabHost;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;


import com.baidu.location.BDLocation;
import com.baidu.location.BDLocationListener;
import com.baidu.location.LocationClient;
import com.baidu.location.LocationClientOption;
import com.baidu.mapapi.map.MapView;
import com.example.make1.find.R;

import android.view.View;

import com.example.make1.find.activity.ActivityFriends;
import com.example.make1.find.activity.ActivityFriendsAdd;
import com.example.make1.find.activity.ActivityUser;


/**
 * Created by wjx on 2017/8/2.
 */

public class FragmentMap extends Fragment implements View.OnClickListener {
    public LocationClient mLocationClient;
    BDLocation location;
    private MapView mapView;
    private Button mBtnMapLocation;
    private Button mBtnAddFriends;
    Intent intent;
    private MyLocationListener myListener = new MyLocationListener();

    FragmentTabHost mTabHost = null;

    @Override
    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

//        myListener.onReceiveLocation(location);
//       // mLocationClient = new LocationClient(FragmentMap.this);//发起定位，添加监听
//        initLocation();//初始化LocationClient选项
//        mLocationClient.registerLocationListener(myListener);
//        mLocationClient.start();
//        Bundle bundle = getArguments();
//        if (null != bundle) {
//            //
//        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_map, null);
        mapView = view.findViewById(R.id.bmapView);
        mBtnAddFriends = view.findViewById(R.id.mBtnAddFriends);
        mBtnMapLocation = view.findViewById(R.id.mBtnMapLocation);
        return view;

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mapView.onDestroy();
    }

    @Override
    public void onResume() {
        super.onResume();
        mBtnAddFriends.setOnClickListener(this);
        mBtnMapLocation.setOnClickListener(this);
        mapView.onResume();
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.mBtnAddFriends:
                intent = new Intent(getActivity(), ActivityFriendsAdd.class);
                startActivity(intent);
                break;
//            case R.id.mBtnMapLocation:
//                intent = new Intent(getActivity(), ActivityFriends.class);
//                startActivity(intent);
//                break;
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
        mapView.onPause();
    }

    private void initLocation() {
        LocationClientOption option = new LocationClientOption();
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
        }

    }
}
