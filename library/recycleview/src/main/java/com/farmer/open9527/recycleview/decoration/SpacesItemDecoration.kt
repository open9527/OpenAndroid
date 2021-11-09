package com.farmer.open9527.recycleview.decoration

import android.content.Context
import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.Rect
import android.graphics.drawable.Drawable
import android.view.View
import androidx.annotation.DrawableRes
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ItemDecoration
import kotlin.math.roundToInt


/**
 *@author   open_9527
 *Create at 2021/11/4
 **/
class SpacesItemDecoration : ItemDecoration {

    private var mContext: Context? = null
    private var mDivider: Drawable? = null
    private val mBounds = Rect()

    private val mListDividerAttrs = intArrayOf(android.R.attr.listDivider)

    private var mHeaderNoShowSize = 0
    private var mFooterNoShowSize = 0

    private var mOrientation = 0
    private var mPaint: Paint? = null
    private var mDividerSpacing = 0
    private var mLeftTopPadding = 0
    private var mRightBottomPadding = 0


    constructor(context: Context) : this(context, RecyclerView.VERTICAL, 0, 0)

    constructor(context: Context, orientation: Int) : this(context, orientation, 0, 0)

    constructor(
        context: Context,
        orientation: Int,
        headerNoShowSize: Int,
        footerNoShowSize: Int
    ) {
        mContext = context
        mHeaderNoShowSize = headerNoShowSize
        mFooterNoShowSize = footerNoShowSize
        setOrientation(orientation)
        val attrRes = context.obtainStyledAttributes(mListDividerAttrs)
        mDivider = attrRes.getDrawable(0)
        attrRes.recycle()
    }


    override fun onDraw(canvas: Canvas, parent: RecyclerView, state: RecyclerView.State) {
        if (parent.layoutManager == null || mDivider == null && mPaint == null) {
            return
        }
        if (mOrientation == RecyclerView.VERTICAL) {
            drawVertical(canvas, parent, state)
        } else {
            drawHorizontal(canvas, parent, state)
        }
    }

    override fun getItemOffsets(
        outRect: Rect,
        view: View,
        parent: RecyclerView,
        state: RecyclerView.State
    ) {
        if (mDivider == null && mPaint == null) {
            outRect[0, 0, 0] = 0
            return
        }
        val lastPosition = state.itemCount - 1
        val position = parent.getChildAdapterPosition(view)
        val isShowDivider =
            mHeaderNoShowSize <= position && position <= lastPosition - mFooterNoShowSize
        if (mOrientation == RecyclerView.VERTICAL) {
            if (isShowDivider) {
                outRect[0, 0, 0] =
                    if (mDivider != null) mDivider!!.intrinsicHeight else mDividerSpacing
            } else {
                outRect[0, 0, 0] = 0
            }
        } else {
            if (isShowDivider) {
                outRect[0, 0, if (mDivider != null) mDivider!!.intrinsicWidth else mDividerSpacing] =
                    0
            } else {
                outRect[0, 0, 0] = 0
            }
        }
    }


    private fun drawVertical(canvas: Canvas, parent: RecyclerView, state: RecyclerView.State) {
        canvas.save()
        val left: Int
        val right: Int
        if (parent.clipToPadding) {
            left = parent.paddingLeft
            right = parent.width - parent.paddingRight
            canvas.clipRect(left, parent.paddingTop, right, parent.height - parent.paddingBottom)
        } else {
            left = 0
            right = parent.width
        }
        val childCount = parent.childCount
        val lastPosition = state.itemCount - 1
        for (i in 0 until childCount) {
            val child = parent.getChildAt(i)
            val childRealPosition = parent.getChildAdapterPosition(child)
            if (childRealPosition < mHeaderNoShowSize) {
                continue
            }
            if (childRealPosition <= lastPosition - mFooterNoShowSize) {
                if (mDivider != null) {
                    parent.getDecoratedBoundsWithMargins(child, mBounds)
                    val bottom = mBounds.bottom + child.translationY.roundToInt()
                    val top = bottom - mDivider!!.intrinsicHeight
                    mDivider!!.setBounds(left, top, right, bottom)
                    mDivider!!.draw(canvas)
                }
                if (mPaint != null) {
                    val params = child.layoutParams as RecyclerView.LayoutParams
                    val left1 = left + mLeftTopPadding
                    val right1 = right - mRightBottomPadding
                    val top1 = child.bottom + params.bottomMargin
                    val bottom1 = top1 + mDividerSpacing
                    canvas.drawRect(
                        left1.toFloat(),
                        top1.toFloat(),
                        right1.toFloat(),
                        bottom1.toFloat(),
                        mPaint!!
                    )
                }
            }
        }
        canvas.restore()
    }


