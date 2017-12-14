package com.wlyilai.weilaibao.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;

import com.wlyilai.weilaibao.R;
import com.wlyilai.weilaibao.adapter.MyAddressAdapter;
import com.wlyilai.weilaibao.entry.Address;
import com.wlyilai.weilaibao.view.ListViewForScrollView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Author : Captain
 * Time : 2017/12/12
 * Describe :确认订单
 */

public class SureOrderActivity extends BaseActivity {

    @BindView(R.id.imgBack)
    ImageView mImgBack;
    @BindView(R.id.txtTitle)
    TextView mTxtTitle;
    @BindView(R.id.relativeAdd)
    RelativeLayout mRelativeAdd;
    @BindView(R.id.edtName)
    EditText mEdtName;
    @BindView(R.id.edtPhone)
    EditText mEdtPhone;
    @BindView(R.id.spnCountry)
    Spinner mSpnCountry;
    @BindView(R.id.spnCity)
    Spinner mSpnCity;
    @BindView(R.id.spnCounty)
    Spinner mSpnCounty;
    @BindView(R.id.edtAddress)
    EditText mEdtAddress;
    @BindView(R.id.sureAdd)
    Button mSureAdd;
    @BindView(R.id.linearAdd)
    LinearLayout mLinearAdd;
    @BindView(R.id.imgGoods)
    ImageView mImgGoods;
    @BindView(R.id.goodsName)
    TextView mGoodsName;
    @BindView(R.id.goodsPrice)
    TextView mGoodsPrice;
    @BindView(R.id.goodsNumber)
    TextView mGoodsNumber;
    @BindView(R.id.goodsTotalNumber)
    TextView mGoodsTotalNumber;
    @BindView(R.id.goodsTotalPrice)
    TextView mGoodsTotalPrice;
    @BindView(R.id.totalPrice)
    TextView mTotalPrice;
    @BindView(R.id.commitOrder)
    TextView mCommitOrder;
    @BindView(R.id.address)
    ListViewForScrollView mAddress;
   // private AddressAdapter mAdapter;
    private MyAddressAdapter mAdapter;
    private List<Address> mList =new ArrayList<>();
    private HashMap<Integer, Boolean> map = new HashMap<>();
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sure_order);
        ButterKnife.bind(this);
        init();
        initEvent();
    }

    private void init() {
        mImgBack.setVisibility(View.VISIBLE);
        mTxtTitle.setText("确认订单");
        initData();
        mAdapter = new MyAddressAdapter(this,mList);
        mAddress.setAdapter(mAdapter);
    }

    private void initEvent() {
        mAdapter.setDeleteListener(new MyAddressAdapter.OnDeleteListener() {
            @Override
            public void onDelete(int position) {
                mList.remove(position);
                mAdapter.notifyDataSetChanged();
            }
        });
        mAdapter.setChooiseListener(new MyAddressAdapter.OnChooiseListener() {
            @Override
            public void onChooise(int position) {
                mAdapter.notifyDataSetChanged();
            }
        });
    }

    private void initData() {
        for (int i = 0; i <3 ; i++) {
            mList.add(new Address("张三","15000000000","湖南省长沙市雨花区万家丽街道",0));
            map.put(i, false);
        }
    }
    @OnClick({R.id.imgBack, R.id.relativeAdd, R.id.sureAdd, R.id.commitOrder})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.imgBack:
                finish();
                break;
            case R.id.relativeAdd:
                mLinearAdd.setVisibility(View.VISIBLE);
                break;
            case R.id.sureAdd:
                mList.add(new Address("张三","15000000000","湖南省长沙市雨花区万家丽街道",0));
                map.put(mList.size(), false);
                mAdapter.notifyDataSetChanged();
                mLinearAdd.setVisibility(View.GONE);
                break;
            case R.id.commitOrder:
                Intent intent = new Intent(this,SurePayActivity.class);
                startActivity(intent);
                break;
        }
    }
}
