package com.iqonicthemes.qibus_softui.ui.adapter

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.RelativeLayout
import android.widget.TextView

import com.iqonicthemes.qibus_softui.QIBusSoftUIBaseActivity
import com.iqonicthemes.qibus_softui.R
import com.iqonicthemes.qibus_softui.model.QIBusSoftUIBookingModel
import androidx.recyclerview.widget.RecyclerView

class QIBusSoftUICancelAdapter/*constructor*/
    (/*variable declaration*/
    private val mCtx: Context, private val mCancelList: List<QIBusSoftUIBookingModel>
)/* initialize parameter*/ : RecyclerView.Adapter<QIBusSoftUICancelAdapter.CancelViewHolder>() {

    /*  inflate layout */
    @SuppressLint("InflateParams")
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CancelViewHolder {
        return CancelViewHolder(
            LayoutInflater.from(mCtx).inflate(
                R.layout.qibus_softui_item_cancel,
                null
            )
        )
    }

    /*bind viewholder*/
    override fun onBindViewHolder(holder1: CancelViewHolder, position: Int) {
        val mBookingModel = mCancelList[position]

        holder1.mTvDestination.text = mBookingModel.destination
        holder1.mTvDuration.text = mBookingModel.duration
        holder1.mTvStartTime.text = mBookingModel.startTime
        holder1.mTvTotalTime.setText(
            String.format(
                "%s %s",
                mBookingModel.totalTime,
                mCtx.getString(R.string.QIBus_softui_text_hour)
            )
        )
        holder1.mTvEndTime.text = mBookingModel.endTime
        holder1.mTvTicketNo.text = mBookingModel.ticketNo
        holder1.mTvPNRNo.text = mBookingModel.pnrNo
        holder1.mTvSeatNo.text = mBookingModel.seatNo
        holder1.mTvTotalFare.setText(
            String.format(
                "%s%s",
                mCtx.getString(R.string.QIBus_softui_rs),
                mBookingModel.totalFare
            )
        )

        holder1.mRlContent.setOnClickListener {
            (mCtx as QIBusSoftUIBaseActivity).showView(holder1.mRlShowMore)
            mCtx.hideView(holder1.mIVShowMore)
            mCtx.hideView(holder1.mTvCancel)
        }
        holder1.mRlShowMore.setOnClickListener {
            (mCtx as QIBusSoftUIBaseActivity).showView(holder1.mIVShowMore)
            mCtx.showView(holder1.mTvCancel)
            mCtx.hideView(holder1.mRlShowMore)
            mCtx.fadeOutIn(holder1.mIVShowMore)
            mCtx.fadeOutIn(holder1.mTvCancel)
        }
    }

    /*item count*/
    override fun getItemCount(): Int {
        return mCancelList.size
    }

    /*view holder*/
    inner class CancelViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val mTvDestination: TextView
        val mTvDuration: TextView
        val mTvStartTime: TextView
        val mTvTotalTime: TextView
        val mTvEndTime: TextView
        val mTvTicketNo: TextView
        val mTvPNRNo: TextView
        val mTvTotalFare: TextView
        val mTvSeatNo: TextView
        val mTvCancel: TextView
        val mRlShowMore: RelativeLayout
        val mRlContent: RelativeLayout
        val mIVShowMore: ImageView

        init {
            mTvDestination = itemView.findViewById(R.id.tvDestination)
            mTvDuration = itemView.findViewById(R.id.tvDuration)
            mTvStartTime = itemView.findViewById(R.id.tvStartTime)
            mTvTotalTime = itemView.findViewById(R.id.tvTotalTime)
            mTvEndTime = itemView.findViewById(R.id.tvEndTime)
            mTvTicketNo = itemView.findViewById(R.id.tvTicketNo)
            mTvPNRNo = itemView.findViewById(R.id.tvPNRNo)
            mTvTotalFare = itemView.findViewById(R.id.tvTotalFare)
            mIVShowMore = itemView.findViewById(R.id.ivShowMore)
            mRlShowMore = itemView.findViewById(R.id.rlShowMore)
            mRlContent = itemView.findViewById(R.id.rlContent)
            mTvSeatNo = itemView.findViewById(R.id.tvSeatNo)
            mTvCancel = itemView.findViewById(R.id.tvCancelled)

        }

    }

}
