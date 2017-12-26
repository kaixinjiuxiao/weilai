package com.wlyilai.weilaibao.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.wlyilai.weilaibao.R;
import com.wlyilai.weilaibao.entry.Banlance;

import java.util.List;

/**
 * @author: captain
 * Time:  2017/12/9 0009
 * Describe: 余额详情
 */
public class BanlanceAdapter extends RecyclerView.Adapter<BanlanceAdapter.BanlanceViewHolder> {
    private Context mContext;
    private List<Banlance> mList;


    public BanlanceAdapter(Context context, List<Banlance> list) {
        mContext = context;
        mList = list;
    }



    @Override
    public BanlanceViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(mContext).inflate(R.layout.item_common_details, parent, false);
        return new BanlanceViewHolder(v);
    }

    @Override
    public void onBindViewHolder(BanlanceViewHolder holder, final int position) {


        holder.details.setText(mList.get(position).getDetails());
        holder.number.setText(mList.get(position).getNumber());
        holder.time.setText(mList.get(position).getTime());

    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    public class BanlanceViewHolder extends RecyclerView.ViewHolder{

        private TextView details,number,time;
        public BanlanceViewHolder(View itemView) {
            super(itemView);

            details = (TextView)itemView.findViewById(R.id.details);
            number = (TextView)itemView.findViewById(R.id.number);
            time = (TextView)itemView.findViewById(R.id.time);
        }
    }

}
