package com.wlyilai.weilaibao.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.wlyilai.weilaibao.R;
import com.wlyilai.weilaibao.adapter.GoodsAdapter;
import com.wlyilai.weilaibao.adapter.TypeAdapter;
import com.wlyilai.weilaibao.entry.Goods;
import com.wlyilai.weilaibao.view.DividerGridItemDecoration;
import com.wlyilai.weilaibao.view.PullLoadMoreRecyclerView;

import java.util.ArrayList;
import java.util.List;

/**
 * @author: captain
 * Time:  2017/12/11 0011
 * Describe:
 */
public class FragmentTwo extends BaseFagment implements PullLoadMoreRecyclerView.PullLoadMoreListener {
    private View mView;
    private RecyclerView mRecyclerView;
    private TypeAdapter mAdapter;
    private List<String> mList= new ArrayList<>();
    private List<String> mList2= new ArrayList<>();
   private PullLoadMoreRecyclerView mPullLoadMore;
    private GoodsAdapter mGoodsAdapter;
    private RecyclerView mRecyclerViewGoods;
    private List<Goods.DataBean> mListGoods = new ArrayList<>();
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if(mView==null){
            mView =inflater.inflate(R.layout.fragment_ping_details,container,false);
            initView();
            initEvent();
        }
        return mView;
    }

    private void initView() {
        mRecyclerView = (RecyclerView) mView.findViewById(R.id.recyclerTwo);
        mRecyclerView.setLayoutManager(new GridLayoutManager(getActivity(),5));
        mRecyclerView.addItemDecoration(new DividerGridItemDecoration(getActivity(),2,0XFFD2d2d2));
        initData();
        mRecyclerView.setAdapter(mAdapter);
        mPullLoadMore = (PullLoadMoreRecyclerView)mView.findViewById(R.id.pullLoadMore);
        mRecyclerViewGoods = mPullLoadMore.getRecyclerView();
        mRecyclerViewGoods.setVerticalScrollBarEnabled(true);
        mPullLoadMore.setRefreshing(true);
        mPullLoadMore.setIsLoadMore(false);
        mPullLoadMore.setLinearLayout();
        mPullLoadMore.setOnPullLoadMoreListener(this);
        mPullLoadMore.setPullLoadMoreCompleted();
        mGoodsAdapter = new GoodsAdapter(getActivity(), mListGoods);
        mPullLoadMore.setAdapter(mGoodsAdapter);
    }

    private void initEvent() {
        mAdapter.setItemClickListener(new TypeAdapter.OnItemClickListener() {
            @Override
            public void onIntemClick(int position) {
                if(position==9){
                    mAdapter.setData(mList);
                    mAdapter.notifyDataSetChanged();
                }else{
                    Log.e("tag","我点击了什么");
                }
            }
        });
    }



    private void initData() {
        for (int i = 0; i <12 ; i++) {
            mList.add("服装");
        }
        if(mList.size()>10){
            for (int i = 0; i <9 ; i++) {
               mList2.add(mList.get(i));
            }
            mList2.add("更多");
            mAdapter = new TypeAdapter(getActivity(),mList2);
        }else{
            mAdapter = new TypeAdapter(getActivity(),mList);
        }

    }

    @Override
    public void onRefresh() {
        mPullLoadMore.setPullLoadMoreCompleted();
    }

    @Override
    public void onLoadMore() {
        mPullLoadMore.setPullLoadMoreCompleted();
    }
}
