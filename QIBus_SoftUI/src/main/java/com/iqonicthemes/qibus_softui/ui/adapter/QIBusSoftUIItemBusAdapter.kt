package com.iqonicthemes.qibus_softui.ui.adapter

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RelativeLayout
import android.widget.TextView

import com.iqonicthemes.qibus_softui.R
import com.iqonicthemes.qibus_softui.model.QIBusSoftUIModel
import com.iqonicthemes.qibus_softui.ui.activity.QIBusSoftUISoftUIBookingActivity
import com.iqonicthemes.qibus_softui.utils.QIBusSoftUIConstants
import androidx.recyclerview.widget.RecyclerView

class QIBusSoftUIItemBusAdapter
/*constructor*/
    (/*variable declaration*/
    private val mContext: Context, private val mBusSoftUIList: List<QIBusSoftUIModel>
)/* initialize parameter*/ : RecyclerView.Adapter<QIBusSoftUIItemBusAdapter.BusitemViewHolder>() {

    /*  inflate layout */
    @SuppressLint("InflateParams")
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): QIBusSoftUIItemBusAdapter.BusitemViewHolder {
        return BusitemViewHolder(
            LayoutInflater.from(mContext).inflate(
                R.layout.qibus_softui_item_available_bus,
                null
            )
        )
    }

    /*bind viewholder*/
    @SuppressLint("DefaultLocale")
    override fun onBindViewHolder(holder1: BusitemViewHolder, position: Int) {

        val mBusModel = mBusSoftUIList[position]
        holder1.mTvTravellerName.text = mBusModel.travelerName
        holder1.mTvStartTime.text = mBusModel.starttime
        holder1.mTvTypeCoach.text = mBusModel.type_coach
        holder1.mTvEndTime.text = mBusModel.enddtime
        holder1.mTvTotalDuration.text = mBusModel.totalDuration
        holder1.mTvStartTimeAA.text = mBusModel.getmStartTimeAA()
        holder1.mTvEndTimeAA.text = mBusModel.getmEndTimeAA()
        holder1.mTvHold.setText(
            String.format(
                mContext.getString(R.string.QIBus_softui_text_addhold),
                mBusModel.hold
            )
        )
        holder1.mTvPrice.setText(String.format("$ %s", mBusModel.price))
        holder1.mTvRatingBar.setText(
            String.format(
                mContext.getString(R.string.QIBus_softui_text_add5),
                mBusModel.rate
            )
        )

        holder1.mRelativeLayout.setOnClickListener {
            val i = Intent(mContext, QIBusSoftUISoftUIBookingActivity::class.java)
            i.flags = Intent.FLAG_ACTIVITY_NEW_TASK
            i.putExtra(QIBusSoftUIConstants.intentdata.TRAVELLERNAME, mBusModel.travelerName)
            i.putExtra(QIBusSoftUIConstants.intentdata.TYPECOACH, mBusModel.type_coach)
            i.putExtra(QIBusSoftUIConstants.intentdata.PRICE, mBusModel.price.replace("$", ""))
            i.putExtra(QIBusSoftUIConstants.intentdata.HOLD, mBusModel.hold)
            mContext.startActivity(i)
        }
    }

    /*item count*/
    override fun getItemCount(): Int {
        return mBusSoftUIList.size
    }

    /*view holder*/
    inner class BusitemViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        val mTvTravellerName: TextView
        val mTvStartTime: TextView
        val mTvEndTime: TextView
        val mTvTotalDuration: TextView
        val mTvHold: TextView
        val mTvPrice: TextView
        val mTvTypeCoach: TextView
        val mTvRatingBar: TextView
        val mTvStartTimeAA: TextView
        val mTvEndTimeAA: TextView
        val mRelativeLayout: RelativeLayout

        init {
            mTvTravellerName = itemView.findViewById(R.id.tvTravellerName)
            mTvStartTime = itemView.findViewById(R.id.tvStartTime)
            mTvEndTime = itemView.findViewById(R.id.tvEndTime)
            mTvTotalDuration = itemView.findViewById(R.id.tvTotalDuration)
            mTvHold = itemView.findViewById(R.id.tvHold)
            mTvRatingBar = itemView.findViewById(R.id.tvRatingbar)
            mTvPrice = itemView.findViewById(R.id.tvPrice)
            mTvTypeCoach = itemView.findViewById(R.id.tvTypeCoach)
            mRelativeLayout = itemView.findViewById(R.id.rlMain)
            mTvStartTimeAA = itemView.findViewById(R.id.tvStartTimeAA)
            mTvEndTimeAA = itemView.findViewById(R.id.tvEndTimeAA)
        }

    }


}