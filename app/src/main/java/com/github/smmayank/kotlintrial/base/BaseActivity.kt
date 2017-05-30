package com.github.smmayank.kotlintrial.base

import android.os.Bundle
import android.support.annotation.IdRes
import android.support.annotation.LayoutRes
import android.support.v4.app.Fragment
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.Toolbar
import android.view.MenuItem
import com.github.smmayank.kotlintrial.base.common.Constants.INVALID_RESOURCE

/**
 * @author Mayank Saxena
 * @version 1.0
 * @since 2017-05-30
 */
abstract class BaseActivity : AppCompatActivity(), BaseFragment.InteractionListener {

    override final fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        beforeLayout()
        setContentView(getLayoutId())
        setToolbar(getToolbarId())
        afterLayout()
    }

    override final fun onBackPressed() {
        val fragment = getCurrentFragment()
        if (fragment?.backHandled()!!) {
            return
        }
        super.onBackPressed()
    }

    private fun getCurrentFragment(): BaseFragment? {
        return supportFragmentManager.findFragmentById(getFragmentContainerId()) as BaseFragment?
    }

    override final fun replace(fragment: Fragment?) {
        val containerId = getFragmentContainerId()
        if (containerId == INVALID_RESOURCE) {
            return
        }
        supportFragmentManager.beginTransaction()
                .replace(containerId, fragment)
                .commitAllowingStateLoss()
    }

    override fun add(fragment: Fragment?) {
        val containerId = getFragmentContainerId()
        if (containerId == INVALID_RESOURCE) {
            return
        }
        supportFragmentManager.beginTransaction()
                .add(containerId, fragment)
                .commitAllowingStateLoss()
    }

    fun setToolbar(@IdRes toolbarId: Int) {
        val toolbar = findViewById(toolbarId)
        setSupportActionBar(toolbar as Toolbar?)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        if (item?.itemId == android.R.id.home) {
            onBackPressed()
            return true
        }
        return super.onOptionsItemSelected(item)
    }

    override final fun setTitle(title: CharSequence?) {
        supportActionBar?.title = title
    }

    open fun beforeLayout() = Unit

    @LayoutRes
    abstract fun getLayoutId(): Int

    open fun getToolbarId(): Int = INVALID_RESOURCE

    open fun afterLayout() = Unit

    @IdRes
    open fun getFragmentContainerId(): Int = INVALID_RESOURCE
}