package com.github.smmayank.kotlintrial.home

import android.view.View
import android.widget.TextView
import com.github.smmayank.kotlintrial.common.ViewHelper.findById
import com.github.smmayank.kotlintrial.widgets.BaseRecyclerViewHolder

/**
 * @author Mayank Saxena
 * @version 1.0
 * @since 2017-05-31
 */
class HomeListDataHolder(itemView: View?) : BaseRecyclerViewHolder<HomeListItem>(itemView) {

    var listener: HomeListDataHolderInteractionListener? = null

    private val clickListener: View.OnClickListener = View.OnClickListener {
        listener?.itemClicked(adapterPosition)
    }

    init {
        itemView?.setOnClickListener(clickListener)
    }

    val titleText: TextView? = findById(itemView, android.R.id.text1)

    override fun updateData(item: HomeListItem) {
        titleText?.text = item.name
    }

    interface HomeListDataHolderInteractionListener {
        fun itemClicked(position: Int)
    }
}