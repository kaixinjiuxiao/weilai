package com.wlyilai.weilaibao.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.widget.ImageView;
import android.widget.TextView;

import com.wlyilai.weilaibao.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

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

    }

    private void initData() {
    }

    @OnClick(R.id.imgBack)
    public void onClick() {
        finish();
    }
}
