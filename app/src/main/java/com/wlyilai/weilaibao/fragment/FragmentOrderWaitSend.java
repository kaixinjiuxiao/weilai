package com.wlyilai.weilaibao.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.google.gson.Gson;
import com.wlyilai.weilaibao.R;
import com.wlyilai.weilaibao.activity.LoginActivity;
import com.wlyilai.weilaibao.activity.OrderDetailsActivity;
import com.wlyilai.weilaibao.adapter.OrderAdapter;
import com.wlyilai.weilaibao.entry.Order;
import com.wlyilai.weilaibao.utils.Constant;
import com.wlyilai.weilaibao.utils.PreferenceUtil;
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
 * Time:  2017/12/11 0011
 * Describe:
 */
public class FragmentOrderWaitSend extends BaseFagment implements PullLoadMoreRecyclerView.PullLoadMoreListener {
    private static final String type = "1";
    private View mView;
    private OrderAdapter mAdapter;
    private PullLoadMoreRecyclerView mPullLoadMore;
    private RecyclerView mRecyclerView;
    private List<Order.DataBean> mList = new ArrayList<>();
    private int page = 1;
    private TextView mTxt;
    private String mToken;
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
        mTxt = (TextView) mView.findViewById(R.id.noMore);
        mPullLoadMore = (PullLoadMoreRecyclerView)mView.findViewById(R.id.pullLoadMore);
        mRecyclerView = mPullLoadMore.getRecyclerView();
        mRecyclerView.setVerticalScrollBarEnabled(true);
        mPullLoadMore.setRefreshing(true);
        mPullLoadMore.setLinearLayout();
        mPullLoadMore.setOnPullLoadMoreListener(this);
        mPullLoadMore.setPullLoadMoreCompleted();
        mAdapter = new OrderAdapter(getActivity(), mList);
        mToken = PreferenceUtil.getString("token",null);
        if(TextUtils.isEmpty(mToken)){
            PreferenceUtil.removeAll();
            Intent intent = new Intent(getActivity(), LoginActivity.class);
            startActivity(intent);
            getActivity().finish();
        }
        getOrder(type,page);
    }

    private void initEvent() {
        mAdapter.setLookDetailsListener(new OrderAdapter.OnLookDetailsListener() {
            @Override
            public void lookDetails(int position) {
                Intent intent = new Intent(getActivity(),OrderDetailsActivity.class);
                intent.putExtra("osn",mList.get(position).getOsn());
                intent.putExtra("token",mToken);
                startActivity(intent);
            }
        });
    }

    private void getOrder(String state, final int page) {
        OkHttpUtils.post().url(Constant.MY_ORDER)
                .addParams("access_token", mToken)
                .addParams("state", state)
                .addParams("page", String.valueOf(page)).build().execute(new StringCallback() {
            @Override
            public void onError(Call call, Exception e, int id) {
                Log.e("tag", "订单错误" + e.toString());
            }

            @Override
            public void onResponse(String response, int id) {
                Log.e("tag", "所有订单---all" + response);
                try {
                    JSONObject jsonObject = new JSONObject(response);
                    if (jsonObject.getInt("status") == 1) {
                        Order order = new Gson().fromJson(response, Order.class);
                        if (order.getData().size() == 0) {
                            mPullLoadMore.setPullLoadMoreCompleted();
                            mTxt.setVisibility(View.VISIBLE);
                        } else {
                            if (page == 1) {
                                for (int i = 0; i < order.getData().size(); i++) {
                                    mList.add(order.getData().get(i));
                                }
                                mPullLoadMore.setAdapter(mAdapter);
                                mPullLoadMore.setPullLoadMoreCompleted();
                            } else {
                                for (int i = 0; i < order.getData().size(); i++) {
                                    mList.add(order.getData().get(i));
                                }
                                mAdapter.notifyDataSetChanged();
                                mPullLoadMore.setPullLoadMoreCompleted();
                            }
                            mAdapter.notifyDataSetChanged();
                        }
                    } else {
                        ToastUtils.showShort(getActivity(), jsonObject.getString("msg"));
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
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
                getOrder(type,1);
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
                getOrder(type,page);
                mPullLoadMore.setPullLoadMoreCompleted();
            }
        }, 2000);
    }
}
