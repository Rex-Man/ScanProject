package com.oocl.manre.fragmentstudy.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.oocl.manre.fragmentstudy.R;
import com.oocl.manre.fragmentstudy.entity.Scan;

import java.util.List;

/**
 * Created by manre on 23/02/2018.
 */

public class ScanAdapter extends RecyclerView.Adapter<ScanHolder> {

    private List<Scan> mData;
    private Context mContext;
    public ScanAdapter(){

    }
    public ScanAdapter(Context context, List<Scan> data)
    {

        this.mData = data;
        this.mContext = context;
        notifyDataSetChanged();

    }
    public void updataScan(Context context,List<Scan> data)
    {
        this.mData=data;
        this.mContext = context;
        notifyDataSetChanged();
    }

    @Override
    public ScanHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v= LayoutInflater.from(mContext).inflate(R.layout.view_scan_item, parent, false);
        ScanHolder viewHolder = new ScanHolder(v);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ScanHolder holder, int position) {
        holder.mTv.setText(mData.get(position).getScanCode());
        holder.mCreateTime.setText(mData.get(position).getCreateDate().toString());
    }

    @Override
    public int getItemCount() {
        return mData == null ? 0 : mData.size();
    }
}
