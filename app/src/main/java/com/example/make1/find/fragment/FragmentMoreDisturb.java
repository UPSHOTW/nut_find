package com.example.make1.find.fragment;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.make1.find.R;
import com.example.make1.find.adapter.MoreDisturbAdapter;
import com.example.make1.find.adapter.RegisterAdapter;
import com.example.make1.find.layout.SlidingTabLayout;

import java.util.ArrayList;
import java.util.List;

/**
 *
 */
public class FragmentMoreDisturb extends Fragment {
    private View view;
    private ViewPager viewPager;
    private Toolbar toolbar;
    private List<Fragment> list;
    private SlidingTabLayout tabLayout;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_more_disturb, container, false);
        initView();
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initDate();
    }

    private void initView() {
        viewPager =  view.findViewById(R.id.viewPagerMoreDisturb);
        toolbar = view.findViewById(R.id.toolbarMoreDisturb);
        tabLayout = view.findViewById(R.id.tabLayoutMoreDisturb);
    }

    private void initDate() {
        list = new ArrayList<>();
        list.add(new FragmentMoreDisturbArea());
        list.add(new FragmentMoreDisturbDormancy());
        MoreDisturbAdapter adapter = new MoreDisturbAdapter(getChildFragmentManager(), list, view.getContext());
        viewPager.setAdapter(adapter);

        tabLayout.setViewPager(viewPager);
        tabLayout.setCustomTabColorizer(new SlidingTabLayout.TabColorizer() {
            @Override
            public int getIndicatorColor(int position) {
                return Color.WHITE;
            }
        });
    }
}
