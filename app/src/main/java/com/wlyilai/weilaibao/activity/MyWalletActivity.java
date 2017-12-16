package com.wlyilai.weilaibao.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.wlyilai.weilaibao.R;

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
    @BindView(R.id.banlance)
    TextView mBanlance;
    @BindView(R.id.consumption)
    TextView mConsumption;
    @BindView(R.id.recharge)
    TextView mRecharge;
    @BindView(R.id.img1)
    ImageView mImg1;
    @BindView(R.id.twentyRecharge)
    RelativeLayout mTwentyRecharge;
    @BindView(R.id.img2)
    ImageView mImg2;
    @BindView(R.id.fiftyRecharge)
    RelativeLayout mFiftyRecharge;
    @BindView(R.id.img3)
    ImageView mImg3;
    @BindView(R.id.hundredRechagre)
    RelativeLayout mHundredRechagre;

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

    @OnClick({R.id.imgBack, R.id.twentyRecharge, R.id.fiftyRecharge, R.id.hundredRechagre})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.imgBack:
                finish();
                break;
            case R.id.twentyRecharge:
                break;
            case R.id.fiftyRecharge:
                break;
            case R.id.hundredRechagre:
                break;
        }
    }
}
