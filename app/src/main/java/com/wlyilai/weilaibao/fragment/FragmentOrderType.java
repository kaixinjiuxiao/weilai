package com.wlyilai.weilaibao.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.wlyilai.weilaibao.R;
import com.wlyilai.weilaibao.activity.OrderDetailsActivity;
import com.wlyilai.weilaibao.adapter.GroupingAdapter;
import com.wlyilai.weilaibao.entry.MyGroup;
import com.wlyilai.weilaibao.view.PullLoadMoreRecyclerView;

import java.util.ArrayList;
import java.util.List;

/**
 * @author: captain
 * Time:  2017/12/11 0011
 * Describe:
 */
public class FragmentOrderType extends BaseFagment implements PullLoadMoreRecyclerView.PullLoadMoreListener {
    private View mView;
    private GroupingAdapter mAdapter;
    private PullLoadMoreRecyclerView mPullLoadMore;
    private RecyclerView mRecyclerView;
    private List<MyGroup> mList = new ArrayList<>();
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if(mView==null){
            mView =inflater.inflate(R.layout.fragment_common_order,container,false);
            initView();
            initEvent();
        }
        return mView;
    }

    private void initView() {
        mPullLoadMore = (PullLoadMoreRecyclerView)mView.findViewById(R.id.pullLoadMore);
        mRecyclerView = mPullLoadMore.getRecyclerView();
        mRecyclerView.setVerticalScrollBarEnabled(true);
        mPullLoadMore.setRefreshing(true);
        mPullLoadMore.setLinearLayout();
        mPullLoadMore.setOnPullLoadMoreListener(this);
        mPullLoadMore.setPullLoadMoreCompleted();
        initData();
        mAdapter = new GroupingAdapter(getActivity(), mList);
        mPullLoadMore.setAdapter(mAdapter);
    }

    private void initEvent() {
        mAdapter.setLookDetailsListener(new GroupingAdapter.OnLookDetailsListener() {
            @Override
            public void lookDetails(int position) {
                startActivity(new Intent(getActivity(),OrderDetailsActivity.class));
            }
        });
    }

    private void initData() {
        for (int i = 0; i <10 ; i++) {
            mList.add(new MyGroup("http://img1.3lian.com/2015/a1/95/d/105.jpg","订单号：PT20171212140909123456778",
                    "团购进行中","【VIP价16.99】新疆核桃500","￥16.99/1件","x2","共2个商品","总额：￥34.98元"));
        }
    }

    @Override
    public void onRefresh() {
        mPullLoadMore.setPullLoadMoreCompleted();
    }

    @Override
    public void onLoadMore() {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                mList.add(new MyGroup("http://img1.3lian.com/2015/a1/95/d/105.jpg","订单号：PT20171212140909123456778",
                        "团购进行中","【VIP价16.99】新疆核桃500","￥16.99/1件","x2","共2个商品","总额：￥34.98元"));
                mAdapter.notifyDataSetChanged();
                mPullLoadMore.setPullLoadMoreCompleted();
            }
        },2000);

    }
}
