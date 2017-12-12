package com.wlyilai.weilaibao.activity;

import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.Gravity;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.view.View;
import android.widget.TextView;

import com.wlyilai.weilaibao.R;

/**
 * @author: captain
 * Time:  2017/12/9 0009
 * Describe:
 */
public class GroupDetailsActivity extends BaseActivity{
    private PopupWindow popupWindow;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_group_details);
        init();
    }

    private void init(){
        TextView rightNow= (TextView)findViewById(R.id.rightNow);
        rightNow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showPoprpWindow();
            }
        });
    }
    private void showPoprpWindow() {
        View layout = View.inflate(GroupDetailsActivity.this, R.layout.layout_sure_buy, null);
        popupWindow = new PopupWindow(layout, ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        popupWindow.setFocusable(true); // 点击空白处时，隐藏掉POP窗口
        popupWindow.setOutsideTouchable(true); // 点击外部时，隐藏掉POP窗口
        popupWindow.setBackgroundDrawable(new BitmapDrawable());
        popupWindow.showAtLocation(GroupDetailsActivity.this.findViewById(R.id.main), Gravity.BOTTOM, 20, 0);
        ImageView img = (ImageView) layout.findViewById(R.id.goodsImg);
        ImageView imgCancel = (ImageView) layout.findViewById(R.id.imgCancel);
        TextView price = (TextView) layout.findViewById(R.id.price);
        TextView danjia = (TextView) layout.findViewById(R.id.danjia);
        TextView oldPrice = (TextView) layout.findViewById(R.id.oldPrice);
        TextView reduce = (TextView) layout.findViewById(R.id.reduce);
        TextView number = (TextView) layout.findViewById(R.id.number);
        TextView add = (TextView) layout.findViewById(R.id.add);
        Button sure = (Button) layout.findViewById(R.id.btnSure);
        popupWindow.setOnDismissListener(new PopupWindow.OnDismissListener() {
            @Override
            public void onDismiss() {
                popupWindow.dismiss();
            }
        });
    }
}
