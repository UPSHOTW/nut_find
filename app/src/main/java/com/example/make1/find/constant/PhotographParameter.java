package com.example.make1.find.constant;

/**
 * Created by make1 on 2017/8/15.
 * 拍照功能参数
 */

public class PhotographParameter {
    /* 头像文件 */
    public static final String IMAGE_FILE_NAME = "/temp.jpg";

    // 裁剪后图片的宽(X)和高(Y),320 X 320的正方形。
    public static int output_X = 180;
    public static int output_Y = 180;

    public static final String IMAGE_UNSPECIFIED = "image/*";
    /**
     * 调用手机拍照功能、从相册获取照片功能参数
     */
    public static final int NONE = 0;
    public static final int PHOTO_GRAPH = 1;// 拍照  
    public static final int PHOTO_ZOOM = 2; // 缩放  
    public static final int PHOTO_RESOULT = 3;// 结果  
    public static final String change_path = "/find";
}
