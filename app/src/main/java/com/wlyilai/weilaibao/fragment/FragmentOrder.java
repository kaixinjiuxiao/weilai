package com.wlyilai.weilaibao.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.wlyilai.weilaibao.R;
import com.wlyilai.weilaibao.adapter.HomeAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * @author: captain
 * Time:  2017/12/13 0013
 * Describe:
 */
public class FragmentOrder extends BaseFagment {
   private View mView;
    private TabLayout mTabLayout;
    private ViewPager mViewpager;
    private HomeAdapter mAdapter;
    private List<String> mStringList = new ArrayList<>();
    private List<Fragment> mlist = new ArrayList<>();
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if(mView==null){
            mView = inflater.inflate(R.layout.fragment_order,container,false);
            initView();
            initData();
        }
        return mView;
    }

    private void initView() {
        mTabLayout= (TabLayout)mView.findViewById(R.id.tabLayout);
        mViewpager =(ViewPager) mView.findViewById(R.id.viewpager);
        mStringList.add("全部");
        mStringList.add("待付款");
        mStringList.add("待发货");
        mStringList.add("待收货");
        mStringList.add("已完成");
        for (int i = 0; i < 5; i++) {
            FragmentOrderType all = new FragmentOrderType();
            mlist.add(all);
        }
    }

    private void initData() {
        mAdapter = new HomeAdapter(getChildFragmentManager(),mlist,mStringList);
        mViewpager.setAdapter(mAdapter);
        //   mViewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(mTabLayout));
        mTabLayout.setupWithViewPager(mViewpager);
        mViewpager.setCurrentItem(0);
    }
}
