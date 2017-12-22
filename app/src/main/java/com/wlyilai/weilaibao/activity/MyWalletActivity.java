package com.wlyilai.weilaibao.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.wlyilai.weilaibao.R;
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
    }


    @OnClick({R.id.imgBack, R.id.banlanceDetails, R.id.redPackDetails, R.id.integerDetails})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.imgBack:
                break;
            case R.id.banlanceDetails:
                break;
            case R.id.redPackDetails:
                break;
            case R.id.integerDetails:
                break;
        }
    }
}
