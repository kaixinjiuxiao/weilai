package com.wlyilai.weilaibao.utils;

import android.content.Context;

import org.json.JSONObject;

/**
 * Created by Administrator on 2017/7/13.
 */

public class AppManager  {
    private static AppManager mAppManager;
    public static final int INPUT_FILE_REQUEST_CODE = 1;
    private static Context appContext;
    public boolean paySuccess = false;
    public JSONObject wxLoginInfo;
    public static int NONEACTION = 0;
    public static int BINDACTION = 1;
    public static int LOGINACTION = 2;
    public static int PAYACTION = 3;
    public int action = 0;
    public static AppManager sharedManager(Context context) {
        appContext = context;
        if (mAppManager == null) {
            mAppManager = new AppManager();
        }
        return mAppManager;
    }

}
