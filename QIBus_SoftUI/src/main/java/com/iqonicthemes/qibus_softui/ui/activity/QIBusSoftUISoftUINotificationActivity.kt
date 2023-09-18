package com.iqonicthemes.qibus_softui.ui.activity

import android.annotation.SuppressLint
import android.app.Dialog
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import android.widget.RelativeLayout
import android.widget.TextView

import com.google.android.material.snackbar.Snackbar
import com.iqonicthemes.qibus_softui.QIBusSoftUIBaseActivity
import com.iqonicthemes.qibus_softui.R
import com.iqonicthemes.qibus_softui.model.QIBusSoftUIBookingModel
import com.iqonicthemes.qibus_softui.ui.adapter.QIBusSoftUINotificationAdapter
import com.iqonicthemes.qibus_softui.utils.QIBusSoftUISwipeToDeleteCallback

import java.util.ArrayList
import java.util.Objects
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class QIBusSoftUISoftUINotificationActivity : QIBusSoftUIBaseActivity(), View.OnClickListener,
    QIBusSoftUINotificationAdapter.onClickListener {

    /*variable declaration*/

    private var mTvDestination: TextView? = null
    private var mTvDuration: TextView? = null
    private var mTvStartTime: TextView? = null
    private var mTvTotalTime: TextView? = null
    private var mTvEndTime: TextView? = null
    private var mTvTicketNo: TextView? = null
    private var mTvPNRNo: TextView? = null
    private var mTvTotalFare: TextView? = null
    private var mTvSeatNo: TextView? = null
    private var mRvNotification: RecyclerView? = null
    private var mNotificationList: MutableList<QIBusSoftUIBookingModel>? = null
    private var mIvBack: ImageView? = null
    private var mIvClose: ImageView? = null
    private var mSoftUINotificationAdapter: QIBusSoftUINotificationAdapter? = null
    private var mRlMain: RelativeLayout? = null
    private var mDialog: Dialog? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.qibus_softui_activity_notification)

        initLayouts()
        initializeLayouts()
        enableSwipeToDeleteAndUndo()
    }

    /* init layout */
    private fun initLayouts() {
        mRvNotification = findViewById(R.id.rvNotification)
        mIvBack = findViewById(R.id.ivBack)
        mRlMain = findViewById(R.id.rlMain)
    }

    /* initialize listener */
    private fun initializeLayouts() {
        mIvBack!!.setOnClickListener(this)
        mNotificationList = ArrayList()
        mRvNotification!!.layoutManager = LinearLayoutManager(this, RecyclerView.VERTICAL, false)

        mNotificationList!!.add(
            QIBusSoftUIBookingModel(
                getString(R.string.QIBus_softui_lbl_DelhiToMubai),
                getString(R.string.QIBus_softui_lbl_booking_duration1),
                getString(R.string.QIBus_softui_lbl_may),
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
        mNotificationList!!.add(
            QIBusSoftUIBookingModel(
                getString(R.string.QIBus_softui_lbl_MumbaiToPune),
                getString(R.string.QIBus_softui_lbl_booking_duration2),
                getString(R.string.QIBus_softui_lbl_may),
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

        mSoftUINotificationAdapter = QIBusSoftUINotificationAdapter(this, mNotificationList!!)
        mRvNotification!!.adapter = mSoftUINotificationAdapter
        mSoftUINotificationAdapter!!.setOnClickListener(this)
        RunLayoutAnimation(mRvNotification!!)
    }

    /* swipe to delete & undo */
    private fun enableSwipeToDeleteAndUndo() {
        val swipeToDeleteCallback = object : QIBusSoftUISwipeToDeleteCallback(this) {
            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, i: Int) {


                val position = viewHolder.adapterPosition
                val notificationModelSoftUI: QIBusSoftUIBookingModel
                notificationModelSoftUI = mSoftUINotificationAdapter!!.data[position]

                mSoftUINotificationAdapter!!.removeItem(position)

                val snackbar = Snackbar.make(
                    mRlMain!!,
                    getString(R.string.QIBus_softui_text_remove),
                    Snackbar.LENGTH_LONG
                )
                snackbar.setAction(getString(R.string.QIBus_softui_text_undo)) {
                    mSoftUINotificationAdapter!!.restoreItem(notificationModelSoftUI, position)
                    mRvNotification!!.scrollToPosition(position)
                }

                snackbar.setActionTextColor(Color.YELLOW)
                snackbar.show()

            }
        }

        val itemTouchhelper = ItemTouchHelper(swipeToDeleteCallback)
        itemTouchhelper.attachToRecyclerView(mRvNotification)
    }

    /* onClick listener */
    override fun onClick(v: View) {
        if (v === mIvBack)
            onBackPressed()
    }

    /* onClick listener & open mDialog*/
    @SuppressLint("ClickableViewAccessibility")
    override fun onClick(notificationModelSoftUI: QIBusSoftUIBookingModel) {

        val dialogView = View.inflate(this, R.layout.qibus_softui_layout_notification, null)

        mDialog = Dialog(this)
        mDialog!!.setContentView(dialogView)
        mDialog!!.setCancelable(true)
        Objects.requireNonNull(mDialog!!.window)!!.setBackgroundDrawable(ColorDrawable(0))
        mDialog!!.window!!.attributes.windowAnimations = R.style.DialogAnimation
        mTvDestination = mDialog!!.findViewById(R.id.tvDestination)
        mTvDuration = mDialog!!.findViewById(R.id.tvDuration)
        mTvStartTime = mDialog!!.findViewById(R.id.tvStartTime)
        mTvTotalTime = mDialog!!.findViewById(R.id.tvTotalTime)
        mTvEndTime = mDialog!!.findViewById(R.id.tvEndTime)
        mTvTicketNo = mDialog!!.findViewById(R.id.tvTicketNo)
        mTvPNRNo = mDialog!!.findViewById(R.id.tvPNRNo)
        mTvTotalFare = mDialog!!.findViewById(R.id.tvTotalFare)
        mIvClose = mDialog!!.findViewById(R.id.ivClose)

        mTvSeatNo = mDialog!!.findViewById(R.id.tvSeatNo)
        mTvDestination!!.text = notificationModelSoftUI.destination
        mTvDuration!!.setText(
            String.format(
                "%s %s",
                notificationModelSoftUI.duration,
                notificationModelSoftUI.month
            )
        )
        mTvStartTime!!.text = notificationModelSoftUI.startTime
        mTvSeatNo!!.text = notificationModelSoftUI.seatNo
        mTvTotalTime!!.setText(
            String.format(
                "%s %s",
                notificationModelSoftUI.totalTime,
                this.getString(R.string.QIBus_softui_text_hour)
            )
        )
        mTvEndTime!!.text = notificationModelSoftUI.endTime
        mTvTicketNo!!.text = notificationModelSoftUI.ticketNo
        mTvPNRNo!!.text = notificationModelSoftUI.pnrNo
        mTvTotalFare!!.setText(
            String.format(
                "%s%s",
                this.getString(R.string.QIBus_softui_rs),
                notificationModelSoftUI.totalFare
            )
        )

        mIvClose!!.setOnTouchListener { v, event ->
            mDialog!!.hide()
            false
        }

        mDialog!!.show()
    }

    /* onBackPressed*/
    override fun onBackPressed() {
        if (mDialog != null && mDialog!!.isShowing) {
            mDialog!!.dismiss()
            mDialog = null
        }
        super.onBackPressed()
    }
}

