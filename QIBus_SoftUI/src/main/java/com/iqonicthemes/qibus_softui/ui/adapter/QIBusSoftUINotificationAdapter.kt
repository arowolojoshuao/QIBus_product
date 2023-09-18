package com.iqonicthemes.qibus_softui.ui.adapter

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView

import com.iqonicthemes.qibus_softui.R
import com.iqonicthemes.qibus_softui.model.QIBusSoftUIBookingModel
import androidx.recyclerview.widget.RecyclerView

class QIBusSoftUINotificationAdapter/*constructor*/
(/*variable declaration*/
        private val mContext: Context, private val mNotificationList: MutableList<QIBusSoftUIBookingModel>)/* initialize parameter*/ : RecyclerView.Adapter<QIBusSoftUINotificationAdapter.NotificationViewHolder>() {
    private var mListener: onClickListener? = null

    /*  get cricketdata */
    val data: List<QIBusSoftUIBookingModel>
        get() = mNotificationList

    /*set onClick listener*/
    fun setOnClickListener(mListener: onClickListener) {
        this.mListener = mListener
    }

    /*  inflate layout */
    @SuppressLint("InflateParams")
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NotificationViewHolder {
        return NotificationViewHolder(LayoutInflater.from(mContext).inflate(R.layout.qibus_softui_item_notification, null))
    }

    /*bind viewholder*/
    override fun onBindViewHolder(holder1: NotificationViewHolder, position: Int) {
        val mNotificationModel = mNotificationList[position]

        holder1.mTvDestination.text = mNotificationModel.destination
        holder1.mTvStatus.text = mNotificationModel.status
        holder1.mTvDate.text = mNotificationModel.duration
        holder1.mTvSeatNo.text = mNotificationModel.seatNo
        holder1.mTvPNR.text = mNotificationModel.pnrNo
        holder1.mTvMonth.text = mNotificationModel.month
        holder1.tvTicketNo.text = mNotificationModel.ticketNo
        holder1.mTvTotalFare.text = mNotificationModel.totalFare
        holder1.mTvStartTime.text = mNotificationModel.startTime
        holder1.mTvEndTime.text = mNotificationModel.endTime
        holder1.mTvTotalTime.text = mNotificationModel.totalTime
        holder1.itemView.setOnClickListener {
            if (mListener != null) {
                notifyDataSetChanged()
                mListener!!.onClick(mNotificationModel)

            }
        }
    }

    /*  remove item */
    fun removeItem(position: Int) {
        mNotificationList.removeAt(position)
        notifyItemRemoved(position)
    }

    /*  restore item */
    fun restoreItem(item: QIBusSoftUIBookingModel, position: Int) {
        mNotificationList.add(position, item)
        notifyItemInserted(position)
    }

    /*item count*/
    override fun getItemCount(): Int {
        return mNotificationList.size
    }

    /*onclick listener interface*/
    interface onClickListener {

        fun onClick(notificationModelSoftUI: QIBusSoftUIBookingModel)
    }

    /*view holder*/
    inner class NotificationViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
         val mTvDestination: TextView
         val mTvStatus: TextView
         val mTvDate: TextView
         val mTvMonth: TextView
         val mTvSeatNo: TextView
         val mTvPNR: TextView
         val tvTicketNo: TextView
         val mTvTotalFare: TextView
         val mTvStartTime: TextView
         val mTvEndTime: TextView
         val mTvTotalTime: TextView


        init {
            mTvDestination = itemView.findViewById(R.id.tvDestination)
            mTvStatus = itemView.findViewById(R.id.tvStatus)
            mTvDate = itemView.findViewById(R.id.tvDate)
            mTvSeatNo = itemView.findViewById(R.id.tvSeatNo)
            mTvMonth = itemView.findViewById(R.id.tvMonth)
            mTvPNR = itemView.findViewById(R.id.tvPNR)
            tvTicketNo = itemView.findViewById(R.id.tvTicketNo)
            mTvTotalFare = itemView.findViewById(R.id.totalfare)
            mTvStartTime = itemView.findViewById(R.id.starttime)
            mTvEndTime = itemView.findViewById(R.id.endtime)
            mTvTotalTime = itemView.findViewById(R.id.totaltime)
        }

    }
}
