package com.example.make1.find.utils;

import android.content.Context;

/**
 * Created by make1 on 2017/8/21.
 */

public class DensityDpToPx {
    /**
     * 根据手机的分辨率从dp的单位转成为Px(像素)
     */
    public static int dpToPx(final Context context,final float dp){
        return (int)(dp*context.getResources().getDisplayMetrics().density);
    }
    /**
     * 根据手机的分辨率从px的单位转成为dp
     *
     */
    public static int px2dip(Context context,float pxValue){
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int)(pxValue/scale+0.5f);
    }
}
