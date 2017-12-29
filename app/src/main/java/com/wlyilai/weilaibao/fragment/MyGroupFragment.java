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
import com.wlyilai.weilaibao.adapter.MyGroupAdapter;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

/**
 * @author: captain
 * Time:  2017/12/27 0027
 * Describe: 我的团
 */
public class MyGroupFragment extends BaseFagment {
    private View mView;
    private MyGroupAdapter mAdapter;
    private TabLayout mTabLayout;
    private ViewPager mViewpager;
    private List<String> mStringList = new ArrayList<>();
    private List<Fragment> mlist = new ArrayList<>();
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if(mView==null){
            mView=inflater.inflate(R.layout.fragment_my_group,container,false);
            initView();
            initData();
        }
        return mView;
    }
    private void initView() {
        mTabLayout= (TabLayout)mView.findViewById(R.id.tabLayout);
        mViewpager =(ViewPager) mView.findViewById(R.id.viewpager);
//        mStringList.add("全部");
//        mStringList.add("待付款");
//        mStringList.add("待发货");
//        mStringList.add("待收货");
//        mStringList.add("已完成");
//        FragmentOrderWaitFull all = new FragmentOrderWaitFull();
//        FragmentOrderWaitPay waitPay = new FragmentOrderWaitPay();
//        FragmentOrderWaitSend waitSend = new FragmentOrderWaitSend();
//        FragmentOrderWaitReceive waitReceive = new FragmentOrderWaitReceive();
//        FragmentOrderSuccess success = new FragmentOrderSuccess();
//        mlist.add(all);
//        mlist.add(waitPay);
//        mlist.add(waitSend);
//        mlist.add(waitReceive);
//        mlist.add(success);

    }

    private void initData() {
        mAdapter = new MyGroupAdapter(getChildFragmentManager());
        mViewpager.setAdapter(mAdapter);
        mViewpager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(mTabLayout));
        mTabLayout.setupWithViewPager(mViewpager);
        mTabLayout.post(new Runnable() {
            @Override
            public void run() {
                setIndicator(mTabLayout, 30, 30);
            }
        });
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
