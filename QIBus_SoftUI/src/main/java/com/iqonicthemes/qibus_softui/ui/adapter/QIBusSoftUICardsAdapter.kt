package com.iqonicthemes.qibus_softui.ui.adapter

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView

import com.iqonicthemes.qibus_softui.QIBusSoftUIBaseActivity
import com.iqonicthemes.qibus_softui.R
import com.iqonicthemes.qibus_softui.model.QIBusSoftUICardModel

import java.util.ArrayList
import androidx.recyclerview.widget.RecyclerView

class QIBusSoftUICardsAdapter/*constructor*/
    (/*variable declaration*/
    private val mCtx: Context, private val mSoftUICardList: ArrayList<QIBusSoftUICardModel>
)/* initialize parameter*/ : RecyclerView.Adapter<QIBusSoftUICardsAdapter.viewcardViewHolder>() {
    private var mListener: onClickListener? = null

    /*  get cricketdata */
    val data: List<QIBusSoftUICardModel>
        get() = mSoftUICardList

    /*set onClick listener*/
    fun setOnClickListener(mListener: onClickListener) {
        this.mListener = mListener
    }

    /*  remove item */
    fun removeItem(position: Int) {
        mSoftUICardList.removeAt(position)
        notifyItemRemoved(position)
    }

    /*  restore item */
    fun restoreItem(item: QIBusSoftUICardModel, position: Int) {
        mSoftUICardList.add(position, item)
        notifyItemInserted(position)
    }

    /*  inflate layout */
    @SuppressLint("InflateParams")
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): QIBusSoftUICardsAdapter.viewcardViewHolder {
        return viewcardViewHolder(
            LayoutInflater.from(mCtx).inflate(
                R.layout.qibus_softui_item_card,
                null
            )
        )
    }

    /*bind viewholder*/
    override fun onBindViewHolder(
        holder1: QIBusSoftUICardsAdapter.viewcardViewHolder,
        position: Int
    ) {

        val mCardModel = mSoftUICardList[position]
        holder1.mTvDigit1.text = mCardModel.txtDigit1
        holder1.mTvDigit2.text = mCardModel.txtDigit2
        holder1.mTvDigit3.text = mCardModel.txtDigit3
        holder1.mTvDigit4.text = mCardModel.txtDigit4
        holder1.mTvHolderName.text = mCardModel.txtHolderName
        holder1.mTvValidDate.text = mCardModel.getmValidDate()
        holder1.mTvType.text = mCardModel.cardtype

        (mCtx as QIBusSoftUIBaseActivity).setImage(mCardModel.cardbg, holder1.mCard)

        holder1.itemView.setOnClickListener {
            if (mListener != null) {
                notifyDataSetChanged()
                mListener!!.onClick(mCardModel)

            }
        }
    }

    /*item count*/
    override fun getItemCount(): Int {
        return mSoftUICardList.size
    }

    /*onclick listener interface*/
    interface onClickListener {

        fun onClick(softUICardModel: QIBusSoftUICardModel)
    }

    /*view holder*/
    inner class viewcardViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val mTvDigit1: TextView
        val mTvDigit2: TextView
        val mTvDigit3: TextView
        val mTvDigit4: TextView
        val mTvHolderName: TextView
        val mTvValidDate: TextView
        val mTvType: TextView
        val mCard: ImageView


        init {
            mTvDigit1 = itemView.findViewById(R.id.edDigit1)
            mTvDigit2 = itemView.findViewById(R.id.edDigit2)
            mTvDigit3 = itemView.findViewById(R.id.edDigit3)
            mTvDigit4 = itemView.findViewById(R.id.edDigit4)
            mTvHolderName = itemView.findViewById(R.id.tvHolderName)
            mTvValidDate = itemView.findViewById(R.id.tvValidDate)
            mCard = itemView.findViewById(R.id.ivCardbg)
            mTvType = itemView.findViewById(R.id.tvType)
        }
    }

}


