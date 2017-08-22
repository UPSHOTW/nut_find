package com.example.make1.find.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import com.example.make1.find.R;
import com.example.make1.find.activity.ActivityEquipmentDetails;
import com.example.make1.find.activity.ActivityEquipmentDetailsRemind;


/**
 *设备详情中的防丢模式页面
 */
public class FragmentEquipmentDisturb extends Fragment {
    private View view;
    private RelativeLayout rltRemind;
    Intent intent;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_equipment_disturb, container, false);
        rltRemind = view.findViewById(R.id.rltRemind);
        rltRemind.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                intent = new Intent(getActivity(),ActivityEquipmentDetailsRemind.class);
                startActivity(intent);
            }
        });
        return view;
    }
    @Override
    public void onResume() {
        super.onResume();

    }


    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }
}
