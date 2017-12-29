package com.wlyilai.weilaibao.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.wlyilai.weilaibao.R;
import com.wlyilai.weilaibao.fragment.WithDrawBankFragment;
import com.wlyilai.weilaibao.fragment.WithDrawWXFragment;
import com.wlyilai.weilaibao.utils.FragmentController2;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * @author: captain
 * Time:  2017/12/25 0025
 * Describe:提现
 */
public class WithDrawalsActivity extends BaseActivity {
    @BindView(R.id.imgBack)
    ImageView mImgBack;
    @BindView(R.id.txtWX)
    TextView mTxtWX;
    @BindView(R.id.imgWX)
    ImageView mImgWX;
    @BindView(R.id.linearWX)
    LinearLayout mLinearWX;
    @BindView(R.id.txtBank)
    TextView mTxtBank;
    @BindView(R.id.imgBank)
    ImageView mImgBank;
    @BindView(R.id.linearBank)
    LinearLayout mLinearBank;
    @BindView(R.id.txtTitle)
    TextView mTxtTitle;
    private FragmentController2 mController;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_with_drawals);
        ButterKnife.bind(this);
        initView();
    }

    private void initView() {
        mImgBack.setVisibility(View.VISIBLE);
        mTxtTitle.setText("提现");
        List<Fragment> list = new ArrayList<>();
        list.add(new WithDrawWXFragment());
        list.add(new WithDrawBankFragment());
        mController = FragmentController2.getInstance(this, R.id.content, true,list);
        mController.showFragment(0);
        mTxtWX.setSelected(true);
        mImgWX.setSelected(true);
    }

    @OnClick({R.id.imgBack, R.id.linearWX, R.id.linearBank})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.imgBack:
                finish();
                break;
            case R.id.linearWX:
                select();
                mTxtWX.setSelected(true);
                mImgWX.setSelected(true);
                mController.showFragment(0);
                break;
            case R.id.linearBank:
                select();
                mTxtBank.setSelected(true);
                mImgBank.setSelected(true);
                mController.showFragment(1);
                break;
        }
    }

    private void select() {
        mTxtWX.setSelected(false);
        mImgWX.setSelected(false);
        mTxtBank.setSelected(false);
        mImgBank.setSelected(false);
    }

}
