package com.wlyilai.weilaibao.fragment;

import android.content.res.Resources;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.wlyilai.weilaibao.R;
import com.wlyilai.weilaibao.adapter.HomeAdapter;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

import butterknife.Unbinder;

/**
 * @author: captain
 * Time:  2017/12/11 0011
 * Describe:
 */
public class FragmentOne extends BaseFagment {
    private TabLayout mTabLayout;
    private ViewPager mViewpager;
    private View mView;
    private Unbinder mUnbinder;
    private HomeAdapter mAdapter;
    private List<String> mStringList = new ArrayList<>();
    private List<Fragment> mlist = new ArrayList<>();
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if (mView == null) {
            mView = inflater.inflate(R.layout.fragment_one, container, false);
//            mUnbinder = ButterKnife.bind(this, mView);
            initView();
            initData();
        }
        return mView;
    }

    private void initView() {
        mTabLayout= (TabLayout)mView.findViewById(R.id.tabLayout);
        mViewpager =(ViewPager) mView.findViewById(R.id.viewpager);
        mStringList.add("首页");
        mStringList.add("服装");
        mStringList.add("商超综合");
        mStringList.add("缴费付费");
        mStringList.add("缴费付费");
        mStringList.add("缴费付费");
        mStringList.add("服装");
        mStringList.add("服装");
        mStringList.add("服装");
        mStringList.add("服装");
        mStringList.add("服装");
        PinGouFragment ping =new PinGouFragment();
        mlist.add(ping);
//        mlist.add(two);
//        mlist.add(three);
//        mlist.add(four);
        for (int i = 0; i <10 ; i++) {
            FragmentTwo two = new FragmentTwo();
            mlist.add(two);
            mTabLayout.addTab(mTabLayout.newTab().setText(mStringList.get(i)));
        }
//        mTabLayout.addTab(mTabLayout.newTab().setText(mStringList.get(1)));
//        mTabLayout.addTab(mTabLayout.newTab().setText(mStringList.get(2)));
//        mTabLayout.addTab(mTabLayout.newTab().setText(mStringList.get(3)));
//        mTabLayout.post(new Runnable() {
//            @Override
//            public void run() {
//                setIndicator(mTabLayout, 10, 10);
//            }
//        });
    }

    private void initData() {
        mAdapter = new HomeAdapter(getChildFragmentManager(),mlist,mStringList);
        mViewpager.setAdapter(mAdapter);
        //   mViewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(mTabLayout));
        mTabLayout.setupWithViewPager(mViewpager);
        mViewpager.setCurrentItem(0);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
//        mUnbinder.unbind();
    }


    public void setIndicator(TabLayout tabs, int leftDip, int rightDip) {
        Class<?> tabLayout = tabs.getClass();
        Field tabStrip = null;
        try {
            tabStrip = tabLayout.getDeclaredField("mTabStrip");
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        }

        tabStrip.setAccessible(true);
        LinearLayout llTab = null;
        try {
            llTab = (LinearLayout) tabStrip.get(tabs);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }

        int left = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, leftDip, Resources.getSystem().getDisplayMetrics());
        int right = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, rightDip, Resources.getSystem().getDisplayMetrics());

        for (int i = 0; i < llTab.getChildCount(); i++) {
            View child = llTab.getChildAt(i);
            child.setPadding(0, 0, 0, 0);
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(0, LinearLayout.LayoutParams.MATCH_PARENT, 1);
            params.leftMargin = left;
            params.rightMargin = right;
            child.setLayoutParams(params);
            child.invalidate();
        }
    }
}
