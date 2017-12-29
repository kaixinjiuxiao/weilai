package com.wlyilai.weilaibao.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.wlyilai.weilaibao.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * @author: captain
 * Time:  2017/12/27 0027
 * Describe:
 */
public class WithDrawBankFragment extends BaseFagment {
    @BindView(R.id.banlance)
    TextView mBanlance;
    @BindView(R.id.edtNumber)
    EditText mEdtNumber;
    @BindView(R.id.txtNumber)
    TextView mTxtNumber;
    @BindView(R.id.bank)
    TextView mBank;
    @BindView(R.id.bankDetails)
    TextView mBankDetails;
    @BindView(R.id.bankCode)
    TextView mBankCode;
    @BindView(R.id.bankUser)
    TextView mBankUser;
    @BindView(R.id.btnCommit)
    Button mBtnCommit;
    Unbinder unbinder;
    private View mView;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if (mView == null) {
            mView = inflater.inflate(R.layout.fragment_drawals_bank, container, false);
            init();
        }
        unbinder = ButterKnife.bind(this, mView);
        return mView;
    }

    private void init() {

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @OnClick(R.id.btnCommit)
    public void onClick() {
    }
}
