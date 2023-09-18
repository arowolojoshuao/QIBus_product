package com.iqonicthemes.qibus_softui.ui.adapter

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.RelativeLayout
import android.widget.TextView

import com.iqonicthemes.qibus_softui.R
import com.iqonicthemes.qibus_softui.model.QIBusSoftUIBookingModel

import java.util.ArrayList
import androidx.recyclerview.widget.RecyclerView

class QIBusSoftUIRecentSearchAdapter/*constructor*/
    (
    private val mContext: Context,
    private val mRecentSearchList: ArrayList<QIBusSoftUIBookingModel>
)/* initialize parameter*/ :
    RecyclerView.Adapter<QIBusSoftUIRecentSearchAdapter.RecentSearchViewHolder>() {

    /*variable declaration*/
    private var mListener: onClickListener? = null

    /*set onClick listener*/
    fun setOnClickListener(mListener: onClickListener) {
        this.mListener = mListener
    }

    /*  inflate layout */
    @SuppressLint("InflateParams")
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecentSearchViewHolder {
        return RecentSearchViewHolder(
            LayoutInflater.from(mContext).inflate(
                R.layout.qibus_softui_item_recentsearch,
                null
            )
        )
    }

    /*bind viewholder*/
    override fun onBindViewHolder(holder: RecentSearchViewHolder, position: Int) {

        val mBookibgModel = mRecentSearchList[position]

        holder.mTvDestination.text = mBookibgModel.destination
        holder.mTvDate.text = mBookibgModel.date
        holder.mBtnBook.setOnClickListener {
            if (mListener != null) {
                notifyDataSetChanged()
                mListener!!.onClick(mBookibgModel)

            }
        }

    }

    /*item count*/
    override fun getItemCount(): Int {
        return mRecentSearchList.size
    }

    /*onclick listener interface*/
    interface onClickListener {

        fun onClick(cardModelSoftUI: QIBusSoftUIBookingModel)
    }

    /*view holder*/
    inner class RecentSearchViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        val mTvDestination: TextView
        val mTvDate: TextView
        val mBtnBook: RelativeLayout

        init {

            mTvDestination = itemView.findViewById(R.id.tvDestination)
            mTvDate = itemView.findViewById(R.id.tvDate)
            mBtnBook = itemView.findViewById(R.id.btnBook)

        }
    }

}

