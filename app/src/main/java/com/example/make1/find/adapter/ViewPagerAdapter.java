package com.example.make1.find.adapter;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.example.make1.find.R;
import com.example.make1.find.fragment.FragmentDisturbArea;
import com.example.make1.find.fragment.FragmentDisturbDormancy;
import com.example.make1.find.fragment.TabFragmentHost;

import java.util.List;


public class ViewPagerAdapter extends FragmentStatePagerAdapter{

    private List<Fragment> list;
    public TabFragmentHost mTabHost;
    private Context context;
    private String[] titles={"勿扰区域","休眠时段"};
    private Integer[] ImgTab = { R.layout.tab_disturb_area,
            R.layout.tab_disturb_dormancy};

    private Class[] ClassTab = { FragmentDisturbArea.class, FragmentDisturbDormancy.class};
    public ViewPagerAdapter(FragmentManager fm, List<Fragment> list, Context context) {
        super(fm);
        this.list = list;
        this.context = context;
    }

    @Override
    public Fragment getItem(int position) {
        return list.get(position);
    }

    @Override
    public int getCount() {
        if(list!=null)
            return list.size();
        return 0;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return titles[position];
    }
}
