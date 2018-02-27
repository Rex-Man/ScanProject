package com.oocl.manre.fragmentstudy.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.oocl.manre.fragmentstudy.R;

/**
 * Created by manre on 23/02/2018.
 */

public class ScanHolder extends RecyclerView.ViewHolder {
    TextView mTv;
    TextView mCreateTime;
    public ScanHolder(View itemView) {
        super(itemView);
        mTv = (TextView) itemView.findViewById(R.id.item_scantv);
        mCreateTime = (TextView) itemView.findViewById(R.id.item_createTime);
    }
}
