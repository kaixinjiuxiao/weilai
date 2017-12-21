package com.wlyilai.weilaibao.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.wlyilai.weilaibao.R;
import com.wlyilai.weilaibao.adapter.RPNoteAdapter;
import com.wlyilai.weilaibao.entry.RedPNote;
import com.wlyilai.weilaibao.view.NumberRunningTextView;
import com.wlyilai.weilaibao.view.PullLoadMoreRecyclerView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * @author: captain
 * Time:  2017/12/21 0021
 * Describe:
 */
public class RedPackageNoteActivity extends BaseActivity implements PullLoadMoreRecyclerView.PullLoadMoreListener {
    @BindView(R.id.imgBack)
    ImageView mImgBack;
    @BindView(R.id.txtTitle)
    TextView mTxtTitle;
    @BindView(R.id.rpCash)
    NumberRunningTextView mRpCash;
    @BindView(R.id.name)
    TextView mName;
    @BindView(R.id.myRedPackage)
    NumberRunningTextView mMyRedPackage;
    @BindView(R.id.reRedPackage)
    NumberRunningTextView mReRedPackage;
    @BindView(R.id.pullLoadMore)
    PullLoadMoreRecyclerView mPullLoadMore;
    private RecyclerView mRecyclerView;
    private List<RedPNote> mList = new ArrayList<>();
    private RPNoteAdapter mAdapter;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rp_note);
        ButterKnife.bind(this);
        init();
    }

    private void init() {
        mImgBack.setVisibility(View.VISIBLE);
        mTxtTitle.setText("红包记录");
        mRpCash.setContent("10000.00");
        mMyRedPackage.setContent("20");
        mReRedPackage.setContent("15");
        mRecyclerView = mPullLoadMore.getRecyclerView();
        mPullLoadMore.setLinearLayout();
        mRecyclerView.setVerticalScrollBarEnabled(true);
        mPullLoadMore.setRefreshing(true);
        mPullLoadMore.setOnPullLoadMoreListener(this);
        mPullLoadMore.setPullLoadMoreCompleted();
        initdata();
        mAdapter = new RPNoteAdapter(this,mList);
        mPullLoadMore.setAdapter(mAdapter);
    }

    private void initdata() {
        for (int i = 0; i <5 ; i++) {
            mList.add(new RedPNote("张三","18.00","2017-12-21","0"));
        }
        mList.add(new RedPNote("张三","18.00","2017-12-21","1"));
    }

    @OnClick(R.id.imgBack)
    public void onClick() {
        finish();
    }

    @Override
    public void onRefresh() {
        mList.clear();
        for (int i = 0; i <4 ; i++) {
            mList.add(new RedPNote("王五","18.18","2017-12-21","1"));
        }
        mAdapter.notifyDataSetChanged();
        mPullLoadMore.setPullLoadMoreCompleted();
    }

    @Override
    public void onLoadMore() {
        for (int i = 0; i <4 ; i++) {
            mList.add(new RedPNote("王五","18.18","2017-12-21","1"));
        }
        mAdapter.notifyDataSetChanged();
        mPullLoadMore.setPullLoadMoreCompleted();
    }
}
