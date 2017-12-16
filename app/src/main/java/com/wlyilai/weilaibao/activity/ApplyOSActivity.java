package com.wlyilai.weilaibao.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
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
public class ApplyOSActivity extends BaseActivity {
    @BindView(R.id.imgBack)
    ImageView mImgBack;
    @BindView(R.id.txtTitle)
    TextView mTxtTitle;
    @BindView(R.id.textView)
    TextView mTextView;
    @BindView(R.id.edtName)
    EditText mEdtName;
    @BindView(R.id.edtPhone)
    EditText mEdtPhone;
    @BindView(R.id.spnProvince)
    Spinner mSpnProvince;
    @BindView(R.id.spnCity)
    Spinner mSpnCity;
    @BindView(R.id.spnCounty)
    Spinner mSpnCounty;
    @BindView(R.id.edtAddress)
    EditText mEdtAddress;
    @BindView(R.id.edtMessage)
    EditText mEdtMessage;
    @BindView(R.id.btnCommit)
    Button mBtnCommit;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_apply_shop);
        ButterKnife.bind(this);
        init();
    }

    private void init() {
        mImgBack.setVisibility(View.VISIBLE);
        mTxtTitle.setText("申请开店");
    }

    @OnClick({R.id.imgBack,R.id.btnCommit})
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.imgBack:
                finish();
                break;
            case R.id.btnCommit:
                break;
        }
    }


}
