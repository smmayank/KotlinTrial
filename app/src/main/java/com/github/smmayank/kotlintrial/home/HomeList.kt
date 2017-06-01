package com.github.smmayank.kotlintrial.home

import android.content.Context
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.View
import com.github.smmayank.kotlintrial.R
import com.github.smmayank.kotlintrial.base.BaseFragment
import com.github.smmayank.kotlintrial.common.RecyclerDividerHelper
import com.github.smmayank.kotlintrial.common.RecyclerDividerHelper.DividerType.VERTICAL
import com.github.smmayank.kotlintrial.common.RecyclerDividerHelper.FLAG_DEFAULT
import com.github.smmayank.kotlintrial.common.ViewHelper

/**
 * @author Mayank Saxena
 * @version 1.0
 * @since 2017-05-30
 */
class HomeList : BaseFragment(), HomeListAdapter.HomeListAdapterInteractionListener {

    override fun getContext(): Context? {
        return super.getContext()
    }

    companion object {
        fun init(): HomeList = HomeList()
    }

    override fun getViewId(): Int = R.layout.fragment_home_list

    override fun viewCreated(created: View?) {
        setTitle(R.string.home_list_title)
        val listAdapter = HomeListAdapter(context)
        listAdapter.interactionListener = this
        val recycler: RecyclerView? = ViewHelper.findById(view, R.id.home_list_recycler)
        recycler?.layoutManager = LinearLayoutManager(context)
        recycler?.addItemDecoration(createDefaultDivider())
        recycler?.adapter = listAdapter
    }

    private fun createDefaultDivider(): RecyclerView.ItemDecoration? {
        return RecyclerDividerHelper
                .Builder()
                .setFlags(FLAG_DEFAULT)
                .setType(VERTICAL)
                .build(context)
    }

    override fun openFragment(fragment: Fragment) {
        replace(fragment)
    }
}