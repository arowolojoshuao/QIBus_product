package com.iqonicthemes.qibus_softui.ui.adapter

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RelativeLayout
import android.widget.TextView

import com.iqonicthemes.qibus_softui.R
import com.iqonicthemes.qibus_softui.model.QIBusSoftUIDroppingModel
import androidx.recyclerview.widget.RecyclerView

class QIBusSoftUIDroppingAdapter/*constructor*/
    (/*variable declaration*/
    private val mCtx: Context, private val mList: List<QIBusSoftUIDroppingModel>
)/* initialize parameter*/ : RecyclerView.Adapter<QIBusSoftUIDroppingAdapter.ViewHolder>() {
    private var mListener: onClickListener? = null
    private var mLastSelectedPosition = -1

    /*set onClick listener*/
    fun setOnClickListener(mListener: onClickListener) {
        this.mListener = mListener
    }

    /*  inflate layout */
    @SuppressLint("InflateParams")
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): QIBusSoftUIDroppingAdapter.ViewHolder {
        return ViewHolder(
            LayoutInflater.from(mCtx).inflate(
                R.layout.qibus_softui_item_dropping1,
                null
            )
        )
    }

    /*bind viewholder*/
    override fun onBindViewHolder(holder1: QIBusSoftUIDroppingAdapter.ViewHolder, position: Int) {
        val mModel = mList[position]

        // set the cricketdata in items
        holder1.mTvTravelName.text = mModel.travelName
        holder1.mTvTime.text = mModel.duration
        holder1.mTvLocation.text = mModel.loction

        if (mLastSelectedPosition == -1) {
            holder1.mCard.background =
                mCtx.resources.getDrawable(R.drawable.qibus_softu_ic_card_new)
        } else {
            if (mLastSelectedPosition == position) {

                holder1.mCard.background =
                    mCtx.resources.getDrawable(R.drawable.qibus_softu_ic_card_new)


            } else {
                holder1.mCard.background =
                    mCtx.resources.getDrawable(R.drawable.qibus_softu_ic_card_new)
            }
        }
        holder1.itemView.setOnClickListener {
            if (mListener != null) {

                mLastSelectedPosition = position
                notifyDataSetChanged()
                mListener!!.onClick(position, mModel.travelName)

            }
        }

    }

    /*item count*/
    override fun getItemCount(): Int {
        return mList.size
    }

    /*onclick listener interface*/
    interface onClickListener {

        fun onClick(i: Int, name: String)
    }

    /*view holder*/
    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val mTvTravelName: TextView
        val mTvTime: TextView
        val mTvLocation: TextView
        val mCard: RelativeLayout


        init {
            mTvTravelName = itemView.findViewById(R.id.tvTravelName)
            mTvTime = itemView.findViewById(R.id.tvTime)
            mTvLocation = itemView.findViewById(R.id.tvLocation)
            mCard = itemView.findViewById(R.id.relative)
        }

    }

}
