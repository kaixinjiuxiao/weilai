package com.wlyilai.weilaibao.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.wlyilai.weilaibao.R;
import com.wlyilai.weilaibao.entry.Address;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * @author: captain
 * Time:  2017/12/4 0004
 * Describe:
 */
public class AddressAdapter extends RecyclerView.Adapter<AddressAdapter.AddressViewHolder> {
    private Context mContext;
    private List<Address> mList;
    private  HashMap<Integer, Boolean> mMap;
    int mCurrentPosition;

    public OnDeleteAddressListener mOnDeleteListener;
    public onSeetingMorenListener mOnSettMOrenListener;

    public interface OnEditAddresListener{
        void editAddress();
    }

    public interface OnDeleteAddressListener{
        void deleteAddress();
    }
    public interface onSeetingMorenListener{
       void seetMoren(int position);
    }
    public AddressAdapter(Context context, List<Address> list, HashMap<Integer, Boolean> map) {
        mContext = context;
        mList = list;
        mMap = map;
    }

    public OnDeleteAddressListener getOnDeleteListener() {
        return mOnDeleteListener;
    }

    public void setOnDeleteListener(OnDeleteAddressListener onDeleteListener) {
        mOnDeleteListener = onDeleteListener;
    }

    public onSeetingMorenListener getOnSettMOrenListener() {
        return mOnSettMOrenListener;
    }

    public void setOnSettMOrenListener(onSeetingMorenListener onSettMOrenListener) {
        mOnSettMOrenListener = onSettMOrenListener;
    }
    @Override
    public AddressViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(mContext).inflate(R.layout.item_address, parent, false);
        return new AddressViewHolder(v);
    }


    @Override
    public void onBindViewHolder(final AddressViewHolder holder, final int position) {

        holder.name.setText(mList.get(position).getName());
        holder.phone.setText(mList.get(position).getPhone());
        holder.address.setText(mList.get(position).getAddress());
       // holder.select.setSelected(mMap.get(position));
        holder.select.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mOnSettMOrenListener.seetMoren(position);

                mMap.put(position, !mMap.get(position));
                for (int i = 0; i <mList.size() ; i++) {
                    mList.get(i).setSelected(false);
                }
                mList.get(position).setSelected(true);
                //刷新适配器
                notifyDataSetChanged();
                //单选
                singlesel(position);
            }
        });
    }

    /**
     * 单选
     *
     * @param postion
     */
    public void singlesel(int postion) {
        mCurrentPosition = postion;
        Set<Map.Entry<Integer, Boolean>> entries = mMap.entrySet();
        for (Map.Entry<Integer, Boolean> entry : entries) {
            entry.setValue(false);
        }
        mMap.put(postion, true);
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    public class AddressViewHolder extends RecyclerView.ViewHolder {
        public TextView name, phone,address,delete;
        private ImageView select;

        public AddressViewHolder(View itemView) {
            super(itemView);
            name = (TextView) itemView.findViewById(R.id.name);
            phone = (TextView) itemView.findViewById(R.id.phone);
            address = (TextView) itemView.findViewById(R.id.address);
            delete = (TextView) itemView.findViewById(R.id.delete);
            select= (ImageView) itemView.findViewById(R.id.checkbox);

        }
    }
}
