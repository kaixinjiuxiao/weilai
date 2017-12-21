package com.wlyilai.weilaibao.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import com.wlyilai.weilaibao.R;
import com.wlyilai.weilaibao.fragment.BaseFagment;

/**
 * @author: captain
 * Time:  2017/12/11 0011
 * Describe:
 */
public class UserCenterFragment extends BaseFagment implements View.OnClickListener {
    private View mView;
    private RelativeLayout info,wallet,consumption,apply,setting,redPackage;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if(mView==null){
            mView =inflater.inflate(R.layout.fragment_user_center,container,false);
            initView();
            initEvent();
        }
        return mView;
    }

    private void initView() {
        info = (RelativeLayout)mView.findViewById(R.id.relativeInfo);
        wallet = (RelativeLayout)mView.findViewById(R.id.relativeWallet);
        consumption = (RelativeLayout)mView.findViewById(R.id.relativeConsu);
        apply = (RelativeLayout)mView.findViewById(R.id.relativeApply);
        setting = (RelativeLayout)mView.findViewById(R.id.relativeSetting);
        redPackage = (RelativeLayout)mView.findViewById(R.id.relativeRP);
    }

    private void initEvent() {
        info.setOnClickListener(this);
        wallet.setOnClickListener(this);
        consumption.setOnClickListener(this);
        apply.setOnClickListener(this);
        setting.setOnClickListener(this);
        redPackage.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        Intent intent = new Intent();
        switch (v.getId()){
            case R.id.relativeInfo:
                intent.setClass(getActivity(),UserInfoActivity.class);
                break;
            case R.id.relativeWallet:
                intent.setClass(getActivity(),MyWalletActivity.class);
                break;
            case R.id.relativeRP:
                intent.setClass(getActivity(),RedPackageNoteActivity.class);
                break;
            case R.id.relativeConsu:
                intent.setClass(getActivity(),ConsumptionActivity.class);
                break;
            case R.id.relativeApply:
                intent.setClass(getActivity(),ApplyOSActivity.class);
                break;
            case R.id.relativeSetting:
                intent.setClass(getActivity(),SettingActivity.class);
                break;
            default:
                break;
        }
        startActivity(intent);
    }
}
