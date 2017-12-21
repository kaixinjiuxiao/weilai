package com.wlyilai.weilaibao.activity;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.alipay.sdk.app.PayTask;
import com.google.gson.Gson;
import com.tencent.mm.opensdk.modelpay.PayReq;
import com.tencent.mm.opensdk.openapi.IWXAPI;
import com.tencent.mm.opensdk.openapi.WXAPIFactory;
import com.wlyilai.weilaibao.R;
import com.wlyilai.weilaibao.entry.PayResult;
import com.wlyilai.weilaibao.entry.SureOrder;
import com.wlyilai.weilaibao.utils.AliPayResults;
import com.wlyilai.weilaibao.utils.Constant;
import com.wlyilai.weilaibao.utils.PreferenceUtil;
import com.wlyilai.weilaibao.utils.ToastUtils;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import okhttp3.Call;

/**
 * Author : Captain
 * Time : 2017/12/12
 * Describe :支付
 */

public class SurePayActivity extends BaseActivity {
    private static final int SDK_PAY_FLAG = 1;
    private static final String WX_APP_ID_ONE = "wx4f71e1e33abc41a1";
    @BindView(R.id.imgBack)
    ImageView mImgBack;
    @BindView(R.id.txtTitle)
    TextView mTxtTitle;
    @BindView(R.id.orderCode)
    TextView mOrderCode;
    @BindView(R.id.orderPrice)
    TextView mOrderPrice;
    @BindView(R.id.linearALi)
    LinearLayout mLinearALi;
    @BindView(R.id.textBanlance)
    TextView mTextBanlance;
    @BindView(R.id.linearBalance)
    LinearLayout mLinearBalance;
    @BindView(R.id.txtRight)
    TextView mTxtRight;
    @BindView(R.id.linearWX)
    LinearLayout mLinearWX;
    private PopupWindow popupWindow;
    private AlertDialog mDialog;
    private String orderCode;
    private IWXAPI iwxapi;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sure_pay);
        ButterKnife.bind(this);
        init();
    }

    private void init() {
        PreferenceUtil.init(SurePayActivity.this);
        mImgBack.setVisibility(View.VISIBLE);
        mTxtTitle.setText("收银台");
        Bundle bunder = getIntent().getBundleExtra("sure");
        SureOrder order = (SureOrder) bunder.getSerializable("order");
        mOrderCode.setText(order.getData().getOsn());
        orderCode = order.getData().getOsn();
        iwxapi = WXAPIFactory.createWXAPI(SurePayActivity.this, WX_APP_ID_ONE, true);
        iwxapi.registerApp(WX_APP_ID_ONE);
        getBanlance();
    }

    @OnClick({R.id.imgBack, R.id.linearALi, R.id.linearBalance, R.id.linearWX})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.imgBack:
                finish();
                break;
            case R.id.linearALi:
                if (isAppInstalled("com.eg.android.AlipayGphone") == false) {
                    ToastUtils.showShort(SurePayActivity.this, "很遗憾，您没有安装支付宝！");
                } else {
                    startPay("1");
                }
                break;
            case R.id.linearWX:
//                if (isAppInstalled("com.tencent.mm") == false) {
//                    ToastUtils.showShort(SurePayActivity.this, "很遗憾，您没有安装微信！");
//                } else {
//                    startPay("0");
//                }
                Uri uri=Uri.parse("app://hnyst");
                Intent intent=new Intent(Intent.ACTION_VIEW,uri);
                startActivity(intent);

