package com.wlyilai.weilaibao.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;

import com.wlyilai.weilaibao.R;
import com.wlyilai.weilaibao.view.LoadingCenter;

/**
 * @author: captain
 * Time:  2017/12/9 0009
 * Describe:
 */
public class BaseFagment extends Fragment {

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }
    private LoadingCenter mLoadingCenter;
    protected void dismissLoadingDialog() {
        if (mLoadingCenter != null) {
            if (mLoadingCenter.isShowing()) {
                mLoadingCenter.dismiss();
            }
        }
    }

    protected void showLoadingDialog() {
        if (mLoadingCenter == null) {
            mLoadingCenter = new LoadingCenter(getActivity(), R.style.LoadingDialog);
            mLoadingCenter.setCanceledOnTouchOutside(false);
        }
        if (mLoadingCenter != null) {
            if (!mLoadingCenter.isShowing()) {
                mLoadingCenter.show();
            }
        }
    }
}
