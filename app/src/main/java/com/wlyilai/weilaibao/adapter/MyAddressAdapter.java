package com.wlyilai.weilaibao.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.wlyilai.weilaibao.R;
import com.wlyilai.weilaibao.entry.ReceivingAddress;

import java.util.List;

/**
 * @author: captain
 * Time:  2017/12/13 0013
 * Describe:
 */
public class MyAddressAdapter extends BaseAdapter {
    private Context mContext;
    private List<ReceivingAddress.DataBean.AddressBean> mList;
    private int selectPosition = -1;//用于记录用户选择的变量
    private OnChooiseListener mChooiseListener;
    private OnDeleteListener mDeleteListener;

    public void setChooiseListener(OnChooiseListener chooiseListener) {
        mChooiseListener = chooiseListener;
    }

    public void setDeleteListener(OnDeleteListener deleteListener) {
        mDeleteListener = deleteListener;
    }

    public interface OnChooiseListener{
        void onChooise(int position);
    }
    public interface OnDeleteListener{
        void onDelete(int position);
    }
    public MyAddressAdapter(Context context, List<ReceivingAddress.DataBean.AddressBean> list) {
        mContext = context;
        mList = list;
    }

    @Override
    public int getCount() {
        return mList.size();
    }

    @Override
    public Object getItem(int position) {
        return mList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {

        ViewHolder viewHolder = null;
        if(convertView == null){
            convertView = LayoutInflater.from(mContext).inflate(R.layout.item_address,parent,false);
            viewHolder = new ViewHolder();
            viewHolder. name = (TextView) convertView.findViewById(R.id.name);
            viewHolder. phone = (TextView) convertView.findViewById(R.id.phone);
            viewHolder.address = (TextView) convertView.findViewById(R.id.address);
            viewHolder. delete = (TextView)convertView. findViewById(R.id.delete);
            viewHolder.select= (ImageView) convertView.findViewById(R.id.checkbox);
            convertView.setTag(viewHolder);
        }else{
            viewHolder = (ViewHolder)convertView.getTag();
        }
        viewHolder.name.setText(mList.get(position).getRealname());
        viewHolder.phone.setText(mList.get(position).getMobile());
        String address = mList.get(position).getProvince()+mList.get(position).getCity()+mList.get(position).getCountry()
                +mList.get(position).getDetail();
        viewHolder.address.setText(address);
        viewHolder.select.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectPosition=position;
                mChooiseListener.onChooise(position);
            }
        });
        viewHolder.delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectPosition=0;
                mDeleteListener.onDelete(position);
            }
        });
        if(selectPosition == position){
            viewHolder.select.setSelected(true);
        }
        else{
            viewHolder.select.setSelected(false);
        }
        return convertView;
    }

    public static class ViewHolder{
        public TextView name, phone,address,delete;
        private ImageView select;
    }
}
