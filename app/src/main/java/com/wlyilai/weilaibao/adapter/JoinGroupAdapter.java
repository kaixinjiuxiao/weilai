package com.wlyilai.weilaibao.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.wlyilai.weilaibao.R;
import com.wlyilai.weilaibao.entry.Goods;
import com.wlyilai.weilaibao.entry.JoinInfo;

import java.util.List;

/**
 * @author: captain
 * Time:  2017/12/9 0009
 * Describe: 参团人信息
 */
public class JoinGroupAdapter extends RecyclerView.Adapter<JoinGroupAdapter.JoinViewHolder> {
    private Context mContext;
    private List<JoinInfo> mList;

    public JoinGroupAdapter(Context context, List<JoinInfo> list) {
        mContext = context;
        mList = list;
    }


    @Override
    public JoinViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(mContext).inflate(R.layout.item_cantuan_xinxi, parent, false);
        return new JoinViewHolder(v);
    }

    @Override
    public void onBindViewHolder(JoinViewHolder holder, final int position) {
        holder.phone.setText(mList.get(position).getPhone());
        holder.time.setText(mList.get(position).getTime());
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    public class JoinViewHolder extends RecyclerView.ViewHolder {
        private TextView phone, time;

        public JoinViewHolder(View itemView) {
            super(itemView);
            phone = (TextView) itemView.findViewById(R.id.phone);
            time = (TextView) itemView.findViewById(R.id.time);
        }
    }
}
