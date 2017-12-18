package com.wlyilai.weilaibao.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.wlyilai.weilaibao.fragment.GroupErrorFragment;
import com.wlyilai.weilaibao.fragment.GroupSuccessFragment;
import com.wlyilai.weilaibao.fragment.GroupingFragment;

import java.util.ArrayList;
import java.util.List;

/**
 * Author:  captain
 * Time:  2017/10/28 0028
 * Describe:
 */
public class MyGroupAdapter extends FragmentPagerAdapter {
    private String[] mTitles = new String[]{"组团中", "组团成功", "组团失败"};
   private List<Fragment> mFragments;
    public MyGroupAdapter(FragmentManager fm) {
        super(fm);
        mFragments = new ArrayList<>();
        mFragments.add(new GroupingFragment());
        mFragments.add(new GroupSuccessFragment());
        mFragments.add(new GroupErrorFragment());
    }


    @Override
    public Fragment getItem(int position) {
        return mFragments.get(position);
    }

    @Override
    public int getCount() {
        return mTitles.length;
    }
    @Override
    public CharSequence getPageTitle(int position) {
        return mTitles[position];
    }
}
