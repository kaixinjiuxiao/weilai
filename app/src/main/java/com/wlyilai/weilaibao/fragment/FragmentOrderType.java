package com.wlyilai.weilaibao.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.wlyilai.weilaibao.R;
import com.wlyilai.weilaibao.activity.OrderDetailsActivity;
import com.wlyilai.weilaibao.adapter.GroupingAdapter;
import com.wlyilai.weilaibao.entry.MyGroup;
import com.wlyilai.weilaibao.view.PullLoadMoreRecyclerView;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import java.util.ArrayList;
import java.util.List;

import okhttp3.Call;

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
    private List<MyGroup.DataBean> mList = new ArrayList<>();
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
        initData("1",1);
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

    private void initData(String state,int page) {
        OkHttpUtils.post().url("http://test.mgbh.wlylai.com/AppApi/get_user_order")
                .addParams("access_token", "02c8b29f1b09833e43a37c770a87db23")
                .addParams("state", state)
                .addParams("page", String.valueOf(page)).build().execute(new StringCallback() {
            @Override
            public void onError(Call call, Exception e, int id) {
            Log.e("tag","订单错误"+e.toString());
            }

            @Override
            public void onResponse(String response, int id) {
                Log.e("tag","所有订单"+response);
            }
        });
    }

    @Override
    public void onRefresh() {
        mPullLoadMore.setPullLoadMoreCompleted();
    }

    @Override
    public void onLoadMore() {


    }
}
