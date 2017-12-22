package com.wlyilai.weilaibao.activity;

import android.content.Intent;
import android.graphics.Paint;
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
import com.wlyilai.weilaibao.entry.SureBuy;
import com.wlyilai.weilaibao.utils.Constant;
import com.wlyilai.weilaibao.utils.ToastUtils;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import org.json.JSONException;
import org.json.JSONObject;

import okhttp3.Call;

/**
 * @author: captain
 * Time:  2017/12/9 0009
 * Describe:
 */
public class GroupDetailsActivity extends BaseActivity implements View.OnClickListener {
    private PopupWindow popupWindow,shareWindow;
    private ImageView mBack, mShare, mGoodsImg;
    private TextView mTitle, goodName, newPrice, oldPrice, goodsPeriods, goodsAllNumber, goodsSingle, goodsSurplus, shopAddress, rightNow;
    private ProgressBar goodsProgress;
    private LinearLayout linearKefu, linearShop, linearEnter;
    private TextView mBuyNumber;
    private int n, singleNumber;
    private String mGoodsId,mOldPrice,mNewPrice,mImgUrl,mShopId,mShopName;
    private TextView mYuanjia;
    private TextView mDanjia;
    private TextView mTotalPrice;

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
        mGoodsId = getIntent().getStringExtra("id");
        getDetails(mGoodsId);
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
                Log.e("tag","团"+response);
                GoodsDetails details = new Gson().fromJson(response,GoodsDetails.class);
                if(details.getStatus()==1){
                    Glide.with(GroupDetailsActivity.this).load(details.getData().getGimg()).into(mGoodsImg);
                    mImgUrl = details.getData().getGimg();
                    goodName.setText(details.getData().getGname());
                    newPrice.setText(details.getData().getGteam_price());
                    mNewPrice = details.getData().getGteam_price();
                    oldPrice.setText(details.getData().getGprice());
                    oldPrice.getPaint().setFlags(Paint. STRIKE_THRU_TEXT_FLAG);
                    mShopId = details.getData().getSid();
                    mShopName=details.getData().getGname();
                    mOldPrice= details.getData().getGprice();
                    goodsPeriods.setText(details.getData().getSid());
                    goodsAllNumber.setText(details.getData().getGnum()+"件");
                    goodsSingle.setText(details.getData().getGpay_limit()+"件");
                    n = Integer.parseInt(details.getData().getGpay_limit());
                    singleNumber = Integer.parseInt(details.getData().getGpay_limit());
                    int shengyu = Integer.parseInt(details.getData().getGnum())-Integer.parseInt(details.getData().getGpay_num());
                    goodsSurplus.setText("已团"+details.getData().getGpay_num()+"件，还差"+String.valueOf(shengyu)+"件。");
                    goodsProgress.setMax(Integer.parseInt(details.getData().getGnum()));
                    goodsProgress.setSecondaryProgress(Integer.parseInt(details.getData().getGpay_num()));
                    shopAddress.setText(details.getData().getSname());
                }
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
        Glide.with(GroupDetailsActivity.this).load(mImgUrl).into(img);
        ImageView imgCancel = (ImageView) layout.findViewById(R.id.imgCancel);
        mTotalPrice = (TextView) layout.findViewById(R.id.price);
        Double total = n*(Double.parseDouble(mNewPrice));
        mTotalPrice.setText("¥"+total);
        mDanjia = (TextView) layout.findViewById(R.id.danjia);
        mDanjia.setText("¥"+mNewPrice+"/件");
        mYuanjia = (TextView) layout.findViewById(R.id.oldPrice);
        mYuanjia.setText("¥"+mOldPrice);
        TextView reduce = (TextView) layout.findViewById(R.id.reduce);
        mBuyNumber = (TextView) layout.findViewById(R.id.number);
        mBuyNumber.setText(n+"");
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
                showSharePopupWindow();
                break;
            case R.id.linearEnter:
                Intent intent = new Intent(GroupDetailsActivity.this,ShopGoodsActivity.class);
                intent.putExtra("sid",mShopId);
                intent.putExtra("shopName",mShopName);
                startActivity(intent);
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
                mTotalPrice.setText("¥"+n*(Double.parseDouble(mNewPrice)));
                break;
            case R.id.reduce:
                if (n > singleNumber) {
                    n--;
                    mBuyNumber.setText(n + "");
                    mTotalPrice.setText("¥"+n*(Double.parseDouble(mNewPrice)));
                }else{
                    ToastUtils.showShort(GroupDetailsActivity.this,"起购量为"+singleNumber+"件");
                }
                break;
            case R.id.imgCancel:
                popupWindow.dismiss();
                setBackgroundAlpha(1.0f);
                break;
            case R.id.btnSure:
                buyGoods();
                popupWindow.dismiss();
                break;
            case R.id.share_qq:
                //shareToQQ();
                break;
            case R.id.share_weixin:
                // shareToWX(SHARE_FRIEND);
                break;
            case R.id.share_pyquan:
                //shareToWX(SHARE_FRIEND_CIRCLE);
                break;
            case R.id.linear_cancel:
                shareWindow.dismiss();
                break;
            default:
                break;
        }
    }

    private void showSharePopupWindow() {
        View layout = View.inflate(GroupDetailsActivity.this, R.layout.share_bottom, null);
        shareWindow = new PopupWindow(layout, ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        shareWindow.setFocusable(true); // 点击空白处时，隐藏掉POP窗口
        shareWindow.setOutsideTouchable(true); // 点击外部时，隐藏掉POP窗口
        shareWindow.setBackgroundDrawable(new BitmapDrawable());
        shareWindow.showAtLocation(GroupDetailsActivity.this.findViewById(R.id.main), Gravity.BOTTOM, 20, 0);
        LinearLayout qqShare = (LinearLayout) layout.findViewById(R.id.share_qq);
        LinearLayout wxShare = (LinearLayout) layout.findViewById(R.id.share_weixin);
        LinearLayout pyqShare = (LinearLayout) layout.findViewById(R.id.share_pyquan);
        LinearLayout cancel = (LinearLayout) layout.findViewById(R.id.linear_cancel);
        qqShare.setOnClickListener(this);
        wxShare.setOnClickListener(this);
        pyqShare.setOnClickListener(this);
        cancel.setOnClickListener(this);
        shareWindow.setOnDismissListener(new PopupWindow.OnDismissListener() {
            @Override
            public void onDismiss() {
                shareWindow.dismiss();
            }
        });
    }

    private void buyGoods() {
        OkHttpUtils.post().url(Constant.BUY_GOODS)
                .addParams("access_token","02c8b29f1b09833e43a37c770a87db23")
                .addParams("id",mGoodsId)
                .addParams("buy_num",mBuyNumber.getText().toString())
                .build().execute(new StringCallback() {
            @Override
            public void onError(Call call, Exception e, int id) {
                Log.e("tag","错误"+e.toString());
            }

            @Override
            public void onResponse(String response, int id) {
                Log.e("tag","购买"+response);
                try {
                    JSONObject jsonObject = new JSONObject(response);
                    if(jsonObject.getInt("status")==1){
                        SureBuy data= new Gson().fromJson(response,SureBuy.class);
                        Intent intent = new Intent(GroupDetailsActivity.this, SureOrderActivity.class);
                        Bundle bundle = new Bundle();
                        bundle.putSerializable("data",data);
                        intent.putExtra("goods",bundle);
                        startActivity(intent);
                    }else if(jsonObject.getInt("status")==0){
                        ToastUtils.showShort(GroupDetailsActivity.this,jsonObject.getString("msg"));
                        setBackgroundAlpha(1.0f);
                        //popupWindow.dismiss();
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });
    }
}
