package com.wlyilai.weilaibao.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
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
public class UserInfoActivity extends BaseActivity {
    @BindView(R.id.imgBack)
    ImageView mImgBack;
    @BindView(R.id.txtTitle)
    TextView mTxtTitle;
    @BindView(R.id.txtRecommond)
    TextView mTxtRecommond;
    @BindView(R.id.userName)
    TextView mUserName;
    @BindView(R.id.userPhone)
    TextView mUserPhone;
    @BindView(R.id.shopRecommond)
    TextView mShopRecommond;
    @BindView(R.id.bank)
    TextView mBank;
    @BindView(R.id.linearBank)
    LinearLayout mLinearBank;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_info);
        ButterKnife.bind(this);
        init();
    }

    private void init() {
        mImgBack.setVisibility(View.VISIBLE);
        mTxtTitle.setText("用户信息");
    }

    @OnClick({R.id.imgBack, R.id.linearBank})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.imgBack:
                finish();
                break;
            case R.id.linearBank:
                break;
        }

    }


}
