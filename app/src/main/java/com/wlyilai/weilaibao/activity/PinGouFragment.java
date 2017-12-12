package com.wlyilai.weilaibao.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.wlyilai.weilaibao.R;
import com.wlyilai.weilaibao.adapter.PinGouAdapter;
import com.wlyilai.weilaibao.entry.FirstEvent;
import com.wlyilai.weilaibao.entry.Goods;
import com.wlyilai.weilaibao.view.PullLoadMoreRecyclerView;
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;
import com.youth.banner.loader.ImageLoader;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

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
    private List<Goods> mList = new ArrayList<>();
    private AlertDialog mDialog;
    private Banner mMZBannerView;
    private List<String >mListString = new ArrayList<>();

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
        initData();
        mAdapter = new PinGouAdapter(getActivity(), mList);
        mPullLoadMore.setAdapter(mAdapter);
        mMZBannerView = (Banner) mView.findViewById(R.id.banber);
        mListString.add("http://img1.3lian.com/2015/a1/95/d/105.jpg");
        mListString.add("http://img.taopic.com/uploads/allimg/130331/240460-13033106243430.jpg");
        mListString.add("http://img1.3lian.com/2015/a1/46/d/198.jpg");
       // setPages(mListString);
        //设置banner样式
        //设置图片加载器
        mMZBannerView.setImageLoader(new GlideImageLoader());
        //设置图片集合
        mMZBannerView.setImages(mListString);
        mMZBannerView.setIndicatorGravity(BannerConfig.RIGHT);
        //banner设置方法全部调用完毕时最后调用
        mMZBannerView.start();
    }

    public class GlideImageLoader extends ImageLoader {
        @Override
        public void displayImage(Context context, Object path, ImageView imageView) {
            Glide.with(context).load(path).into(imageView);
        }
    }

    private void initEvent() {
        mAdapter.setBuyListener(new PinGouAdapter.OnBuyListener() {
            @Override
            public void onBuy(int position) {
                displayDialog();
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
    private void initData() {
        for (int i = 0; i < 10; i++) {
            mList.add(new Goods("", "52度浓香型白酒公司宴典高度酒 500ml", "￥22.4", "10人团", "￥18.9/件"));
        }
    }

    @Override
    public void onRefresh() {

    }

    @Override
    public void onLoadMore() {

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        Log.e("tag","这里执行了？");
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
