package com.github.smmayank.kotlintrial.home

import android.support.annotation.LayoutRes
import com.github.smmayank.kotlintrial.R
import com.github.smmayank.kotlintrial.base.BaseActivity

class HomePage : BaseActivity() {

    @LayoutRes
    override fun getLayoutId(): Int = R.layout.activity_home_page

    override fun getToolbarId(): Int = R.id.app_toolbar

    override fun getFragmentContainerId(): Int = R.id.home_page_fragment_container

    override fun afterLayout() = add(HomeList.init(), false)

}
