package com.wlyilai.weilaibao.utils;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;

import java.util.ArrayList;
import java.util.List;

/**
 * Author : Captain
 * Time : 2017/12/6
 * Describe :
 */

public class FragmentController2 {
    private int containerId;
    private FragmentManager fm;
    private ArrayList<Fragment> fragments;

    private static FragmentController2 controller;
    private static boolean isReload;

    public static FragmentController2 getInstance(FragmentActivity activity, int containerId, boolean isReload,List<Fragment> list) {
        FragmentController2.isReload = isReload;
        if (controller == null) {
            controller = new FragmentController2(activity, containerId,list);
        }
        return controller;
    }

    public static void onDestroy() {
        controller = null;
    }

    private FragmentController2(FragmentActivity activity, int containerId, List<Fragment> list) {
        this.containerId = containerId;
        fm = activity.getSupportFragmentManager();
        initFragment(list);
    }

    private void initFragment(List<Fragment> data) {
        fragments = new ArrayList<>();
        if (isReload) {
            for (int i = 0; i <data.size() ; i++) {
                fragments.add(data.get(i));
            }
            FragmentTransaction ft = fm.beginTransaction();
            for (int i = 0; i < fragments.size(); i++) {
                ft.add(containerId, fragments.get(i), "" + i);
            }
            ft.commit();

        } else {
            for (int i = 0; i < 5; i++) {
                fragments.add( fm.findFragmentByTag(i+""));
            }
        }
    }

    public void showFragment(int position) {
        hideFragments();
        Fragment fragment = fragments.get(position);
        FragmentTransaction ft = fm.beginTransaction();
        //ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        ft.show(fragment);
        ft.commitAllowingStateLoss();
    }

    public void hideFragments() {
        FragmentTransaction ft = fm.beginTransaction();
        for (Fragment fragment : fragments) {
            if (fragment != null) {
                ft.hide(fragment);
            }
        }
        ft.commitAllowingStateLoss();
    }

    public Fragment getFragment(int position) {
        return fragments.get(position);
    }
}
