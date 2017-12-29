package com.wlyilai.weilaibao.activity;

import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.wlyilai.weilaibao.R;

/**
 * @author: captain
 * Time:  2017/12/16 0016
 * Describe:
 */
public class UserInfoActivity extends BaseActivity implements View.OnClickListener {
    private ImageView mImgBack;
    private TextView mTxtTitle,mTxtRecommond,mUserName,mUserPhone,mShopRecommond,mBank,mBankCode,mWxCode,mBankName;
    private LinearLayout mLinearBank,mLinearWX,mLinearRecommend;
    private PopupWindow popupWindow;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_info);
        initView();
        initEvent();
    }

    private void initView() {
        mImgBack = (ImageView) findViewById(R.id.imgBack);
        mImgBack.setVisibility(View.VISIBLE);
        mTxtTitle = (TextView) findViewById(R.id.txtTitle);
        mTxtTitle.setText("用户信息");
        mTxtRecommond = (TextView) findViewById(R.id.txtRecommond);
        mUserName = (TextView) findViewById(R.id.userName);
        mUserPhone = (TextView) findViewById(R.id.userPhone);
        mShopRecommond = (TextView) findViewById(R.id.shopRecommond);
        mBank = (TextView) findViewById(R.id.bank);
        mBankCode = (TextView) findViewById(R.id.bankCode);
        mWxCode = (TextView) findViewById(R.id.wxCode);
        mLinearBank= (LinearLayout)findViewById(R.id.linearBank);
        mLinearWX= (LinearLayout)findViewById(R.id.linearWX);
        mLinearRecommend= (LinearLayout)findViewById(R.id.linearRecommend);
    }

    private void initEvent() {
        mImgBack.setOnClickListener(this);
        mLinearBank.setOnClickListener(this);
        mLinearWX.setOnClickListener(this);
        mLinearRecommend.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.imgBack:
                finish();
                break;
            case R.id.linearBank:
                showPoprpWindow();
                break;
            case R.id.linearWX:
                loginByWX();
                break;
            case R.id.chooiceBank:
                displayDialog();
                break;
            case R.id.linearRecommend:
                startActivity(new Intent(UserInfoActivity.this,MyTeamActivity.class));
                break;
        }

    }

    private void displayDialog() {
       final String [] str = {"中国银行","中国建设银行","中国工商银行","中国农业银行","浦发银行","招商银行","交通银行","不知什么鬼银行"};
        AlertDialog.Builder builder =new AlertDialog.Builder(UserInfoActivity.this);
        builder.setSingleChoiceItems(str, 0, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                mBankName.setText(str[which]);
                dialog.dismiss();
            }
        });
        Dialog dialog = builder.create();
        dialog.show();
    }

    /**
     * 微信登陆
     */
    private void loginByWX() {
    }

    private void showPoprpWindow() {
        View layout = View.inflate(UserInfoActivity.this, R.layout.layout_modifi_bank, null);
        popupWindow = new PopupWindow(layout, ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        popupWindow.setFocusable(true); // 点击空白处时，隐藏掉POP窗口
        popupWindow.setOutsideTouchable(true); // 点击外部时，隐藏掉POP窗口
        setBackgroundAlpha(0.5f);//设置屏幕透明度
        popupWindow.setBackgroundDrawable(new BitmapDrawable());
        popupWindow.setAnimationStyle(R.style.popwin_anim_style);
        popupWindow.showAtLocation(UserInfoActivity.this.findViewById(R.id.main), Gravity.BOTTOM, 20, 0);
        LinearLayout chooiceBank = (LinearLayout) layout.findViewById(R.id.chooiceBank);
        chooiceBank.setOnClickListener(this);
        mBankName =(TextView)layout.findViewById(R.id.bankName);
        popupWindow.setOnDismissListener(new PopupWindow.OnDismissListener() {
            @Override
            public void onDismiss() {
                popupWindow.dismiss();
                setBackgroundAlpha(1.0f);
            }
        });
    }

    public void setBackgroundAlpha(float bgAlpha) {
        WindowManager.LayoutParams lp = UserInfoActivity.this.getWindow()
                .getAttributes();
        lp.alpha = bgAlpha;
        UserInfoActivity.this.getWindow().setAttributes(lp);
    }


}
