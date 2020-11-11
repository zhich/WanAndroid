package com.zch.base.widget

import android.content.Context
import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.Rect
import android.graphics.drawable.Drawable

import com.zch.base.R

/**
 * Created by zch on 2019/01/12.
 */
class CustomDecoration(context: Context, orientation: Int, drawable: Int, private val mInset: Int) : androidx.recyclerview.widget.RecyclerView.ItemDecoration() {

    private val mDivider: Drawable = context.resources.getDrawable(drawable)
    private var mOrientation: Int = 0
    private val mPaint: Paint = Paint()

    @JvmOverloads
    constructor(context: Context, drawable: Int = R.drawable.shape_divider_1, inset: Int = 0) : this(context, 1, drawable, inset)

    init {
        this.mPaint.color = context.resources.getColor(R.color.color_ffffff)
        this.mPaint.style = Paint.Style.FILL
        this.mPaint.isAntiAlias = true
        this.setOrientation(orientation)
    }

    fun setOrientation(orientation: Int) {
        if (orientation != 0 && orientation != 1) {
            throw IllegalArgumentException("invalid orientation")
        } else {
            this.mOrientation = orientation
        }
    }

    override fun onDraw(c: Canvas, parent: androidx.recyclerview.widget.RecyclerView) {
        if (this.mOrientation == 1) {
            this.drawVertical(c, parent)
        } else {
            this.drawHorizontal(c, parent)
        }

    }

    private fun drawVertical(c: Canvas, parent: androidx.recyclerview.widget.RecyclerView) {
        val left = parent.paddingLeft
        val right = parent.width - parent.paddingRight
        val childCount = parent.childCount

        for (i in 0 until childCount - 1) {
            val child = parent.getChildAt(i)
            val params = child.layoutParams as androidx.recyclerview.widget.RecyclerView.LayoutParams
            val top = child.bottom + params.bottomMargin
            val bottom = top + this.mDivider.intrinsicHeight
            if (this.mInset > 0) {
                c.drawRect(left.toFloat(), top.toFloat(), right.toFloat(), bottom.toFloat(), this.mPaint)
                this.mDivider.setBounds(left + this.mInset, top, right - this.mInset, bottom)
            } else {
                this.mDivider.setBounds(left, top, right, bottom)
            }

            this.mDivider.draw(c)
        }

    }

    private fun drawHorizontal(c: Canvas, parent: androidx.recyclerview.widget.RecyclerView) {
        val top = parent.paddingTop
        val bottom = parent.height - parent.paddingBottom
        val childCount = parent.childCount

        for (i in 0 until childCount - 1) {
            val child = parent.getChildAt(i)
            val params = child.layoutParams as androidx.recyclerview.widget.RecyclerView.LayoutParams
            val left = child.right + params.rightMargin
            val right = left + this.mDivider.intrinsicHeight
            this.mDivider.setBounds(left, top, right, bottom)
            this.mDivider.draw(c)
        }

    }

    override fun getItemOffsets(outRect: Rect, itemPosition: Int, parent: androidx.recyclerview.widget.RecyclerView) {
        if (this.mOrientation == 1) {
            outRect.set(0, 0, 0, this.mDivider.intrinsicHeight)
        } else {
            outRect.set(0, 0, this.mDivider.intrinsicWidth, 0)
        }

    }

    companion object {

        val HORIZONTAL_LIST = 0
        val VERTICAL_LIST = 1
    }
}
