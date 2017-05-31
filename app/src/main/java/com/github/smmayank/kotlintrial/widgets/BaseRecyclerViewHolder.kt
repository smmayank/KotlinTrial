package com.github.smmayank.kotlintrial.widgets

import android.support.v7.widget.RecyclerView.ViewHolder
import android.view.View

/**
 * @author Mayank Saxena
 * @version 1.0
 * @since 2017-05-31
 */
abstract class BaseRecyclerViewHolder<in Item : BaseRecyclerItem>(
        itemView: View?) : ViewHolder(itemView) {
    abstract fun updateData(item: Item)
}