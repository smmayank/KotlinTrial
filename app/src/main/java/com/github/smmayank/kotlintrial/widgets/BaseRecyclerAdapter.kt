package com.github.smmayank.kotlintrial.widgets

/**
 * @author Mayank Saxena
 * @version 1.0
 * @since 2017-05-31
 */
abstract class BaseRecyclerAdapter<Item : BaseRecyclerItem,
        Holder : BaseRecyclerViewHolder<Item>>(context: android.content.Context?) : android.support.v7.widget.RecyclerView.Adapter<Holder>() {

    private val inflater: android.view.LayoutInflater? = android.view.LayoutInflater.from(context)

    val itemList = java.util.LinkedList<Item>()

    override final fun getItemCount(): Int = itemList.size

    override final fun onCreateViewHolder(parent: android.view.ViewGroup?,
            viewType: Int): Holder = createHolder(parent, viewType)

    override final fun onBindViewHolder(holder: Holder?, position: Int) {
        holder?.updateData(itemList[position])
    }

    override final fun getItemViewType(position: Int): Int = itemList[position].getType()

    fun addItems(vararg items: Item) {
        itemList += items
        notifyDataSetChanged()
    }

    fun inflate(parent: android.view.ViewGroup?, @android.support.annotation.LayoutRes layoutId: Int): android.view.View? {
        return inflater?.inflate(layoutId, parent, false)
    }

    abstract fun createHolder(parent: android.view.ViewGroup?, viewType: Int): Holder
}