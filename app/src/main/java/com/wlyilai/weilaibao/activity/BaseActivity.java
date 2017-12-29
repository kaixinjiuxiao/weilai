package com.wlyilai.weilaibao.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.wlyilai.weilaibao.R;
import com.wlyilai.weilaibao.view.LoadingCenter;

/**
 * @author: captain
 * Time:  2017/12/9 0009
 * Describe:
 */
public  class BaseActivity extends AppCompatActivity {
    private LoadingCenter mLoadingCenter;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }
    protected void dismissLoadingDialog() {
        if (mLoadingCenter != null) {
            if (mLoadingCenter.isShowing()) {
                mLoadingCenter.dismiss();
            }
        }
    }

    protected void showLoadingDialog() {
        if (mLoadingCenter == null) {
            mLoadingCenter = new LoadingCenter(this, R.style.LoadingDialog);
            mLoadingCenter.setCanceledOnTouchOutside(false);
        }
        if (mLoadingCenter != null) {
            if (!mLoadingCenter.isShowing()) {
                mLoadingCenter.show();
            }
        }
    }
}
