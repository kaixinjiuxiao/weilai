package com.wlyilai.weilaibao.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.style.StrikethroughSpan;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.wlyilai.weilaibao.R;
import com.wlyilai.weilaibao.entry.Goods;
import com.wlyilai.weilaibao.view.RoundImageView;

import java.util.List;

/**
 * @author: captain
 * Time:  2017/12/9 0009
 * Describe:
 */
public class ShopAdapter extends RecyclerView.Adapter<ShopAdapter.ShopViewHolder> {
    private Context mContext;
    private List<Goods.DataBean> mList;
    private OnBuyListener mBuyListener;
    public interface OnBuyListener{
        void onBuy(int position);
    }
    public ShopAdapter(Context context, List<Goods.DataBean> list) {
        mContext = context;
        mList = list;
    }

    public OnBuyListener getBuyListener() {
        return mBuyListener;
    }

    public void setBuyListener(OnBuyListener buyListener) {
        mBuyListener = buyListener;
    }

    @Override
    public ShopViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(mContext).inflate(R.layout.item_shop, parent, false);
        return new ShopViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ShopViewHolder holder, final int position) {
        Glide.with(mContext).load(mList.get(position).getGimg()).into(holder.img);

        holder.name.setText(mList.get(position).getGname());

        SpannableString spanned1 = new SpannableString(mList.get(position).getGprice());
        StrikethroughSpan strike = new StrikethroughSpan();
        spanned1.setSpan(strike,0,spanned1.length(), Spanned.SPAN_INCLUSIVE_EXCLUSIVE);
        holder.old.setText(spanned1);
//        holder.old.setText(mList.get(position).getOldPrice());
        holder.newPrice.setText(mList.get(position).getGteam_price());
        holder.go.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mBuyListener.onBuy(position);
            }
        });
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    public class ShopViewHolder extends RecyclerView.ViewHolder{
        private RoundImageView img;
        private TextView name,old,newPrice,go;
        public ShopViewHolder(View itemView) {
            super(itemView);
            img = (RoundImageView) itemView.findViewById(R.id.goodsImg);
            name = (TextView)itemView.findViewById(R.id.goodsName);
            newPrice = (TextView)itemView.findViewById(R.id.goodsPrice);
            old = (TextView)itemView.findViewById(R.id.goodsOld);
            go = (TextView)itemView.findViewById(R.id.buy);
        }
    }
    public void setData(List<Goods.DataBean>data){
        mList.clear();
        mList=data;
    }
}
