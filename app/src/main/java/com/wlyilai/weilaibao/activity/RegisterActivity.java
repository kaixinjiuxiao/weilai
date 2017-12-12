package com.wlyilai.weilaibao.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.CheckBox;
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
 * Time:  2017/12/9 0009
 * Describe:
 */
public class RegisterActivity extends BaseActivity {
    @BindView(R.id.imgBack)
    ImageView mImgBack;
    @BindView(R.id.txtTitle)
    TextView mTxtTitle;
    @BindView(R.id.inputName)
    EditText mInputName;
    @BindView(R.id.inputPassword)
    EditText mInputPassword;
    @BindView(R.id.inputPasswordAgain)
    EditText mInputPasswordAgain;
    @BindView(R.id.spnProvince)
    Spinner mSpnProvince;
    @BindView(R.id.spnCity)
    Spinner mSpnCity;
    @BindView(R.id.spnCounty)
    Spinner mSpnCounty;
    @BindView(R.id.txtRecommond)
    TextView mTxtRecommond;
    @BindView(R.id.checkbox)
    CheckBox mCheckbox;
    @BindView(R.id.txtAggrement)
    TextView mTxtAggrement;
    @BindView(R.id.btnCommit)
    Button mBtnCommit;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        ButterKnife.bind(this);
        init();
    }

    private void init() {
        mImgBack.setVisibility(View.VISIBLE);
        mTxtTitle.setText("完善资料");

    }

    @OnClick({R.id.imgBack, R.id.checkbox, R.id.txtAggrement, R.id.btnCommit})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.imgBack:
                finish();
                break;
            case R.id.checkbox:
                break;
            case R.id.txtAggrement:
                showAggrement();
                break;
            case R.id.btnCommit:
                startActivity(new Intent(this,MainActivity.class));
                break;
        }
    }

    private void showAggrement() {
        AlertDialog.Builder builder= new AlertDialog.Builder(this);
        View view = LayoutInflater.from(this).inflate(R.layout.activity_aggrement,null);
        WebView web = view.findViewById(R.id.webview);
        web.loadUrl("http://test.mgbh.wlylai.com/Login/user.html");
        builder.setView(view);
        AlertDialog dialog= builder.create();
        dialog.show();
    }
}
