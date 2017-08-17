package com.example.make1.find.activity;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.LinearLayout;

import com.example.make1.find.R;
import com.example.make1.find.fragment.FragmentDisturb;
import com.example.make1.find.fragment.FragmentMap;
import com.example.make1.find.fragment.FragmentMore;
import com.example.make1.find.fragment.MainTabFragmentHost;

/**
 * 主界面
 */
public class MainTab extends FragmentActivity {
	Intent intent;
	public MainTabFragmentHost mTabHost;
	// 标签
	private String[] TabTag = { "tab1", "tab2", "tab3" };
	// 自定义tab布局显示文本和顶部的图片
	private Integer[] ImgTab = { R.layout.tab_main_disturb,
			R.layout.tab_main_map, R.layout.tab_main_more };
	// tab 选中的activity
	private Class[] ClassTab = { FragmentDisturb.class, FragmentMap.class,
			FragmentMore.class };

	// tab选中背景 drawable 样式图片 背景都是同一个,背景颜色都是 白色。。。
	private Integer[] StyleTab = { R.color.white, R.color.white, R.color.white,
			R.color.white };

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.maintabs);
		setupView();
		initValue();
		setLinstener();
		fillData();
		LocationDialog();

	}
	/**
	 * 打开定位提示框
	 */
	private void LocationDialog() {
		View view = LayoutInflater.from(MainTab.this).inflate(R.layout.dialog_open_location, null);
		final Button mBtnAbrogate = view.findViewById(R.id.mBtnAbrogate);//"取消"按钮
		final Button mBtnAffirm = view.findViewById(R.id.mBtnAffirm);//"确认"按钮
		final AlertDialog.Builder builder = new AlertDialog.Builder(MainTab.this).setView(view);
		builder.setView(view);
		final AlertDialog alertDialog = builder.create();
		view.setBackgroundResource(R.drawable.button_shape_white);
		mBtnAffirm.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				//跳转到手机系统中的定位设置
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

	private void setupView() {

		// 实例化framentTabHost
		mTabHost = (MainTabFragmentHost) findViewById(android.R.id.tabhost);
		mTabHost.setup(this, getSupportFragmentManager(),
				android.R.id.tabcontent);

	}

	private void initValue() {

		// 初始化tab选项卡
		InitTabView();
	}

	private void setLinstener() {
		// imv_back.setOnClickListener(this);

	}

	private void fillData() {
		// TODO Auto-generated method stub

	}

	// 初始化 tab 自定义的选项卡 ///////////////
	private void InitTabView() {

		// 可以传递参数 b;传递公共的userid,version,sid
		Bundle b = new Bundle();
		// 循环加入自定义的tab
		for (int i = 0; i < TabTag.length; i++) {
			// 封装的自定义的tab的样式
			View indicator = getIndicatorView(i);
			mTabHost.addTab(
					mTabHost.newTabSpec(TabTag[i]).setIndicator(indicator),
					ClassTab[i], b);// 传递公共参数

		}
		mTabHost.getTabWidget().setDividerDrawable(R.color.white);
	}

	// 设置tab自定义样式:注意 每一个tab xml子布局的linearlayout 的id必须一样
	private View getIndicatorView(int i) {
		// 找到tabhost的子tab的布局视图
		View v = getLayoutInflater().inflate(ImgTab[i], null);
		LinearLayout tv_lay = (LinearLayout) v.findViewById(R.id.layout_back);
		tv_lay.setBackgroundResource(StyleTab[i]);

		return v;
	}
}
