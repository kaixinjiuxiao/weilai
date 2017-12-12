package com.wlyilai.weilaibao.activity;

import android.os.Bundle;
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
    private FragmentController mController;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        EventBus.getDefault().register(this);
        init();
    }

    private void init() {
        mController = FragmentController.getInstance(this, R.id.content, true);
        mController.showFragment(0);
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onDataSynEvent(FirstEvent event) {
        if (event.getMsg().equals("ok")) {
            mController.showFragment(2);
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
        mController.onDestroy();
    }

    @OnClick({R.id.linearMain, R.id.linearOrder, R.id.lineaUser})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.linearMain:
                mController.showFragment(0);
                break;
            case R.id.linearOrder:
                mController.showFragment(1);
                break;
            case R.id.lineaUser:
                mController.showFragment(2);
                break;
        }
    }
}
