package com.wlyilai.weilaibao.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.wlyilai.weilaibao.R;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import okhttp3.Call;

/**
 * Author : Captain
 * Time : 2017/12/12
 * Describe :支付
 */

public class SurePayActivity extends BaseActivity {
    @BindView(R.id.imgBack)
    ImageView mImgBack;
    @BindView(R.id.txtTitle)
    TextView mTxtTitle;
    @BindView(R.id.orderCode)
    TextView mOrderCode;
    @BindView(R.id.orderPrice)
    TextView mOrderPrice;
    @BindView(R.id.linearALi)
    LinearLayout mLinearALi;
    @BindView(R.id.textBanlance)
    TextView mTextBanlance;
    @BindView(R.id.linearBalance)
    LinearLayout mLinearBalance;
    private PopupWindow popupWindow;
    private AlertDialog mDialog;
    private String orderCode;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sure_pay);
        ButterKnife.bind(this);
        init();
    }

    private void init() {
        mImgBack.setVisibility(View.VISIBLE);
        mTxtTitle.setText("收银台");
    }

    @OnClick({R.id.imgBack, R.id.linearALi, R.id.linearBalance})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.imgBack:
                finish();
                break;
            case R.id.linearALi:
                startPay("1");
                break;
            case R.id.linearBalance:
                displayDialog();
                break;
        }
    }
    private void displayDialog() {
        final AlertDialog.Builder builder =new AlertDialog.Builder(SurePayActivity.this);
        View view = LayoutInflater.from(SurePayActivity.this).inflate(R.layout.layout_sure_pay,null);
        builder.setView(view);

        TextView sure = (TextView) view.findViewById(R.id.sure);
        TextView cancel = (TextView) view.findViewById(R.id.cancel);
        sure.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startPay("2");
                Intent intent = new Intent(SurePayActivity.this,PaySuccessActivity.class);
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


   private void startPay(String type){
       Map<String ,String > parmas = new HashMap<>();
       parmas.put("access_token", "02c8b29f1b09833e43a37c770a87db23");
       parmas.put("osn", orderCode);
       parmas.put("out_trade_type", type);
       parmas.put("pay_source", "APP");
       OkHttpUtils.post().url("http://test.mgbh.wlylai.com/AppApi/pay_order").params(parmas)
               .build().execute(new StringCallback() {
           @Override
           public void onError(Call call, Exception e, int id) {

           }

           @Override
           public void onResponse(String response, int id) {

           }
       });
   }
}
