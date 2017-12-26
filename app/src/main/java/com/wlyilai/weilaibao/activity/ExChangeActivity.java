package com.wlyilai.weilaibao.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.wlyilai.weilaibao.R;
import com.wlyilai.weilaibao.utils.ToastUtils;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * @author: captain
 * Time:  2017/12/25 0025
 * Describe:
 */
public class ExChangeActivity extends BaseActivity {


    @BindView(R.id.imgBack)
    ImageView mImgBack;
    @BindView(R.id.txtTitle)
    TextView mTxtTitle;
    @BindView(R.id.allInteger)
    TextView mAllInteger;
    @BindView(R.id.edtNumber)
    EditText mEdtNumber;
    @BindView(R.id.sureExcharge)
    Button mSureExcharge;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exchange);
        ButterKnife.bind(this);
        init();
    }

    private void init() {
        mImgBack.setVisibility(View.VISIBLE);
        mTxtTitle.setText("换余额");
    }


    @OnClick({R.id.imgBack, R.id.sureExcharge})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.imgBack:
                finish();
                break;
            case R.id.sureExcharge:
                if(TextUtils.isEmpty(mEdtNumber.getText().toString())){
                    ToastUtils.showShort(ExChangeActivity.this,"请输入要兑换的余额");
                    return;
                }
                sureExcharge();
                break;
        }
    }

    /**
     * 确定兑换余额
     */
    private void sureExcharge() {
    }
}
