package com.wlyilai.weilaibao.utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;

/**
 * * @author: captain
 * Time:  2017/11/9 0009
 * Description：SharedPreferences的管理类
 */
public class PreferenceUtil {

    private static SharedPreferences mSharedPreferences = null;
    private static Editor mEditor = null;
    private static final String USER_CENTRE = "user_centre";

    public PreferenceUtil(Context context) {
        if (null == mSharedPreferences) {
            mSharedPreferences = android.preference.PreferenceManager.getDefaultSharedPreferences(context);
        }
    }

    public static void init(Context context) {
        if (null == mSharedPreferences) {
            mSharedPreferences = android.preference.PreferenceManager.getDefaultSharedPreferences(context);
        }
    }

    public static void removeKey(String key) {
        mEditor = mSharedPreferences.edit();
        mEditor.remove(key);
        mEditor.commit();
    }

    public static void removeAll() {
        mEditor = mSharedPreferences.edit();
        mEditor.clear();
        mEditor.commit();
    }

    public static void commitString(String key, String value) {
        mEditor = mSharedPreferences.edit();
        mEditor.putString(key, value);
        mEditor.commit();
    }

    public static String getString(String key, String faillValue) {
        return mSharedPreferences.getString(key, faillValue);
    }

    public static void commitInt(String key, int value) {
        mEditor = mSharedPreferences.edit();
        mEditor.putInt(key, value);
        mEditor.commit();
    }

    public static int getInt(String key, int failValue) {
        return mSharedPreferences.getInt(key, failValue);
    }

    public static void commitLong(String key, long value) {
        mEditor = mSharedPreferences.edit();
        mEditor.putLong(key, value);
        mEditor.commit();
    }

    public static long getLong(String key, long failValue) {
        return mSharedPreferences.getLong(key, failValue);
    }

    public static void commitBoolean(String key, boolean value) {
        mEditor = mSharedPreferences.edit();
        mEditor.putBoolean(key, value);
        mEditor.commit();
    }

    public static Boolean getBoolean(String key, boolean failValue) {
        return mSharedPreferences.getBoolean(key, failValue);
    }

    /**
     * 保存配置项
     *
     * @param preferenceName
     * @param obj
     */
    public void savePreference(String preferenceName, Object obj) {
        if (obj instanceof Integer) {
            mEditor.putInt(preferenceName, (Integer) obj);
        } else if (obj instanceof String) {
            mEditor.putString(preferenceName, String.valueOf(obj));
        } else if (obj instanceof Boolean) {
            mEditor.putBoolean(preferenceName, (Boolean) obj);
        } else if (obj instanceof Float) {
            mEditor.putFloat(preferenceName, (Float) obj);
        } else if (obj instanceof Long) {
            mEditor.putLong(preferenceName, (Long) obj);
        }
        mEditor.apply();

    }

    public void saveUser(String key, String name) {
        this.savePreference(USER_CENTRE + key, name);
    }


    public String getUser(String key) {
        return getString(USER_CENTRE + key, "");
    }
}
