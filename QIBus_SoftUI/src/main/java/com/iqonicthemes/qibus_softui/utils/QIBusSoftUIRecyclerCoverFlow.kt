package com.iqonicthemes.qibus_softui.utils

import android.content.Context
import android.util.AttributeSet
import android.view.MotionEvent
import androidx.recyclerview.widget.RecyclerView

class QIBusSoftUIRecyclerCoverFlow : RecyclerView {
    /*variable declaration*/

    private var mDownX: Float = 0.toFloat()

    private var mManagerBuilder: QIBusSoftUICoverFlowLayoutManger.Builder? = null

    val softUICoverFlowLayout: QIBusSoftUICoverFlowLayoutManger
        get() = layoutManager as QIBusSoftUICoverFlowLayoutManger

    val selectedPos: Int
        get() = softUICoverFlowLayout.selectedPos

    /*constructor*/

    constructor(context: Context) : super(context) {
        init()
    }

    /*constructor*/

    constructor(context: Context, attrs: AttributeSet?) : super(context, attrs) {
        init()
    }

    /*constructor*/

    constructor(context: Context, attrs: AttributeSet?, defStyle: Int) : super(context, attrs, defStyle) {
        init()
    }

    private fun init() {
        createManageBuilder()
        layoutManager = mManagerBuilder!!.build()
        isChildrenDrawingOrderEnabled = true
        //        setOverScrollMode(OVER_SCROLL_NEVER);
    }

    private fun createManageBuilder() {
        if (mManagerBuilder == null) {
            mManagerBuilder = QIBusSoftUICoverFlowLayoutManger.Builder()
        }
    }

    fun setFlatFlow(isFlat: Boolean) {
        createManageBuilder()
        mManagerBuilder!!.setFlat(isFlat)
        layoutManager = mManagerBuilder!!.build()
    }

    fun setGreyItem(greyItem: Boolean) {
        createManageBuilder()
        mManagerBuilder!!.setGreyItem(greyItem)
        layoutManager = mManagerBuilder!!.build()
    }

    fun setAlphaItem(alphaItem: Boolean) {
        createManageBuilder()
        mManagerBuilder!!.setAlphaItem(alphaItem)
        layoutManager = mManagerBuilder!!.build()
    }

    fun setIntervalRatio(intervalRatio: Float) {
        createManageBuilder()
        mManagerBuilder!!.setIntervalRatio(intervalRatio)
        layoutManager = mManagerBuilder!!.build()
    }

    override fun setLayoutManager(layout: RecyclerView.LayoutManager?) {
        if (layout !is QIBusSoftUICoverFlowLayoutManger) {
            throw IllegalArgumentException("The layout manager must be QIBusSoftUICoverFlowLayoutManger")
        }
        super.setLayoutManager(layout)
    }

    override fun getChildDrawingOrder(childCount: Int, i: Int): Int {
        var center = softUICoverFlowLayout.centerPosition - softUICoverFlowLayout.firstVisiblePosition
        if (center < 0)
            center = 0
        else if (center > childCount) center = childCount
        val order: Int
        if (i == center) {
            order = childCount - 1
        } else if (i > center) {
            order = center + childCount - 1 - i
        } else {
            order = i
        }
        return order
    }

    fun setOnItemSelectedListener(l: QIBusSoftUICoverFlowLayoutManger.OnSelected) {
        softUICoverFlowLayout.setOnSelectedListener(l)
    }

    /*touch event*/

    override fun dispatchTouchEvent(ev: MotionEvent): Boolean {
        when (ev.action) {
            MotionEvent.ACTION_DOWN -> {
                mDownX = ev.x
                parent.requestDisallowInterceptTouchEvent(false)
            }
            MotionEvent.ACTION_MOVE -> if (ev.x > mDownX && softUICoverFlowLayout.centerPosition == 0 || ev.x < mDownX && softUICoverFlowLayout.centerPosition == softUICoverFlowLayout.itemCount - 1) {
                //                    getParent().requestDisallowInterceptTouchEvent(false);
            } else {
                parent.requestDisallowInterceptTouchEvent(false)
            }
        }
        return super.dispatchTouchEvent(ev)
    }
}
