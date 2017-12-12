package com.wlyilai.weilaibao.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.wlyilai.weilaibao.R;
import com.wlyilai.weilaibao.entry.Goods;

import java.util.List;

/**
 * @author: captain
 * Time:  2017/12/11 0011
 * Describe:
 */
public class TypeAdapter extends RecyclerView.Adapter<TypeAdapter.TypeViewHolder> {
    private Context mContext;
    private List<String> mList;
    private OnItemClickListener mItemClickListener;

    public void setItemClickListener(OnItemClickListener itemClickListener) {
        mItemClickListener = itemClickListener;
    }

    public interface OnItemClickListener{
        void onIntemClick(int position);
    }
    public TypeAdapter(Context context, List<String> list) {
        mContext = context;
        mList = list;
    }

    @Override
    public TypeViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(mContext).inflate(R.layout.item_type, parent, false);
        return new TypeViewHolder(v);
    }

    @Override
    public void onBindViewHolder(TypeViewHolder holder, final int position) {
        holder.type.setText(mList.get(position));
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mItemClickListener.onIntemClick(position);
            }
        });
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    public class TypeViewHolder extends RecyclerView.ViewHolder{
        TextView type;
        public TypeViewHolder(View itemView) {
            super(itemView);
            type =(TextView) itemView.findViewById(R.id.typeName);
        }
    }
    public void setData(List<String>data){
        mList.clear();
        mList=data;
    }
}
