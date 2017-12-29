package com.wlyilai.weilaibao.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.style.StrikethroughSpan;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.wlyilai.weilaibao.R;
import com.wlyilai.weilaibao.entry.Goods;
import com.wlyilai.weilaibao.utils.DensityUtils;
import com.wlyilai.weilaibao.utils.ScreenUtils;

import java.util.List;

/**
 * @author: captain
 * Time:  2017/12/9 0009
 * Describe:
 */
public class PinGouAdapter extends RecyclerView.Adapter<PinGouAdapter.PinGouViewHolder> {
    private Context mContext;
    private List<Goods.DataBean> mList;
    private OnBuyListener mBuyListener;
    private float totalWidth;
    private int  needWidth;
    public interface OnBuyListener{
        void onBuy(int position);
    }
    public PinGouAdapter(Context context, List<Goods.DataBean> list) {
        mContext = context;
        mList = list;
        totalWidth =DensityUtils.pxToDp(mContext, ScreenUtils.getScreenWidth(mContext));
        needWidth = DensityUtils.dpToPx(mContext,(totalWidth-40)/2);
    }

    public OnBuyListener getBuyListener() {
        return mBuyListener;
    }

    public void setBuyListener(OnBuyListener buyListener) {
        mBuyListener = buyListener;
    }

    @Override
    public PinGouViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(mContext).inflate(R.layout.item_tuan, parent, false);
        return new PinGouViewHolder(v);
    }

    @Override
    public void onBindViewHolder(PinGouViewHolder holder, final int position) {
        ViewGroup.LayoutParams lp = holder.img.getLayoutParams();
        lp.width =needWidth;
        lp.height = needWidth;
        holder.img.setLayoutParams(lp);
        Glide.with(mContext).load(mList.get(position).getGimg()).into(holder.img);
        holder.name.setText(mList.get(position).getGname());
        SpannableString  spanned1 = new SpannableString(mList.get(position).getGprice());
        StrikethroughSpan strike = new StrikethroughSpan();
        spanned1.setSpan(strike,0,spanned1.length(), Spanned.SPAN_INCLUSIVE_EXCLUSIVE);
        holder.old.setText(spanned1);
        holder.numer.setText(mList.get(position).getGuser_limit());
//        SpannableString  spanned2 = new SpannableString("¥"+mList.get(position).getGteam_price()+"/件");
//        RelativeSizeSpan relative = new RelativeSizeSpan(1.5f);
//        spanned2.setSpan(relative,1,spanned2.toString().indexOf("/"), Spanned.SPAN_INCLUSIVE_EXCLUSIVE);
//        holder.newPrice.setText(spanned2);
        holder.newPrice.setText(mList.get(position).getGteam_price());
        holder.buy.setOnClickListener(new View.OnClickListener() {
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
        private TextView name,old,numer,newPrice,buy;
        public PinGouViewHolder(View itemView) {
            super(itemView);
            img = (ImageView) itemView.findViewById(R.id.img);
            name = (TextView)itemView.findViewById(R.id.name);
            old = (TextView)itemView.findViewById(R.id.oldPrice);
            numer = (TextView)itemView.findViewById(R.id.number);
            newPrice = (TextView)itemView.findViewById(R.id.newPrice);
            buy = (TextView)itemView.findViewById(R.id.pingtuan);
        }
    }
}