//                if(isAppInstalled("com.hnrryst.hnyst")==true){
//                    Intent intent = new Intent(Intent.ACTION_MAIN);
//                    intent.addCategory(Intent.CATEGORY_LAUNCHER);
//                    ComponentName cn = new ComponentName("com.hnrryst.hnyst", "com.hnrryst.hnyst.activity.SplashActivity");
//                    intent.setComponent(cn);
//                    startActivity(intent);
//                }



                break;
            case R.id.linearBalance:
                displayDialog();
                break;
        }
    }

    private void displayDialog() {
        final AlertDialog.Builder builder = new AlertDialog.Builder(SurePayActivity.this);
        View view = LayoutInflater.from(SurePayActivity.this).inflate(R.layout.layout_sure_pay, null);
        builder.setView(view);
        TextView sure = (TextView) view.findViewById(R.id.sure);
        TextView cancel = (TextView) view.findViewById(R.id.cancel);
        sure.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startPay("2");
                mDialog.dismiss();
            }
        });
        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mDialog.dismiss();
            }
        });
        mDialog = builder.create();
        mDialog.show();
    }

    private void getBanlance() {
        OkHttpUtils.post().url(Constant.USER_INFO)
                .addParams("access_token", "02c8b29f1b09833e43a37c770a87db23")
                .build().execute(new StringCallback() {
            @Override
            public void onError(Call call, Exception e, int id) {

            }

            @Override
            public void onResponse(String response, int id) {
                try {
                    JSONObject jsonObject = new JSONObject(response);
                    if (jsonObject.getInt("status") == 1) {
                        JSONObject data = jsonObject.getJSONObject("data");
                        mTextBanlance.setText("￥" + data.getString("balance"));
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });
    }



    private void startPay(final String type) {
        Map<String, String> parmas = new HashMap<>();
        parmas.put("access_token", "02c8b29f1b09833e43a37c770a87db23");
        parmas.put("osn", orderCode);
        parmas.put("out_trade_type", type);
        parmas.put("pay_source", "APP");
        OkHttpUtils.post().url(Constant.START_PAY).params(parmas)
                .build().execute(new StringCallback() {
            @Override
            public void onError(Call call, Exception e, int id) {

            }

            @Override
            public void onResponse(String response, int id) {
                try {
                    JSONObject json = new JSONObject(response);
                    if (json.getInt("status") == 1) {
                        if (type.equals("0")) {
                            JSONObject orderInfo = json.getJSONObject("msg");
                            PayReq request = new PayReq();
                            request.appId = WX_APP_ID_ONE;
                            request.partnerId = orderInfo.getString("partnerid");
                            request.prepayId = orderInfo.getString("prepayid");
                            request.packageValue = "Sign=WXPay";
                            request.nonceStr = orderInfo.getString("noncestr");
                            request.timeStamp = "" + orderInfo.getInt("timestamp");
                            request.sign = orderInfo.getString("sign");
                            if (request.checkArgs()) {
                                iwxapi.sendReq(request);
                            } else {
                                ToastUtils.showShort(SurePayActivity.this,"参数错误");
                            }
                        } else if (type.equals("1")) {
                            String info = json.getString("data");
                            startPayByZHB(info);
                        } else {
                            PayResult result = new Gson().fromJson(response, PayResult.class);
                            Intent intent = new Intent(SurePayActivity.this, PaySuccessActivity.class);
                            intent.putExtra("order", result.getData().getOsn());
                            startActivity(intent);
                        }
                    } else {
                        ToastUtils.showShort(SurePayActivity.this, json.getString("msg"));
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    private void startPayByZHB(final String info) {
        Runnable payRunnable = new Runnable() {
            @Override
            public void run() {
                PayTask alipay = new PayTask(SurePayActivity.this);
                Map<String, String> result = alipay.payV2(info, true);
                Message msg = new Message();
                msg.what = SDK_PAY_FLAG;
                msg.obj = result;
                mHandler.sendMessage(msg);
            }
        };
        Thread payThread = new Thread(payRunnable);
        payThread.start();
    }

    private Handler mHandler = new Handler() {
        @SuppressWarnings("unused")
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case SDK_PAY_FLAG: {
                    @SuppressWarnings("unchecked")
                    AliPayResults payResult = new AliPayResults((Map<String, String>) msg.obj);
                    /**
                     对于支付结果，请商户依赖服务端的异步通知结果。同步通知结果，仅作为支付结束的通知。
                     */
                    String resultInfo = payResult.getResult();// 同步返回需要验证的信息
                    String resultStatus = payResult.getResultStatus();
                    // 判断resultStatus 为9000则代表支付成功
                    if (TextUtils.equals(resultStatus, "9000")) {
                        // 该笔订单是否真实支付成功，需要依赖服务端的异步通知。
                        ToastUtils.showShort(SurePayActivity.this, "支付成功");
                        Intent intent = new Intent(SurePayActivity.this,PaySuccessActivity.class);
                        intent.putExtra("order",orderCode);
                        startActivity(intent);
                    } else {
                        ToastUtils.showShort(SurePayActivity.this, "支付失败");
                    }
                    break;
                }
                default:
                    break;
            }
        }
    };

    private boolean isAppInstalled(String uri) {
        PackageManager pm = SurePayActivity.this.getPackageManager();
        boolean installed = false;
        try {
            pm.getPackageInfo(uri, PackageManager.GET_ACTIVITIES);
            installed = true;
        } catch (PackageManager.NameNotFoundException e) {
            installed = false;
        }
        return installed;
    }
}
