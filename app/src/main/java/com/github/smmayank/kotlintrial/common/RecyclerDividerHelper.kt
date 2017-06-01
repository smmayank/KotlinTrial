package com.github.smmayank.kotlintrial.common

import android.content.Context
import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.Rect
import android.support.v4.content.ContextCompat
import android.support.v7.widget.RecyclerView
import android.support.v7.widget.RecyclerView.ItemDecoration
import android.view.View
import com.github.smmayank.kotlintrial.R

/**
 * @author Mayank Saxena
 * @version 1.0
 * @since 2017-05-31
 */
object RecyclerDividerHelper {

    val FLAG_DIVIDER_START = 0x0001
    val FLAG_DIVIDER_END = 0x0010
    val FLAG_DIVIDER_MIDDLE = 0x0100

    val FLAG_DEFAULT = FLAG_DIVIDER_MIDDLE or FLAG_DIVIDER_END


    enum class DividerType {
        VERTICAL
    }

    class Builder {
        lateinit private var type: DividerType
        private var flags: Int = 0

        fun setType(type: DividerType): Builder {
            this.type = type
            return this
        }

        fun setFlags(flags: Int): Builder {
            this.flags = flags
            return this
        }

        fun build(context: Context?): ItemDecoration? {
            val divider: BaseRecyclerDivider? = createDivider(type)
            val dividerWidth = context?.resources?.getDimensionPixelOffset(R.dimen.divider_dimension) ?: 0
            val dividerColor = ContextCompat.getColor(context, R.color.divider_color)
            divider?.dividerDimen = dividerWidth
            divider?.setColor(dividerColor)
            divider?.setFlags(flags)
            return divider
        }

        private fun createDivider(type: DividerType): BaseRecyclerDivider? {
            if (type == DividerType.VERTICAL) {
                return RecyclerVerticalDivider()
            }
            return null
        }
    }

    private abstract class BaseRecyclerDivider() : ItemDecoration() {

        var dividerDimen: Int = 0

        fun setColor(value: Int) {
            paint.color = value
        }

        var start = false
        var end = false
        var middle = false

        fun setFlags(flags: Int) {
            start = set(flags, FLAG_DIVIDER_START)
            end = set(flags, FLAG_DIVIDER_END)
            middle = set(flags, FLAG_DIVIDER_MIDDLE)
        }

        val paint: Paint = Paint()

        init {
            paint.isAntiAlias = true
            paint.style = Paint.Style.FILL
        }

        private fun set(flag: Int, mask: Int): Boolean = flag and mask == mask


        abstract fun setStartOffset(outRect: Rect?)
        abstract fun setMiddleOffset(outRect: Rect?)
        abstract fun setEndOffset(outRect: Rect?)

        abstract fun addStartDivider(canvas: Canvas?, child: View?)
        abstract fun addMiddleDivider(canvas: Canvas?, child: View?)
        abstract fun addEndDivider(canvas: Canvas?, child: View?)
    }

    private abstract class LinearRecyclerDivider() : BaseRecyclerDivider() {

        override final fun getItemOffsets(outRect: Rect?, view: View?, parent: RecyclerView?,
                state: RecyclerView.State?) {
            val childIndex = parent?.indexOfChild(view)
            val firstChild = childIndex == 0
            val lastChild = childIndex == parent?.childCount?.minus(1)
            if (firstChild) {
                if (start) {
                    setStartOffset(outRect)
                }
            }
            if (lastChild) {
                if (end) {
                    setEndOffset(outRect)
                }
            } else if (middle) {
                setMiddleOffset(outRect)
            }
        }

        override fun onDraw(canvas: Canvas?, parent: RecyclerView?, state: RecyclerView.State?) {
            for (index in 0..parent?.childCount as Int) {
                val child = parent.getChildAt(index)
                val childIndex = parent.indexOfChild(child)
                val firstChild = childIndex == 0
                val lastChild = childIndex == parent.childCount.minus(1)
                if (firstChild) {
                    if (start) {
                        addStartDivider(canvas, child)
                    }
                }
                if (lastChild) {
                    if (end) {
                        addEndDivider(canvas, child)
                    }
                } else if (middle) {
                    addMiddleDivider(canvas, child)
                }
            }
        }
    }

    private class RecyclerVerticalDivider() : LinearRecyclerDivider() {

        override fun setStartOffset(outRect: Rect?) {
            outRect?.top = dividerDimen
        }

        override fun setMiddleOffset(outRect: Rect?) {
            outRect?.bottom = dividerDimen
        }

        override fun setEndOffset(outRect: Rect?) {
            outRect?.bottom = dividerDimen
        }

        override fun addStartDivider(canvas: Canvas?, child: View?) {
            val rect = Rect()
            rect.left = child?.left ?: 0
            rect.right = child?.right ?: 0
            rect.top = child?.top ?: 0
            rect.bottom = child?.top?.plus(dividerDimen) ?: 0
            canvas?.drawRect(rect, paint)
        }

        override fun addMiddleDivider(canvas: Canvas?, child: View?) {
            addEndDivider(canvas, child)
        }

        override fun addEndDivider(canvas: Canvas?, child: View?) {
            val rect = Rect()
            rect.left = child?.left ?: 0
            rect.right = child?.right ?: 0
            rect.bottom = child?.bottom ?: 0
            rect.top = child?.bottom?.minus(dividerDimen) ?: 0
            canvas?.drawRect(rect, paint)
        }
    }
}