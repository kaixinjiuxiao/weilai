package com.wlyilai.weilaibao.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.google.gson.Gson;
import com.wlyilai.weilaibao.R;
import com.wlyilai.weilaibao.adapter.MyAddressAdapter;
import com.wlyilai.weilaibao.entry.ReceivingAddress;
import com.wlyilai.weilaibao.entry.SureBuy;
import com.wlyilai.weilaibao.entry.SureOrder;
import com.wlyilai.weilaibao.utils.ToastUtils;
import com.wlyilai.weilaibao.view.ListViewForScrollView;
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
 * Author : Captain
 * Time : 2017/12/12
 * Describe :确认订单
 */

public class SureOrderActivity extends BaseActivity {

    @BindView(R.id.imgBack)
    ImageView mImgBack;
    @BindView(R.id.txtTitle)
    TextView mTxtTitle;
    @BindView(R.id.relativeAdd)
    RelativeLayout mRelativeAdd;
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
    @BindView(R.id.linearAdd)
    LinearLayout mLinearAdd;
    @BindView(R.id.imgGoods)
    ImageView mImgGoods;
    @BindView(R.id.goodsName)
    TextView mGoodsName;
    @BindView(R.id.goodsPrice)
    TextView mGoodsPrice;
    @BindView(R.id.goodsNumber)
    TextView mGoodsNumber;
    @BindView(R.id.goodsTotalNumber)
    TextView mGoodsTotalNumber;
    @BindView(R.id.goodsTotalPrice)
    TextView mGoodsTotalPrice;
    @BindView(R.id.totalPrice)
    TextView mTotalPrice;
    @BindView(R.id.commitOrder)
    TextView mCommitOrder;
    @BindView(R.id.address)
    ListViewForScrollView mAddress;
    // private AddressAdapter mAdapter;
    private MyAddressAdapter mAdapter;
    private List<ReceivingAddress.DataBean.AddressBean> mList = new ArrayList<>();
    private HashMap<Integer, Boolean> map = new HashMap<>();
//    private List<ProvinceCityArea.DataBean> mListProvice;
//    private List<ProvinceCityArea.DataBean> mListCity;
//    private List<ProvinceCityArea.DataBean> mListArea;
//    private PCAreaAdapter mSpinnerAdapter;
//    private String province, city, area;
    private String addressId,goodsId,goodNumber;
    private SureBuy mSureBuy;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sure_order);
        ButterKnife.bind(this);
        init();
        initEvent();
    }

    private void init() {
        mImgBack.setVisibility(View.VISIBLE);
        mTxtTitle.setText("确认订单");
        Bundle bundle = getIntent().getBundleExtra("goods");
        mSureBuy = (SureBuy) bundle.getSerializable("data");
        goodsId = mSureBuy.getData().getId();
        goodNumber = mSureBuy.getData().getBuy_num();
        Glide.with(SureOrderActivity.this).load(mSureBuy.getData().getGimg()).into(mImgGoods);
        mGoodsName.setText(mSureBuy.getData().getGname());
        mGoodsPrice.setText(mSureBuy.getData().getGteam_price());
        mGoodsPrice.setText(mSureBuy.getData().getGteam_price());
        mGoodsNumber.setText("x"+mSureBuy.getData().getBuy_num());
        mGoodsTotalNumber.setTag("共"+mSureBuy.getData().getBuy_num()+"件商品");
        mGoodsTotalPrice.setText(mSureBuy.getData().getTotal_price()+"");
        mTotalPrice.setText(mSureBuy.getData().getTotal_price()+"");

        mAdapter = new MyAddressAdapter(this, mList);
        mAddress.setAdapter(mAdapter);
        getAddress();
     //   getCity(0, mSpnCountry, 0);
    }

    private void initEvent() {
        mAdapter.setDeleteListener(new MyAddressAdapter.OnDeleteListener() {
            @Override
            public void onDelete(int position) {
                deleteAddress(mList.get(position).getId(), position);
            }
        });
        mAdapter.setChooiseListener(new MyAddressAdapter.OnChooiseListener() {
            @Override
            public void onChooise(int position) {
                addressId = mList.get(position).getId();
                mAdapter.notifyDataSetChanged();
            }
        });
//        mSpnCountry.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
//            @Override
//            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
//                int pid = Integer.parseInt(mListProvice.get(position).getId());
//                province = mListProvice.get(position).getName();
//                getCity(pid, mSpnCity, 1);
//            }
//
//            @Override
//            public void onNothingSelected(AdapterView<?> parent) {
//
//            }
//        });
//
//        mSpnCity.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
//            @Override
//            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
//                int pid = Integer.parseInt(mListCity.get(position).getId());
//                city = mListCity.get(position).getName();
//                getCity(pid, mSpnCounty, 2);
//            }
//
//            @Override
//            public void onNothingSelected(AdapterView<?> parent) {
//
//            }
//        });
//        mSpnCounty.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
//            @Override
//            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
//                area = mListArea.get(position).getName();
//            }
//
//            @Override
//            public void onNothingSelected(AdapterView<?> parent) {
//
//            }
//        });
    }

    /**
     * 获取收货地址
     */
    private void getAddress() {
        OkHttpUtils.post().url("http://test.mgbh.wlylai.com/AppApi/get_address")
                .addParams("access_token", "02c8b29f1b09833e43a37c770a87db23")
                .build().execute(new StringCallback() {
            @Override
            public void onError(Call call, Exception e, int id) {
                Log.e("tag", "地址错误" + e.toString());
            }

            @Override
            public void onResponse(String response, int id) {
                ReceivingAddress address = new Gson().fromJson(response, ReceivingAddress.class);
                if (address.getStatus() == 1) {
                    mList.clear();
                    for (int i = 0; i < address.getData().getAddress().size(); i++) {
                        mList.add(address.getData().getAddress().get(i));
                    }
                    mAdapter.notifyDataSetChanged();
                }
            }
        });
    }
