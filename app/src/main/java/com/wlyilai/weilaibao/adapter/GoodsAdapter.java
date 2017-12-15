package com.wlyilai.weilaibao.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.wlyilai.weilaibao.R;
import com.wlyilai.weilaibao.entry.Goods;

import java.util.List;

/**
 * @author: captain
 * Time:  2017/12/9 0009
 * Describe:
 */
public class GoodsAdapter extends RecyclerView.Adapter<GoodsAdapter.PinGouViewHolder> {
    private Context mContext;
    private List<Goods.DataBean> mList;
    private OnBuyListener mBuyListener;
    public interface OnBuyListener{
        void onBuy(int position);
    }
    public GoodsAdapter(Context context, List<Goods.DataBean> list) {
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
    public PinGouViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(mContext).inflate(R.layout.item_type_goods, parent, false);
        return new PinGouViewHolder(v);
    }

    @Override
    public void onBindViewHolder(PinGouViewHolder holder, final int position) {
        Glide.with(mContext).load(mList.get(position).getGimg()).into(holder.img);
        holder.name.setText(mList.get(position).getGname());
        holder.old.setText(mList.get(position).getGprice());
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

    public class PinGouViewHolder extends RecyclerView.ViewHolder{
        private ImageView img;
        private TextView name,old,newPrice,go;
        public PinGouViewHolder(View itemView) {
            super(itemView);
            img = (ImageView) itemView.findViewById(R.id.img);
            name = (TextView)itemView.findViewById(R.id.goodName);
            old = (TextView)itemView.findViewById(R.id.oldPrice);
            newPrice = (TextView)itemView.findViewById(R.id.price);
            go = (TextView)itemView.findViewById(R.id.go);
        }
    }
    public void setData(List<Goods.DataBean>data){
        mList.clear();
        mList=data;
    }
}
