package com.wlyilai.weilaibao.activity;

import android.content.Intent;
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
import android.widget.ProgressBar;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.google.gson.Gson;
import com.wlyilai.weilaibao.R;
import com.wlyilai.weilaibao.entry.GoodsDetails;
import com.wlyilai.weilaibao.utils.Constant;
import com.wlyilai.weilaibao.utils.ToastUtils;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import okhttp3.Call;

/**
 * @author: captain
 * Time:  2017/12/9 0009
 * Describe:
 */
public class GroupDetailsActivity extends BaseActivity implements View.OnClickListener {
    private PopupWindow popupWindow;
    private ImageView mBack, mShare, mGoodsImg;
    private TextView mTitle, goodName, newPrice, oldPrice, goodsPeriods, goodsAllNumber, goodsSingle, goodsSurplus, shopAddress, rightNow;
    private ProgressBar goodsProgress;
    private LinearLayout linearKefu, linearShop, linearEnter;
    private TextView mBuyNumber;
    private int n = 2;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_group_details);
        initView();
        initEvent();
    }

    private void initView() {
        mBack = (ImageView) findViewById(R.id.imgBack);
        mBack.setVisibility(View.VISIBLE);
        mShare = (ImageView) findViewById(R.id.imgMore);
        mShare.setVisibility(View.VISIBLE);
        mTitle = (TextView) findViewById(R.id.txtTitle);
        mTitle.setText("团购详情");
        mGoodsImg = (ImageView) findViewById(R.id.goodsImg);
        goodName = (TextView) findViewById(R.id.goodName);
        newPrice = (TextView) findViewById(R.id.newPrice);
        oldPrice = (TextView) findViewById(R.id.oldPrice);
        goodsPeriods = (TextView) findViewById(R.id.goodsPeriods);
        goodsAllNumber = (TextView) findViewById(R.id.goodsAllNumber);
        goodsSingle = (TextView) findViewById(R.id.goodsSingle);
        goodsSurplus = (TextView) findViewById(R.id.goodsSurplus);
        shopAddress = (TextView) findViewById(R.id.shopAddress);
        rightNow = (TextView) findViewById(R.id.rightNow);
        goodsProgress = (ProgressBar) findViewById(R.id.goodsProgress);
        linearKefu = (LinearLayout) findViewById(R.id.linearKefu);
        linearShop = (LinearLayout) findViewById(R.id.linearShop);
        linearEnter = (LinearLayout) findViewById(R.id.linearEnter);
        String id = getIntent().getStringExtra("id");
        getDetails(id);
    }

    private void getDetails(String id) {
        OkHttpUtils.post().url(Constant.GOODS_DETAILS)
                .addParams("id",id).addParams("access_token","02c8b29f1b09833e43a37c770a87db23")
                .build().execute(new StringCallback() {
            @Override
            public void onError(Call call, Exception e, int id) {

            }

            @Override
            public void onResponse(String response, int id) {
                GoodsDetails details = new Gson().fromJson(response,GoodsDetails.class);
                if(details.getStatus()==1){
                    Glide.with(GroupDetailsActivity.this).load(details.getData().getGimg()).into(mGoodsImg);
                }
                Log.e("tag","商品详情"+response);
            }
        });
    }

    private void initEvent() {
        mBack.setOnClickListener(this);
        mShare.setOnClickListener(this);
        linearKefu.setOnClickListener(this);
        linearShop.setOnClickListener(this);
        linearEnter.setOnClickListener(this);
        rightNow.setOnClickListener(this);
    }

    private void showPoprpWindow() {
        View layout = View.inflate(GroupDetailsActivity.this, R.layout.layout_sure_buy, null);
        popupWindow = new PopupWindow(layout, ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        popupWindow.setFocusable(true); // 点击空白处时，隐藏掉POP窗口
        popupWindow.setOutsideTouchable(true); // 点击外部时，隐藏掉POP窗口
        setBackgroundAlpha(0.5f);//设置屏幕透明度
        popupWindow.setBackgroundDrawable(new BitmapDrawable());
        popupWindow.showAtLocation(GroupDetailsActivity.this.findViewById(R.id.main), Gravity.BOTTOM, 20, 0);
        ImageView img = (ImageView) layout.findViewById(R.id.goodsImg);
        ImageView imgCancel = (ImageView) layout.findViewById(R.id.imgCancel);
        TextView price = (TextView) layout.findViewById(R.id.price);
        TextView danjia = (TextView) layout.findViewById(R.id.danjia);
        TextView oldPrice = (TextView) layout.findViewById(R.id.oldPrice);
        TextView reduce = (TextView) layout.findViewById(R.id.reduce);
        mBuyNumber = (TextView) layout.findViewById(R.id.number);
        TextView add = (TextView) layout.findViewById(R.id.add);
        Button sure = (Button) layout.findViewById(R.id.btnSure);
        imgCancel.setOnClickListener(this);
        sure.setOnClickListener(this);
        add.setOnClickListener(this);
        reduce.setOnClickListener(this);
        popupWindow.setOnDismissListener(new PopupWindow.OnDismissListener() {
            @Override
            public void onDismiss() {
                setBackgroundAlpha(1.0f);
                popupWindow.dismiss();
            }
        });
    }


    public void setBackgroundAlpha(float bgAlpha) {
        WindowManager.LayoutParams lp = GroupDetailsActivity.this.getWindow()
                .getAttributes();
        lp.alpha = bgAlpha;
        GroupDetailsActivity.this.getWindow().setAttributes(lp);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.imgBack:
                finish();
                break;
            case R.id.imgMore:
                break;
            case R.id.linearEnter:
                break;
            case R.id.linearKefu:
                break;
            case R.id.linearShop:
                finish();
                break;
            case R.id.rightNow:
                showPoprpWindow();
                setBackgroundAlpha(0.5f);
                break;
            case R.id.add:
                n++;
                mBuyNumber.setText(n + "");
                break;
            case R.id.reduce:
                n = Integer.parseInt(mBuyNumber.getText().toString());
                if (n > 2) {
                    n--;
                    mBuyNumber.setText(n + "");
                }else{
                    ToastUtils.showShort(GroupDetailsActivity.this,"起购量为两件");
                }
                break;
            case R.id.imgCancel:
                popupWindow.dismiss();
                setBackgroundAlpha(1.0f);
                break;
            case R.id.btnSure:
                Intent intent = new Intent(this, SureOrderActivity.class);
                startActivity(intent);
                popupWindow.dismiss();
                break;
            default:
                break;
        }
    }
}
