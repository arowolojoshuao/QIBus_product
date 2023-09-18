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

class QIBusSoftUISoftUICardsActivity : QIBusSoftUIBaseActivity(), View.OnClickListener {
    /*variable declaration*/

    private var mSoftUICardList: ArrayList<QIBusSoftUICardModel>? = null
    private var mRvCard: RecyclerView? = null
    private var mIvBack: ImageView? = null
    private var mIVAddCardDetail: ImageView? = null
    private var mCardAdapterSoftUI: QIBusSoftUICardsAdapter? = null
    private var mRlMain: RelativeLayout? = null
    private var mFlags: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.qibus_softui_activity_card)
        initLayouts()
        initializeListeners()
        enableSwipeToDeleteAndUndo()
    }

    /* initialize listener */
    private fun initializeListeners() {
        mIvBack!!.setOnClickListener(this)
        mIVAddCardDetail!!.setOnClickListener(this)
    }

    /* init layout */
    private fun initLayouts() {
        setTitle(R.string.QIBus_softui_title_toolbar_cards)

        mRvCard = findViewById(R.id.rvCard)
        mIvBack = findViewById(R.id.ivBack)
        mRlMain = findViewById(R.id.rlMain)
        mIVAddCardDetail = findViewById(R.id.ivAddCardDetail)
        mRvCard!!.layoutManager = LinearLayoutManager(this, RecyclerView.VERTICAL, false)
        mSoftUICardList = ArrayList()


        mSoftUICardList!!.add(QIBusSoftUICardModel(getString(R.string.QIBus_softui_lbl_cardtype1), R.drawable.qibus_softui_ic_card, getString(R.string.QIBus_softui_lbl_card_digit1), getString(R.string.QIBus_softui_lbl_card_digit2), getString(R.string.QIBus_softui_lbl_card_digit3), getString(R.string.QIBus_softui_lbl_card_digit4), getString(R.string.QIBus_softui_lbl_card_validdate1), getString(R.string.QIBus_softui_lbl_booking_passengername1)))
        mSoftUICardList!!.add(QIBusSoftUICardModel(getString(R.string.QIBus_softui_lbl_cardtype2), R.drawable.qibus_softui_ic_card3, getString(R.string.QIBus_softui_lbl_card_digit2), getString(R.string.QIBus_softui_lbl_card_digit4), getString(R.string.QIBus_softui_lbl_card_digit1), getString(R.string.QIBus_softui_lbl_card_digit3), getString(R.string.QIBus_softui_lbl_card_validdate1), getString(R.string.QIBus_softui_lbl_booking_passengername1)))

        mCardAdapterSoftUI = QIBusSoftUICardsAdapter(this, mSoftUICardList!!)
        mRvCard!!.adapter = mCardAdapterSoftUI
        RunLayoutAnimation(mRvCard!!)

        mFlags = intent.getStringExtra(QIBusSoftUIConstants.intentdata.CARDFLAG)

    }

    /* onClick listener */
    override fun onClick(v: View) {
        if (v === mIvBack) onBackPressed()
        if (v === mIVAddCardDetail) {
            val intent = Intent(this@QIBusSoftUISoftUICardsActivity, QIBusSoftUISoftUICardDetailActivity::class.java)
            intent.putExtra(QIBusSoftUIConstants.intentdata.CARDFLAG, mFlags)
            startActivity(intent)
        }
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
                        .make(mRlMain!!, getString(R.string.QIBus_softui_text_remove), Snackbar.LENGTH_LONG)
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

}
