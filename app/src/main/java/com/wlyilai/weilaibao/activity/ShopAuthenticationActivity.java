package com.wlyilai.weilaibao.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import com.google.gson.Gson;
import com.wlyilai.weilaibao.R;
import com.wlyilai.weilaibao.adapter.PCAreaAdapter;
import com.wlyilai.weilaibao.entry.ProvinceCityArea;
import com.wlyilai.weilaibao.utils.Constant;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import okhttp3.Call;

/**
 * @author: captain
 * Time:  2017/12/9 0009
 * Describe:
 */
public class ShopAuthenticationActivity extends BaseActivity {
    @BindView(R.id.imgBack)
    ImageView mImgBack;
    @BindView(R.id.txtTitle)
    TextView mTxtTitle;
    @BindView(R.id.edtCompany)
    EditText mEdtCompany;
    @BindView(R.id.edtName)
    EditText mEdtName;
    @BindView(R.id.edtCode)
    EditText mEdtCode;
    @BindView(R.id.spnProvince)
    Spinner mSpnProvince;
    @BindView(R.id.spnCity)
    Spinner mSpnCity;
    @BindView(R.id.spnCounty)
    Spinner mSpnCounty;
    @BindView(R.id.edtAddress)
    EditText mEdtAddress;
    @BindView(R.id.imgAdd)
    ImageView mImgAdd;
    @BindView(R.id.delete)
    TextView mDelete;
    @BindView(R.id.btnAuthen)
    Button mBtnAuthen;
    private List<ProvinceCityArea.DataBean> mListProvice;
    private List<ProvinceCityArea.DataBean> mListCity;
    private List<ProvinceCityArea.DataBean> mListArea;
    private PCAreaAdapter mSpinnerAdapter;
    private String province, city, area;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shop_authentication);
        ButterKnife.bind(this);
        initView();
        initEvent();
    }

    private void initView() {
        mImgBack.setVisibility(View.VISIBLE);
        mTxtTitle.setText("店家认证");
        getCity(0, mSpnProvince, 0);

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



    @OnClick({R.id.imgAdd, R.id.delete, R.id.btnAuthen,R.id.imgBack})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.imgBack:
                finish();
                break;
            case R.id.imgAdd:
                break;
            case R.id.delete:
                break;
            case R.id.btnAuthen:
                break;
        }
    }

    /**
     * 获取省市区
     *
     * @param pid
     * @param spinner
     * @param type
     */
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
                    mSpinnerAdapter = new PCAreaAdapter(ShopAuthenticationActivity.this, mListProvice);
                } else if (type == 1) {
                    mListCity = pca.getData();
                    mSpinnerAdapter = new PCAreaAdapter(ShopAuthenticationActivity.this, mListCity);
                } else if (type == 2) {
                    mListArea = pca.getData();
                    mSpinnerAdapter = new PCAreaAdapter(ShopAuthenticationActivity.this, mListArea);
                }
                spinner.setAdapter(mSpinnerAdapter);
            }
        });
    }
}
