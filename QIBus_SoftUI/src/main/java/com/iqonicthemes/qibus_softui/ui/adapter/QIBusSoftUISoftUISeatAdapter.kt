package com.iqonicthemes.qibus_softui.ui.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView

import com.iqonicthemes.qibus_softui.QIBusSoftUIBaseActivity
import com.iqonicthemes.qibus_softui.R
import com.iqonicthemes.qibus_softui.model.QIBusSoftUISeatModel
import com.iqonicthemes.qibus_softui.model.QIBusSoftUISeatType
import com.iqonicthemes.qibus_softui.utils.menu.QIBusSoftUIAbstractItem
import com.iqonicthemes.qibus_softui.utils.menu.QIBusSoftUIOnSeatSelected
import androidx.recyclerview.widget.RecyclerView

class QIBusSoftUISoftUISeatAdapter/*constructor*/
(/*variable declaration*/
        private val mSoftUIOnSeatSelected: QIBusSoftUIOnSeatSelected, private val mItemSoftUIS: List<QIBusSoftUIAbstractItem>, private val mCtx: Context, private val mSoftUISeatItem: List<QIBusSoftUISeatModel>) : QIBusSoftUISelectableAdapter<RecyclerView.ViewHolder>() {
    private val mLayoutInflater: LayoutInflater
    private var mListener: onClickListener? = null

    init {
        mLayoutInflater = LayoutInflater.from(mCtx)

    }/* initialize parameter*/

    /*set onClick listener*/
    fun setOnClickListener(mListener: onClickListener) {
        this.mListener = mListener
    }

    /*item count*/
    override fun getItemCount(): Int {
        return mItemSoftUIS.size
    }

    /*item type*/
    override fun getItemViewType(position: Int): Int {
        return mItemSoftUIS[position].type
    }

    /*view holder*/
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        if (viewType == QIBusSoftUIAbstractItem.TYPE_CENTER) {
            val itemView = mLayoutInflater.inflate(R.layout.qibus_softui_list_item_seat, parent, false)
            return CenterViewHolder(itemView)
        } else if (viewType == QIBusSoftUIAbstractItem.TYPE_EDGE) {
            val itemView = mLayoutInflater.inflate(R.layout.qibus_softui_list_item_seat, parent, false)
            return EdgeViewHolder(itemView)
        } else {
            val itemView = View(mCtx)
            return EmptyViewHolder(itemView)
        }
    }

    /*bind viewholder*/
    override fun onBindViewHolder(viewHolder: RecyclerView.ViewHolder, position: Int) {
        val type = mItemSoftUIS[position].type

        val seatModel = mSoftUISeatItem[position]

        if (type == QIBusSoftUIAbstractItem.TYPE_CENTER) {

            val holder = viewHolder as CenterViewHolder

            val i = Integer.parseInt(mItemSoftUIS[position].getmLabel())
            if (i <= 9) {
                holder.mTvSeatNo.setText(String.format("L0%s", mItemSoftUIS[position].getmLabel()))
            } else {
                holder.mTvSeatNo.setText(String.format("L%s", mItemSoftUIS[position].getmLabel()))

            }
            if (seatModel.isSelected) {
                (mCtx as QIBusSoftUIBaseActivity).showView(holder.mTvSeatNo)
            } else {

                (mCtx as QIBusSoftUIBaseActivity).invisibleView(holder.mTvSeatNo)

            }
            if (seatModel.softUISeatType == QIBusSoftUISeatType.EMPTY) {

                holder.mIvSeat.setOnClickListener {
                    toggleSelection(position)
                    seatModel.isSelected = !seatModel.isSelected
                    notifyItemChanged(position)


                    mSoftUIOnSeatSelected.onSeatSelected(selectedItemCount, mItemSoftUIS[position].getmLabel())
                }

                holder.mIvSeatSelected.visibility = if (isSelected(position)) View.VISIBLE else View.INVISIBLE
            }

            if (seatModel.softUISeatType == QIBusSoftUISeatType.BOOKED) {
                mCtx.showView(holder.mIvSeatBooked)

                holder.mIvSeatBooked.setOnClickListener { mCtx.showToast(mCtx.getString(R.string.QIBus_softui_text_booked)) }
            }
            if (seatModel.softUISeatType == QIBusSoftUISeatType.LADIES) {
                mCtx.showView(holder.mTvSeatLadies)

                holder.mTvSeatLadies.setOnClickListener { mCtx.showToast(mCtx.getString(R.string.QIBus_softui_text_booked)) }

            } else {
                holder.mIvSeat.setOnClickListener {
                    toggleSelection(position)
                    seatModel.isSelected = !seatModel.isSelected
                    notifyItemChanged(position)
                    mSoftUIOnSeatSelected.onSeatSelected(selectedItemCount, mItemSoftUIS[position].getmLabel())
                }


                holder.mIvSeatSelected.visibility = if (isSelected(position)) View.VISIBLE else View.INVISIBLE
            }

        } else if (type == QIBusSoftUIAbstractItem.TYPE_EDGE) {


            val holder = viewHolder as EdgeViewHolder

            holder.itemView.setOnClickListener {
                if (mListener != null) {
                    mListener!!.onClick(seatModel)
                }
            }
            val i = Integer.parseInt(mItemSoftUIS[position].getmLabel())
            if (i <= 9) {
                holder.mTvSeatNo.setText(String.format("L0%s", mItemSoftUIS[position].getmLabel()))
            } else {
                holder.mTvSeatNo.setText(String.format("L%s", mItemSoftUIS[position].getmLabel()))

            }
            if (seatModel.isSelected) {
                (mCtx as QIBusSoftUIBaseActivity).showView(holder.mTvSeatNo)

            } else {

                (mCtx as QIBusSoftUIBaseActivity).invisibleView(holder.mTvSeatNo)

            }
            if (seatModel.softUISeatType == QIBusSoftUISeatType.EMPTY) {

                holder.mIvSeat.setOnClickListener {
                    toggleSelection(position)
                    seatModel.isSelected = !seatModel.isSelected
                    notifyItemChanged(position)
                    mSoftUIOnSeatSelected.onSeatSelected(selectedItemCount, mItemSoftUIS[position].getmLabel())
                }

                holder.mIvSeatSelected.visibility = if (isSelected(position)) View.VISIBLE else View.INVISIBLE
            }

            if (seatModel.softUISeatType == QIBusSoftUISeatType.BOOKED) {
                mCtx.showView(holder.mIvSeatBooked)

                holder.mIvSeatBooked.setOnClickListener { mCtx.showToast(mCtx.getString(R.string.QIBus_softui_text_booked)) }
            }
            if (seatModel.softUISeatType == QIBusSoftUISeatType.LADIES) {
                mCtx.showView(holder.mTvSeatLadies)

                holder.mTvSeatLadies.setOnClickListener { mCtx.showToast(mCtx.getString(R.string.QIBus_softui_text_booked)) }


            } else {
                holder.mIvSeat.setOnClickListener {
                    toggleSelection(position)
                    seatModel.isSelected = !seatModel.isSelected
                    notifyItemChanged(position)
                    mSoftUIOnSeatSelected.onSeatSelected(selectedItemCount, mItemSoftUIS[position].getmLabel())
                }

                holder.mIvSeatSelected.visibility = if (isSelected(position)) View.VISIBLE else View.INVISIBLE
            }
        }


    }

    /*onclick listener interface*/
    interface onClickListener {

        fun onClick(softUISeatModel: QIBusSoftUISeatModel)
    }

    /*view holder*/
    private class EdgeViewHolder internal constructor(itemView: View) : RecyclerView.ViewHolder(itemView) {

        val mIvSeatSelected: ImageView
        val mIvSeat: ImageView
        val mIvSeatBooked: ImageView
        val mTvSeatLadies: ImageView
        val mTvSeatNo: TextView

        init {
            mIvSeat = itemView.findViewById(R.id.ivSeat)
            mIvSeatSelected = itemView.findViewById(R.id.ivSeatSelected)
            mTvSeatNo = itemView.findViewById(R.id.tvSeatNo)
            mTvSeatLadies = itemView.findViewById(R.id.ivSeatLadies)
            mIvSeatBooked = itemView.findViewById(R.id.ivSeatBooked)
        }

    }

    /*view holder*/
    private class CenterViewHolder internal constructor(itemView: View) : RecyclerView.ViewHolder(itemView) {

        val mIvSeatSelected: ImageView
        val mIvSeat: ImageView
        val mTvSeatLadies: ImageView
        val mIvSeatBooked: ImageView
        val mTvSeatNo: TextView

        init {
            mIvSeat = itemView.findViewById(R.id.ivSeat)
            mIvSeatSelected = itemView.findViewById(R.id.ivSeatSelected)
            mTvSeatNo = itemView.findViewById(R.id.tvSeatNo)
            mTvSeatLadies = itemView.findViewById(R.id.ivSeatLadies)
            mIvSeatBooked = itemView.findViewById(R.id.ivSeatBooked)

        }

    }

    /*view holder*/
    private class EmptyViewHolder internal constructor(itemView: View) : RecyclerView.ViewHolder(itemView)

}
