package com.wlyilai.weilaibao.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.webkit.WebView;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import com.google.gson.Gson;
import com.wlyilai.weilaibao.R;
import com.wlyilai.weilaibao.adapter.PCAreaAdapter;
import com.wlyilai.weilaibao.entry.ProvinceCityArea;
import com.wlyilai.weilaibao.utils.ActivityManager;
import com.wlyilai.weilaibao.utils.Constant;
import com.wlyilai.weilaibao.utils.PreferenceUtil;
import com.wlyilai.weilaibao.utils.ToastUtils;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import okhttp3.Call;

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
    @BindView(R.id.txtPhone)
    TextView mTxtPhone;
    private List<ProvinceCityArea.DataBean> mListProvice;
    private List<ProvinceCityArea.DataBean> mListCity;
    private List<ProvinceCityArea.DataBean> mListArea;
    private PCAreaAdapter mAdapter;
    private String province, city, area;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        ButterKnife.bind(this);
        init();
        initEvent();
    }

    private void init() {
        PreferenceUtil.init(this);
        mImgBack.setVisibility(View.VISIBLE);
        mTxtTitle.setText("完善资料");
        String phone = getIntent().getStringExtra("phone");
        mTxtPhone.setText(phone);
        mTxtRecommond.setText(phone);
        mListProvice = new ArrayList<>();
        mListCity = new ArrayList<>();
        mListArea = new ArrayList<>();
        getCity(0, mSpnProvince, 0);
    }

    private void getCity(int pid, final Spinner spinner, final int type) {
        OkHttpUtils.post().url(Constant.GET_CCC).addParams("pid", String.valueOf(pid))
                .build().execute(new StringCallback() {
            @Override
            public void onError(Call call, Exception e, int id) {

            }

            @Override
            public void onResponse(String response, int id) {
                ProvinceCityArea pca = new Gson().fromJson(response, ProvinceCityArea.class);
                if (type == 0) {
                    mListProvice = pca.getData();
                    mAdapter = new PCAreaAdapter(RegisterActivity.this, mListProvice);
                } else if (type == 1) {
                    mListCity = pca.getData();
                    mAdapter = new PCAreaAdapter(RegisterActivity.this, mListCity);
                } else if (type == 2) {
                    mListArea = pca.getData();
                    mAdapter = new PCAreaAdapter(RegisterActivity.this, mListArea);
                }
                spinner.setAdapter(mAdapter);
            }
        });
    }

    private void initEvent() {
        mSpnProvince.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                int pid = Integer.parseInt(mListProvice.get(position).getId());
                province = mListProvice.get(position).getName();
                getCity(pid, mSpnCity, 1);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        mSpnCity.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                int pid = Integer.parseInt(mListCity.get(position).getId());
                city = mListCity.get(position).getName();
                getCity(pid, mSpnCounty, 2);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        mSpnCounty.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                area = mListArea.get(position).getName();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    @OnClick({R.id.imgBack, R.id.checkbox, R.id.txtAggrement, R.id.btnCommit})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.imgBack:
                finish();
                break;
            case R.id.checkbox:
                mCheckbox.setSelected(true);
                break;
            case R.id.txtAggrement:
                showAggrement();
                break;
            case R.id.btnCommit:
                register();
                break;
        }
    }

    private void register() {
        if(TextUtils.isEmpty(mInputName.getText().toString())){
            ToastUtils.showShort(RegisterActivity.this,"请输入真实姓名");
            return;
        }
        if(TextUtils.isEmpty(mInputPassword.getText().toString())){
            ToastUtils.showShort(RegisterActivity.this,"请输入密码");
            return;
        }
        if(TextUtils.isEmpty(mInputPasswordAgain.getText().toString())){
            ToastUtils.showShort(RegisterActivity.this,"请再次输入密码");
            return;
        }
        if(!mInputPassword.getText().toString().equals(mInputPasswordAgain.getText().toString())){
            ToastUtils.showShort(RegisterActivity.this,"两次输入密码不一致");
            return;
        }
        if(province.equals("请选择")||city.equals("请选择")||area.equals("请选择")){
            ToastUtils.showShort(RegisterActivity.this,"请选择省市区");
            return;
        }
        if(mCheckbox.isSelected()==false){
            ToastUtils.showShort(RegisterActivity.this,"请先同意用户协议");
            return;
        }
        Map<String,String>parmas = new HashMap<>();
        parmas.put("mobile",mTxtPhone.getText().toString());
        parmas.put("province",province);
        parmas.put("city",city);
        parmas.put("region",area);
        parmas.put("password",mInputPassword.getText().toString());
        parmas.put("realname",mInputName.getText().toString());
        parmas.put("pmobile",mTxtRecommond.getText().toString());
        OkHttpUtils.post().url(Constant.REGISTER)
                .params(parmas).build().execute(new StringCallback() {
            @Override
            public void onError(Call call, Exception e, int id) {

            }

            @Override
            public void onResponse(String response, int id) {
                Log.e("tag","注册信息"+response);
                //{"status":1,"msg":"198","access_token":"02c8b29f1b09833e43a37c770a87db23"}注册成功
                try {
                    JSONObject jsonObject = new JSONObject(response);
                    if(jsonObject.getInt("status")==1){
                        PreferenceUtil.commitString("token",jsonObject.getString("access_token"));
                        ActivityManager.getInstance().finishAllActivity();
                        Intent intent = new Intent(RegisterActivity.this,MainActivity.class);
                        startActivity(intent);
                        finish();
                    }else{
                        ToastUtils.showShort(RegisterActivity.this,jsonObject.getString("msg"));
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    private void showAggrement() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        View view = LayoutInflater.from(this).inflate(R.layout.activity_aggrement, null);
        WebView web = view.findViewById(R.id.webview);
        web.loadUrl("http://test.mgbh.wlylai.com/Login/user.html");
        builder.setView(view);
        AlertDialog dialog = builder.create();
        dialog.show();
    }


}
