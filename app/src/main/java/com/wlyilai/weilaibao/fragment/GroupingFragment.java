package com.wlyilai.weilaibao.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.gson.Gson;
import com.wlyilai.weilaibao.R;
import com.wlyilai.weilaibao.activity.GroupPurchaseDetailsActivity;
import com.wlyilai.weilaibao.adapter.GroupingAdapter;
import com.wlyilai.weilaibao.entry.MyGroup;
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
 * Describe:正在团
 */
public class GroupingFragment extends BaseFagment implements PullLoadMoreRecyclerView.PullLoadMoreListener {
    private View mView;
    private GroupingAdapter mAdapter;
    private PullLoadMoreRecyclerView mPullLoadMore;
    private RecyclerView mRecyclerView;
    private int page = 1;
    private List<MyGroup.DataBean> mList = new ArrayList<>();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        if (mView == null) {
            mView = inflater.inflate(R.layout.fragment_mygroup_common, container, false);
            initView();
            getData("0", 1);
            initEvent();

        }
        return mView;
    }

    private void initView() {
        mPullLoadMore = (PullLoadMoreRecyclerView) mView.findViewById(R.id.pullLoadMore);
        mRecyclerView = mPullLoadMore.getRecyclerView();
        mRecyclerView.setVerticalScrollBarEnabled(true);
        mPullLoadMore.setRefreshing(true);
        mPullLoadMore.setLinearLayout();
        mPullLoadMore.setOnPullLoadMoreListener(this);
        mAdapter = new GroupingAdapter(getActivity(), mList);
    }

    private void initEvent() {
        mAdapter.setLookDetailsListener(new GroupingAdapter.OnLookDetailsListener() {
            @Override
            public void lookDetails(int position) {
                Intent intent = new Intent(getActivity(),GroupPurchaseDetailsActivity.class);
                String grid = mList.get(position).getGrid();
                intent.putExtra("grid",grid);
                startActivity(intent);
            }
        });
    }

    @Override
    public void onRefresh() {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                page = 1;
                mList.clear();
                if (mAdapter != null) {
                    mAdapter.notifyDataSetChanged();
                }
                getData("0",1);
                // mPullLoadMoreRecyclerView.setPullLoadMoreCompleted();
            }
        }, 2000);
    }

    @Override
    public void onLoadMore() {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                page++;
                getData("0",page);
                mPullLoadMore.setPullLoadMoreCompleted();
            }
        }, 2000);
    }

    private void getData(String state, final int page) {
        OkHttpUtils.post().url("http://test.mgbh.wlylai.com/AppApi/get_group_order").
                addParams("access_token", "02c8b29f1b09833e43a37c770a87db23")
                .addParams("state", state)
                .addParams("page", String.valueOf(page))
                .build().execute(new StringCallback() {
            @Override
            public void onError(Call call, Exception e, int id) {
                Log.e("tag","信息"+e.toString());
            }

            @Override
            public void onResponse(String response, int id) {
                Log.e("tag","信息"+response);
                try {
                    JSONObject jsonObject = new JSONObject(response);
                    if (jsonObject.getInt("status") == 1) {
                        MyGroup group = new Gson().fromJson(response, MyGroup.class);
                        if (page == 1) {
                            for (int i = 0; i < group.getData().size(); i++) {
                                mList.add(group.getData().get(i));
                            }
                            mPullLoadMore.setAdapter(mAdapter);
                            mPullLoadMore.setPullLoadMoreCompleted();
                        } else {
                            for (int i = 0; i < group.getData().size(); i++) {
                                mList.add(group.getData().get(i));
                            }
                            mAdapter.notifyDataSetChanged();
                            mPullLoadMore.setPullLoadMoreCompleted();
                        }
                        mAdapter.notifyDataSetChanged();
                    } else {

                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });
    }
}
