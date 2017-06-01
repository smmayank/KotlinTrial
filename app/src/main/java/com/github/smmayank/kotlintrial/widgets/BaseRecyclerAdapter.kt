package com.github.smmayank.kotlintrial.widgets

import android.content.Context
import android.support.annotation.LayoutRes
import android.support.v7.widget.RecyclerView.Adapter
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import java.util.*

/**
 * @author Mayank Saxena
 * @version 1.0
 * @since 2017-05-31
 */
abstract class BaseRecyclerAdapter<Item : BaseRecyclerItem, Holder : BaseRecyclerViewHolder<Item>>(
        context: Context?) : Adapter<Holder>() {

    private val inflater: LayoutInflater? = LayoutInflater.from(context)

    val itemList = LinkedList<Item>()

    override final fun getItemCount(): Int = itemList.size

    override final fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): Holder =
            createHolder(parent, viewType)

    override final fun onBindViewHolder(holder: Holder?, position: Int) {
        holder?.updateData(itemList[position])
    }

    override final fun getItemViewType(position: Int): Int = itemList[position].getType()

    fun addItems(vararg items: Item) {
        val lastIndex = itemList.lastIndex
        itemList += items
        notifyItemInserted(lastIndex)
    }

    fun inflate(parent: ViewGroup?, @LayoutRes layoutId: Int): View? {
        return inflater?.inflate(layoutId, parent, false)
    }

    abstract fun createHolder(parent: ViewGroup?, viewType: Int): Holder
}