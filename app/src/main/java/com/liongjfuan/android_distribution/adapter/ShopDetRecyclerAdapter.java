package com.liongjfuan.android_distribution.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.liongjfuan.android_distribution.R;
import com.liongjfuan.android_distribution.bean.ReturnData;

import java.util.List;

/**
 *
 * @author Lifu.Zheng
 * @date 2018.01.18
 */

public class ShopDetRecyclerAdapter extends BaseRecycleViewAdapter<ReturnData.ShopDetailRsp.CommodityInfo, ShopDetRecyclerAdapter.DetailViewHolder> {


    public ShopDetRecyclerAdapter(Context context) {
        super(context);
    }

    public ShopDetRecyclerAdapter(Context mContext, List<ReturnData.ShopDetailRsp.CommodityInfo> datas) {
        super(mContext, datas);
    }

    @Override
    public DetailViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.content_shop_par, parent, false);
        DetailViewHolder holder = new DetailViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(DetailViewHolder holder, int position) {
        ReturnData.ShopDetailRsp.CommodityInfo commodityInfo = datas.get(position);
        Glide.with(mContext).load(commodityInfo.getIconUrl()).into(holder.ivCommodity);
        holder.tvName.setText(commodityInfo.getName());
        holder.tvComInfo.setText(commodityInfo.getIntro());
        holder.tvPrice.setText(commodityInfo.getDisPrice());
        holder.position = position;
    }


    class DetailViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener, View.OnLongClickListener {

        View rootView;
        ImageView ivCommodity;
        TextView tvName;
        TextView tvComInfo;
        TextView tvPrice;

        int position;

        public DetailViewHolder(View itemView) {
            super(itemView);
            rootView = itemView.findViewById(R.id.shop_item_root);
            ivCommodity = (ImageView) itemView.findViewById(R.id.iv_commodity);
            tvName = (TextView) itemView.findViewById(R.id.tv_name);
            tvComInfo = (TextView) itemView.findViewById(R.id.tv_commodity_info);
            tvPrice = (TextView) itemView.findViewById(R.id.tv_price);

            rootView.setOnClickListener(this);
            rootView.setOnLongClickListener(this);
        }

        @Override
        public void onClick(View view) {
            if (null != onRecyclerViewListener) {
                onRecyclerViewListener.onItemClick(view, position);
            }
        }

        @Override
        public boolean onLongClick(View view) {
            return false;
        }
    }
}
