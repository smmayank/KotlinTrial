package com.github.smmayank.kotlintrial.home

import android.view.View
import android.widget.TextView
import com.github.smmayank.kotlintrial.widgets.BaseRecyclerViewHolder
import com.github.smmayank.kotlintrial.common.ViewHelper.findById

/**
 * @author Mayank Saxena
 * @version 1.0
 * @since 2017-05-31
 */
class HomeListDataHolder(itemView: View?) : BaseRecyclerViewHolder<HomeListItem>(itemView) {

    val titleText: TextView? = findById(itemView, android.R.id.text1)

    override fun updateData(item: HomeListItem) {
        titleText?.text = item.name
    }
}