package com.wlyilai.weilaibao.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.webkit.WebView;

import com.wlyilai.weilaibao.R;

/**
 * @author: captain
 * Time:  2017/12/9 0009
 * Describe:
 */
public class AggrementActivity extends BaseActivity {
    private WebView mWebView;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_aggrement);
       init();
    }

    private void init() {
        mWebView = (WebView) findViewById(R.id.webview);
    }
}
