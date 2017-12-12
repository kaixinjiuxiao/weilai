package com.wlyilai.weilaibao.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.wlyilai.weilaibao.R;
import com.wlyilai.weilaibao.adapter.JoinGroupAdapter;
import com.wlyilai.weilaibao.entry.JoinInfo;
import com.wlyilai.weilaibao.view.PullLoadMoreRecyclerView;

import java.util.ArrayList;
import java.util.List;

import static android.icu.lang.UCharacter.GraphemeClusterBreak.V;

/**
 * @author: captain
 * Time:  2017/12/12 0012
 * Describe:
 */
public class GroupPurchaseDetailsActivity extends BaseActivity implements PullLoadMoreRecyclerView.PullLoadMoreListener {
    private ProgressBar progress;
    private ImageView mBack;
    private TextView mTitle;
    private PullLoadMoreRecyclerView mPullLoadMoreRecyclerView;
    private JoinGroupAdapter mAdapter;
    private RecyclerView mRecyclerView;
    private List<JoinInfo> mList = new ArrayList<>();
    private int page = 1;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_group_purchase_details);
        initView();
    }
    private void initView() {
        mBack = (ImageView) findViewById(R.id.imgBack);
        mBack.setVisibility(View.VISIBLE);
        mTitle = (TextView) findViewById(R.id.txtTitle);
        mTitle.setText("团详情");
        progress = (ProgressBar) findViewById(R.id.progress);
        progress.setMax(10);
        progress.setSecondaryProgress(8);
        mPullLoadMoreRecyclerView = (PullLoadMoreRecyclerView)findViewById(R.id.pullLoadMore);
        mRecyclerView = mPullLoadMoreRecyclerView.getRecyclerView();
        mRecyclerView.setVerticalScrollBarEnabled(true);
        mPullLoadMoreRecyclerView.setRefreshing(true);
        mPullLoadMoreRecyclerView.setLinearLayout();
        mPullLoadMoreRecyclerView.setOnPullLoadMoreListener(this);
        initData();
        mAdapter = new JoinGroupAdapter(this,mList);
        mPullLoadMoreRecyclerView.setAdapter(mAdapter);
    }

    private void initData() {
        for (int i = 0; i <9 ; i++) {
            mList.add(new JoinInfo("150xxxx5678","2017-12-12 20:20:20参团"));
        }
        mList.add(new JoinInfo("150xxxx5678","2017-12-12 20:20:20开团"));
    }

    @Override
    public void onRefresh() {
        mPullLoadMoreRecyclerView.setPullLoadMoreCompleted();
    }

    @Override
    public void onLoadMore() {
        mPullLoadMoreRecyclerView.setPullLoadMoreCompleted();
    }


}
