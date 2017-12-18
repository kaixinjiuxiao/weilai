package com.wlyilai.weilaibao.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.wlyilai.weilaibao.R;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

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

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pay_success);
        ButterKnife.bind(this);
        init();
    }

    private void init() {
        mImgBack.setVisibility(View.VISIBLE);
        mTxtTitle.setText("交易详情");
//        Bundle bundle = getIntent().getBundleExtra("pay");
//        PayResult result = (PayResult) bundle.getSerializable("result");
//        mMoney.setText(result.getData().getPay_price());
         getData();
    }

    @OnClick(R.id.imgBack)
    public void onClick() {
        finish();
    }

    private void getData(){
        OkHttpUtils.post().url("http://test.mgbh.wlylai.com/AppApi/get_order_success")
                .addParams("access_token", "02c8b29f1b09833e43a37c770a87db23")
                .addParams("osn","62017121811471271828")
                .build().execute(new StringCallback() {
            @Override
            public void onError(Call call, Exception e, int id) {
                Log.e("tag","支付成功"+e.toString());
            }

            @Override
            public void onResponse(String response, int id) {
                Log.e("tag","支付成功"+response);
            }
        });
    }
}
