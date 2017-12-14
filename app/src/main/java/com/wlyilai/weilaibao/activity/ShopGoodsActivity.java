package com.wlyilai.weilaibao.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.wlyilai.weilaibao.R;
import com.wlyilai.weilaibao.adapter.ShopAdapter;
import com.wlyilai.weilaibao.entry.Goods;
import com.wlyilai.weilaibao.view.GridSpacingItemDecoration;
import com.wlyilai.weilaibao.view.PullLoadMoreRecyclerView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * @author: captain
 * Time:  2017/12/14 0014
 * Describe:
 */
public class ShopGoodsActivity extends BaseActivity implements PullLoadMoreRecyclerView.PullLoadMoreListener {
    @BindView(R.id.imgBack)
    ImageView mImgBack;
    @BindView(R.id.txtTitle)
    TextView mTxtTitle;
    @BindView(R.id.pullLoadMore)
    PullLoadMoreRecyclerView mPullLoadMore;
    private RecyclerView mRecyclerView;
    private ShopAdapter mAdapter;
    private List<Goods> mList = new ArrayList<>();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shop_goods);
        ButterKnife.bind(this);
        init();
        initEvent();
    }

    private void init() {
        mImgBack.setVisibility(View.VISIBLE);
        mTxtTitle.setText("");
        mRecyclerView = mPullLoadMore.getRecyclerView();
        mRecyclerView.setVerticalScrollBarEnabled(true);
        mPullLoadMore.setRefreshing(true);
        mPullLoadMore.setGridLayout(2);
        mRecyclerView.addItemDecoration(new GridSpacingItemDecoration(2,15,true));
        mPullLoadMore.setOnPullLoadMoreListener(this);
        mPullLoadMore.setPullLoadMoreCompleted();
        initData();
        mAdapter = new ShopAdapter(ShopGoodsActivity.this, mList);
        mPullLoadMore.setAdapter(mAdapter);
    }

    private void initEvent() {
        mAdapter.setBuyListener(new ShopAdapter.OnBuyListener() {
            @Override
            public void onBuy(int position) {
                startActivity(new Intent(ShopGoodsActivity.this,GroupDetailsActivity.class));
            }
        });
    }

    private void initData() {
        for (int i = 0; i < 10; i++) {
            mList.add(new Goods("http://img1.3lian.com/2015/a1/95/d/105.jpg", "柠檬红茶小青", "￥22.4", "10人团", "18.9"));
        }
    }

    @OnClick(R.id.imgBack)
    public void onClick() {
        finish();
    }

    @Override
    public void onRefresh() {

    }

    @Override
    public void onLoadMore() {

    }
}
