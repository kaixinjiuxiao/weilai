package com.wlyilai.weilaibao.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.google.gson.Gson;
import com.wlyilai.weilaibao.R;
import com.wlyilai.weilaibao.entry.OrderDetails;
import com.wlyilai.weilaibao.utils.Constant;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import org.json.JSONException;
import org.json.JSONObject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import okhttp3.Call;

/**
 * @author: captain
 * Time:  2017/12/13 0013
 * Describe:
 */
public class OrderDetailsActivity extends BaseActivity {
    @BindView(R.id.imgBack)
    ImageView mImgBack;
    @BindView(R.id.txtTitle)
    TextView mTxtTitle;
    @BindView(R.id.payState)
    TextView mPayState;
    @BindView(R.id.payNumber)
    TextView mPayNumber;
    @BindView(R.id.img1)
    ImageView mImg1;
    @BindView(R.id.info)
    TextView mInfo;
    @BindView(R.id.address)
    TextView mAddress;
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
    @BindView(R.id.orderCode)
    TextView mOrderCode;
    @BindView(R.id.createTime)
    TextView mCreateTime;
    @BindView(R.id.payTime)
    TextView mPayTime;
    @BindView(R.id.linearPayTime)
    LinearLayout mLinearPayTime;
    @BindView(R.id.txtOperation)
    TextView mTxtOperation;
    private String mOSN;
    private String mToken;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_details);
        ButterKnife.bind(this);
        init();
        getData();
    }

    private void init() {
        mImgBack.setVisibility(View.VISIBLE);
        mTxtTitle.setText("订单详情");
        mPayState.getPaint().setFakeBoldText(true);
        mOSN = getIntent().getStringExtra("osn");
        mToken = getIntent().getStringExtra("token");
    }

    private void getData() {
        OkHttpUtils.post().url(Constant.ORDER_DETAILS)
                .addParams("access_token", "02c8b29f1b09833e43a37c770a87db23")
                .addParams("osn", mOSN)
                .build().execute(new StringCallback() {
            @Override
            public void onError(Call call, Exception e, int id) {
                Log.e("tag", "错误" + e.toString());
            }

            @Override
            public void onResponse(String response, int id) {
                Log.e("tag", "详情" + response);
                try {
                    JSONObject json = new JSONObject(response);
                    if(json.getInt("status")==1){
                        OrderDetails details = new Gson().fromJson(response,OrderDetails.class);
                        if(details.getData().getTrade().equals("1")){
                            mPayState.setText("待发货");
                        }else if(details.getData().getTrade().equals("3")){
                            mPayState.setText("已收货");
                        }else if(details.getData().getTrade().equals("2")){
                            mPayState.setText("待收货");
                        }
                        mPayNumber.setText("订单金额（含运费）：¥"+details.getData().getPay_price());
                        mInfo.setText(details.getData().getRealname()+" "+details.getData().getMobile());
                        mAddress.setText(details.getData().getProvince()+details.getData().getCity()+details.getData().getCountry()+details.getData().getAddress());
                        Glide.with(OrderDetailsActivity.this).load(details.getData().getGimg()).into(mImgGoods);
                        mGoodsName.setText(details.getData().getGname());
                        mGoodsPrice.setText("¥"+details.getData().getGteam_price()+"/件");
                        mGoodsNumber.setText("x"+details.getData().getPay_num());
                        mGoodsTotalNumber.setText(details.getData().getPay_num());
                        mGoodsTotalPrice.setText(details.getData().getPay_price());
                        mOrderCode.setText(details.getData().getOsn());
                        mCreateTime.setText(details.getData().getAddtime());
                        mPayTime.setText(details.getData().getPaytime());
                    }else{

                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    @OnClick({R.id.txtOperation,R.id.imgBack})
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.imgBack:
                finish();
                break;
            case R.id.txtOperation:
                break;

        }
    }


}
