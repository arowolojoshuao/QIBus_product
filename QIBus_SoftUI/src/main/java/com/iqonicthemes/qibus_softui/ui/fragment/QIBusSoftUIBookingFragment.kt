package com.iqonicthemes.qibus_softui.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView

import com.iqonicthemes.qibus_softui.R
import com.iqonicthemes.qibus_softui.model.QIBusSoftUIBookingModel
import com.iqonicthemes.qibus_softui.ui.activity.QIBusSoftUISoftUIDashboardActivity
import com.iqonicthemes.qibus_softui.ui.adapter.QIBusSoftUIBookingAdapter
import com.iqonicthemes.qibus_softui.ui.adapter.QIBusSoftUICancelAdapter

import java.util.ArrayList
import java.util.Objects

import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class QIBusSoftUIBookingFragment : Fragment(), View.OnClickListener {
    private var mTvCompleted: TextView? = null
    private var mTvCancel: TextView? = null
    private var mRvCompleted: RecyclerView? = null
    private var mRvCancelled: RecyclerView? = null
    private var mBookList: MutableList<QIBusSoftUIBookingModel>? = null
    private var mCancelList: MutableList<QIBusSoftUIBookingModel>? = null
    /* create view */
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.qibus_softui_fragment_mybooking, null)

        initView(view)
        initializeListeners()
        return view
    }

    /* init view */
    private fun initView(view: View) {
        mRvCompleted = view.findViewById(R.id.rvBooking)
        mRvCancelled = view.findViewById(R.id.rvCancelled)
        mTvCompleted = view.findViewById(R.id.tvCompleted)
        mTvCancel = view.findViewById(R.id.tvCancelled)
    }

    /* initialize listener */
    private fun initializeListeners() {

        mTvCompleted!!.setOnClickListener(this)
        mTvCancel!!.setOnClickListener(this)

        mBookList = ArrayList()
        mCancelList = ArrayList()
        mRvCompleted!!.layoutManager = LinearLayoutManager(activity, RecyclerView.VERTICAL, false)
        mRvCancelled!!.layoutManager = LinearLayoutManager(activity, RecyclerView.VERTICAL, false)

        mBookList!!.add(
            QIBusSoftUIBookingModel(
                getString(R.string.QIBus_softui_lbl_DelhiToMubai),
                getString(R.string.QIBus_softui_lbl_booking_date1),
                getString(R.string.QIBus_softui_lbl_booking_starttime1),
                getString(R.string.QIBus_softui_lbl_booking_totaltime1),
                getString(R.string.QIBus_softui_lbl_booking_endtime1),
                getString(R.string.QIBus_softui_lbl_booking_SeatNo1),
                getString(R.string.QIBus_softui_lbl_booking_passengername1),
                getString(R.string.QIBus_softui_lbl_booking_ticketno1),
                getString(R.string.QIBus_softui_lbl_booking_pnr1),
                getString(R.string.QIBus_softui_lbl_booking_totalfare1),
                getString(R.string.QIBus_softui_text_confirmed)
            )
        )
        mBookList!!.add(
            QIBusSoftUIBookingModel(
                getString(R.string.QIBus_softui_lbl_MumbaiToPune),
                getString(R.string.QIBus_softui_lbl_booking_date2),
                getString(R.string.QIBus_softui_lbl_booking_starttime2),
                getString(R.string.QIBus_softui_lbl_booking_totaltime1),
                getString(R.string.QIBus_softui_lbl_booking_endtime2),
                getString(R.string.QIBus_softui_lbl_booking_SeatNo1),
                getString(R.string.QIBus_softui_lbl_booking_passengername1),
                getString(R.string.QIBus_softui_lbl_booking_ticketno2),
                getString(R.string.QIBus_softui_lbl_booking_pnr21),
                getString(R.string.QIBus_softui_lbl_booking_totalfare2),
                getString(R.string.QIBus_softui_text_confirmed)
            )
        )

        mRvCompleted!!.adapter = QIBusSoftUIBookingAdapter(activity!!, mBookList!!)

        mCancelList!!.add(
            QIBusSoftUIBookingModel(
                getString(R.string.QIBus_softui_lbl_MumbaiToPune),
                getString(R.string.QIBus_softui_lbl_booking_date1),
                getString(R.string.QIBus_softui_lbl_booking_starttime2),
                getString(R.string.QIBus_softui_lbl_booking_totaltime1),
                getString(R.string.QIBus_softui_lbl_booking_endtime2),
                getString(R.string.QIBus_softui_lbl_booking_SeatNo1),
                getString(R.string.QIBus_softui_lbl_booking_passengername1),
                getString(R.string.QIBus_softui_lbl_booking_ticketno2),
                getString(R.string.QIBus_softui_lbl_booking_pnr21),
                getString(R.string.QIBus_softui_lbl_booking_totalfare2),
                getString(R.string.QIBus_softui_text_confirmed)
            )
        )
        mRvCancelled!!.adapter = QIBusSoftUICancelAdapter(activity!!, mCancelList!!)

        (Objects.requireNonNull(activity) as QIBusSoftUISoftUIDashboardActivity).RunLayoutAnimation(
            mRvCompleted!!
        )
        (activity as QIBusSoftUISoftUIDashboardActivity).RunLayoutAnimation(mRvCancelled!!)

    }

    /* onClick listener */
    override fun onClick(v: View) {
        if (v === mTvCompleted) {
            mTvCompleted!!.background =
                resources.getDrawable(R.drawable.qibus_softui_bg_leftswitch_select)
            mTvCompleted!!.setTextColor(resources.getColor(R.color.QIBus_softui_white))

            mTvCancel!!.setTextColor(resources.getColor(R.color.QIBus_softui_textHeader))
            mTvCancel!!.background = resources.getDrawable(R.drawable.qibus_softui_bg_rightswitch)

            (Objects.requireNonNull(activity) as QIBusSoftUISoftUIDashboardActivity).showView(
                mRvCompleted!!
            )
            (activity as QIBusSoftUISoftUIDashboardActivity).RunLayoutAnimation(mRvCompleted!!)
            (activity as QIBusSoftUISoftUIDashboardActivity).hideView(mRvCancelled!!)


        } else if (v === mTvCancel) {
            mTvCancel!!.background =
                resources.getDrawable(R.drawable.qibus_softui_bg_rightswitch_select)
            mTvCancel!!.setTextColor(resources.getColor(R.color.QIBus_softui_white))
            mTvCompleted!!.setTextColor(resources.getColor(R.color.QIBus_softui_textHeader))
            mTvCompleted!!.background = resources.getDrawable(R.drawable.qibus_softui_bg_leftswitch)

            (Objects.requireNonNull(activity) as QIBusSoftUISoftUIDashboardActivity).hideView(
                mRvCompleted!!
            )
            (activity as QIBusSoftUISoftUIDashboardActivity).showView(mRvCancelled!!)
            (activity as QIBusSoftUISoftUIDashboardActivity).RunLayoutAnimation(mRvCancelled!!)

        }
    }

    companion object {
        /*variable declaration*/
        val mTitle = "My Booking"
    }
}