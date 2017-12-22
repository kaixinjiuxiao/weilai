package com.wlyilai.weilaibao.utils;

import android.content.Context;
import android.util.TypedValue;

/**
 * @author: captain
 * Time:  2017/11/15 0015
 * Describe: 常用单位转换
 */
public class DensityUtils {

    private DensityUtils(){
        throw  new UnsupportedOperationException("cannot be instantiated");
    }

    /**
     * dp转换成px
     * @param context
     * @param dp
     * @return
     */
    public static int dpToPx(Context context ,float dp){
        return (int)TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP,dp,context.getResources().getDisplayMetrics());
    }

    /**
     * sp转换成px
     * @param context
     * @param sp
     * @return
     */
    public static int spToPx(Context context ,float sp){
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP,sp,context.getResources().getDisplayMetrics());
    }

    /**
     * px转换成dp
     * @param context
     * @param px
     * @return
     */
    public static float pxToDp(Context context,int px){
        float scal = context.getResources().getDisplayMetrics().density;
        return (px/scal);
    }

    /**
     * px转换成sp
     * @param context
     * @param px
     * @return
     */
    public static float pxToSp(Context context,int px){
        float scal = context.getResources().getDisplayMetrics().scaledDensity;
        return (px/scal);
    }
}
