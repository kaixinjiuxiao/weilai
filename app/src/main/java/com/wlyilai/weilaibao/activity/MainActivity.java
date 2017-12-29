package com.wlyilai.weilaibao.activity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.wlyilai.weilaibao.R;
import com.wlyilai.weilaibao.entry.FirstEvent;
import com.wlyilai.weilaibao.utils.FragmentController;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends BaseActivity {
    @BindView(R.id.imgPG)
    ImageView mImgPG;
    @BindView(R.id.txtPG)
    TextView mTxtPG;
    @BindView(R.id.linearMain)
    LinearLayout mLinearMain;
    @BindView(R.id.imgOrder)
    ImageView mImgOrder;
    @BindView(R.id.txtOrder)
    TextView mTxtOrder;
    @BindView(R.id.linearOrder)
    LinearLayout mLinearOrder;
    @BindView(R.id.imgMy)
    ImageView mImgMy;
    @BindView(R.id.txtMy)
    TextView mTxtMy;
    @BindView(R.id.lineaUser)
    LinearLayout mLineaUser;
    @BindView(R.id.imgGroup)
    ImageView mImgGroup;
    @BindView(R.id.txtGroup)
    TextView mTxtGroup;
    @BindView(R.id.linearGroup)
    LinearLayout mLinearGroup;
    private FragmentController mController;
    public String code ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        EventBus.getDefault().register(this);
        init();
    }

    private void init() {
        code = getIntent().getStringExtra("token");
        Log.e("tag",code);
        mController = FragmentController.getInstance(this, R.id.content, true);
        mController.showFragment(0);
        mTxtPG.setSelected(true);
        mImgPG.setSelected(true);

    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onDataSynEvent(FirstEvent event) {
        if (event.getMsg().equals("ok")) {
            selected();
            mTxtGroup.setSelected(true);
            mImgGroup.setSelected(true);
            mController.showFragment(1);
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
        mController.onDestroy();
    }

    @OnClick({R.id.linearMain, R.id.linearOrder, R.id.lineaUser,R.id.linearGroup})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.linearMain:
                selected();
                mTxtPG.setSelected(true);
                mImgPG.setSelected(true);
                mController.showFragment(0);
                break;
            case R.id.linearGroup:
                selected();
                mTxtGroup.setSelected(true);
                mImgGroup.setSelected(true);
                mController.showFragment(1);
                break;
            case R.id.linearOrder:
                selected();
                mTxtOrder.setSelected(true);
                mImgOrder.setSelected(true);
                mController.showFragment(2);
                break;
            case R.id.lineaUser:
                selected();
                mTxtMy.setSelected(true);
                mImgMy.setSelected(true);
                mController.showFragment(3);
                break;
        }
    }

    public void selected() {
        mTxtPG.setSelected(false);
        mImgPG.setSelected(false);
        mTxtGroup.setSelected(false);
        mImgGroup.setSelected(false);
        mTxtOrder.setSelected(false);
        mImgOrder.setSelected(false);
        mTxtMy.setSelected(false);
        mImgMy.setSelected(false);
    }

    @OnClick(R.id.linearGroup)
    public void onClick() {
    }
}
