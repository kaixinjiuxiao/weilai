package com.wlyilai.weilaibao.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.google.gson.Gson;
import com.wlyilai.weilaibao.R;
import com.wlyilai.weilaibao.adapter.JoinGroupAdapter;
import com.wlyilai.weilaibao.entry.GroupDetails;
import com.wlyilai.weilaibao.utils.ToastUtils;
import com.wlyilai.weilaibao.view.PullLoadMoreRecyclerView;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import okhttp3.Call;

/**
 * @author: captain
 * Time:  2017/12/12 0012
 * Describe:
 */
public class GroupPurchaseDetailsActivity extends BaseActivity implements PullLoadMoreRecyclerView.PullLoadMoreListener, View.OnClickListener {
    private ProgressBar progress;
    private ImageView mBack;
    private TextView mTitle, mState, mShare;
    private PullLoadMoreRecyclerView mPullLoadMoreRecyclerView;
    private JoinGroupAdapter mAdapter;
    private RecyclerView mRecyclerView;
    private List<GroupDetails.DataBean.OrderDetailBean> mList = new ArrayList<>();
    private int page = 1;
    private String mGrid;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_group_purchase_details);
        initView();
        initEvent();
    }

    private void initView() {
        mBack = (ImageView) findViewById(R.id.imgBack);
        mBack.setVisibility(View.VISIBLE);
        mTitle = (TextView) findViewById(R.id.txtTitle);
        mState = (TextView) findViewById(R.id.state);
        mShare = (TextView) findViewById(R.id.txtShare);
        mTitle.setText("团详情");
        mGrid = getIntent().getStringExtra("grid");
        getDetails(mGrid);
        progress = (ProgressBar) findViewById(R.id.progress);
        progress.setMax(10);
        progress.setSecondaryProgress(8);
        mPullLoadMoreRecyclerView = (PullLoadMoreRecyclerView) findViewById(R.id.pullLoadMore);
        mRecyclerView = mPullLoadMoreRecyclerView.getRecyclerView();
        mRecyclerView.setVerticalScrollBarEnabled(true);
        mPullLoadMoreRecyclerView.setRefreshing(true);
        mPullLoadMoreRecyclerView.setLinearLayout();
        mPullLoadMoreRecyclerView.setOnPullLoadMoreListener(this);
        mAdapter = new JoinGroupAdapter(this, mList);
    }

    private void initEvent() {
        mBack.setOnClickListener(this);
        mShare.setOnClickListener(this);
    }

    private void getDetails(String grid) {
        OkHttpUtils.post().url("http://test.mgbh.wlylai.com/AppApi/get_group_detail").
                addParams("access_token", "02c8b29f1b09833e43a37c770a87db23")
                .addParams("grid", grid).build().execute(new StringCallback() {
            @Override
            public void onError(Call call, Exception e, int id) {

            }

            @Override
            public void onResponse(String response, int id) {
                try {
                    JSONObject json = new JSONObject(response);
                    if (json.getInt("status") == 1) {
                        GroupDetails details = new Gson().fromJson(response, GroupDetails.class);
                        mState.setText("已团" + details.getData().getGnum() + "件，还剩" + details.getData().getGpay_num() + "件");
                        progress.setMax(Integer.parseInt(details.getData().getGnum()));
                        progress.setProgress(Integer.parseInt(details.getData().getGpay_num()));
                        for (int i = 0; i < details.getData().getOrder_detail().size(); i++) {
                            mList.add(details.getData().getOrder_detail().get(i));
                        }
                        mPullLoadMoreRecyclerView.setAdapter(mAdapter);
                        mPullLoadMoreRecyclerView.setPullLoadMoreCompleted();
                    } else {
                        ToastUtils.showShort(GroupPurchaseDetailsActivity.this, json.getString("msg"));
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });
    }


    @Override
    public void onRefresh() {
        mPullLoadMoreRecyclerView.setPullLoadMoreCompleted();
    }

    @Override
    public void onLoadMore() {
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.imgBack:
                finish();
                break;
            case R.id.txtShare:
                finish();
                break;
            default:
                break;
        }
    }

}
