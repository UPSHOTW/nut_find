package com.example.make1.find.activity;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.SeekBar;

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

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 *
 */

public class ActivityEquipmentFindArea extends AppCompatActivity implements View.OnClickListener {
    @BindView(R.id.mImgBack)
    ImageView mImgBack;
    @BindView(R.id.seekBarRadius)
    SeekBar seekBarRadius;
    public LocationClient mLocationClient;
    BDLocation location;
    private MapView mMapView;
    private BaiduMap mBaiduMap;
    boolean isVisible = true;

    boolean isFirstLoc = true;// 是否首次定位
    BitmapDescriptor mCurMarker;
    LatLng ll;
    Intent intent;
    public MyLocationListener myListener = new MyLocationListener();
    LocationClientOption option = new LocationClientOption();

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.equipment_find_area);
        mMapView = (MapView) findViewById(R.id.bmapLocation);
        mBaiduMap = mMapView.getMap();
        mBaiduMap.setMyLocationEnabled(true);
        mLocationClient = new LocationClient(ActivityEquipmentFindArea.this);//发起定位，添加监听
        initLocation();//初始化LocationClient选项
        mLocationClient.registerLocationListener(myListener);
        option.setOpenGps(true);
        mLocationClient.setLocOption(option);
        myListener.onReceiveLocation(location);
        Log.i("nut", "option" + option);
        mLocationClient.start();
        //滑动条
        ButterKnife.bind(this);
       // seekBarRadius.setMin(200);
        seekBarRadius.setMax(500);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mMapView.onDestroy();
    }


    @Override
    public void onResume() {
        super.onResume();
        mMapView.onResume();
        mImgBack.setOnClickListener(this);
        seekBarRadius.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                System.out.println(progress);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.mImgBack:
                intent = new Intent(ActivityEquipmentFindArea.this,ActivityDisturbStatment.class);
                startActivity(intent);
                break;
            case R.id.mImgLocation:
                intent = new Intent(ActivityEquipmentFindArea.this, ActivityDisturbStatment.class);
                startActivity(intent);
                break;
            default:
        }
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