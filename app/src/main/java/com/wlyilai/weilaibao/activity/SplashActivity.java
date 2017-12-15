package com.wlyilai.weilaibao.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.widget.ImageView;

import com.wlyilai.weilaibao.R;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import butterknife.BindView;
import butterknife.ButterKnife;
import okhttp3.Call;

import static com.wlyilai.weilaibao.utils.Constant.HOME_GOODS;

/**
 * @author: captain
 * Time:  2017/12/9 0009
 * Describe:
 */
public class SplashActivity extends BaseActivity {
    @BindView(R.id.imgSplash)
    ImageView mImgSplash;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        ButterKnife.bind(this);
        initView();
    }


    private void initView() {
        AlphaAnimation animation = new AlphaAnimation(1.0f, 0.0f);
        animation.setDuration(2000);
        animation.setFillAfter(true);
        mImgSplash.startAnimation(animation);
        getBanner();
        animation.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                Intent intent = new Intent();
                intent.setClass(SplashActivity.this, MainActivity.class);
                startActivity(intent);
                finish();
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });

    }
    private void getBanner(){
        OkHttpUtils.post().url(HOME_GOODS)
                .addParams("access_token","02c8b29f1b09833e43a37c770a87db23")
                .addParams("page","0")
                .build().execute(new StringCallback() {
            @Override
            public void onError(Call call, Exception e, int id) {
                Log.e("tag","错误"+e.toString());
            }

            @Override
            public void onResponse(String response, int id) {
                Log.e("tag","一级分类和录播图"+response);
            }
        });
    }
}