//
//    /**
//     * 获取省市区
//     *
//     * @param pid
//     * @param spinner
//     * @param type
//     */
//    private void getCity(int pid, final Spinner spinner, final int type) {
//        OkHttpUtils.post().url(Constant.GET_CCC).addParams("pid", String.valueOf(pid))
//                .build().execute(new StringCallback() {
//            @Override
//            public void onError(Call call, Exception e, int id) {
//
//            }
//
//            @Override
//            public void onResponse(String response, int id) {
//                ProvinceCityArea pca = new Gson().fromJson(response, ProvinceCityArea.class);
//                if (type == 0) {
//                    mListProvice = pca.getData();
//                    mSpinnerAdapter = new PCAreaAdapter(SureOrderActivity.this, mListProvice);
//                } else if (type == 1) {
//                    mListCity = pca.getData();
//                    mSpinnerAdapter = new PCAreaAdapter(SureOrderActivity.this, mListCity);
//                } else if (type == 2) {
//                    mListArea = pca.getData();
//                    mSpinnerAdapter = new PCAreaAdapter(SureOrderActivity.this, mListArea);
//                }
//                spinner.setAdapter(mSpinnerAdapter);
//            }
//        });
//    }

    @OnClick({R.id.imgBack, R.id.relativeAdd, R.id.sureAdd, R.id.commitOrder})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.imgBack:
                finish();
                break;
            case R.id.relativeAdd:
                Intent intent  =new Intent(SureOrderActivity.this,AddManagerActivity.class);
                startActivityForResult(intent,1);
               // mLinearAdd.setVisibility(View.VISIBLE);
                break;
            case R.id.sureAdd:
              //  addAddress();
               // mLinearAdd.setVisibility(View.GONE);
                break;
            case R.id.commitOrder:
                commitOrder();
