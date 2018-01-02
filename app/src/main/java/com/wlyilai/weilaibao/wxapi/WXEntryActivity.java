package com.wlyilai.weilaibao.wxapi;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;

import com.tencent.mm.opensdk.modelbase.BaseReq;
import com.tencent.mm.opensdk.modelbase.BaseResp;
import com.tencent.mm.opensdk.modelmsg.SendAuth;
import com.tencent.mm.opensdk.openapi.IWXAPI;
import com.tencent.mm.opensdk.openapi.IWXAPIEventHandler;
import com.tencent.mm.opensdk.openapi.WXAPIFactory;
import com.wlyilai.weilaibao.utils.ToastUtils;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import org.json.JSONException;
import org.json.JSONObject;

import okhttp3.Call;

/**
 * Created by dudu on 2016/11/24.
 */

public class WXEntryActivity extends Activity implements IWXAPIEventHandler {

    private final String TAG = this.getClass().getSimpleName();
    private static final String WX_APP_ID = "wx27d2fb7168caf7e0";
    private static final String WX_APP_SECRECT = "2ac57e8753d6bf930fe0be70feaaa6fa";
    private static final int RETURN_MSG_TYPE_LOGIN = 1;
    private static final int RETURN_MSG_TYPE_SHARE = 2;

    private IWXAPI mApi;
    private JSONObject wxLoginInfo;

    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mApi = WXAPIFactory.createWXAPI(this, WX_APP_ID, true);
        mApi.handleIntent(this.getIntent(), this);
        Log.e(TAG, "OK");
    }

    //微信发送的请求将回调到onReq方法
    @Override
    public void onReq(BaseReq baseReq) {

    }

    //发送到微信请求的响应结果
    @Override
    public void onResp(BaseResp resp) {
        switch (resp.errCode) {
            case BaseResp.ErrCode.ERR_OK:
                Log.e("tag","什么鬼"+resp.errStr);
                //发送成功
//                if (resp instanceof SendAuth.Resp) {
//                    SendAuth.Resp response = (SendAuth.Resp) resp;
//                    getAccess_token(response.code);
//                    return;
//                }
                switch (resp.getType()) {
                    case RETURN_MSG_TYPE_LOGIN:
                        //拿到了微信返回的code,立马再去请求access_token
                        String code = ((SendAuth.Resp) resp).code;
                        Log.e("tag " , code);
                        getAccess_token(code);
                        //就在这个地方，用网络库什么的或者自己封的网络api，发请求去咯，注意是get请求

                        break;

                    case RETURN_MSG_TYPE_SHARE:
                        ToastUtils.showShort(WXEntryActivity.this, "分享成功");
                        finish();
                        break;
                }
                break;
            case BaseResp.ErrCode.ERR_USER_CANCEL:
                if (RETURN_MSG_TYPE_SHARE == resp.getType()) {
                    ToastUtils.showShort(WXEntryActivity.this, "分享失败");
                } else {
                    ToastUtils.showShort(WXEntryActivity.this, "登陆失败");
                }
                break;
            case BaseResp.ErrCode.ERR_AUTH_DENIED:
                //发送被拒绝
                break;
            default:
                //发送返回
                break;
        }
        finish();
    }

    private void getAccess_token(final String code) {
        final String path = "https://api.weixin.qq.com/sns/oauth2/access_token?appid="
                + WX_APP_ID
                + "&secret="
                + WX_APP_SECRECT
                + "&code="
                + code
                + "&grant_type=authorization_code";
        OkHttpUtils.get().url(path).build().execute(new StringCallback() {
            @Override
            public void onError(Call call, Exception e, int id) {

            }

            @Override
            public void onResponse(String response, int id) {
                Log.e("tag","这里是什么信息"+response);
                try {
                    JSONObject jsonObject = new JSONObject(response);
                    if(jsonObject!=null){
                        String openid = jsonObject.getString("openid").trim();
                        String access_token = jsonObject.getString("access_token").trim();
                        getUserMesg(access_token, openid);
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });
//        new Thread() {
//            @Override
//            public void run() {
//                try {
//                    URL url = new URL(path);
//                    HttpsURLConnection conn = (HttpsURLConnection) url.openConnection();
//                    conn.setRequestMethod("GET");
//                    InputStream inputStream = conn.getInputStream();
//                    JSONObject jsonObject = new JSONObject(new BufferedReader(new InputStreamReader(inputStream)).readLine());
//                    inputStream.close();
//                    if (null != jsonObject) {
//                        String openid = jsonObject.getString("openid").trim();
//                        String access_token = jsonObject.getString("access_token").trim();
//                        getUserMesg(access_token, openid);
//                    }
//                } catch (Exception e) {
//                    e.printStackTrace();
//                }
//            }
//        }.start();

    }

    private void getUserMesg(final String access_token, final String openid) {
        final String path = "https://api.weixin.qq.com/sns/userinfo?access_token="
                + access_token
                + "&openid="
                + openid;
        OkHttpUtils.get().url(path).build().execute(new StringCallback() {
            @Override
            public void onError(Call call, Exception e, int id) {

            }

            @Override
            public void onResponse(String response, int id) {
                Log.e("tag","微信用户信息="+response);
            }
        });
//        new Thread() {
//            @Override
//            public void run() {
//                try {
//                    URL url = new URL(path);
//                    HttpsURLConnection conn = (HttpsURLConnection) url.openConnection();
//                    conn.setRequestMethod("GET");
//                    InputStream inputStream = conn.getInputStream();
//                    JSONObject jsonObject = new JSONObject(new BufferedReader(new InputStreamReader(inputStream)).readLine());
//                    inputStream.close();
//                    if (null != jsonObject) {
//                        //AppManager.sharedManager(WXEntryActivity.this).wxLoginInfo = jsonObject;
//                        wxLoginInfo = jsonObject;
//                    }
//                    //  AppManager.sharedManager(WXEntryActivity.this).hiddenWaitingView();
//                    finish();
//                } catch (Exception e) {
//                    e.printStackTrace();
//                }
//            }
//        }.start();
    }
}
