package com.github.smmayank.kotlintrial.home

import android.content.Context
import android.support.v4.app.Fragment
import android.view.ViewGroup
import com.github.smmayank.kotlintrial.R
import com.github.smmayank.kotlintrial.home.HomeListDataHolder.HomeListDataHolderInteractionListener
import com.github.smmayank.kotlintrial.widgets.BaseRecyclerAdapter

/**
 * @author Mayank Saxena
 * @version 1.0
 * @since 2017-05-31
 */
class HomeListAdapter(context: Context?) :
        BaseRecyclerAdapter<HomeListItem, HomeListDataHolder>(context),
        HomeListDataHolderInteractionListener {

    var interactionListener: HomeListAdapterInteractionListener? = null

    private val layoutId = R.layout.home_list_item

    override fun createHolder(parent: ViewGroup?, viewType: Int): HomeListDataHolder {
        val holder = HomeListDataHolder(inflate(parent, layoutId))
        holder.listener = this
        return holder
    }

    override fun itemClicked(position: Int) {
        interactionListener?.openFragment(itemList[position].fragment)
    }

    interface HomeListAdapterInteractionListener {
        fun openFragment(fragment: Fragment)
    }
}