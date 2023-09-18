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

class QIBusSoftUIViewOfferAdapter
/*constructor*/
    (/*variable declaration*/
    private val mContext: Context, private val mOfferListSoftUI: ArrayList<QIBusSoftUINewOfferModel>
)/* initialize parameter*/ :
    RecyclerView.Adapter<QIBusSoftUIViewOfferAdapter.NewOfferViewHolder>() {

    /*  inflate layout */
    @SuppressLint("InflateParams")
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewOfferViewHolder {
        return NewOfferViewHolder(
            LayoutInflater.from(mContext).inflate(
                R.layout.qibus_softui_item_newoffers1,
                null
            )
        )
    }

    /*bind viewholder*/
    override fun onBindViewHolder(holder: NewOfferViewHolder, position: Int) {

       /* val newOfferModel = mOfferListSoftUI[position]

        holder.mTvSpecial.text = newOfferModel.usecode

        val androidColors = mContext.resources.getIntArray(R.array.QIBus_softui_color)
        val randomAndroidColor = androidColors[Random().nextInt(androidColors.size)]
      //  holder.mRlOfferBackground.setBackgroundColor(randomAndroidColor)

        if (position % 2 == 0) {
            holder.mIvImageSrc.setImageResource(R.drawable.qibus_softui_ic_sale_1)

        } else {
            holder.mIvImageSrc.setImageResource(R.drawable.qibus_softui_ic_sale_2)
        }
        holder.mLlOffer.setOnClickListener {
            (mContext as QIBusSoftUIBaseActivity).showToast(
                mContext.getString(R.string.QIBus_softui_txt_copy)
            )
        }*/
    }

    /*item count*/
    override fun getItemCount(): Int {
        return mOfferListSoftUI.size
    }

    /*view holder*/
    inner class NewOfferViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

      /*  val mTvSpecial: TextView
        val mIvImageSrc: ImageView
        val mRlOfferBackground: MaterialCardView
        val mLlOffer: LinearLayout

        init {

            mTvSpecial = itemView.findViewById(R.id.tvSpecial)
            mIvImageSrc = itemView.findViewById(R.id.ivImageSrc)
            mRlOfferBackground = itemView.findViewById(R.id.rlOfferBackground)
            mLlOffer = itemView.findViewById(R.id.llOffer)
        }*/
    }

}

