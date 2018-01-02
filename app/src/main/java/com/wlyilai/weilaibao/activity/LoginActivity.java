package com.wlyilai.weilaibao.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.wlyilai.weilaibao.R;
import com.wlyilai.weilaibao.utils.ActivityManager;
import com.wlyilai.weilaibao.utils.Constant;
import com.wlyilai.weilaibao.utils.NetWorkState;
import com.wlyilai.weilaibao.utils.PreferenceUtil;
import com.wlyilai.weilaibao.utils.ToastUtils;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import org.json.JSONException;
import org.json.JSONObject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import okhttp3.Call;

/**
 * @author: captain
 * Time:  2017/12/9 0009
 * Describe:
 */
public class LoginActivity extends BaseActivity {
    @BindView(R.id.imgBack)
    ImageView mImgBack;
    @BindView(R.id.txtTitle)
    TextView mTxtTitle;
    @BindView(R.id.txtRight)
    TextView mTxtRight;
    @BindView(R.id.btnNext)
    Button mBtnNext;
    @BindView(R.id.edtPhone)
    EditText mEdtPhone;
    @BindView(R.id.getCode)
    TextView mGetCode;
    public static int UPDATE = 60;
    private static Runnable mRun;
    private static Handler mHandler = new Handler();
    @BindView(R.id.edtCode)
    EditText mEdtCode;
    private boolean canClick = true;
    private AlertDialog mDialog;
    private EditText mEdtName;
    private String code,mPhone;
    private String firstInputName, firstInputPhone;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);
        init();
    }

    private void init() {
        PreferenceUtil.init(this);
      //  ActivityManager.getInstance().addActivity(this);
        mTxtTitle.setText("登录");
        mEdtPhone.setText(PreferenceUtil.getString("phone",null));
    }


    @OnClick({R.id.getCode, R.id.btnNext})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.getCode:
                if(NetWorkState.isNetWorkAvailabe(LoginActivity.this)){
                    getVerificationCode();
                }else{
                    ToastUtils.showShort(LoginActivity.this,"当前网络不可用，请检测网络连接");
                }
                break;
            case R.id.btnNext:
                if(NetWorkState.isNetWorkAvailabe(LoginActivity.this)){
                    startNext(mEdtPhone.getText().toString());
                }else{
                    ToastUtils.showShort(LoginActivity.this,"当前网络不可用，请检测网络连接");
                }
                break;
        }
    }

    /**
     *点击下一步
     * @param phone 手机号
     */
    private void startNext(String phone) {
//        if(TextUtils.isEmpty(mEdtPhone.getText().toString())){
//            ToastUtils.showShort(LoginActivity.this,"手机号码不能为空");
//            return;
//        }
//
//        if(TextUtils.isEmpty(mEdtCode.getText().toString())){
//            ToastUtils.showShort(LoginActivity.this,"验证码不能为空");
//            return;
//        }
//        if(TextUtils.isEmpty(code)||TextUtils.isEmpty(mPhone)){
//            ToastUtils.showShort(LoginActivity.this,"请先获取验证码");
//            return;
//        }
//        if(!mPhone.equals(mEdtPhone.getText().toString())){
//            ToastUtils.showShort(LoginActivity.this,"手机号码不一致");
//            return;
//        }
//        if (!code.equals(mEdtCode.getText().toString())) {
//            ToastUtils.showShort(LoginActivity.this,"验证码错误");
//            return;
//        }
        OkHttpUtils.post().url(Constant.LOGIN)
                .addParams("mobile", phone).build().execute(new StringCallback() {
            @Override
            public void onError(Call call, Exception e, int id) {
            }

            @Override
            public void onResponse(String response, int id) {
                Log.e("tag", "下一步信息：" + response);
                try {
                    JSONObject jsonObject = new JSONObject(response);
                    if (jsonObject.getInt("status") == 0) {
                        if (jsonObject.getInt("code") == 2) {
                            //新用户完善资料
                            ActivityManager.getInstance().addActivity(LoginActivity.this);
                            Intent intent = new Intent(LoginActivity.this, RegisterActivity.class);
                            intent.putExtra("phone", mEdtPhone.getText().toString());
                            startActivity(intent);
                        } else if (jsonObject.getInt("code") == 3) {
                            //验证数CDS用户
                            displayDialog();
                        }
                    } else if (jsonObject.getInt("status") == 1) {
                        //正常登陆成功
                        String code = jsonObject.getString("access_token");
                        PreferenceUtil.commitString("token",code);
                        Intent intent = new Intent(LoginActivity.this,MainActivity.class);
                        startActivity(intent);
                        finish();
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    private void displayDialog() {
        final AlertDialog.Builder builder = new AlertDialog.Builder(this,R.style.myCorDialog);
        View view = LayoutInflater.from(this).inflate(R.layout.layout_verification_name, null);
        builder.setView(view);
        mEdtName = (EditText) view.findViewById(R.id.edtName);
        Button btn = (Button) view.findViewById(R.id.btnLogin);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                verificationName();
            }
        });
        mDialog = builder.create();
        mDialog.show();
    }

    /**
     * 验证数钜宝用户
     */
    // TODO: 2017/12/28 0028   没有实际验证用户
    private void verificationName() {
        if (TextUtils.isEmpty(mEdtName.getText().toString())) {
            ToastUtils.showShort(LoginActivity.this, "请输入姓名");
            return;
        }
        verificationCDS(mEdtPhone.getText().toString(),mEdtName.getText().toString());
    }

    private void verificationCDS(String phone,String name) {
        OkHttpUtils.post().url(Constant.VER_NAME)
                .addParams("mobile", phone)
                .addParams("realname", name)
                .build().execute(new StringCallback() {
            @Override
            public void onError(Call call, Exception e, int id) {

            }

            @Override
            public void onResponse(String response, int id) {
                Log.e("tag", "ffgccf" + response);
                try {
                    JSONObject jsonObject = new JSONObject(response);
                    if(jsonObject.getInt("status")==1){

                    }else{
                        ToastUtils.showShort(LoginActivity.this,jsonObject.getString("msg"));
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    /**
     * 获取验证码
     */
    private void getVerificationCode() {
        if (TextUtils.isEmpty(mEdtPhone.getText().toString())) {
            ToastUtils.showShort(this, "手机号码不能为空");
            return;
        }
        if (canClick) {
            startTime();
            firstInputPhone = mEdtPhone.getText().toString();
            getSMSCode(firstInputPhone);
        }

    }

    /**
     * 获取验证码
     * @param phone
     */
    private void getSMSCode(final String phone) {
        OkHttpUtils.post()
                .url(Constant.GET_CODE)
                .addParams("mobile", phone).addParams("state", "2")
                .build().execute(new StringCallback() {

            @Override
            public void onError(Call call, Exception e, int id) {

            }

            @Override
            public void onResponse(String response, int id) {
                try {
                    JSONObject jsonObject = new JSONObject(response);
                    if (jsonObject.getInt("status") == 1) {
                        ToastUtils.showShort(LoginActivity.this, jsonObject.getString("msg"));
                        JSONObject data = jsonObject.getJSONObject("data");
                        code = data.getString("code");
                        mPhone = data.getString("mobile");
                    } else {
                        ToastUtils.showShort(LoginActivity.this, jsonObject.getString("msg"));
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    private void startTime() {
        canClick = false;
        mRun = new Runnable() {
            @Override
            public void run() {
                mGetCode.setText(UPDATE-- + "");
                if (UPDATE == 0) {
                    mGetCode.setClickable(true);
                    mGetCode.setText("发送验证码");
                    UPDATE = 60;
                    canClick = true;
                } else {
                    mHandler.postDelayed(this, 1000);
                }
            }
        };
        mHandler.post(mRun);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mHandler.removeCallbacks(mRun);
        UPDATE = 60;
    }
}
