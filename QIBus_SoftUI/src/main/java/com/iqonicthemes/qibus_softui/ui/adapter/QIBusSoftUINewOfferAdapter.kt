package com.iqonicthemes.qibus_softui.ui.adapter

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.iqonicthemes.qibus_softui.R
import com.iqonicthemes.qibus_softui.model.QIBusSoftUINewOfferModel
import java.util.*

class QIBusSoftUINewOfferAdapter/*constructor*/
    (/*variable declaration*/
    private val mContext: Context, private val mOfferListSoftUI: ArrayList<QIBusSoftUINewOfferModel>
)/* initialize parameter*/ : RecyclerView.Adapter<QIBusSoftUINewOfferAdapter.NewofferViewHolder>() {
    private var mListener: onClickListener? = null

    /*set onClick listener*/
    fun setOnClickListener(mListener: onClickListener) {
        this.mListener = mListener
    }

    /*  inflate layout */
    @SuppressLint("InflateParams")
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewofferViewHolder {
        return NewofferViewHolder(
            LayoutInflater.from(mContext).inflate(
                R.layout.qibus_softui_item_newoffers,
                null
            )
        )
    }

    /*bind viewholder*/
    override fun onBindViewHolder(holder: NewofferViewHolder, position: Int) {


    }

    /*item count*/
    override fun getItemCount(): Int {
        return mOfferListSoftUI.size
    }

    /*onclick listener interface*/
    interface onClickListener {

        fun onClick(i: Int)
    }

    /*view holder*/
    inner class NewofferViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    }

}

