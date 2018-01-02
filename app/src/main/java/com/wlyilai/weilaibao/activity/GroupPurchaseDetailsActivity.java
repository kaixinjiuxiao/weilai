package com.wlyilai.weilaibao.activity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.google.gson.Gson;
import com.tencent.mm.opensdk.modelmsg.SendMessageToWX;
import com.tencent.mm.opensdk.modelmsg.WXMediaMessage;
import com.tencent.mm.opensdk.modelmsg.WXWebpageObject;
import com.tencent.mm.opensdk.openapi.IWXAPI;
import com.tencent.mm.opensdk.openapi.WXAPIFactory;
import com.wlyilai.weilaibao.R;
import com.wlyilai.weilaibao.adapter.JoinGroupAdapter;
import com.wlyilai.weilaibao.entry.GroupDetails;
import com.wlyilai.weilaibao.utils.ActivityManager;
import com.wlyilai.weilaibao.utils.Constant;
import com.wlyilai.weilaibao.utils.PreferenceUtil;
import com.wlyilai.weilaibao.utils.ToastUtils;
import com.wlyilai.weilaibao.utils.Util;
import com.wlyilai.weilaibao.view.PullLoadMoreRecyclerView;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import okhttp3.Call;

/**
 * @author: captain
 * Time:  2017/12/12 0012
 * Describe:
 */
