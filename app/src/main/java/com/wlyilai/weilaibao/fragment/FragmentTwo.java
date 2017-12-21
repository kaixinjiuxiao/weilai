package com.wlyilai.weilaibao.fragment;

import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.wlyilai.weilaibao.R;
import com.wlyilai.weilaibao.adapter.GoodsAdapter;
import com.wlyilai.weilaibao.adapter.TypeAdapter;
import com.wlyilai.weilaibao.entry.CateInfo;
import com.wlyilai.weilaibao.entry.Goods;
import com.wlyilai.weilaibao.utils.Constant;
import com.wlyilai.weilaibao.utils.ToastUtils;
import com.wlyilai.weilaibao.view.DividerGridItemDecoration;
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
public class FragmentTwo extends BaseFagment implements PullLoadMoreRecyclerView.PullLoadMoreListener {
        private String id;
        private int page=1;
        private View mView;
        private RecyclerView mRecyclerView;
        private TypeAdapter mAdapter;
        private List<CateInfo.DataBean> mList= new ArrayList<>();
        private List<CateInfo.DataBean> mList2= new ArrayList<>();
       private PullLoadMoreRecyclerView mPullLoadMore;
        private GoodsAdapter mGoodsAdapter;
        private RecyclerView mRecyclerViewGoods;
        private List<Goods.DataBean> mListGoods = new ArrayList<>();
        private boolean itemClick;
        private TextView txt;

        @Nullable
        @Override
        public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
            if(mView==null){
                mView =inflater.inflate(R.layout.fragment_ping_details,container,false);
                Bundle bundle = getArguments();
                if(bundle!=null){
                    id = bundle.getString("id");
                }
                initView();
                initEvent();
            }
            return mView;
        }

        private void initView() {
            mRecyclerView = (RecyclerView) mView.findViewById(R.id.recyclerTwo);
            mRecyclerView.setLayoutManager(new GridLayoutManager(getActivity(),5));
            mRecyclerView.addItemDecoration(new DividerGridItemDecoration(getActivity(),2,0XFFD2d2d2));
            txt = (TextView) mView.findViewById(R.id.txt);
            getCate();
            mAdapter = new TypeAdapter(getActivity(),mList);
            mRecyclerView.setAdapter(mAdapter);
            mPullLoadMore = (PullLoadMoreRecyclerView)mView.findViewById(R.id.pullLoadMore);
            mRecyclerViewGoods = mPullLoadMore.getRecyclerView();
            mRecyclerViewGoods.setVerticalScrollBarEnabled(true);
            mPullLoadMore.setRefreshing(true);
            mPullLoadMore.setIsLoadMore(false);
            mPullLoadMore.setLinearLayout();
            mPullLoadMore.setOnPullLoadMoreListener(this);
            mGoodsAdapter = new GoodsAdapter(getActivity(), mListGoods);
            getGoods(page,id);
        }

        private void initEvent() {
            mAdapter.setItemClickListener(new TypeAdapter.OnItemClickListener() {
                @Override
                public void onIntemClick(int position) {
                    itemClick =true;
                    mListGoods.clear();
                    getGoodsDetails(page,mList.get(position).getId());
                }
            });
        }

    private void getGoodsDetails(final int page, String id) {
        OkHttpUtils.post().url(Constant.GET_GOODS)
                .addParams("access_token","02c8b29f1b09833e43a37c770a87db23")
                .addParams("gcid",id)
                .addParams("page",String.valueOf(page))
                .build().execute(new StringCallback() {
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
                            txt.setVisibility(View.VISIBLE);
                        } else {
                            if (page == 1) {
                                for (int i = 0; i < goods.getData().size(); i++) {
                                    mListGoods.add(goods.getData().get(i));
                                }
                                mPullLoadMore.setAdapter(mGoodsAdapter);
                                mPullLoadMore.setPullLoadMoreCompleted();
                            } else {
                                for (int i = 0; i < goods.getData().size(); i++) {
                                    mListGoods.add(goods.getData().get(i));
                                }
                                mGoodsAdapter.notifyDataSetChanged();
                                mPullLoadMore.setPullLoadMoreCompleted();
                            }
                            mGoodsAdapter.notifyDataSetChanged();
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

    private void getCate(){
            Log.e("tag","id=="+id);
            OkHttpUtils.post().url(Constant.CATE_INFO)
                    .addParams("access_token","02c8b29f1b09833e43a37c770a87db23")
                    .addParams("gcid",id).build().execute(new StringCallback() {
                @Override
                public void onError(Call call, Exception e, int id) {

                }

                @Override
                public void onResponse(String response, int id) {
                    try {
                        JSONObject jsonObject = new JSONObject(response);
                        if(jsonObject.getInt("status")==1){
                            CateInfo cateInfo = new Gson().fromJson(response,CateInfo.class);
                            for (int i = 0; i <cateInfo.getData().size() ; i++) {
                                mList.add(cateInfo.getData().get(i));
                            }
                            mAdapter.notifyDataSetChanged();
                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            });
        }

    private void getGoods(final int page,final String gcid) {
        OkHttpUtils.post().url(Constant.GET_GOODS)
                .addParams("access_token","02c8b29f1b09833e43a37c770a87db23")
                .addParams("gcid",gcid)
                .addParams("page",String.valueOf(page))
                .build().execute(new StringCallback() {
            @Override
            public void onError(Call call, Exception e, int id) {

            }

            @Override
            public void onResponse(String response, int id) {
                Log.e("tag","数据"+response);
                Goods goods = new Gson().fromJson(response,Goods.class);
                if(goods.getStatus()==1){
                    if (page == 1) {
                        for (int i = 0; i < goods.getData().size(); i++) {
                            mListGoods.add(goods.getData().get(i));
                        }
                        mPullLoadMore.setAdapter(mGoodsAdapter);
                        mPullLoadMore.setPullLoadMoreCompleted();
                    } else {
                        for (int i = 0; i < goods.getData().size(); i++) {
                            mListGoods.add(goods.getData().get(i));
                        }
                        mGoodsAdapter.notifyDataSetChanged();
                        mPullLoadMore.setPullLoadMoreCompleted();
                    }
                    mGoodsAdapter.notifyDataSetChanged();
                } else if (goods.getStatus() == 0) {
                    mPullLoadMore.setPullLoadMoreCompleted();
                    Toast.makeText(getActivity(), "无更多数据加载！", Toast.LENGTH_SHORT).show();
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
                mListGoods.clear();
                if (mAdapter != null) {
                    mAdapter.notifyDataSetChanged();
                }
                getGoods(1,id);
            }
        }, 2000);
    }

    @Override
    public void onLoadMore() {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                page++;
                getGoods(page,id);
                mPullLoadMore.setPullLoadMoreCompleted();
            }
        }, 2000);
    }
}
