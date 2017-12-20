package com.wlyilai.weilaibao.wxapi;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.tencent.mm.opensdk.modelbase.BaseReq;
import com.tencent.mm.opensdk.modelbase.BaseResp;
import com.tencent.mm.opensdk.openapi.IWXAPI;
import com.tencent.mm.opensdk.openapi.IWXAPIEventHandler;
import com.tencent.mm.opensdk.openapi.WXAPIFactory;
import com.wlyilai.weilaibao.R;



/**
 * Created by dudu on 2016/12/7.
 */

public class WXPayEntryActivity extends Activity implements IWXAPIEventHandler {
    private IWXAPI api;
    private static final String WX_APP_ID_ONE = "wx4f71e1e33abc41a1";
    private static final String WX_APP_SECRECT_ONE = "79093d8ca0486cd08af2b1551935881e";
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_entry);

        api = WXAPIFactory.createWXAPI(this, WX_APP_ID_ONE, false);
        api.handleIntent(this.getIntent(), this);
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);

        setIntent(intent);
        api.handleIntent(intent, this);
    }

    @Override
    public void onReq(BaseReq baseReq) {
        Log.v("debug",baseReq.openId);
    }

    @Override
    public void onResp(BaseResp baseResp) {
        if (baseResp.errCode == 0) {
            Toast.makeText(this,"支付成功",Toast.LENGTH_SHORT).show();
           // Intent intent = new Intent(WXPayEntryActivity.this, LookDetailsBillActivity.class);
           // startActivity(intent);
            finish();
        }
        else {
            Toast.makeText(this,"支付失败",Toast.LENGTH_SHORT).show();
        }
        finish();
    }
}
