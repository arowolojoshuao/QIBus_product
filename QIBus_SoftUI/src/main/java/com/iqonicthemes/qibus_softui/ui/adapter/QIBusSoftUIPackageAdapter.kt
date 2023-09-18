package com.iqonicthemes.qibus_softui.ui.adapter

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.RatingBar
import android.widget.TextView

import com.iqonicthemes.qibus_softui.QIBusSoftUIBaseActivity
import com.iqonicthemes.qibus_softui.R
import com.iqonicthemes.qibus_softui.model.QIBusSoftUINewPackageModel
import androidx.recyclerview.widget.RecyclerView

class QIBusSoftUIPackageAdapter/*constructor*/
    (/*variable declaration*/
    private val mCtx: Context, private val mPackagelist: List<QIBusSoftUINewPackageModel>
)/* initialize parameter*/ :
    RecyclerView.Adapter<QIBusSoftUIPackageAdapter.NewPackageViewHolder>() {
    private var mListener: onClickListener? = null

    /*set onClick listener*/
    fun setOnClickListener(mListener: onClickListener) {
        this.mListener = mListener
    }

    /*  inflate layout */
    @SuppressLint("InflateParams")
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): QIBusSoftUIPackageAdapter.NewPackageViewHolder {
        return NewPackageViewHolder(
            LayoutInflater.from(mCtx).inflate(
                R.layout.qibus_softui_item_package,
                null
            )
        )
    }

    /*bind viewholder*/
    override fun onBindViewHolder(
        holder1: QIBusSoftUIPackageAdapter.NewPackageViewHolder,
        position: Int
    ) {

        val mPackageModel = mPackagelist[position]
        holder1.mTvDestination.text = mPackageModel.destination
        holder1.mTvDuration.text = mPackageModel.duration
        holder1.mTvNewPrice.text = mPackageModel.newprice
        (mCtx as QIBusSoftUIBaseActivity).setImage(mPackageModel.image, holder1.mIvPlace)

        holder1.mRating.rating = java.lang.Float.parseFloat(mPackageModel.rating)
        holder1.itemView.setOnClickListener {
            if (mListener != null) {
                notifyDataSetChanged()
                mListener!!.onClick(mPackageModel)

            }
        }


    }

    /*item count*/
    override fun getItemCount(): Int {
        return mPackagelist.size
    }

    /*onclick listener interface*/
    interface onClickListener {

        fun onClick(notificationModelSoftUI: QIBusSoftUINewPackageModel)
    }

    /*view holder*/
    inner class NewPackageViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val mTvDestination: TextView
        val mTvDuration: TextView
        val mTvNewPrice: TextView
        val mIvPlace: ImageView
        val mRating: RatingBar

        init {
            mTvDestination = itemView.findViewById(R.id.tvDestination)
            mTvDuration = itemView.findViewById(R.id.tvDuration)
            mTvNewPrice = itemView.findViewById(R.id.tvLatestPrice)
            mIvPlace = itemView.findViewById(R.id.ivPlace)
            mRating = itemView.findViewById(R.id.ratingbar)

        }

    }

}
