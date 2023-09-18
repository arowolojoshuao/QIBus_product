package com.iqonicthemes.qibus_softui.ui.activity

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.ImageView

import com.iqonicthemes.qibus_softui.QIBusSoftUIBaseActivity
import com.iqonicthemes.qibus_softui.R
import com.iqonicthemes.qibus_softui.model.QIBusSoftUINewPackageModel
import com.iqonicthemes.qibus_softui.ui.adapter.QIBusSoftUIViewPackageAdapter
import com.iqonicthemes.qibus_softui.utils.QIBusSoftUIConstants

import java.util.ArrayList

import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class QIBusSoftUISoftUIPackageActivity : QIBusSoftUIBaseActivity(), View.OnClickListener,
    QIBusSoftUIViewPackageAdapter.onClickListener {
    /*variable declaration*/
    private var mRvOffer: RecyclerView? = null
    private var mPackageListSoftUI: ArrayList<QIBusSoftUINewPackageModel>? = null
    private var mIvBack: ImageView? = null
    private var mAdapterSoftUI: QIBusSoftUIViewPackageAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.qibus_softui_activity_package)
        initLayouts()
        initializeListeners()
    }

    /* initialize listener */
    private fun initializeListeners() {
        mIvBack!!.setOnClickListener(this)
        mPackageListSoftUI = ArrayList()
        mPackageListSoftUI =
            intent.getSerializableExtra(QIBusSoftUIConstants.intentdata.PACKAGE) as ArrayList<QIBusSoftUINewPackageModel>

        mRvOffer!!.layoutManager = LinearLayoutManager(this, RecyclerView.VERTICAL, false)

        mAdapterSoftUI = QIBusSoftUIViewPackageAdapter(this, mPackageListSoftUI!!)
        mRvOffer!!.adapter = mAdapterSoftUI
        mAdapterSoftUI!!.setOnClickListener(this)
        RunLayoutAnimation(mRvOffer!!)
    }

    /* init layout */
    private fun initLayouts() {
        mIvBack = findViewById(R.id.ivBack)
        mRvOffer = findViewById(R.id.rvPackage)
    }

    /* onClick listener */
    override fun onClick(v: View) {
        if (v === mIvBack) onBackPressed()
    }

    /* onClick listener for item */
    override fun onClick(aPackageModelSoftUI: QIBusSoftUINewPackageModel) {
        val intent = Intent(this, QIBusSoftUIBusSoftUIListActivity::class.java)
        intent.putExtra(
            QIBusSoftUIConstants.intentdata.PACKAGE_NAME,
            aPackageModelSoftUI.destination
        )
        startActivity(intent)

    }
}