public class GroupPurchaseDetailsActivity extends BaseActivity implements PullLoadMoreRecyclerView.PullLoadMoreListener, View.OnClickListener {
    private static final String QQ_APP_ID = "1106264324";
    private static final String WX_APP_ID_ONE = "wx27d2fb7168caf7e0";
    public static final int SHARE_FRIEND = 1;
    public static final int SHARE_FRIEND_CIRCLE = 2;
    private static final int THUMB_SIZE = 150;
    private IWXAPI iwxapi;
    private ProgressBar progress;
    private ImageView mBack;
    private TextView mTitle, mState, mShare,mShenyu;
    private PullLoadMoreRecyclerView mPullLoadMoreRecyclerView;
    private JoinGroupAdapter mAdapter;
    private RecyclerView mRecyclerView;
    private List<GroupDetails.DataBean.OrderDetailBean> mList = new ArrayList<>();
    private String mGrid,mToken;
    private PopupWindow popupWindow;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_group_purchase_details);
        initView();
        initEvent();
    }

    private void initView() {
        PreferenceUtil.init(GroupPurchaseDetailsActivity.this);
        iwxapi = WXAPIFactory.createWXAPI(GroupPurchaseDetailsActivity.this, WX_APP_ID_ONE, true);
        iwxapi.registerApp(WX_APP_ID_ONE);
        mBack = (ImageView) findViewById(R.id.imgBack);
        mBack.setVisibility(View.VISIBLE);
        mTitle = (TextView) findViewById(R.id.txtTitle);
        mState = (TextView) findViewById(R.id.state);
        mShare = (TextView) findViewById(R.id.txtShare);
        mShenyu = (TextView) findViewById(R.id.shenyu);
        mTitle.setText("团详情");
        mGrid = getIntent().getStringExtra("grid");
        String code = PreferenceUtil.getString("token",null);
        if(TextUtils.isEmpty(code)){
            PreferenceUtil.removeAll();
            ActivityManager.getInstance().finishAllActivity();
            Intent intent = new Intent(GroupPurchaseDetailsActivity.this, LoginActivity.class);
            startActivity(intent);
            finish();
        }else{
            mToken=code;
        }
        getDetails(mGrid);
        progress = (ProgressBar) findViewById(R.id.progress);
        mPullLoadMoreRecyclerView = (PullLoadMoreRecyclerView) findViewById(R.id.pullLoadMore);
        mRecyclerView = mPullLoadMoreRecyclerView.getRecyclerView();
        mRecyclerView.setVerticalScrollBarEnabled(true);
        mPullLoadMoreRecyclerView.setLinearLayout();
        mPullLoadMoreRecyclerView.setSwipeRefreshEnable(false);
        mPullLoadMoreRecyclerView.setPullRefreshEnable(false);
        mPullLoadMoreRecyclerView.setPushRefreshEnable(false);
        mPullLoadMoreRecyclerView.setOnPullLoadMoreListener(this);
        mAdapter = new JoinGroupAdapter(this, mList);
    }

    private void initEvent() {
        mBack.setOnClickListener(this);
        mShare.setOnClickListener(this);
    }

    private void getDetails(String grid) {
        OkHttpUtils.post().url(Constant.GROUP_DETAILS).
                addParams("access_token", mToken)
                .addParams("grid", grid).build().execute(new StringCallback() {
            @Override
            public void onError(Call call, Exception e, int id) {

            }

            @Override
            public void onResponse(String response, int id) {
                Log.e("tag","团详情"+response);
                try {
                    JSONObject json = new JSONObject(response);
                    if (json.getInt("status") == 1) {
                        GroupDetails details = new Gson().fromJson(response, GroupDetails.class);
                        int shenyu = Integer.parseInt(details.getData().getGnum())-Integer.parseInt(details.getData().getGpay_num());
                       mShenyu.setText("拼团剩余"+shenyu+"件");
                        mState.setText("已团" + details.getData().getGpay_num() + "件，还剩" + shenyu + "件");
                        progress.setMax(Integer.parseInt(details.getData().getGnum()));
                        progress.setProgress(Integer.parseInt(details.getData().getGpay_num()));
                        for (int i = 0; i < details.getData().getOrder_detail().size(); i++) {
                            mList.add(details.getData().getOrder_detail().get(i));
                        }
                        mPullLoadMoreRecyclerView.setAdapter(mAdapter);
                        mPullLoadMoreRecyclerView.setPullLoadMoreCompleted();
                    } else {
                        ToastUtils.showShort(GroupPurchaseDetailsActivity.this, json.getString("msg"));
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });
    }


    @Override
    public void onRefresh() {
        mPullLoadMoreRecyclerView.setPullLoadMoreCompleted();
    }

    @Override
    public void onLoadMore() {
        mPullLoadMoreRecyclerView.setPullLoadMoreCompleted();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.imgBack:
                finish();
                break;
            case R.id.txtShare:
                showPoprpWindow();
                break;
            case R.id.share_qq:
                //shareToQQ();
                break;
            case R.id.share_weixin:
               shareToWX(SHARE_FRIEND);
                break;
            case R.id.share_pyquan:
                shareToWX(SHARE_FRIEND_CIRCLE);
                break;
            case R.id.linear_cancel:
                popupWindow.dismiss();
                break;
            default:
                break;
        }
    }

    private void shareToWX(int code) {
        WXWebpageObject webPage = new WXWebpageObject();
        webPage.webpageUrl = "http://test.mgbh.wlylai.com";
        final WXMediaMessage msg = new WXMediaMessage(webPage);
        msg.title ="测试";
        msg.description = "湖南未来已来科技有限公司";
            Bitmap bmp = BitmapFactory.decodeResource(getResources(), R.mipmap.icon_logo);
            Bitmap thumbBmp = Bitmap.createScaledBitmap(bmp, THUMB_SIZE, THUMB_SIZE, true);
            bmp.recycle();
            msg.thumbData = Util.bmpToByteArray(thumbBmp, true);
            SendMessageToWX.Req req = new SendMessageToWX.Req();
            req.transaction = buildTransaction("webpage");
            req.message = msg;
            if (code == SHARE_FRIEND) {//分享至微信好友
                req.scene = SendMessageToWX.Req.WXSceneSession;
            } else if (code == SHARE_FRIEND_CIRCLE) {//分享至微信朋友圈
                req.scene = SendMessageToWX.Req.WXSceneTimeline;
            }
            iwxapi.sendReq(req);
        popupWindow.dismiss();
    }
    public String buildTransaction(final String type) {
        return (type == null) ? String.valueOf(System.currentTimeMillis()) : type + System.currentTimeMillis();
    }
    private void showPoprpWindow() {
        View layout = View.inflate(GroupPurchaseDetailsActivity.this, R.layout.share_bottom, null);
        popupWindow = new PopupWindow(layout, ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        popupWindow.setFocusable(true); // 点击空白处时，隐藏掉POP窗口
        popupWindow.setOutsideTouchable(true); // 点击外部时，隐藏掉POP窗口
        popupWindow.setBackgroundDrawable(new BitmapDrawable());
        popupWindow.showAtLocation(GroupPurchaseDetailsActivity.this.findViewById(R.id.main), Gravity.BOTTOM, 20, 0);
        LinearLayout qqShare = (LinearLayout) layout.findViewById(R.id.share_qq);
        LinearLayout wxShare = (LinearLayout) layout.findViewById(R.id.share_weixin);
        LinearLayout pyqShare = (LinearLayout) layout.findViewById(R.id.share_pyquan);
        LinearLayout cancel = (LinearLayout) layout.findViewById(R.id.linear_cancel);
        qqShare.setOnClickListener(this);
        wxShare.setOnClickListener(this);
        pyqShare.setOnClickListener(this);
        cancel.setOnClickListener(this);
        popupWindow.setOnDismissListener(new PopupWindow.OnDismissListener() {
            @Override
            public void onDismiss() {
                popupWindow.dismiss();
            }
        });
    }
}
