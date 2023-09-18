package com.iqonicthemes.qibus_softui.ui.activity

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView

import com.iqonicthemes.qibus_softui.QIBusSoftUIBaseActivity
import com.iqonicthemes.qibus_softui.R
import com.iqonicthemes.qibus_softui.model.QIBusSoftUISoftUIEdgeItem
import com.iqonicthemes.qibus_softui.model.QIBusSoftUISoftUIEmptyItem
import com.iqonicthemes.qibus_softui.model.QIBusSoftUISeatModel
import com.iqonicthemes.qibus_softui.model.QIBusSoftUISeatType
import com.iqonicthemes.qibus_softui.ui.adapter.QIBusSoftUISoftUISeatAdapter
import com.iqonicthemes.qibus_softui.ui.adapter.QIBusSoftUISleeperAdapter
import com.iqonicthemes.qibus_softui.utils.QIBusSoftUIConstants
import com.iqonicthemes.qibus_softui.utils.menu.QIBusSoftUIAbstractItem
import com.iqonicthemes.qibus_softui.utils.menu.QIBusSoftUICenterItem
import com.iqonicthemes.qibus_softui.utils.menu.QIBusSoftUIOnSeatSelected

import java.util.ArrayList

import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.qibus_softui_activity_booking.*

class QIBusSoftUISoftUIBookingActivity : QIBusSoftUIBaseActivity(), View.OnClickListener {
    private var mSeatNo = 0
    private var mSeatNoSleeper = 0
    private var mRvViewSeats: RecyclerView? = null
    private var mIvAvailable: ImageView? = null
    private var mIcBook: ImageView? = null
    private var mIcSelect: ImageView? = null
    private var mIvLadies: ImageView? = null
    private var mLinear: LinearLayout? = null
    private var mLlDack: LinearLayout? = null
    private var mLlDynamic: LinearLayout? = null
    private var mSoftUIAbstractItemsList: MutableList<QIBusSoftUIAbstractItem>? = null
    private var mSoftUISeatModelsItemsList: MutableList<QIBusSoftUISeatModel>? = null
    private var mBtnBook: Button? = null
    private var mTvLower: TextView? = null
    private var mTvUpper: TextView? = null
    private var mIvBack: ImageView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.qibus_softui_activity_booking)

        initLayouts()
        initializeLayouts()
        initializeSeats()

    }

    /* initialize seats */
    private fun initializeSeats() {
        if (intent.getStringExtra(QIBusSoftUIConstants.intentdata.TYPECOACH).contains(getString(R.string.QIBus_softui_text_sleeper))) {
            showView(mLlDack!!)
            mIvAvailable!!.setImageDrawable(resources.getDrawable(R.drawable.qibus_softui_ic_sleeper))
            mIcBook!!.setImageDrawable(resources.getDrawable(R.drawable.qibus_softui_ic_sleeper))
            mIcSelect!!.setImageDrawable(resources.getDrawable(R.drawable.qibus_softui_ic_sleeper))
            mIvLadies!!.setImageDrawable(resources.getDrawable(R.drawable.qibus_softui_ic_sleeper))
            mIvAvailable!!.setColorFilter(
                ContextCompat.getColor(
                    this,
                    R.color.QIBus_softui_view_color
                )
            )
            mIcBook!!.setColorFilter(ContextCompat.getColor(this, R.color.QIBus_softui_dark_gray))
            mIcSelect!!.setColorFilter(
                ContextCompat.getColor(
                    this,
                    R.color.QIBus_softui_colorPrimary
                )
            )
            mIvLadies!!.setColorFilter(ContextCompat.getColor(this, R.color.QIBus_softui_pink))

            val mSleeperColumns = 4
            for (i in 0..27) {
                mSoftUISeatModelsItemsList!!.add(QIBusSoftUISeatModel(QIBusSoftUISeatType.EMPTY))
                mSoftUISeatModelsItemsList!!.add(QIBusSoftUISeatModel(QIBusSoftUISeatType.EMPTY))
                mSoftUISeatModelsItemsList!!.add(QIBusSoftUISeatModel(QIBusSoftUISeatType.EMPTY))
                mSoftUISeatModelsItemsList!!.add(QIBusSoftUISeatModel(QIBusSoftUISeatType.EMPTY))
                mSoftUISeatModelsItemsList!!.add(QIBusSoftUISeatModel(QIBusSoftUISeatType.BOOKED))
                mSoftUISeatModelsItemsList!!.add(QIBusSoftUISeatModel(QIBusSoftUISeatType.LADIES))
                if (i % mSleeperColumns == 0 || i % mSleeperColumns == 3) {
                    mSeatNoSleeper++
                    mSoftUIAbstractItemsList!!.add(QIBusSoftUISoftUIEdgeItem(mSeatNoSleeper.toString()))

                } else if (i % mSleeperColumns == 2) {
                    mSeatNoSleeper++
                    mSoftUIAbstractItemsList!!.add(QIBusSoftUICenterItem(mSeatNoSleeper.toString()))
                } else {
                    mSoftUIAbstractItemsList!!.add(
                        QIBusSoftUISoftUIEmptyItem(
                            mSoftUISeatModelsItemsList!!
                        )
                    )
                }
            }

            val mManager1 = GridLayoutManager(this, mSleeperColumns)
            mRvViewSeats!!.layoutManager = mManager1
            val adapter = QIBusSoftUISleeperAdapter(
                seatListener,
                mSoftUIAbstractItemsList!!,
                this,
                mSoftUISeatModelsItemsList!!
            )
            mRvViewSeats!!.adapter = adapter

        } else {

            mIvAvailable!!.setColorFilter(
                ContextCompat.getColor(
                    this,
                    R.color.QIBus_softui_view_color
                )
            )
            mIcBook!!.setColorFilter(ContextCompat.getColor(this, R.color.QIBus_softui_dark_gray))
            mIcSelect!!.setColorFilter(
                ContextCompat.getColor(
                    this,
                    R.color.QIBus_softui_colorPrimary
                )
            )
            mIvLadies!!.setColorFilter(ContextCompat.getColor(this, R.color.QIBus_softui_pink))
            for (i in 0..39) {
                mSoftUISeatModelsItemsList!!.add(QIBusSoftUISeatModel(QIBusSoftUISeatType.EMPTY))
                mSoftUISeatModelsItemsList!!.add(QIBusSoftUISeatModel(QIBusSoftUISeatType.LADIES))
                mSoftUISeatModelsItemsList!!.add(QIBusSoftUISeatModel(QIBusSoftUISeatType.BOOKED))
                mSoftUISeatModelsItemsList!!.add(QIBusSoftUISeatModel(QIBusSoftUISeatType.EMPTY))
                val mCOLUMNS = 5
                if (i % mCOLUMNS == 0 || i % mCOLUMNS == 4) {
                    mSeatNo++
                    mSoftUIAbstractItemsList!!.add(QIBusSoftUISoftUIEdgeItem(mSeatNo.toString()))
                } else if (i % mCOLUMNS == 1 || i % mCOLUMNS == 3) {
                    mSeatNo++
                    mSoftUIAbstractItemsList!!.add(QIBusSoftUICenterItem(mSeatNo.toString()))
                } else {
                    mSoftUIAbstractItemsList!!.add(
                        QIBusSoftUISoftUIEmptyItem(
                            mSoftUISeatModelsItemsList!!
                        )
                    )
                }
                val mManager = GridLayoutManager(this, mCOLUMNS)
                mRvViewSeats!!.layoutManager = mManager
                val adapter = QIBusSoftUISoftUISeatAdapter(
                    seatListener,
                    mSoftUIAbstractItemsList!!,
                    this,
                    mSoftUISeatModelsItemsList!!
                )
                mRvViewSeats!!.adapter = adapter
            }
        }
        mSeatNo = 0
        mSeatNoSleeper = 0
    }

    private var seatListener = object : QIBusSoftUIOnSeatSelected {
        override fun onSeatSelected(count: Int, label: String) {
            if (count == 0) {
                hideView(mLinear!!)
            } else {
                mCountSeat = count
                showView(mLinear!!)
                mSb.append("$label ")
                txtTicketPrice.text = String.format(
                    "%s%s",
                    getString(R.string.QIBus_softui_rs),
                    (Integer.parseInt(intent.getStringExtra(QIBusSoftUIConstants.intentdata.PRICE)) * count).toString()
                )
                mTotal =
                    Integer.parseInt(intent.getStringExtra(QIBusSoftUIConstants.intentdata.PRICE)) * count + 5
                tvTotal.text = String.format(
                    "%s%s",
                    getString(R.string.QIBus_softui_rs),
                    (Integer.parseInt(intent.getStringExtra(QIBusSoftUIConstants.intentdata.PRICE)) * count + 5).toString()
                )
            }
        }

    }

    /* initialize */
    private fun initializeLayouts() {
        mBtnBook!!.setOnClickListener(this)
        mTvLower!!.setOnClickListener(this)
        mTvUpper!!.setOnClickListener(this)
        mIvBack!!.setOnClickListener(this)
        mBtnBook!!.stateListAnimator = null


        val mCount = Integer.parseInt(intent.getStringExtra(QIBusSoftUIConstants.intentdata.HOLD))

        var i = 0
        while (i < mCount) {
            i++
            val view1 = layoutInflater.inflate(R.layout.qibus_softui_layout_hold, mLlDynamic, false)
            val mIvHold = view1.findViewById<ImageView>(R.id.ivHold)
            mIvHold.setOnClickListener { showToast(getString(R.string.QIBus_softui_text_city)) }
            mLlDynamic!!.addView(view1)
        }
    }

    /* bind view ids */

    private fun initLayouts() {
        mBtnBook = findViewById(R.id.btnBook)
        mLinear = findViewById(R.id.llOffer)
        mIvAvailable = findViewById(R.id.ivAvailable)
        mIvLadies = findViewById(R.id.icLadies)
        mIcBook = findViewById(R.id.icBook)
        mIcSelect = findViewById(R.id.icSelect)
        mSoftUIAbstractItemsList = ArrayList()
        mSoftUISeatModelsItemsList = ArrayList()
        mSb = StringBuffer()
        mRvViewSeats = findViewById(R.id.rvSeat)
        mLlDack = findViewById(R.id.lvDack)
        mTvLower = findViewById(R.id.tvLower)
        mTvUpper = findViewById(R.id.tvUpper)
        mLlDynamic = findViewById(R.id.llDynamicContent)
        mIvBack = findViewById(R.id.ivBack)
    }

    /* on click listener */
    override fun onClick(v: View) {
        if (v === mBtnBook)
            startActivity(QIBusSoftUISoftUIDroppingPointActivity::class.java)
        else if (v === mTvUpper) {
            mTvUpper!!.background =
                resources.getDrawable(R.drawable.qibus_softui_bg_leftswitch_select)
            mTvUpper!!.setTextColor(resources.getColor(R.color.QIBus_softui_white))
            mTvLower!!.setTextColor(resources.getColor(R.color.QIBus_softui_textHeader))
            mTvLower!!.background = resources.getDrawable(R.drawable.qibus_softui_bg_rightswitch)
        } else if (v === mTvLower) {
            mTvLower!!.background =
                resources.getDrawable(R.drawable.qibus_softui_bg_rightswitch_select)
            mTvLower!!.setTextColor(resources.getColor(R.color.QIBus_softui_white))
            mTvUpper!!.setTextColor(resources.getColor(R.color.QIBus_softui_textHeader))
            mTvUpper!!.background = resources.getDrawable(R.drawable.qibus_softui_bg_leftswitch)
        } else if (v === mIvBack) onBackPressed()
    }

    companion object {

        /*variable declaration*/

        var mCountSeat: Int = 0
        var mTotal: Int = 0
        lateinit var mSb: StringBuffer
    }
}
