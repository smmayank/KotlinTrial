package com.github.smmayank.kotlintrial.home

import android.content.Context
import android.view.ViewGroup
import com.github.smmayank.kotlintrial.R
import com.github.smmayank.kotlintrial.widgets.BaseRecyclerAdapter

/**
 * @author Mayank Saxena
 * @version 1.0
 * @since 2017-05-31
 */
class HomeListAdapter(context: Context?) :
        BaseRecyclerAdapter<HomeListItem, HomeListDataHolder>(context) {

    private val layoutId = R.layout.home_list_item

    override fun createHolder(parent: ViewGroup?, viewType: Int): HomeListDataHolder {
        return HomeListDataHolder(inflate(parent, layoutId))
    }
}