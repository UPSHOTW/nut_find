package com.example.make1.find.activity;

import android.app.TabActivity;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TabHost;
import android.widget.TabWidget;

import com.example.make1.find.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MoreDisturbActivity extends TabActivity implements View.OnClickListener {
	@BindView(R.id.mImgBack)
	ImageButton mImgBack;
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.more_disturb_tab);
		ButterKnife.bind(this);
		Resources res = getResources(); // Resource object to get Drawables
		TabHost tabHost = getTabHost(); // The activity TabHost
		TabHost.TabSpec spec; // Reusable TabSpec for each tab
		Intent intent; // Reusable Intent for each tab

		// Create an Intent to launch an Activity for the tab (to be reused)
		intent = new Intent().setClass(this, MoreAreaActivity .class);
		spec = tabHost.newTabSpec("area")
				.setIndicator("勿扰区域")
				.setContent(intent);
		tabHost.addTab(spec);

		// Do the same for the other tabs

		intent = new Intent().setClass(this,MoreDormancyActivity.class);
		spec = tabHost.newTabSpec("dormancy")
				.setIndicator("休眠时段")
				.setContent(intent);
		tabHost.addTab(spec);
		tabHost.setCurrentTab(0);
		
		
	}
	@Override
	public void onResume() {
		super.onResume();
		TabWidget tabWidget = getTabHost().getTabWidget();
		for (int i = 0;i<tabWidget.getChildCount();i++){
			View v = tabWidget.getChildAt(i);
			v.setBackgroundResource(R.color.gray);
		}
		mImgBack.setOnClickListener(this);
	}


	@Override
	public void onClick(View view) {
		switch (view.getId()) {
			case R.id.mImgBack:
				setResult(9);
				finish();
				break;
			default:
		}
	}
	
}