package com.wlyilai.weilaibao.utils;

import android.app.Activity;

import java.util.Stack;

/**
 * Created by Administrator on 2017/9/26 0026.
 */

public class ActivityManager {
    private static ActivityManager manager;
    private Stack<Activity> activityStack;
    private ActivityManager() {
    }
    //单例
    public static  ActivityManager getInstance() {
        if (manager == null) {
            synchronized (ActivityManager.class) {
                if (manager == null) {
                    manager = new ActivityManager();
                }
            }
        }
        return manager;
    }

    /**
     * 添加Activity到堆栈
     */
    // Stack <Activity> activityStack;
    public void addActivity(Activity activity) {
        if (activityStack == null) {
            activityStack = new Stack<Activity>();
        }
        activityStack.add(activity);
    }

    /**
     * 获取当前Activity（堆栈中最后一个压入的）
     */
    public Activity currentActivity() {
        Activity activity = activityStack.lastElement();
        return activity;
    }

    /**
     * 结束当前Activity（堆栈中最后一个压入的）
     */
    public void finishActivity() {
        Activity activity = activityStack.lastElement();
        finishActivity(activity);
    }

    /**
     * 结束指定的Activity
     */
    public void finishActivity(Activity activity) {
        if (activity != null) {
            activityStack.remove(activity);
            activity.finish();
            activity = null;
        }
    }

    /**
     * 结束指定类名的Activity
     */
    public void finishActivity(Class<?> cls) {
        for (Activity activity : activityStack) {
            if (activity.getClass().equals(cls)) {
                finishActivity(activity);
            }
        }
    }

    public  void finishBesidesIndex(){

        if(activityStack.size()==1){
            return;
        }
        for (int i = 1; i < activityStack.size(); i++) {

            if (null != activityStack.get(i)) {
                activityStack.get(i).finish();
                }
            }
    }
    /**
     * 结束所有Activity
     */
    public void finishAllActivity() {
        for (int i = 0; i < activityStack.size(); i++) {
            if (null != activityStack.get(i)) {
                activityStack.get(i).finish();
            }
        }
        activityStack.clear();
    }

    public int counts(){
        if(activityStack==null){
            activityStack =  new Stack<Activity>();
        }
        return activityStack.size();
    }
}