//                Intent intent = new Intent(this, SurePayActivity.class);
//                startActivity(intent);
                break;
        }
    }

    /**
     * 提交订单
     */
    private void commitOrder() {
        if(TextUtils.isEmpty(addressId)){
            ToastUtils.showShort(SureOrderActivity.this,"请选择收货地址");
            return;
        }
         Map<String, String> parmas = new HashMap<>();
        parmas.put("access_token", "02c8b29f1b09833e43a37c770a87db23");
        parmas.put("aid", addressId);
        parmas.put("id", goodsId);
        parmas.put("buy_num", goodNumber);
        OkHttpUtils.post().url("http://test.mgbh.wlylai.com/AppApi/create_order")
                .params(parmas).build().execute(new StringCallback() {
            @Override
            public void onError(Call call, Exception e, int id) {
                Log.e("tag","提交订单错误"+e.toString());
            }

            @Override
            public void onResponse(String response, int id) {
                try {
                    JSONObject jsonObject = new JSONObject(response);
                    if(jsonObject.getInt("status")==1){
                        SureOrder order = new Gson().fromJson(response,SureOrder.class);
                        Bundle bundle = new Bundle();
                        bundle.putSerializable("order",order);
                        Intent intent  =new Intent(SureOrderActivity.this,SurePayActivity.class);
                        intent.putExtra("sure",bundle);
                        startActivity(intent);
                   }else if(jsonObject.getInt("status")==0){
                        ToastUtils.showShort(SureOrderActivity.this,jsonObject.getString("msg"));
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });
    }

//    /**
//     * 添加收货地址
//     */
//    private void addAddress() {
//        final Map<String, String> parmas = new HashMap<>();
//        parmas.put("access_token", "02c8b29f1b09833e43a37c770a87db23");
//        parmas.put("mobile", mEdtPhone.getText().toString());
//        parmas.put("realname", mEdtName.getText().toString());
//        parmas.put("province", province);
//        parmas.put("city", city);
//        parmas.put("country", area);
//        parmas.put("detail", mEdtAddress.getText().toString());
//        OkHttpUtils.post().url("http://test.mgbh.wlylai.com/AppApi/add_address")
//                .params(parmas).build().execute(new StringCallback() {
//            @Override
//            public void onError(Call call, Exception e, int id) {
//                Log.e("tag", "地址错误" + e.toString());
//            }
//
//            @Override
//            public void onResponse(String response, int id) {
//                Log.e("tag", "地址" + response);
//                try {
//                    JSONObject object = new JSONObject(response);
//                    if(object.getInt("status")==1){
////                        JSONObject data = object.getJSONObject("data");
////                        String addressId = data.getString("id");
////                        ReceivingAddress.DataBean.AddressBean address = new ReceivingAddress.DataBean.AddressBean();
////                        address.setId(addressId);
////                        address.setCity(city);
////                        address.setCountry(area);
////                        address.setProvince(province);
////                        address.setDetail(mEdtAddress.getText().toString());
////                        address.setMobile(mEdtPhone.getText().toString());
////                        address.setRealname(mEdtName.getText().toString());
////                        address.setUid(mEdtName.getText().toString());
//
//                        getAddress();
//                    }
//                } catch (JSONException e) {
//                    e.printStackTrace();
//                }
//            }
//        });
//    }

    private void deleteAddress(String id, final int position) {
        OkHttpUtils.post().url("http://test.mgbh.wlylai.com/AppApi/del_address")
                .addParams("access_token", "02c8b29f1b09833e43a37c770a87db23")
                .addParams("id", id).build().execute(new StringCallback() {
            @Override
            public void onError(Call call, Exception e, int id) {
            }

            @Override
            public void onResponse(String response, int id) {
                try {
                    JSONObject object = new JSONObject(response);
                    if (object.getInt("status") == 1) {
                        mList.remove(position);
                        mAdapter.notifyDataSetChanged();
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode==1){
          getAddress();
        }
    }
}
