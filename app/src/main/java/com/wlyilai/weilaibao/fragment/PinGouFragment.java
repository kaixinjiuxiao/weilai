package com.wlyilai.weilaibao.fragment;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.gson.Gson;
import com.wlyilai.weilaibao.R;
import com.wlyilai.weilaibao.activity.GroupDetailsActivity;
import com.wlyilai.weilaibao.activity.MyGroupActivity;
import com.wlyilai.weilaibao.adapter.PinGouAdapter;
import com.wlyilai.weilaibao.entry.FirstEvent;
import com.wlyilai.weilaibao.entry.Goods;
import com.wlyilai.weilaibao.entry.TypeAndBanner;
import com.wlyilai.weilaibao.utils.Constant;
import com.wlyilai.weilaibao.view.PullLoadMoreRecyclerView;
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;
import com.youth.banner.loader.ImageLoader;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import okhttp3.Call;

/**
 * @author: captain
 * Time:  2017/12/9 0009
 * Describe:
 */
public class PinGouFragment extends BaseFagment implements PullLoadMoreRecyclerView.PullLoadMoreListener {
    @BindView(R.id.coustom)
    RelativeLayout mCoustom;
    @BindView(R.id.shopTuan)
    RelativeLayout mShopTuan;
    @BindView(R.id.myTuan)
    RelativeLayout mMyTuan;
    @BindView(R.id.userCenter)
    RelativeLayout mUserCenter;
    @BindView(R.id.pullLoadMore)
    PullLoadMoreRecyclerView mPullLoadMore;
    Unbinder unbinder;
    private View mView;
    private Unbinder mUnbinder;
    private PinGouAdapter mAdapter;
    private RecyclerView mRecyclerView;
    private List<Goods.DataBean> mList = new ArrayList<>();
    private AlertDialog mDialog;
    private Banner mMZBannerView;
    private List<TypeAndBanner.DataBean.BannerBean>mListString = new ArrayList<>();
    private int page = 1;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        if (mView == null) {
            mView = inflater.inflate(R.layout.fragment_pingou, container, false);
            mUnbinder = ButterKnife.bind(this, mView);
          //  EventBus.getDefault().register(this);
            initView();
            initEvent();
        }
        unbinder = ButterKnife.bind(this, mView);
        return mView;
    }

    private void initView() {
        mRecyclerView = mPullLoadMore.getRecyclerView();
        mRecyclerView.setVerticalScrollBarEnabled(true);
        mPullLoadMore.setRefreshing(true);
        mPullLoadMore.setIsLoadMore(false);
        mPullLoadMore.setLinearLayout();
        mPullLoadMore.setOnPullLoadMoreListener(this);
        mPullLoadMore.setPullLoadMoreCompleted();
        mMZBannerView = (Banner) mView.findViewById(R.id.banber);
        mAdapter = new PinGouAdapter(getActivity(), mList);
        getBanner();
        getGoods(page);
    }

    public class GlideImageLoader extends ImageLoader {

        @Override
        public void displayImage(Context context, Object path, ImageView imageView) {
           TypeAndBanner.DataBean.BannerBean banner = (TypeAndBanner.DataBean.BannerBean) path;
            Glide.with(context).load(banner.getImg()).into(imageView);
        }
    }
    private void initEvent() {
        mAdapter.setBuyListener(new PinGouAdapter.OnBuyListener() {
            @Override
            public void onBuy(int position) {
               // displayDialog();
                Intent intent = new Intent(getActivity(),GroupDetailsActivity.class);
                intent.putExtra("id",mList.get(position).getId());
                startActivity(intent);
            }
        });
    }

    private void displayDialog() {
        final AlertDialog.Builder builder =new AlertDialog.Builder(getActivity());
        View view = LayoutInflater.from(getActivity()).inflate(R.layout.layout_prompt,null);
        builder.setView(view);

        TextView sure = (TextView) view.findViewById(R.id.sure);
        TextView cancel = (TextView) view.findViewById(R.id.cancel);
        sure.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(),GroupDetailsActivity.class);
                startActivity(intent);
                mDialog.dismiss();
            }
        });
        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mDialog.dismiss();
            }
        });
        mDialog = builder.create();
        mDialog.show();
    }

    private void getGoods(final int page) {
        OkHttpUtils.post().url(Constant.HOME_GOODS)
                .addParams("access_token","02c8b29f1b09833e43a37c770a87db23")
                .addParams("page",String.valueOf(page))
                .build().execute(new StringCallback() {
            @Override
            public void onError(Call call, Exception e, int id) {

            }

            @Override
            public void onResponse(String response, int id) {
                Goods goods = new Gson().fromJson(response,Goods.class);
                if(goods.getStatus()==1){
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
                } else if (goods.getStatus() == 0) {
                    mPullLoadMore.setPullLoadMoreCompleted();
                    Toast.makeText(getActivity(), "无更多数据加载！", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }


    private void getBanner(){
        OkHttpUtils.post().url(Constant.TYPE_BANNER)
                .addParams("access_token","02c8b29f1b09833e43a37c770a87db23")
                .build().execute(new StringCallback() {
            @Override
            public void onError(Call call, Exception e, int id) {

            }

            @Override
            public void onResponse(String response, int id) {
                TypeAndBanner banner = new Gson().fromJson(response,TypeAndBanner.class);
                if(banner.getStatus()==1){
                    mListString = banner.getData().getBanner();
                    mMZBannerView.setImageLoader(new GlideImageLoader());
                    mMZBannerView.setIndicatorGravity(BannerConfig.RIGHT);
                    mMZBannerView.setImages(mListString);
                    mMZBannerView.start();
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
                getGoods(1);
            }
        }, 2000);
    }

    @Override
    public void onLoadMore() {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                page++;
                getGoods(page);
                mPullLoadMore.setPullLoadMoreCompleted();
            }
        }, 2000);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
       // mUnbinder.unbind();
    }

    @OnClick({R.id.coustom, R.id.shopTuan, R.id.myTuan, R.id.userCenter})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.coustom:
                break;
            case R.id.shopTuan:
                break;
            case R.id.myTuan:
                Intent intent = new Intent(getActivity(),MyGroupActivity.class);
                startActivity(intent);
                break;
            case R.id.userCenter:
                EventBus.getDefault().post(new FirstEvent("ok"));
                break;

        }
    }
}
