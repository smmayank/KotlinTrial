package com.github.smmayank.kotlintrial.base

import android.content.Context
import android.os.Bundle
import android.support.annotation.LayoutRes
import android.support.annotation.StringRes
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

/**
 * @author Mayank Saxena
 * @version 1.0
 * @since 2017-05-30
 */

abstract class BaseFragment : Fragment() {

    private var interactionListener: InteractionListener? = null

    override fun onAttach(context: Context?) {
        super.onAttach(context)
        interactionListener = context as InteractionListener
    }

    open fun backHandled(): Boolean = false

    override final fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?,
            savedInstanceState: Bundle?): View? = inflater?.inflate(getViewId(), container, false)

    override final fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        viewCreated(view)
        attachListeners()
        updateUi()
    }

    open fun viewCreated(created: View?) = Unit
    open fun attachListeners() = Unit
    open fun updateUi() = Unit

    @LayoutRes
    abstract fun getViewId(): Int

    fun setTitle(@StringRes title: Int) = setTitle(getString(title))

    fun setTitle(title: CharSequence?) = interactionListener?.setTitle(title)


    interface InteractionListener {
        fun setTitle(title: CharSequence?)
        fun replace(fragment: Fragment?)
        fun add(fragment: Fragment?)
    }
}