package com.example.haowenyu.cloudqcq.adapter

import android.support.v7.widget.RecyclerView
import android.view.ViewGroup
import com.example.haowenyu.cloudqcq.view.items.BaseViewHolder

abstract class BaseAdapter<T> : RecyclerView.Adapter<BaseViewHolder<T>>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder<T> {
        return null!!
    }

    override fun getItemCount(): Int {
       return 0
    }

    override fun onBindViewHolder(holder: BaseViewHolder<T>, position: Int) {
        if (onItemClickListener != null && holder != null) {
            holder.itemView.setOnClickListener {
                run {
                    onItemClickListener?.onRecyclerViewItemClick(position)
                }
            }
        }
    }

    var mList: ArrayList<T>? = null // 数据源
    var onItemClickListener: OnRecyclerViewItemClickListener? = null


}