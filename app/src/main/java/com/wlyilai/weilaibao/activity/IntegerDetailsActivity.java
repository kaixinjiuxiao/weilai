package com.wlyilai.weilaibao.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.wlyilai.weilaibao.R;
import com.wlyilai.weilaibao.adapter.BanlanceAdapter;
import com.wlyilai.weilaibao.entry.Banlance;
import com.wlyilai.weilaibao.view.PullLoadMoreRecyclerView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * @author: captain
 * Time:  2017/12/23 0023
 * Describe:积分详情详情
 */
public class IntegerDetailsActivity extends BaseActivity implements PullLoadMoreRecyclerView.PullLoadMoreListener {
    @BindView(R.id.imgBack)
    ImageView mImgBack;
    @BindView(R.id.pullLoadMore)
    PullLoadMoreRecyclerView mPullLoadMore;
    @BindView(R.id.txtTitle)
    TextView mTxtTitle;
    private RecyclerView mRecyclerView;
    private BanlanceAdapter mAdapter;
    private List<Banlance> mList = new ArrayList<>();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_banlance_details);
        ButterKnife.bind(this);
        init();
    }

    private void init() {
        mImgBack.setVisibility(View.VISIBLE);
        mTxtTitle.setText("积分详情");
        mRecyclerView = mPullLoadMore.getRecyclerView();
        mRecyclerView.addItemDecoration(new DividerItemDecoration(IntegerDetailsActivity.this, DividerItemDecoration.VERTICAL));
        mRecyclerView.setVerticalScrollBarEnabled(true);
        mPullLoadMore.setRefreshing(true);
        mPullLoadMore.setLinearLayout();
        mPullLoadMore.setOnPullLoadMoreListener(this);
        mPullLoadMore.setPullLoadMoreCompleted();
        initData();
        mAdapter = new BanlanceAdapter(this, mList);
        mPullLoadMore.setAdapter(mAdapter);
    }
    private void initData() {
        for (int i = 0; i <5 ; i++) {
            mList.add(new Banlance("积分充值","100.00","2017-12-25 10:30:30"));
        }
    }
    @OnClick(R.id.imgBack)
    public void onClick() {
        finish();
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
