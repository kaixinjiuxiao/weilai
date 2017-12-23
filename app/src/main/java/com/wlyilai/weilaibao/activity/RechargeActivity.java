package com.wlyilai.weilaibao.activity;

import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.wlyilai.weilaibao.R;

/**
 * @author: captain
 * Time:  2017/12/23 0023
 * Describe:
 */
public class RechargeActivity extends BaseActivity implements View.OnClickListener {
    private ImageView mImgBack, mImgWx, mImgAli;
    private TextView mTitle, mPhone, mName, mCZPice, mTxt20, mTxt50, mTxt100, mTexChuZhi1, mTexChuZhi2, mTexChuZhi13;
    private RelativeLayout mRelativeTwenty, mRelativeFifty, mRelativeHundred;
    private PopupWindow popupWindow;
    private String cash;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recharge);
        initView();
        initEvent();
    }

    private void initView() {
        mImgBack = (ImageView) findViewById(R.id.imgBack);
        mImgBack.setVisibility(View.VISIBLE);
        mTitle = (TextView) findViewById(R.id.txtTitle);
        mPhone = (TextView) findViewById(R.id.phone);
        mName = (TextView) findViewById(R.id.name);
        mTxt20 = (TextView) findViewById(R.id.txt20);
        mTxt50 = (TextView) findViewById(R.id.txt50);
        mTxt100 = (TextView) findViewById(R.id.txt100);
        mTexChuZhi1 = (TextView) findViewById(R.id.texChuZhi1);
        mTexChuZhi2 = (TextView) findViewById(R.id.texChuZhi2);
        mTexChuZhi13 = (TextView) findViewById(R.id.texChuZhi13);
        mTitle.setText("充值");
        mRelativeTwenty = (RelativeLayout) findViewById(R.id.twenty);
        mRelativeFifty = (RelativeLayout) findViewById(R.id.fifty);
        mRelativeHundred = (RelativeLayout) findViewById(R.id.hundred);

    }

    private void initEvent() {
        mImgBack.setOnClickListener(this);
        mRelativeTwenty.setOnClickListener(this);
        mRelativeFifty.setOnClickListener(this);
        mRelativeHundred.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.imgBack:
                break;
            case R.id.twenty:
                selected();
                mRelativeTwenty.setSelected(true);
                mTxt20.setSelected(true);
                mTexChuZhi1.setSelected(true);
                cash = "20.00";
                showPoprpWindow();
                setBackgroundAlpha(0.5f);
                break;
            case R.id.fifty:
                selected();
                mRelativeFifty.setSelected(true);
                mTxt50.setSelected(true);
                mTexChuZhi2.setSelected(true);
                cash = "50.00";
                showPoprpWindow();
                setBackgroundAlpha(0.5f);
                break;
            case R.id.hundred:
                selected();
                mRelativeHundred.setSelected(true);
                mTxt100.setSelected(true);
                mTexChuZhi13.setSelected(true);
                cash = "100.00";
                showPoprpWindow();
                setBackgroundAlpha(0.5f);
                break;
            case R.id.imgCancel:
                popupWindow.dismiss();
                setBackgroundAlpha(1.0f);
                break;
            case R.id.linearALi:
                Log.e("tag","点击了");
                mImgAli.setVisibility(View.VISIBLE);
                mImgWx.setVisibility(View.GONE);
                break;
            case R.id.linearWX:
                mImgAli.setVisibility(View.GONE);
                mImgWx.setVisibility(View.VISIBLE);
                break;
            case R.id.btnSure:
                break;
        }
    }

    public void selected() {
        mTxt20.setSelected(false);
        mTexChuZhi1.setSelected(false);
        mTxt50.setSelected(false);
        mTexChuZhi2.setSelected(false);
        mTxt100.setSelected(false);
        mTexChuZhi13.setSelected(false);
        mRelativeTwenty.setSelected(false);
        mRelativeFifty.setSelected(false);
        mRelativeHundred.setSelected(false);
    }


    private void showPoprpWindow() {
        View layout = View.inflate(RechargeActivity.this, R.layout.layout_sure_exchagre, null);
        popupWindow = new PopupWindow(layout, ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        popupWindow.setFocusable(true); // 点击空白处时，隐藏掉POP窗口
        popupWindow.setOutsideTouchable(true); // 点击外部时，隐藏掉POP窗口
        setBackgroundAlpha(0.5f);//设置屏幕透明度
        popupWindow.setBackgroundDrawable(new BitmapDrawable());
        popupWindow.showAtLocation(RechargeActivity.this.findViewById(R.id.main), Gravity.BOTTOM, 20, 0);
        ImageView imgCancel = (ImageView) layout.findViewById(R.id.imgCancel);
        imgCancel.setOnClickListener(this);
        mCZPice = (TextView) layout.findViewById(R.id.czPrice);
        mImgWx = (ImageView) layout.findViewById(R.id.imgWx);
        mImgAli = (ImageView) layout.findViewById(R.id.imgAli);
        LinearLayout linearWx = (LinearLayout) layout.findViewById(R.id.linearWX);
        LinearLayout linearAli = (LinearLayout) layout.findViewById(R.id.linearALi);
        Button btnSure = (Button) layout.findViewById(R.id.btnSure);
        linearWx.setOnClickListener(this);
        linearAli.setOnClickListener(this);
        btnSure.setOnClickListener(this);
        mCZPice.setText(cash);
        popupWindow.setOnDismissListener(new PopupWindow.OnDismissListener() {
            @Override
            public void onDismiss() {
                popupWindow.dismiss();
                setBackgroundAlpha(1.0f);
            }
        });
    }

    public void setBackgroundAlpha(float bgAlpha) {
        WindowManager.LayoutParams lp = RechargeActivity.this.getWindow()
                .getAttributes();
        lp.alpha = bgAlpha;
        RechargeActivity.this.getWindow().setAttributes(lp);
    }

}
