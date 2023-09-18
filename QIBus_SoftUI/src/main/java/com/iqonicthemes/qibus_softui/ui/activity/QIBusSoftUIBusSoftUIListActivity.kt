package com.iqonicthemes.qibus_softui.ui.activity

import android.app.Dialog
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.SeekBar
import android.widget.TextView

import com.iqonicthemes.qibus_softui.QIBusSoftUIBaseActivity
import com.iqonicthemes.qibus_softui.R
import com.iqonicthemes.qibus_softui.model.QIBusSoftUIModel
import com.iqonicthemes.qibus_softui.ui.adapter.QIBusSoftUIItemBusAdapter
import com.iqonicthemes.qibus_softui.utils.QIBusSoftUIConstants

import java.util.ArrayList
import java.util.Calendar
import java.util.Objects
import androidx.appcompat.widget.AppCompatSeekBar
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class QIBusSoftUIBusSoftUIListActivity : QIBusSoftUIBaseActivity(), View.OnClickListener {

    /*variable declaration*/
    private var mRvBuses: RecyclerView? = null
    private var mBusSoftUIList: MutableList<QIBusSoftUIModel>? = null
    private var mIvBack: ImageView? = null
    private var mIvFilter: ImageView? = null
    private var mIvPrevious: ImageView? = null
    private var mIvNext: ImageView? = null
    private var mTvDate: TextView? = null
    private var mTvTitle: TextView? = null
    private var mDepartDateCalendar: Calendar? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.qibus_softui_activity_bus_list)

        initLayouts()
        initializeListeners()

    }

    /* initialize listener */
    private fun initializeListeners() {
        mIvBack!!.setOnClickListener(this)
        mIvFilter!!.setOnClickListener(this)
        mIvPrevious!!.setOnClickListener(this)
        mIvNext!!.setOnClickListener(this)

        val mTitle = intent.getStringExtra(QIBusSoftUIConstants.intentdata.TRIP_KEY)
        val mSearchTitle = intent.getStringExtra(QIBusSoftUIConstants.intentdata.SEARCH_BUS)
        val mPackageTitle = intent.getStringExtra(QIBusSoftUIConstants.intentdata.PACKAGE_NAME)

        if (mTitle != null) {
            mTvTitle!!.text = mTitle
        }
        if (mSearchTitle != null) {
            mTvTitle!!.text = mSearchTitle
        }
        if (mPackageTitle != null) {
            mTvTitle!!.text = mPackageTitle
        }

        mBusSoftUIList = ArrayList()
        mRvBuses!!.layoutManager = LinearLayoutManager(this, RecyclerView.VERTICAL, false)

        mBusSoftUIList!!.add(
            QIBusSoftUIModel(
                getString(R.string.QIBus_softui_lbl_travelname),
                getString(R.string.QIBus_softui_lbl_starttime1),
                getString(R.string.QIBus_softui_text_am),
                getString(R.string.QIBus_softui_lbl_endtime1),
                getString(R.string.QIBus_softui_text_pm),
                getString(R.string.QIBus_softui_lbl_totalDuration),
                getString(R.string.QIBus_softui_lbl_hold),
                getString(R.string.QIBus_softui_lbl_type1),
                3,
                getString(R.string.QIBus_softui_lbl_price1)
            )
        )
        mBusSoftUIList!!.add(
            QIBusSoftUIModel(
                getString(R.string.QIBus_softui_lbl_travelname),
                getString(R.string.QIBus_softui_lbl_starttime1),
                getString(R.string.QIBus_softui_text_am),
                getString(R.string.QIBus_softui_lbl_endtime1),
                getString(R.string.QIBus_softui_text_pm),
                getString(R.string.QIBus_softui_lbl_totalDuration),
                getString(R.string.QIBus_softui_lbl_hold),
                getString(R.string.QIBus_softui_lbl_type1),
                3,
                getString(R.string.QIBus_softui_lbl_price2)
            )
        )
        mBusSoftUIList!!.add(
            QIBusSoftUIModel(
                getString(R.string.QIBus_softui_lbl_travelname),
                getString(R.string.QIBus_softui_lbl_starttime1),
                getString(R.string.QIBus_softui_text_am),
                getString(R.string.QIBus_softui_lbl_endtime1),
                getString(R.string.QIBus_softui_text_pm),
                getString(R.string.QIBus_softui_lbl_totalDuration),
                getString(R.string.QIBus_softui_lbl_hold),
                getString(R.string.QIBus_softui_lbl_type2),
                3,
                getString(R.string.QIBus_softui_lbl_price1)
            )
        )


        mRvBuses!!.adapter = QIBusSoftUIItemBusAdapter(this, mBusSoftUIList!!)
        RunLayoutAnimation(mRvBuses!!)

        mDepartDateCalendar = Calendar.getInstance()
        mTvDate!!.text =
            QIBusSoftUIConstants.DateFormat.DAY_MONTH_YEAR_FORMATTER.format(mDepartDateCalendar!!.time)

    }

    /* init layout */
    private fun initLayouts() {
        mRvBuses = findViewById(R.id.rvBus)
        mIvBack = findViewById(R.id.ivBack)
        mIvFilter = findViewById(R.id.ivFilter)
        mIvPrevious = findViewById(R.id.ivPrevious)
        mIvNext = findViewById(R.id.ivNext)
        mTvDate = findViewById(R.id.tvDate)
        mTvTitle = findViewById(R.id.tvTitle)
    }

    /* onClick listener */
    override fun onClick(v: View) {
        if (v === mIvBack) {
            onBackPressed()
        } else if (v === mIvPrevious) {
            mDepartDateCalendar!!.add(Calendar.DATE, -1)
            mTvDate!!.text =
                QIBusSoftUIConstants.DateFormat.DAY_MONTH_YEAR_FORMATTER.format(mDepartDateCalendar!!.time)
        } else if (v === mIvNext) {
            mDepartDateCalendar!!.add(Calendar.DATE, 1)
            mTvDate!!.text =
                QIBusSoftUIConstants.DateFormat.DAY_MONTH_YEAR_FORMATTER.format(mDepartDateCalendar!!.time)

        } else if (v === mIvFilter) {
            val dialog = Dialog(this)
            dialog.setContentView(R.layout.qibus_softui_dialog_filter)
            dialog.setCancelable(true)
            Objects.requireNonNull(dialog.window)?.setBackgroundDrawable(ColorDrawable(0))


            val tvMax = dialog.findViewById<TextView>(R.id.endprice)
            val mBtnApply = dialog.findViewById<TextView>(R.id.btnApply)
            val mIvClose = dialog.findViewById<ImageView>(R.id.ivClose)

            mBtnApply.stateListAnimator = null
            mBtnApply.setOnClickListener { dialog.dismiss() }
            val rangeSeekbar1 = dialog.findViewById<AppCompatSeekBar>(R.id.rangeSeekbar1)
            rangeSeekbar1.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
                internal var progressChangedValue = 100
                override fun onProgressChanged(seekBar: SeekBar, progress: Int, fromUser: Boolean) {
                    progressChangedValue = progress
                    tvMax.text = progressChangedValue.toString()
                }

                override fun onStartTrackingTouch(seekBar: SeekBar) {

                }

                override fun onStopTrackingTouch(seekBar: SeekBar) {
                    tvMax.text = progressChangedValue.toString()
                }
            })

            mIvClose.setOnClickListener { dialog.dismiss() }
            dialog.show()

        }

    }
}
