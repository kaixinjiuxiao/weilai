package com.wlyilai.weilaibao.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.wlyilai.weilaibao.entry.TypeAndBanner;

import java.util.List;

/**
 * @author: captain
 * Time:  2017/12/5 0005
 * Describe:
 */
public class HomeAdapter extends FragmentStatePagerAdapter {
    private final FragmentManager mFm;
    private List<Fragment> fragments;
    private List<TypeAndBanner.DataBean.CateBean> mChannels;
    private int mChildCount;
    private boolean[] fragmentsUpdateFlag;

    public HomeAdapter(FragmentManager fm, List<Fragment> fragments, List<TypeAndBanner.DataBean.CateBean> channels) {
        super(fm);
        mFm = fm;
        this.fragments = fragments;
        this.mChannels = channels;
    }


    @Override
    public int getCount() {
        return mChannels.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return mChannels == null ? "" : mChannels.get(position).getName();
    }



    @Override
    public int getItemPosition(Object object) {
        return POSITION_NONE;
    }

    @Override
    public Fragment getItem(int position) {
        return fragments.get(position);
    }
}
