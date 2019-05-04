package com.example.haowenyu.cloudqcq.view.items

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.View
import com.example.haowenyu.cloudqcq.adapter.BaseAdapter

abstract class BaseViewHolder<T> constructor(itemView: View) : RecyclerView.ViewHolder(itemView) {

    var context: Context = itemView.context

    abstract fun setData(t: T, position: Int, adapter: BaseAdapter<T>)
}