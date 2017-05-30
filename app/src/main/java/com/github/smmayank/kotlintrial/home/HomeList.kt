package com.github.smmayank.kotlintrial.home

import android.view.View
import com.github.smmayank.kotlintrial.R
import com.github.smmayank.kotlintrial.base.BaseFragment

/**
 * @author Mayank Saxena
 * @version 1.0
 * @since 2017-05-30
 */
class HomeList : BaseFragment() {

    companion object {
        fun init(): HomeList = HomeList()
    }

    override fun getViewId(): Int = R.layout.fragment_home_list

    override fun viewCreated(created: View?) {
        setTitle(R.string.home_list_title)
    }
}