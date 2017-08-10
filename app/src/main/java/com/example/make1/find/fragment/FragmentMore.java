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
import com.example.make1.find.activity.ActivityLogin;
import com.example.make1.find.activity.ActivityMoreAbout;
import com.example.make1.find.activity.ActivityMoreMessage;
import com.example.make1.find.activity.ActivityMoreOpinion;
import com.example.make1.find.activity.ActivityMoreQuestion;
import com.example.make1.find.activity.ActivityMoreShopping;
import com.example.make1.find.activity.ActivityMoreStory;
import com.example.make1.find.activity.ActivityUser;
import com.example.make1.find.R;
import com.example.make1.find.activity.MoreDisturbActivity;
import com.maiml.library.BaseItemLayout;


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
    private BaseItemLayout layout;
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
//        layout = (BaseItemLayout)view.findViewById(R.id.layout_moreitem);
//        initData();
        return view;

    }

    //    private void initData() {
//        List<String> valueList = new ArrayList<>();
//
//        valueList.add("使用说明");
//        valueList.add("用户故事");
//        valueList.add("官方商店");
//        valueList.add("意见反馈");
//        valueList.add("关于我们");
//        valueList.add("分享自在找");
//
//        List<Integer> resIdList = new ArrayList<>();
//
//        resIdList.add(R.mipmap.more_opinion);
//        resIdList.add(R.mipmap.more_nut_story);
//        resIdList.add(R.mipmap.more_shopping);
//        resIdList.add(R.mipmap.more_opinion);
//        resIdList.add(R.mipmap.more_about_us);
//        resIdList.add(R.mipmap.more_share);
//        ConfigAttrs attr = new ConfigAttrs(); // 把全部参数的配置，委托给ConfigAttrs类处理。
//        //参数 使用链式方式配置
//        attr.setValueList(valueList)  // 文字 list
//                .setResIdList(resIdList) // icon list
//                .setTextSize(28)
//                .setIconWidth(24)  //设置icon 的大小
//                .setIconHeight(48)
//                .setItemMarginTop(5)
//               // .setItemMode(Mode.ARROW)
//                .setArrowResId(R.mipmap.ic_web_next)//item 中有设置 Mode.ARROW 模式 就必须得设置该值。
//                .setMarginRight(5);//设置距离右边的间距
//
//
//        layout.setConfigAttrs(attr)
//                .create(); //
//    }
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
                intent = new Intent(getActivity(), MoreDisturbActivity.class);
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
        if (resultCode == 2) { //判断请求码
            ActivityFriends activityFriends = (ActivityFriends) getActivity();
            android.app.FragmentManager fragmentManager = activityFriends.getFragmentManager();
            android.app.FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            fragmentTransaction.commit();

        }
        if (resultCode == 3) { //判断请求码
            ActivityMoreQuestion activityMoreQuestion = (ActivityMoreQuestion) getActivity();
            android.app.FragmentManager fragmentManager = activityMoreQuestion.getFragmentManager();
            android.app.FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            fragmentTransaction.commit();

        }
        if (resultCode == 4) { //判断请求码
            ActivityMoreStory activityMoreStory = (ActivityMoreStory) getActivity();
            android.app.FragmentManager fragmentManager = activityMoreStory.getFragmentManager();
            android.app.FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            fragmentTransaction.commit();
        }
        if (resultCode == 5) { //判断请求码
            ActivityMoreShopping activityMoreShopping = (ActivityMoreShopping) getActivity();
            android.app.FragmentManager fragmentManager = activityMoreShopping.getFragmentManager();
            android.app.FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            fragmentTransaction.commit();
        }
        if (resultCode == 6) { //判断请求码
            ActivityMoreOpinion activityMoreOpinion = (ActivityMoreOpinion) getActivity();
            android.app.FragmentManager fragmentManager = activityMoreOpinion.getFragmentManager();
            android.app.FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            fragmentTransaction.commit();
        }
        if (resultCode == 7) { //判断请求码
            ActivityMoreAbout activityMoreAbout = (ActivityMoreAbout) getActivity();
            android.app.FragmentManager fragmentManager = activityMoreAbout.getFragmentManager();
            android.app.FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            fragmentTransaction.commit();
        }
        if (resultCode == 8) { //判断请求码
            ActivityMoreMessage activityMoreMessage = (ActivityMoreMessage) getActivity();
            android.app.FragmentManager fragmentManager = activityMoreMessage.getFragmentManager();
            android.app.FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            fragmentTransaction.commit();
        }

    }
}
