package com.wlyilai.weilaibao.adapter;

import android.content.Context;
import android.graphics.Typeface;
import android.support.v7.widget.RecyclerView;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.style.ForegroundColorSpan;
import android.text.style.StyleSpan;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.wlyilai.weilaibao.R;
import com.wlyilai.weilaibao.entry.MyGroup;

import java.util.List;

/**
 * @author: captain
 * Time:  2017/12/12 0012
 * Describe:
 */
public class GroupingAdapter extends RecyclerView.Adapter<GroupingAdapter.GroupingViewHolder> {
    private Context mContext;
    private List<MyGroup.DataBean> mList;
    private OnLookDetailsListener mLookDetailsListener;

    public interface OnLookDetailsListener {
        void lookDetails(int position);
    }

    public GroupingAdapter(Context context, List<MyGroup.DataBean> list) {
        mContext = context;
        mList = list;
    }

    public void setLookDetailsListener(OnLookDetailsListener lookDetailsListener) {
        mLookDetailsListener = lookDetailsListener;
    }

    @Override
    public GroupingViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(mContext).inflate(R.layout.item_my_grouping, parent, false);
        return new GroupingViewHolder(v);
    }

    @Override
    public void onBindViewHolder(GroupingViewHolder holder, final int position) {
        Glide.with(mContext).load(mList.get(position).getGimg()).into(holder.img);
        holder.orderCode.setText(mList.get(position).getOsn());
        if(mList.get(position).getState().equals("0")){
            holder.orderStatus.setText("组团中");
        }else if(mList.get(position).getState().equals("1")){
            holder.orderStatus.setText("组团成功");
        }else{
            holder.orderStatus.setText("组团失败");
        }
        SpannableString span1 = new SpannableString(mList.get(position).getGname());
        StyleSpan style = new StyleSpan(Typeface.BOLD);
        span1.setSpan(style,0,span1.length(), Spanned.SPAN_INCLUSIVE_EXCLUSIVE);
        holder.goodsName.setText(span1);

        SpannableString span2 = new SpannableString("￥"+mList.get(position).getGteam_price()+"/1件");
        ForegroundColorSpan bg1 = new ForegroundColorSpan(0XFFe02e24);
        span2.setSpan(bg1,0,span2.toString().indexOf("/"),Spanned.SPAN_INCLUSIVE_EXCLUSIVE);
        holder.goodsPrice.setText(span2);

        holder.goodsNumber.setText(mList.get(position).getPay_num());

        SpannableString span3 = new SpannableString("共"+mList.get(position).getPay_num()+"个商品");
        span3.setSpan(bg1,1,span3.toString().indexOf("个"),Spanned.SPAN_INCLUSIVE_EXCLUSIVE);

        holder.goodsTotalNumber.setText(span3);

        SpannableString span4 = new SpannableString("总额："+mList.get(position).getTotal_price()+"元");
        span4.setSpan(bg1,3,span4.length()-1,Spanned.SPAN_INCLUSIVE_EXCLUSIVE);
        holder.goodsTotalPrice.setText(span4);

        holder.details.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mLookDetailsListener.lookDetails(position);
            }
        });
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    public class GroupingViewHolder extends RecyclerView.ViewHolder {
        public ImageView img;
        public TextView orderCode, orderStatus, goodsName, goodsPrice, goodsNumber, goodsTotalNumber, goodsTotalPrice, details;

        public GroupingViewHolder(View itemView) {
            super(itemView);
            img = (ImageView) itemView.findViewById(R.id.imgGoods);
            orderCode = (TextView) itemView.findViewById(R.id.orderCode);
            orderStatus = (TextView) itemView.findViewById(R.id.orderStatus);
            goodsName = (TextView) itemView.findViewById(R.id.goodsName);
            goodsPrice = (TextView) itemView.findViewById(R.id.goodsPrice);
            goodsNumber = (TextView) itemView.findViewById(R.id.goodsNumber);
            goodsTotalNumber = (TextView) itemView.findViewById(R.id.goodsTotalNumber);
            goodsTotalPrice = (TextView) itemView.findViewById(R.id.goodsTotalPrice);
            details = (TextView) itemView.findViewById(R.id.details);
        }
    }
}
