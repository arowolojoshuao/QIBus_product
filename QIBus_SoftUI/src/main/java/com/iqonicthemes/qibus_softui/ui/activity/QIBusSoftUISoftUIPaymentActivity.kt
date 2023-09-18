package com.iqonicthemes.qibus_softui.ui.activity

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import android.widget.RelativeLayout

import com.google.android.material.snackbar.Snackbar
import com.iqonicthemes.qibus_softui.QIBusSoftUIBaseActivity
import com.iqonicthemes.qibus_softui.R
import com.iqonicthemes.qibus_softui.model.QIBusSoftUICardModel
import com.iqonicthemes.qibus_softui.ui.adapter.QIBusSoftUICardsAdapter
import com.iqonicthemes.qibus_softui.utils.QIBusSoftUIConstants
import com.iqonicthemes.qibus_softui.utils.QIBusSoftUISwipeToDeleteCallback

import java.util.ArrayList
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView


class QIBusSoftUISoftUIPaymentActivity : QIBusSoftUIBaseActivity(), View.OnClickListener,
    QIBusSoftUICardsAdapter.onClickListener {

    /*variable declaration*/
    private var mSoftUICardList: ArrayList<QIBusSoftUICardModel>? = null
    private var mRvCard: RecyclerView? = null
    private var mIvBack: ImageView? = null
    private var mIvAdd: ImageView? = null
    private var mCardAdapterSoftUI: QIBusSoftUICardsAdapter? = null
    private var mRlMain: RelativeLayout? = null
    private var mRlDebit: RelativeLayout? = null
    private var mRlCredit: RelativeLayout? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.qibus_softui_activity_payment)

        initLayouts()
        initializeListeners()
        enableSwipeToDeleteAndUndo()
    }

    /* init layout */
    private fun initLayouts() {
        mRvCard = findViewById(R.id.rvCard)
        mIvBack = findViewById(R.id.ivBack)
        mIvAdd = findViewById(R.id.ivAdd)
        mRlMain = findViewById(R.id.rlMain)
        mRlCredit = findViewById(R.id.rlCreditCard)
        mRlDebit = findViewById(R.id.rlDebitCard)

    }

    /* initialize listener */
    private fun initializeListeners() {
        mIvBack!!.setOnClickListener(this)
        mIvAdd!!.setOnClickListener(this)
        mRlCredit!!.setOnClickListener(this)
        mRlDebit!!.setOnClickListener(this)

        mSoftUICardList = ArrayList()
        mRvCard!!.layoutManager = LinearLayoutManager(this, RecyclerView.VERTICAL, false)
        mSoftUICardList!!.add(
            QIBusSoftUICardModel(
                getString(R.string.QIBus_softui_lbl_cardtype1),
                R.drawable.qibus_softui_ic_card,
                getString(R.string.QIBus_softui_lbl_card_digit1),
                getString(R.string.QIBus_softui_lbl_card_digit2),
                getString(R.string.QIBus_softui_lbl_card_digit3),
                getString(R.string.QIBus_softui_lbl_card_digit4),
                getString(R.string.QIBus_softui_lbl_card_validdate1),
                getString(R.string.QIBus_softui_lbl_booking_passengername1)
            )
        )
        mCardAdapterSoftUI = QIBusSoftUICardsAdapter(this, mSoftUICardList!!)
        mRvCard!!.adapter = mCardAdapterSoftUI
        mCardAdapterSoftUI!!.setOnClickListener(this)
        RunLayoutAnimation(mRvCard!!)
    }

    /* onClick listener */
    override fun onClick(v: View) {
        if (v === mIvBack)
            onBackPressed()
        else if (v === mIvAdd)
            startActivity(QIBusSoftUISoftUICardDetailActivity::class.java)
        else if (v === mRlCredit)
            startActivity(QIBusSoftUISoftUICardDetailActivity::class.java)
        else if (v === mRlDebit) startActivity(QIBusSoftUISoftUICardDetailActivity::class.java)

    }

    /* swipe to delete & undo */
    private fun enableSwipeToDeleteAndUndo() {
        val swipeToDeleteCallback = object : QIBusSoftUISwipeToDeleteCallback(this) {
            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, i: Int) {


                val position = viewHolder.adapterPosition
                val mSoftUICardModel: QIBusSoftUICardModel
                mSoftUICardModel = mCardAdapterSoftUI!!.data[position]

                mCardAdapterSoftUI!!.removeItem(position)


                val snackbar = Snackbar
                    .make(
                        mRlMain!!,
                        getString(R.string.QIBus_softui_text_remove),
                        Snackbar.LENGTH_LONG
                    )
                snackbar.setAction(getString(R.string.QIBus_softui_text_undo)) {
                    mCardAdapterSoftUI!!.restoreItem(mSoftUICardModel, position)
                    mRvCard!!.scrollToPosition(position)
                }

                snackbar.setActionTextColor(Color.YELLOW)
                snackbar.show()

            }
        }

        val itemTouchhelper = ItemTouchHelper(swipeToDeleteCallback)
        itemTouchhelper.attachToRecyclerView(mRvCard)
    }

    /* onClick listener */
    override fun onClick(softUICardModel: QIBusSoftUICardModel) {
        val intent = Intent(this, QIBusSoftUISoftUICardDetailActivity::class.java)
        intent.putExtra(QIBusSoftUIConstants.intentdata.CARDDETAIL, softUICardModel)
        startActivity(intent)
    }
}
