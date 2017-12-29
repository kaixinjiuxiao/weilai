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
 * Time:  2017/12/28 0028
 * Describe:
 */
public class MyTeamActivity extends BaseActivity implements PullLoadMoreRecyclerView.PullLoadMoreListener {
    @BindView(R.id.imgBack)
    ImageView mImgBack;
    @BindView(R.id.txtTitle)
    TextView mTxtTitle;
    @BindView(R.id.name)
    TextView mName;
    @BindView(R.id.type)
    TextView mType;
    @BindView(R.id.joinTime)
    TextView mJoinTime;
    @BindView(R.id.myRecommend)
    TextView mMyRecommend;
    @BindView(R.id.pullLoadMore)
    PullLoadMoreRecyclerView mPullLoadMore;
    private RecyclerView mRecyclerView;
    private BanlanceAdapter mAdapter;
    private List<Banlance> mList = new ArrayList<>();
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_team);
        ButterKnife.bind(this);
        init();
    }

    private void init() {
        mImgBack.setVisibility(View.VISIBLE);
        mTxtTitle.setText("我的团队");
        mRecyclerView = mPullLoadMore.getRecyclerView();
        mRecyclerView.addItemDecoration(new DividerItemDecoration(MyTeamActivity.this, DividerItemDecoration.VERTICAL));
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
        for (int i = 0; i < 5; i++) {
            mList.add(new Banlance("155209938963", "消费者", "2017-12-25 10:30:30"));
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
