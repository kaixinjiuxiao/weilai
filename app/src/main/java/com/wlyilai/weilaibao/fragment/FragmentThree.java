package com.wlyilai.weilaibao.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.wlyilai.weilaibao.R;

/**
 * @author: captain
 * Time:  2017/12/11 0011
 * Describe:
 */
public class FragmentThree extends BaseFagment {
    private View mView;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if(mView==null){
            mView =inflater.inflate(R.layout.fragment_common_order,container,false);
        }
        return mView;
    }
}
