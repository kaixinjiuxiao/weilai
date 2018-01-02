package com.wlyilai.weilaibao.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.wlyilai.weilaibao.R;
import com.wlyilai.weilaibao.utils.ActivityManager;
import com.wlyilai.weilaibao.utils.PreferenceUtil;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * @author: captain
 * Time:  2017/12/16 0016
 * Describe:
 */
public class SettingActivity extends BaseActivity {
    @BindView(R.id.imgBack)
    ImageView mImgBack;
    @BindView(R.id.txtTitle)
    TextView mTxtTitle;
    @BindView(R.id.txtVersion)
    TextView mTxtVersion;
    @BindView(R.id.relativeVersion)
    RelativeLayout mRelativeVersion;
    @BindView(R.id.btnLoginOut)
    Button mBtnLoginOut;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_seeting);
        ButterKnife.bind(this);
        init();
    }

    private void init() {
        PreferenceUtil.init(this);
        mImgBack.setVisibility(View.VISIBLE);
    }

    @OnClick({R.id.imgBack, R.id.relativeVersion, R.id.btnLoginOut})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.imgBack:
                finish();
                break;
            case R.id.relativeVersion:
                break;
            case R.id.btnLoginOut:
                PreferenceUtil.removeAll();
                ActivityManager.getInstance().finishAllActivity();
                Intent intent = new Intent(this,LoginActivity.class);
                startActivity(intent);
                finish();
                break;
        }
    }
}
