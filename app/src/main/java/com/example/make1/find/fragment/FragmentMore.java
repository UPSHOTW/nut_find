package com.example.make1.find.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.example.make1.find.activity.ActivityFriends;
import com.example.make1.find.activity.ActivityMoreAbout;
import com.example.make1.find.activity.ActivityMoreDisturb;
import com.example.make1.find.activity.ActivityMoreMessage;
import com.example.make1.find.activity.ActivityMoreOpinion;
import com.example.make1.find.activity.ActivityMoreQuestion;
import com.example.make1.find.activity.ActivityMoreShopping;
import com.example.make1.find.activity.ActivityMoreStory;
import com.example.make1.find.activity.ActivityUser;
import com.example.make1.find.R;


/**
 * Created by wjx on 2017/8/2.
 * 更多
 */

public class FragmentMore extends Fragment implements View.OnClickListener {

    private RelativeLayout mRltUser;
    private RelativeLayout rltFriends;
    private RelativeLayout rltQuestion;
    private RelativeLayout rltStory;
    private RelativeLayout rltShopping;
    private RelativeLayout rltOpinion;
    private RelativeLayout rltAbout;
    private RelativeLayout rltShare;
    private RelativeLayout rltDisturb;



    private ImageView mImgMessage;
    Intent intent;

    @Override
    public View onCreateView(final LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_more, null);
        mRltUser = view.findViewById(R.id.rltUser);
        rltFriends = view.findViewById(R.id.rltFriends);
        mImgMessage = view.findViewById(R.id.mImgMessage);
        rltQuestion = view.findViewById(R.id.rltQuestion);
        rltStory = view.findViewById(R.id.rltStory);
        rltShopping = view.findViewById(R.id.rltShopping);
        rltOpinion = view.findViewById(R.id.rltOpinion);
        rltAbout = view.findViewById(R.id.rltAbout);
        rltShare = view.findViewById(R.id.rltShare);
        rltDisturb = view.findViewById(R.id.rltDisturb);
        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        mRltUser.setOnClickListener(this);
        rltFriends.setOnClickListener(this);
        mImgMessage.setOnClickListener(this);
        rltQuestion.setOnClickListener(this);
        rltStory.setOnClickListener(this);
        rltShopping.setOnClickListener(this);
        rltOpinion.setOnClickListener(this);
        rltAbout.setOnClickListener(this);
        rltShare.setOnClickListener(this);
        rltDisturb.setOnClickListener(this);


    }


    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.rltUser:
                intent = new Intent(getActivity(), ActivityUser.class);
                startActivity(intent);
                break;
            case R.id.rltFriends:
                intent = new Intent(getActivity(), ActivityFriends.class);
                startActivity(intent);
                break;
            case R.id.rltQuestion:
                intent = new Intent(getActivity(), ActivityMoreQuestion.class);
                startActivity(intent);
                break;
            case R.id.rltStory:
                intent = new Intent(getActivity(), ActivityMoreStory.class);
                startActivity(intent);
                break;
            case R.id.rltShopping:
                intent = new Intent(getActivity(), ActivityMoreShopping.class);
                startActivity(intent);
                break;
            case R.id.rltOpinion:
                intent = new Intent(getActivity(), ActivityMoreOpinion.class);
                startActivity(intent);
                break;
            case R.id.rltAbout:
                intent = new Intent(getActivity(), ActivityMoreAbout.class);
                startActivity(intent);
                break;
            case R.id.mImgMessage:
                intent = new Intent(getActivity(), ActivityMoreMessage.class);
                startActivity(intent);
                break;
            case R.id.rltDisturb:
                intent = new Intent(getActivity(), ActivityMoreDisturb.class);
                startActivity(intent);
                break;
            default:
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == 1) { //判断请求码
            ActivityUser activityUser = (ActivityUser) getActivity();
            android.app.FragmentManager fragmentManager = activityUser.getFragmentManager();
            android.app.FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            fragmentTransaction.commit();
        }
 if ( resultCode == 2) { //判断请求码
            ActivityFriends activityFriends = (ActivityFriends) getActivity();
            android.app.FragmentManager fragmentManager = activityFriends.getFragmentManager();
            android.app.FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            fragmentTransaction.commit();

        } if ( resultCode == 3) { //判断请求码
            ActivityMoreQuestion activityMoreQuestion = (ActivityMoreQuestion) getActivity();
            android.app.FragmentManager fragmentManager = activityMoreQuestion.getFragmentManager();
            android.app.FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            fragmentTransaction.commit();

        }  if ( resultCode == 4) { //判断请求码
            ActivityMoreStory activityMoreStory = (ActivityMoreStory) getActivity();
            android.app.FragmentManager fragmentManager = activityMoreStory.getFragmentManager();
            android.app.FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            fragmentTransaction.commit();
        } if ( resultCode == 5) { //判断请求码
            ActivityMoreShopping activityMoreShopping = (ActivityMoreShopping) getActivity();
            android.app.FragmentManager fragmentManager = activityMoreShopping.getFragmentManager();
            android.app.FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            fragmentTransaction.commit();
        } if ( resultCode == 6) { //判断请求码
            ActivityMoreOpinion activityMoreOpinion = (ActivityMoreOpinion) getActivity();
            android.app.FragmentManager fragmentManager = activityMoreOpinion.getFragmentManager();
            android.app.FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            fragmentTransaction.commit();
        } if ( resultCode == 7) { //判断请求码
            ActivityMoreAbout activityMoreAbout = (ActivityMoreAbout) getActivity();
            android.app.FragmentManager fragmentManager = activityMoreAbout.getFragmentManager();
            android.app.FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            fragmentTransaction.commit();
        } if ( resultCode == 8) { //判断请求码
            ActivityMoreMessage activityMoreMessage = (ActivityMoreMessage) getActivity();
            android.app.FragmentManager fragmentManager = activityMoreMessage.getFragmentManager();
            android.app.FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            fragmentTransaction.commit();
        }
        if ( resultCode == 10) { //判断请求码
            ActivityMoreDisturb activityMoreDisturb = (ActivityMoreDisturb) getActivity();
            android.app.FragmentManager fragmentManager = activityMoreDisturb.getFragmentManager();
            android.app.FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            fragmentTransaction.commit();
        }
    }
}
