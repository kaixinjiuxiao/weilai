package com.wlyilai.weilaibao.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.wlyilai.weilaibao.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * @author: captain
 * Time:  2017/12/16 0016
 * Describe:消费收益
 */
public class ConsumptionActivity extends BaseActivity {
    @BindView(R.id.imgBack)
    ImageView mImgBack;
    @BindView(R.id.txtTitle)
    TextView mTxtTitle;
    @BindView(R.id.money)
    TextView mMoney;
    @BindView(R.id.btnCds)
    Button mBtnCds;
    @BindView(R.id.btnLook)
    Button mBtnLook;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_consumption);
        ButterKnife.bind(this);
        init();
    }

    private void init() {
        mImgBack.setVisibility(View.VISIBLE);
        mTxtTitle.setText("查看收益");
    }

    @OnClick({R.id.imgBack, R.id.btnCds, R.id.btnLook})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.imgBack:
                finish();
                break;
            case R.id.btnCds:
                break;
            case R.id.btnLook:
                break;
        }
    }
}
