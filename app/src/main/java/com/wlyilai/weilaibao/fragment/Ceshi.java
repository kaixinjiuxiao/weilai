package com.wlyilai.weilaibao.fragment;

import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.wlyilai.weilaibao.R;
import com.wlyilai.weilaibao.adapter.GoodsAdapter;
import com.wlyilai.weilaibao.adapter.TypeAdapter;
import com.wlyilai.weilaibao.entry.CateInfo;
import com.wlyilai.weilaibao.entry.Goods;
import com.wlyilai.weilaibao.view.PullLoadMoreRecyclerView;

import java.util.ArrayList;
import java.util.List;

/**
 * @author: captain
 * Time:  2017/12/20 0020
 * Describe:
 */
public class Ceshi extends LazyLoadFragment {
    private String id;
    private RecyclerView mRecyclerView;
    private TypeAdapter mAdapter;
    private List<CateInfo.DataBean> mList= new ArrayList<>();
    private List<CateInfo.DataBean> mList2= new ArrayList<>();
    private PullLoadMoreRecyclerView mPullLoadMore;
    private GoodsAdapter mGoodsAdapter;
    private RecyclerView mRecyclerViewGoods;
    private List<Goods.DataBean> mListGoods = new ArrayList<>();
    @Override
    protected int setContentView() {
        return R.layout.fragment_ping_details;
    }

    @Override
    protected void lazyLoad() {

    }

    @Override
    protected <T extends View> T findViewById(int id) {
        return super.findViewById(id);
    }
}
