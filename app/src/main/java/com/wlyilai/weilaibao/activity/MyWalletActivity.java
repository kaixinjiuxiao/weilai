package com.wlyilai.weilaibao.activity;

import android.content.Intent;
import android.graphics.Paint;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.wlyilai.weilaibao.R;
import com.wlyilai.weilaibao.utils.ToastUtils;
import com.wlyilai.weilaibao.view.NumberRunningTextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * @author: captain
 * Time:  2017/12/16 0016
 * Describe:
 */
public class MyWalletActivity extends BaseActivity {


    @BindView(R.id.imgBack)
    ImageView mImgBack;
    @BindView(R.id.txtTitle)
    TextView mTxtTitle;
    @BindView(R.id.totalYE)
    NumberRunningTextView mTotalYE;
    @BindView(R.id.totalXF)
    NumberRunningTextView mTotalXF;
    @BindView(R.id.totalCZ)
    NumberRunningTextView mTotalCZ;
    @BindView(R.id.totalJF)
    TextView mTotalJF;
    @BindView(R.id.banlanceDetails)
    RelativeLayout mBanlanceDetails;
    @BindView(R.id.redPackDetails)
    RelativeLayout mRedPackDetails;
    @BindView(R.id.integerDetails)
    RelativeLayout mIntegerDetails;
    @BindView(R.id.details)
    TextView mDetails;
    @BindView(R.id.exchange)
    TextView mExchange;
    @BindView(R.id.recharge)
    TextView mRecharge;
    @BindView(R.id.withDrawals)
    TextView mWithDrawals;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_wallet);
        ButterKnife.bind(this);
        init();
    }

    private void init() {
        mImgBack.setVisibility(View.VISIBLE);
        mTxtTitle.setText("我的钱包");
        mExchange.getPaint().setFlags(Paint.UNDERLINE_TEXT_FLAG);
        mExchange.setTextColor(getResources().getColor(R.color.ping_gou));
    }


    @OnClick({R.id.imgBack, R.id.banlanceDetails, R.id.redPackDetails, R.id.integerDetails,R.id.exchange, R.id.recharge, R.id.withDrawals})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.imgBack:
                finish();
                break;
            case R.id.banlanceDetails:
                break;
            case R.id.redPackDetails:
                Intent intent = new Intent(MyWalletActivity.this, RedPackageNoteActivity.class);
                startActivity(intent);
                break;
            case R.id.integerDetails:
                ToastUtils.showShort(this,"我惦记了积分详情");
                break;
            case R.id.exchange:
                ToastUtils.showShort(this,"我点击了换余额");
                break;
            case R.id.recharge:
                startActivity(new Intent(MyWalletActivity.this, RechargeActivity.class));
                break;
            case R.id.withDrawals:
                break;
        }
    }


}
