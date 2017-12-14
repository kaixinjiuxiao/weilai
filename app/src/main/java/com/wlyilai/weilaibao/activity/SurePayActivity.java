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

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

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
}
