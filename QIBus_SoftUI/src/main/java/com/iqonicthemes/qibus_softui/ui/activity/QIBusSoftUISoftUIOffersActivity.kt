package com.iqonicthemes.qibus_softui.ui.activity

import android.os.Bundle
import android.view.View
import android.widget.ImageView

import com.iqonicthemes.qibus_softui.QIBusSoftUIBaseActivity
import com.iqonicthemes.qibus_softui.R
import com.iqonicthemes.qibus_softui.model.QIBusSoftUINewOfferModel
import com.iqonicthemes.qibus_softui.ui.adapter.QIBusSoftUIViewOfferAdapter
import com.iqonicthemes.qibus_softui.utils.QIBusSoftUIConstants

import java.util.ArrayList

import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class QIBusSoftUISoftUIOffersActivity : QIBusSoftUIBaseActivity(), View.OnClickListener {
    /*variable declaration*/
    private var mRvOffer: RecyclerView? = null
    private var mOfferListSoftUI: ArrayList<QIBusSoftUINewOfferModel>? = null
    private var mIvBack: ImageView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.qibus_softui_activity_offers)

        initLayouts()
        initializeListeners()

    }

    /* initialize listener */
    private fun initializeListeners() {
        mIvBack!!.setOnClickListener(this)
        mOfferListSoftUI = ArrayList()
        mOfferListSoftUI = intent.getSerializableExtra(QIBusSoftUIConstants.intentdata.OFFER) as ArrayList<QIBusSoftUINewOfferModel>

        mRvOffer!!.layoutManager = LinearLayoutManager(this, RecyclerView.VERTICAL, false)
        mRvOffer!!.adapter = QIBusSoftUIViewOfferAdapter(this@QIBusSoftUISoftUIOffersActivity, mOfferListSoftUI!!)
        RunLayoutAnimation(mRvOffer!!)
    }

    /* init layout */
    private fun initLayouts() {
        mIvBack = findViewById(R.id.ivBack)
        mRvOffer = findViewById(R.id.rvOffer)
    }

    /* onClick listener */
    override fun onClick(v: View) {
        if (v === mIvBack) onBackPressed()
    }
}