    private fun drawHorizontal(canvas: Canvas, parent: RecyclerView, state: RecyclerView.State) {
        canvas.save()
        val top: Int
        val bottom: Int
        if (parent.clipToPadding) {
            top = parent.paddingTop
            bottom = parent.height - parent.paddingBottom
            canvas.clipRect(
                parent.paddingLeft, top,
                parent.width - parent.paddingRight, bottom
            )
        } else {
            top = 0
            bottom = parent.height
        }
        val childCount = parent.childCount
        val lastPosition = state.itemCount - 1
        for (i in 0 until childCount) {
            val child = parent.getChildAt(i)
            val childRealPosition = parent.getChildAdapterPosition(child)
            if (childRealPosition < mHeaderNoShowSize) {
                continue
            }
            if (childRealPosition <= lastPosition - mFooterNoShowSize) {
                if (mDivider != null) {
                    parent.getDecoratedBoundsWithMargins(child, mBounds)
                    val right = mBounds.right + child.translationX.roundToInt()
                    val left = right - mDivider!!.intrinsicWidth
                    mDivider!!.setBounds(left, top, right, bottom)
                    mDivider!!.draw(canvas)
                }
                if (mPaint != null) {
                    val params = child.layoutParams as RecyclerView.LayoutParams
                    val left1 = child.right + params.rightMargin
                    val right1 = left1 + mDividerSpacing
                    val top1 = top + mLeftTopPadding
                    val bottom1 = bottom - mRightBottomPadding
                    canvas.drawRect(
                        left1.toFloat(),
                        top1.toFloat(),
                        right1.toFloat(),
                        bottom1.toFloat(),
                        mPaint!!
                    )
                }
            }
        }
        canvas.restore()
    }


    fun setOrientation(orientation: Int): SpacesItemDecoration {
        require(!(orientation != RecyclerView.HORIZONTAL && orientation != RecyclerView.VERTICAL)) { "Invalid orientation. It should be either HORIZONTAL or VERTICAL" }
        mOrientation = orientation
        return this
    }

    fun setDrawable(drawable: Drawable?): SpacesItemDecoration {
        requireNotNull(drawable) { "drawable cannot be null." }
        mDivider = drawable
        return this
    }

    fun setDrawable(@DrawableRes id: Int): SpacesItemDecoration {
        setDrawable(ContextCompat.getDrawable(mContext!!, id))
        return this
    }


    fun setNoShowDivider(headerNoShowSize: Int, footerNoShowSize: Int): SpacesItemDecoration {
        mHeaderNoShowSize = headerNoShowSize
        mFooterNoShowSize = footerNoShowSize
        return this
    }


    fun setHeaderNoShowDivider(headerNoShowSize: Int): SpacesItemDecoration {
        mHeaderNoShowSize = headerNoShowSize
        return this
    }

    fun setFooterNoShowDivider(footerNoShowSize: Int): SpacesItemDecoration {
        mFooterNoShowSize = footerNoShowSize
        return this
    }

    fun setParam(dividerColor: Int, dividerSpacing: Int): SpacesItemDecoration {
        return setParam(dividerColor, dividerSpacing, 0, 0)
    }


    fun setParam(
        dividerColor: Int,
        dividerSpacing: Int,
        leftTopPaddingDp: Int,
        rightBottomPaddingDp: Int
    ): SpacesItemDecoration {
        mPaint = Paint(Paint.ANTI_ALIAS_FLAG)
        mPaint?.style = Paint.Style.FILL
        mPaint?.color = ContextCompat.getColor(mContext!!, dividerColor)
        mDividerSpacing = dividerSpacing
        mLeftTopPadding = leftTopPaddingDp
        mRightBottomPadding = rightBottomPaddingDp
        mDivider = null
        return this
    }

    companion object {

    }

}