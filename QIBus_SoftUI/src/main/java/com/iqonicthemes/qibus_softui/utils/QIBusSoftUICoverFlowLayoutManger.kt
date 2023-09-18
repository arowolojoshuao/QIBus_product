package com.iqonicthemes.qibus_softui.utils

import android.animation.Animator
import android.animation.ValueAnimator
import android.graphics.ColorMatrix
import android.graphics.ColorMatrixColorFilter
import android.graphics.Paint
import android.graphics.Rect
import android.util.SparseArray
import android.util.SparseBooleanArray
import android.view.View
import android.view.ViewGroup
import android.view.animation.DecelerateInterpolator

import androidx.recyclerview.widget.RecyclerView


class QIBusSoftUICoverFlowLayoutManger(isFlat: Boolean, isGreyItem: Boolean,
                                       isAlphaItem: Boolean, cstInterval: Float) : RecyclerView.LayoutManager() {
    private val MAX_RECT_COUNT = 100
    private var mOffsetAll = 0
    private var mDecoratedChildWidth = 0
    private var mDecoratedChildHeight = 0
    private var mIntervalRatio = 0.5f
    private var mStartX = 0
    private var mStartY = 0
    private val mAllItemFrames = SparseArray<Rect>()
    private val mHasAttachedItems = SparseBooleanArray()
    private var mRecycle: RecyclerView.Recycler? = null
    private var mState: RecyclerView.State? = null
    private var mAnimation: ValueAnimator? = null
    var selectedPos = 0
        private set
    private var mLastSelectPosition = 0
    private var mSelectedListener: OnSelected? = null

    private var mIsFlatFlow = false

    private var mItemGradualGrey = false

    private var mItemGradualAlpha = false


    private val horizontalSpace: Int
        get() = width - paddingRight - paddingLeft


    private val verticalSpace: Int
        get() = height - paddingBottom - paddingTop


    private val maxOffset: Float
        get() = (itemCount - 1) * intervalDistance


    private val intervalDistance: Float
        get() = mDecoratedChildWidth * mIntervalRatio


    val firstVisiblePosition: Int
        get() {
            val displayFrame = Rect(mOffsetAll, 0, mOffsetAll + horizontalSpace, verticalSpace)
            val cur = centerPosition
            for (i in cur - 1 downTo 0) {
                val rect = getFrame(i)
                if (!Rect.intersects(displayFrame, rect)) {
                    return i + 1
                }
            }
            return 0
        }


    val lastVisiblePosition: Int
        get() {
            val displayFrame = Rect(mOffsetAll, 0, mOffsetAll + horizontalSpace, verticalSpace)
            val cur = centerPosition
            for (i in cur + 1 until itemCount) {
                val rect = getFrame(i)
                if (!Rect.intersects(displayFrame, rect)) {
                    return i - 1
                }
            }
            return cur
        }

    val maxVisibleCount: Int
        get() {
            val oneSide = ((horizontalSpace - mStartX) / intervalDistance).toInt()
            return oneSide * 2 + 1
        }

    val centerPosition: Int
        get() {
            var pos = (mOffsetAll / intervalDistance).toInt()
            val more = (mOffsetAll % intervalDistance).toInt()
            if (more > intervalDistance * 0.5f) pos++
            return pos
        }

    init {
        mIsFlatFlow = isFlat
        mItemGradualGrey = isGreyItem
        mItemGradualAlpha = isAlphaItem
        if (cstInterval >= 0) {
            mIntervalRatio = cstInterval
        } else {
            if (mIsFlatFlow) {
                mIntervalRatio = 1.1f
            }
        }
    }

    override fun generateDefaultLayoutParams(): RecyclerView.LayoutParams {
        return RecyclerView.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT)
    }

    override fun onLayoutChildren(recycler: RecyclerView.Recycler?, state: RecyclerView.State?) {

        if (itemCount <= 0 || state!!.isPreLayout) {
            mOffsetAll = 0
            return
        }
        mAllItemFrames.clear()
        mHasAttachedItems.clear()

        val scrap = recycler!!.getViewForPosition(0)
        addView(scrap)
        measureChildWithMargins(scrap, 0, 0)
        mDecoratedChildWidth = getDecoratedMeasuredWidth(scrap)
        mDecoratedChildHeight = getDecoratedMeasuredHeight(scrap)
        mStartX = Math.round((horizontalSpace - mDecoratedChildWidth) * 1.0f / 2)
        mStartY = Math.round((verticalSpace - mDecoratedChildHeight) * 1.0f / 2)

        var offset = mStartX.toFloat()

        var i = 0
        while (i < itemCount && i < MAX_RECT_COUNT) {
            var frame: Rect? = mAllItemFrames.get(i)
            if (frame == null) {
                frame = Rect()
            }
            frame.set(Math.round(offset), mStartY, Math.round(offset + mDecoratedChildWidth), mStartY + mDecoratedChildHeight)
            mAllItemFrames.put(i, frame)
            mHasAttachedItems.put(i, false)
            offset = offset + intervalDistance
            i++
        }

        detachAndScrapAttachedViews(recycler)
        if ((mRecycle == null || mState == null) && selectedPos != 0) {
            mOffsetAll = calculateOffsetForPosition(selectedPos)
            onSelectedCallBack()
        }

        layoutItems(recycler, state, SCROLL_RIGHT)

        mRecycle = recycler
        mState = state
    }

    override fun scrollHorizontallyBy(dx: Int, recycler: RecyclerView.Recycler?,
                                      state: RecyclerView.State?): Int {
        if (mAnimation != null && mAnimation!!.isRunning) mAnimation!!.cancel()
        var travel = dx
        if (dx + mOffsetAll < 0) {
            travel = -mOffsetAll
        } else if (dx + mOffsetAll > maxOffset) {
            travel = (maxOffset - mOffsetAll).toInt()
        }
        mOffsetAll += travel
        layoutItems(recycler, state!!, if (dx > 0) SCROLL_RIGHT else SCROLL_LEFT)
        return travel
    }

    private fun layoutItems(recycler: RecyclerView.Recycler?,
                            state: RecyclerView.State, scrollDirection: Int) {
        if (state.isPreLayout) return

        val displayFrame = Rect(mOffsetAll, 0, mOffsetAll + horizontalSpace, verticalSpace)

        var position = 0
        for (i in 0 until childCount) {
            val child = getChildAt(i)
            if (child!=null){
                position = getPosition(child)
                val rect = getFrame(position)
                if (!Rect.intersects(displayFrame, rect)) {
                    removeAndRecycleView(child, recycler!!)
                    mHasAttachedItems.delete(position)
                } else {
                    layoutItem(child, rect)
                    mHasAttachedItems.put(position, true)
                }
            }

        }
        if (position == 0) position = selectedPos

        val min = if (position - 50 >= 0) position - 50 else 0
        val max = if (position + 50 < itemCount) position + 50 else itemCount

        for (i in min until max) {
            val rect = getFrame(i)
            if (Rect.intersects(displayFrame, rect) && !mHasAttachedItems.get(i)) {
                val scrap = recycler!!.getViewForPosition(i)
                measureChildWithMargins(scrap, 0, 0)
                if (scrollDirection == SCROLL_LEFT || mIsFlatFlow) {
                    addView(scrap, 0)
                } else {
                    addView(scrap)
                }
                layoutItem(scrap, rect)
                mHasAttachedItems.put(i, true)
            }
        }
    }

    private fun layoutItem(child: View, frame: Rect) {
        layoutDecorated(child,
                frame.left - mOffsetAll,
                frame.top,
                frame.right - mOffsetAll,
                frame.bottom)
        if (!mIsFlatFlow) {
            child.scaleX = computeScale(frame.left - mOffsetAll)
            child.scaleY = computeScale(frame.left - mOffsetAll)
        }

        if (mItemGradualAlpha) {
            child.alpha = computeAlpha(frame.left - mOffsetAll)
        }

        if (mItemGradualGrey) {
            greyItem(child, frame)
        }
    }


    private fun getFrame(index: Int): Rect {
        var frame: Rect? = mAllItemFrames.get(index)
        if (frame == null) {
            frame = Rect()
            val offset = mStartX + intervalDistance * index
            frame.set(Math.round(offset), mStartY, Math.round(offset + mDecoratedChildWidth), mStartY + mDecoratedChildHeight)
        }

        return frame
    }


    private fun greyItem(child: View, frame: Rect) {
        val value = computeGreyScale(frame.left - mOffsetAll)
        val cm = ColorMatrix(floatArrayOf(value, 0f, 0f, 0f, 120 * (1 - value), 0f, value, 0f, 0f, 120 * (1 - value), 0f, 0f, value, 0f, 120 * (1 - value), 0f, 0f, 0f, 1f, 250 * (1 - value)))

        // Create a paint object with color matrix
        val greyPaint = Paint()
        greyPaint.colorFilter = ColorMatrixColorFilter(cm)

        // Create a hardware layer with the grey paint
        child.setLayerType(View.LAYER_TYPE_HARDWARE, greyPaint)
        if (value >= 1) {
            // Remove the hardware layer
            child.setLayerType(View.LAYER_TYPE_NONE, null)
        }

    }

    override fun onScrollStateChanged(state: Int) {
        super.onScrollStateChanged(state)
        when (state) {
            RecyclerView.SCROLL_STATE_IDLE -> fixOffsetWhenFinishScroll()
            RecyclerView.SCROLL_STATE_DRAGGING -> {
            }
            RecyclerView.SCROLL_STATE_SETTLING -> {
            }
        }
    }

    override fun scrollToPosition(position: Int) {
        if (position < 0 || position > itemCount - 1) return
        mOffsetAll = calculateOffsetForPosition(position)
        if (mRecycle == null || mState == null) {
            selectedPos = position
        } else {
            layoutItems(mRecycle, mState!!, if (position > selectedPos) SCROLL_RIGHT else SCROLL_LEFT)
            onSelectedCallBack()
        }
    }

    override fun smoothScrollToPosition(recyclerView: RecyclerView?, state: RecyclerView.State?, position: Int) {
        val finalOffset = calculateOffsetForPosition(position)
        if (mRecycle == null || mState == null) {
            selectedPos = position
        } else {
            startScroll(mOffsetAll, finalOffset)
        }
    }

    override fun canScrollHorizontally(): Boolean {
        return true
    }

    override fun onAdapterChanged(oldAdapter: RecyclerView.Adapter<*>?, newAdapter: RecyclerView.Adapter<*>?) {
        removeAllViews()
        mRecycle = null
        mState = null
        mOffsetAll = 0
        selectedPos = 0
        mLastSelectPosition = 0
        mHasAttachedItems.clear()
        mAllItemFrames.clear()
    }


    private fun computeScale(x: Int): Float {
        var scale = 1 - Math.abs(x - mStartX) * 1.0f / Math.abs(mStartX + mDecoratedChildWidth / mIntervalRatio)
        if (scale < 0) scale = 0f
        if (scale > 1) scale = 1f
        return scale
    }


    private fun computeGreyScale(x: Int): Float {
        val itemMidPos = (x + mDecoratedChildWidth / 2).toFloat() //item中点x坐标
        val itemDx2Mid = Math.abs(itemMidPos - horizontalSpace / 2)
        var value = 1 - itemDx2Mid * 1.0f / (horizontalSpace / 2)
        if (value < 0.1) value = 0.1f
        if (value > 1) value = 1f
        value = Math.pow(value.toDouble(), .8).toFloat()
        return value
    }

    private fun computeAlpha(x: Int): Float {
        var alpha = 1 - Math.abs(x - mStartX) * 1.0f / Math.abs(mStartX + mDecoratedChildWidth / mIntervalRatio)
        if (alpha < 0.3f) alpha = 0.3f
        if (alpha > 1) alpha = 1.0f
        return alpha
    }

    private fun calculateOffsetForPosition(position: Int): Int {
        return Math.round(intervalDistance * position)
    }


    private fun fixOffsetWhenFinishScroll() {
        var scrollN = (mOffsetAll * 1.0f / intervalDistance).toInt()
        val moreDx = mOffsetAll % intervalDistance
        if (moreDx > intervalDistance * 0.5) {
            scrollN++
        }
        val finalOffset = (scrollN * intervalDistance).toInt()
        startScroll(mOffsetAll, finalOffset)
        selectedPos = Math.round(finalOffset * 1.0f / intervalDistance)
    }


    private fun startScroll(from: Int, to: Int) {
        if (mAnimation != null && mAnimation!!.isRunning) {
            mAnimation!!.cancel()
        }
        val direction = if (from < to) SCROLL_RIGHT else SCROLL_LEFT
        mAnimation = ValueAnimator.ofFloat(from.toFloat(), to.toFloat())
        mAnimation!!.duration = 500
        mAnimation!!.interpolator = DecelerateInterpolator()
        mAnimation!!.addUpdateListener { animation ->
            mOffsetAll = Math.round(animation.animatedValue as Float)
            layoutItems(mRecycle, mState!!, direction)
        }
        mAnimation!!.addListener(object : Animator.AnimatorListener {
            override fun onAnimationStart(animation: Animator) {

            }

            override fun onAnimationEnd(animation: Animator) {
                onSelectedCallBack()
            }

            override fun onAnimationCancel(animation: Animator) {

            }

            override fun onAnimationRepeat(animation: Animator) {

            }
        })
        mAnimation!!.start()
    }


    private fun onSelectedCallBack() {
        selectedPos = Math.round(mOffsetAll / intervalDistance)
        if (mSelectedListener != null && selectedPos != mLastSelectPosition) {
            mSelectedListener!!.onItemSelected(selectedPos)
        }
        mLastSelectPosition = selectedPos
    }


    fun setOnSelectedListener(l: OnSelected) {
        mSelectedListener = l
    }

    interface OnSelected {

        fun onItemSelected(position: Int)
    }

    class Builder {
        internal var isFlat = false
        internal var isGreyItem = false
        internal var isAlphaItem = false
        internal var cstIntervalRatio = -1f

        fun setFlat(flat: Boolean): Builder {
            isFlat = flat
            return this
        }

        fun setGreyItem(greyItem: Boolean): Builder {
            isGreyItem = greyItem
            return this
        }

        fun setAlphaItem(alphaItem: Boolean): Builder {
            isAlphaItem = alphaItem
            return this
        }

        fun setIntervalRatio(ratio: Float): Builder {
            cstIntervalRatio = ratio
            return this
        }

        fun build(): QIBusSoftUICoverFlowLayoutManger {
            return QIBusSoftUICoverFlowLayoutManger(isFlat, isGreyItem,
                    isAlphaItem, cstIntervalRatio)
        }
    }

    companion object {
        /*variable declaration*/
        private val SCROLL_LEFT = 1
        private val SCROLL_RIGHT = 2
    }
}