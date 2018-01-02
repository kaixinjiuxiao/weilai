package com.wlyilai.weilaibao.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.wlyilai.weilaibao.R;
import com.wlyilai.weilaibao.utils.Constant;
import com.wlyilai.weilaibao.utils.NetWorkState;
import com.wlyilai.weilaibao.utils.PreferenceUtil;
import com.wlyilai.weilaibao.utils.ToastUtils;
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
public class PaySuccessActivity extends BaseActivity {
    @BindView(R.id.imgBack)
    ImageView mImgBack;
    @BindView(R.id.txtTitle)
    TextView mTxtTitle;
    @BindView(R.id.money)
    TextView mMoney;
    @BindView(R.id.time)
    TextView mTime;
    @BindView(R.id.mode)
    TextView mMode;
    @BindView(R.id.numberin)
    TextView mNumberin;
    private String mOrderCode,mToken;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pay_success);
        ButterKnife.bind(this);
        init();
    }

    private void init() {
        PreferenceUtil.init(this);
        mImgBack.setVisibility(View.VISIBLE);
        mTxtTitle.setText("交易详情");
        mOrderCode =getIntent().getStringExtra("order");
        if(TextUtils.isEmpty(mOrderCode)){
            mOrderCode=  PreferenceUtil.getString("order","null");
        }
        mToken = getIntent().getStringExtra("token");
        if(TextUtils.isEmpty(mToken)){
            mToken = PreferenceUtil.getString("token",null);
        }
        if(NetWorkState.isNetWorkAvailabe(PaySuccessActivity.this)){

            getData(mOrderCode);
        }else{
            ToastUtils.showShort(PaySuccessActivity.this,"当前网络不可用，请检查网络连接");
        }
    }

    @OnClick(R.id.imgBack)
    public void onClick() {
        finish();
    }

    private void getData(String orderCode){
        OkHttpUtils.post().url(Constant.ORDER_SUCCESS)
                .addParams("access_token", mToken)
                .addParams("osn",orderCode)
                .build().execute(new StringCallback() {
            @Override
            public void onError(Call call, Exception e, int id) {
            }

            @Override
            public void onResponse(String response, int id) {
                try {
                    JSONObject jsonObject = new JSONObject(response);
                    if(jsonObject.getInt("status")==1){
                        JSONObject data = jsonObject.getJSONObject("data");
                        mMoney.setText("¥"+data.getString("pay_price"));
                        mTime.setText(data.getString("paytime"));
                        mMode.setText(data.getString("out_trade_type"));
                        mNumberin.setText(data.getString("osn"));
                    }else{
                        ToastUtils.showShort(PaySuccessActivity.this,jsonObject.getString("msg"));
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        });
    }
}
