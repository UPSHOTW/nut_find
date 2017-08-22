package com.example.make1.find.activity;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.example.make1.find.R;
import com.example.make1.find.adapter.RemindSoundAdapter;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by make1 on 2017/8/22.
 */

public class ActivityRemindSound extends Activity {

    @BindView(R.id.btnSure)
    Button btnSure;
    @BindView(R.id.btnBack)
    Button btnBack;
    @BindView(R.id.ring_lv)
    ListView ring_lv;
    SharedPreferences sp;
    SharedPreferences.Editor spe;
    private String mTxtVoice;
    private RemindSoundAdapter mAdapter;
    private Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.remind_notify_sound);
        /*初始化SharedPreferences*/
        sp = getSharedPreferences("ring", 1);
        spe = sp.edit();

		/*初始化listView*/
        ButterKnife.bind(this);
        mAdapter = new RemindSoundAdapter(this, sp.getInt("ring", 0));
        ring_lv.setAdapter(mAdapter);
        ring_lv.setChoiceMode(ListView.CHOICE_MODE_SINGLE);
        ring_lv.setOnItemClickListener(mOnItemClickListener);

		/*初始化返回按钮和保存按钮*/
        btnBack.setOnClickListener(mOnClickListener);
        btnSure.setOnClickListener(mOnClickListener);

    }

    /*listView的按钮点击事件*/
    private AdapterView.OnItemClickListener mOnItemClickListener = new AdapterView.OnItemClickListener() {

        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position,
                                long id) {

            RemindSoundAdapter.ViewHolder mHolder = new RemindSoundAdapter.ViewHolder(parent);
            /*设置Imageview不可被点击*/
            mHolder.iv.setClickable(false);

            /*清空map*/
            mAdapter.map.clear();
            // mAdapter.map.put(position, 1);
            /*将所点击的位置记录在map中*/
            mAdapter.map.put(position, true);
            /*刷新数据*/
            mAdapter.notifyDataSetChanged();
            /*判断位置不为0则播放的条目为position-1*/
            if (position != 0) {
                try {

                    RingtoneManager rm = new RingtoneManager(ActivityRemindSound.this);
                    rm.setType(RingtoneManager.TYPE_NOTIFICATION);
                    rm.getCursor();
                    rm.getRingtone(position - 1).play();

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            /*position为0是跟随系统，先得到系统所使用的铃声，然后播放*/
            if (position == 0) {

                Uri uri = RingtoneManager.getActualDefaultRingtoneUri(
                        ActivityRemindSound.this, RingtoneManager.TYPE_NOTIFICATION);
                RingtoneManager.getRingtone(ActivityRemindSound.this, uri).play();
            }

        }

    };

    /*按钮点击事件*/
    private View.OnClickListener mOnClickListener = new View.OnClickListener() {

        @Override
        public void onClick(View v) {
            switch (v.getId()) {
            /*返回按钮时退出demo*/
                case R.id.btnBack:
                    finish();
                    intent = new Intent(ActivityRemindSound.this, ActivityEquipmentDetailsRemind.class);
                    startActivity(intent);
                    break;
            /*保存按钮则保存SharedPreferences中的数据*/
                case R.id.btnSure:
                    spe.putInt("ring", ring_lv.getCheckedItemPosition()).commit();
                    Toast.makeText(ActivityRemindSound.this, "保存成功", Toast.LENGTH_SHORT)
                            .show();
                    intent = new Intent(ActivityRemindSound.this, ActivityEquipmentDetailsRemind.class);
                    //用Bundle携带数据
                    Bundle bundle = new Bundle();
                    //传递name参数
                    bundle.putString("mTxtVoice", ring_lv.toString());
                    intent.putExtras(bundle);
                    startActivity(intent);
                    break;
            }
        }
    };

}
