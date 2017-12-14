package com.wlyilai.weilaibao.activity;

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
    private boolean canClick = true;
    private AlertDialog mDialog;
    private EditText mEdtName;
    private String code;
    private String firstInputName,firstInputPhone;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);
        init();
    }

    private void init() {
        mTxtTitle.setText("登录");
    }


    @OnClick({R.id.getCode, R.id.btnNext})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.getCode:
                getVerificationCode();
                break;
            case R.id.btnNext:

               // displayDialog();
               //startActivity(new Intent(LoginActivity.this,MainActivity.class));
               // startActivity(new Intent(this, RegisterActivity.class));
                aa();
               bb();
                break;
        }
    }

    private void aa() {
        OkHttpUtils.post().url("http://test.mgbh.wlylai.com/AppLogin/user_login")
                .addParams("mobile","15580993896").build().execute(new StringCallback() {
            @Override
            public void onError(Call call, Exception e, int id) {
                Log.e("tag","错五"+e.toString());
            }

            @Override
            public void onResponse(String response, int id) {
                Log.e("tag","sjkfhsjf"+response);
            }
        });
    }

    private void bb() {
        OkHttpUtils.post().url("http://test.mgbh.wlylai.com/AppLogin/user_register_cds")
                .addParams("mobile","18573180570")
                .addParams("realname","苏嗣武")
                .build().execute(new StringCallback() {
            @Override
            public void onError(Call call, Exception e, int id) {
                Log.e("tag","错五"+e.toString());
            }

            @Override
            public void onResponse(String response, int id) {
                Log.e("tag","ffgccf"+response);
            }
        });
    }


    private void displayDialog() {
        final AlertDialog.Builder builder =new AlertDialog.Builder(this);
        View view = LayoutInflater.from(this).inflate(R.layout.layout_verification_name,null);
        builder.setView(view);
        mEdtName = (EditText)view.findViewById(R.id.edtName);
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

    private void verificationName() {
        if(TextUtils.isEmpty(mEdtName.getText().toString())){
            ToastUtils.showShort(LoginActivity.this,"请输入姓名");
            return;
        }
        //startActivity(new Intent(LoginActivity.this,MainActivity.class));
        bb();
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

    private void getSMSCode(String phone) {
                OkHttpUtils.post()
                .url("http://test.mgbh.wlylai.com/AppLogin/send_sms")
                .addParams("mobile","15580993896").addParams("state","2")
                .build().execute(new StringCallback() {

            @Override
            public void onError(Call call, Exception e, int id) {

            }

            @Override
            public void onResponse(String response, int id) {
                try {
                    JSONObject jsonObject = new JSONObject(response);
                    if(jsonObject.getInt("status")==1){
                        ToastUtils.showShort(LoginActivity.this,jsonObject.getString("msg"));
                        JSONObject info = jsonObject.getJSONObject("data");
                        code = info.getString("verify");
                    }else{
                        ToastUtils.showShort(LoginActivity.this,jsonObject.getString("msg"));
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
