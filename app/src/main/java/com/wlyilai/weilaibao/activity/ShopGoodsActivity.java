package com.wlyilai.weilaibao.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.gson.Gson;
import com.wlyilai.weilaibao.R;
import com.wlyilai.weilaibao.adapter.ShopAdapter;
import com.wlyilai.weilaibao.entry.Goods;
import com.wlyilai.weilaibao.utils.ToastUtils;
import com.wlyilai.weilaibao.view.GridSpacingItemDecoration;
import com.wlyilai.weilaibao.view.PullLoadMoreRecyclerView;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import okhttp3.Call;

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
    private List<Goods.DataBean> mList = new ArrayList<>();
    private String mSid;
    private int page = 1;
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
        mTxtTitle.setText(getIntent().getStringExtra("shopName"));
        mRecyclerView = mPullLoadMore.getRecyclerView();
        mSid = getIntent().getStringExtra("sid");
        mRecyclerView.setVerticalScrollBarEnabled(true);
        mPullLoadMore.setRefreshing(true);
        mPullLoadMore.setGridLayout(2);
        mRecyclerView.addItemDecoration(new GridSpacingItemDecoration(2,15,true));
        mPullLoadMore.setOnPullLoadMoreListener(this);
        mPullLoadMore.setPullLoadMoreCompleted();

        mAdapter = new ShopAdapter(ShopGoodsActivity.this, mList);
        //mPullLoadMore.setAdapter(mAdapter);
        getData(page);
    }

    private void initEvent() {
        mAdapter.setBuyListener(new ShopAdapter.OnBuyListener() {
            @Override
            public void onBuy(int position) {
                startActivity(new Intent(ShopGoodsActivity.this,GroupDetailsActivity.class));
            }
        });
    }

    private void getData(final int page){
        OkHttpUtils.post().url("http://test.mgbh.wlylai.com/AppApi/get_goods_list")
                .addParams("access_token", "02c8b29f1b09833e43a37c770a87db23")
                .addParams("sid", mSid)
                .addParams("page", String.valueOf(page)).build().execute(new StringCallback() {
            @Override
            public void onError(Call call, Exception e, int id) {

            }

            @Override
            public void onResponse(String response, int id) {
                try {
                    JSONObject jsonObject = new JSONObject(response);
                    if (jsonObject.getInt("status") == 1) {
                        Goods goods = new Gson().fromJson(response, Goods.class);
                        if (goods.getData().size() == 0) {
                            mPullLoadMore.setPullLoadMoreCompleted();
                           // mTxt.setVisibility(View.VISIBLE);
                        } else {
                            if (page == 1) {
                                for (int i = 0; i < goods.getData().size(); i++) {
                                    mList.add(goods.getData().get(i));
                                }
                                mPullLoadMore.setAdapter(mAdapter);
                                mPullLoadMore.setPullLoadMoreCompleted();
                            } else {
                                for (int i = 0; i < goods.getData().size(); i++) {
                                    mList.add(goods.getData().get(i));
                                }
                                mAdapter.notifyDataSetChanged();
                                mPullLoadMore.setPullLoadMoreCompleted();
                            }
                            mAdapter.notifyDataSetChanged();
                        }
                    } else {
                        ToastUtils.showShort(ShopGoodsActivity.this, jsonObject.getString("msg"));
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });
    }


    @OnClick(R.id.imgBack)
    public void onClick() {
        finish();
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
                getData(1);
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
                getData(page);
                mPullLoadMore.setPullLoadMoreCompleted();
            }
        }, 2000);
    }
}
