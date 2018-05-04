package com.liongjfuan.android_distribution.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

import com.kevin.wraprecyclerview.BaseRecyclerAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Lifu.Zheng
 * @date 2018.01.18
 */

public abstract class BaseRecycleViewAdapter<T, VH extends RecyclerView.ViewHolder> extends RecyclerView.Adapter<VH> {

    protected final Context mContext;
    protected List<T> datas = new ArrayList<T>();
    //条目操作的回调监听
    protected OnRecyclerViewListener onRecyclerViewListener;

    public BaseRecycleViewAdapter(Context mContext) {
        this.mContext = mContext;
    }

    public BaseRecycleViewAdapter(Context mContext, List<T> datas) {
        this.mContext = mContext;
        this.datas = datas;
    }

    public List<T> getDatas() {
        return datas;
    }

    public void setDatas(List<T> datas) {
        this.datas = datas;
    }

    @Override
    public int getItemCount() {
        return datas.size();
    }

    public Context getmContext() {
        return mContext;
    }

    public void setOnRecyclerViewListener(OnRecyclerViewListener onRecyclerViewListener) {
        this.onRecyclerViewListener = onRecyclerViewListener;
    }

    public interface OnRecyclerViewListener {
        /**
         * 条目点击的监听回调
         *
         * @param position
         */
        void onItemClick(View view, int position);

        /**
         * 长按点击的监听回调
         *
         * @param position
         */
        boolean onItemLongClick(int position);
    }
}
