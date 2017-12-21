package com.wlyilai.weilaibao.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.wlyilai.weilaibao.R;
import com.wlyilai.weilaibao.entry.RedPNote;

import java.util.List;

/**
 * @author: captain
 * Time:  2017/12/9 0009
 * Describe:
 */
public class RPNoteAdapter extends RecyclerView.Adapter<RPNoteAdapter.RPViewHolder> {
    private Context mContext;
    private List<RedPNote> mList;

    public RPNoteAdapter(Context context, List<RedPNote> list) {
        mContext = context;
        mList = list;
    }


    @Override
    public RPViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(mContext).inflate(R.layout.item_rp_note, parent, false);
        return new RPViewHolder(v);
    }

    @Override
    public void onBindViewHolder(RPViewHolder holder, final int position) {

        holder.name.setText(mList.get(position).getName());
        if(mList.get(position).getStatus().equals("1")){
            holder.qiang.setVisibility(View.VISIBLE);
        }
        holder.number.setText(mList.get(position).getNumber());
        holder.time.setText(mList.get(position).getTime());

    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    public class RPViewHolder extends RecyclerView.ViewHolder{
        private TextView name,number,qiang,time;
        public RPViewHolder(View itemView) {
            super(itemView);
            name = (TextView)itemView.findViewById(R.id.name);
            number = (TextView)itemView.findViewById(R.id.number);
            qiang = (TextView)itemView.findViewById(R.id.qiang);
            time = (TextView)itemView.findViewById(R.id.time);
        }
    }
}
