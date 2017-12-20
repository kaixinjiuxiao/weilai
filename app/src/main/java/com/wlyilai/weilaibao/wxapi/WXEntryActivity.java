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

import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;

import javax.net.ssl.HttpsURLConnection;

/**
 * Created by dudu on 2016/11/24.
 */

public class WXEntryActivity extends Activity implements IWXAPIEventHandler {

    private final String TAG = this.getClass().getSimpleName();
    private static final String WX_APP_ID_ONE = "wx4f71e1e33abc41a1";
    private static final String WX_APP_SECRECT_ONE = "79093d8ca0486cd08af2b1551935881e";
    private IWXAPI mApi;
    private JSONObject wxLoginInfo;
    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mApi = WXAPIFactory.createWXAPI(this, WX_APP_ID_ONE, true);
        mApi.handleIntent(this.getIntent(), this);
        Log.v(TAG, "OK");
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
                //发送成功
                if (resp instanceof SendAuth.Resp) {
                    SendAuth.Resp response = (SendAuth.Resp) resp;
                    getAccess_token(response.code);
                    return;
                }
                break;
            case BaseResp.ErrCode.ERR_USER_CANCEL:
                //发送取消
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
                + WX_APP_ID_ONE
                + "&secret="
                + WX_APP_SECRECT_ONE
                + "&code="
                + code
                + "&grant_type=authorization_code";
      //  AppManager.sharedManager(this).showWaitingView();
        new Thread() {
            @Override
            public void run() {
                try {
                    URL url = new URL(path);
                    HttpsURLConnection conn = (HttpsURLConnection) url.openConnection();
                    conn.setRequestMethod("GET");
                    InputStream inputStream = conn.getInputStream();
                    JSONObject jsonObject = new JSONObject(new BufferedReader(new InputStreamReader(inputStream)).readLine());
                    inputStream.close();
                    if (null != jsonObject) {
                        String openid = jsonObject.getString("openid").trim();
                        String access_token = jsonObject.getString("access_token").trim();
                        getUserMesg(access_token, openid);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }.start();

    }

    private void getUserMesg(final String access_token, final String openid) {
        final String path = "https://api.weixin.qq.com/sns/userinfo?access_token="
                + access_token
                + "&openid="
                + openid;
        new Thread() {
            @Override
            public void run() {
                try {
                    URL url = new URL(path);
                    HttpsURLConnection conn = (HttpsURLConnection) url.openConnection();
                    conn.setRequestMethod("GET");
                    InputStream inputStream = conn.getInputStream();
                    JSONObject jsonObject = new JSONObject(new BufferedReader(new InputStreamReader(inputStream)).readLine());
                    inputStream.close();
                    if (null != jsonObject) {
                        //AppManager.sharedManager(WXEntryActivity.this).wxLoginInfo = jsonObject;
                        wxLoginInfo = jsonObject;
                    }
                  //  AppManager.sharedManager(WXEntryActivity.this).hiddenWaitingView();
                    finish();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }.start();
    }
}
