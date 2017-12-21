package com.wlyilai.weilaibao.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
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

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import okhttp3.Call;

/**
 * @author: captain
 * Time:  2017/12/20 0020
 * Describe:
 */
public class AddManagerActivity extends BaseActivity {
    @BindView(R.id.imgBack)
    ImageView mImgBack;
    @BindView(R.id.txtTitle)
    TextView mTxtTitle;
 /*   @BindView(R.id.address)
    ListViewForScrollView mAddress;*/
    @BindView(R.id.edtName)
    EditText mEdtName;
    @BindView(R.id.edtPhone)
    EditText mEdtPhone;
    @BindView(R.id.spnCountry)
    Spinner mSpnCountry;
    @BindView(R.id.spnCity)
    Spinner mSpnCity;
    @BindView(R.id.spnCounty)
    Spinner mSpnCounty;
    @BindView(R.id.edtAddress)
    EditText mEdtAddress;
    @BindView(R.id.sureAdd)
    Button mSureAdd;
  /*  private MyAddressAdapter mAdapter;
    private List<ReceivingAddress.DataBean.AddressBean> mList = new ArrayList<>();*/
    private List<ProvinceCityArea.DataBean> mListProvice;
    private List<ProvinceCityArea.DataBean> mListCity;
    private List<ProvinceCityArea.DataBean> mListArea;
    private PCAreaAdapter mSpinnerAdapter;
    private String province, city, area;
    private String addressId;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_address_man);
        ButterKnife.bind(this);
        init();
        initEvent();
    }

    private void init() {
        mImgBack.setVisibility(View.VISIBLE);
        mTxtTitle.setText("添加收货地址");
        getCity(0, mSpnCountry, 0);
//        mAdapter = new MyAddressAdapter(this, mList);
//        mAddress.setAdapter(mAdapter);
      //  getAddress();
    }

    private void initEvent() {
//        mAdapter.setDeleteListener(new MyAddressAdapter.OnDeleteListener() {
//            @Override
//            public void onDelete(int position) {
//                deleteAddress(mList.get(position).getId(), position);
//            }
//        });
//        mAdapter.setChooiseListener(new MyAddressAdapter.OnChooiseListener() {
//            @Override
//            public void onChooise(int position) {
//                addressId = mList.get(position).getId();
//                mAdapter.notifyDataSetChanged();
//                Intent intent = new Intent();
//                intent.putExtra("address",addressId);
//                setResult(1,intent);
//            }
//        });
        mSpnCountry.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
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
//    /**
//     * 获取收货地址
//     */
//    private void getAddress() {
//        OkHttpUtils.post().url("http://test.mgbh.wlylai.com/AppApi/get_address")
//                .addParams("access_token", "02c8b29f1b09833e43a37c770a87db23")
//                .build().execute(new StringCallback() {
//            @Override
//            public void onError(Call call, Exception e, int id) {
//                Log.e("tag", "地址错误" + e.toString());
//            }
//
//            @Override
//            public void onResponse(String response, int id) {
//                ReceivingAddress address = new Gson().fromJson(response, ReceivingAddress.class);
//                if (address.getStatus() == 1) {
//                    mList.clear();
//                    for (int i = 0; i < address.getData().getAddress().size(); i++) {
//                        mList.add(address.getData().getAddress().get(i));
//                    }
//                    mAdapter.notifyDataSetChanged();
//                }
//            }
//        });
//    }

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
                    mSpinnerAdapter = new PCAreaAdapter(AddManagerActivity.this, mListProvice);
                } else if (type == 1) {
                    mListCity = pca.getData();
                    mSpinnerAdapter = new PCAreaAdapter(AddManagerActivity.this, mListCity);
                } else if (type == 2) {
                    mListArea = pca.getData();
                    mSpinnerAdapter = new PCAreaAdapter(AddManagerActivity.this, mListArea);
                }
                spinner.setAdapter(mSpinnerAdapter);
            }
        });
    }


    @OnClick({R.id.imgBack, R.id.sureAdd})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.imgBack:
                finish();
                break;
            case R.id.sureAdd:
                addAddress();
                break;
        }
    }

    /**
     * 添加收货地址
     */
    private void addAddress() {
        final Map<String, String> parmas = new HashMap<>();
        parmas.put("access_token", "02c8b29f1b09833e43a37c770a87db23");
        parmas.put("mobile", mEdtPhone.getText().toString());
        parmas.put("realname", mEdtName.getText().toString());
        parmas.put("province", province);
        parmas.put("city", city);
        parmas.put("country", area);
        parmas.put("detail", mEdtAddress.getText().toString());
        OkHttpUtils.post().url(Constant.ADD_ADDRESS)
                .params(parmas).build().execute(new StringCallback() {
            @Override
            public void onError(Call call, Exception e, int id) {
                Log.e("tag", "地址错误" + e.toString());
            }

            @Override
            public void onResponse(String response, int id) {
                Log.e("tag", "地址" + response);
                try {
                    JSONObject object = new JSONObject(response);
                    if(object.getInt("status")==1){
                      // getAddress();
                        Intent intent = new Intent();
                        intent.putExtra("address",addressId);
                        setResult(1,intent);
                        finish();
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });
    }
//    private void deleteAddress(String id, final int position) {
//        OkHttpUtils.post().url("http://test.mgbh.wlylai.com/AppApi/del_address")
//                .addParams("access_token", "02c8b29f1b09833e43a37c770a87db23")
//                .addParams("id", id).build().execute(new StringCallback() {
//            @Override
//            public void onError(Call call, Exception e, int id) {
//            }
//
//            @Override
//            public void onResponse(String response, int id) {
//                try {
//                    JSONObject object = new JSONObject(response);
//                    if (object.getInt("status") == 1) {
//                        mList.remove(position);
//                        mAdapter.notifyDataSetChanged();
//                    }
//                } catch (JSONException e) {
//                    e.printStackTrace();
//                }
//            }
//        });
//    }
}
