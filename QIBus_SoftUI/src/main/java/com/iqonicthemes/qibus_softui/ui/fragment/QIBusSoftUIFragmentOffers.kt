package com.iqonicthemes.qibus_softui.ui.fragment


import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView

import com.iqonicthemes.qibus_softui.R
import com.iqonicthemes.qibus_softui.model.QIBusSoftUINewPackageModel
import com.iqonicthemes.qibus_softui.ui.activity.QIBusSoftUIBusSoftUIListActivity
import com.iqonicthemes.qibus_softui.ui.activity.QIBusSoftUISoftUIPackageActivity
import com.iqonicthemes.qibus_softui.ui.adapter.QIBusSoftUIPackageAdapter
import com.iqonicthemes.qibus_softui.utils.QIBusSoftUIConstants
import com.iqonicthemes.qibus_softui.utils.QIBusSoftUIRecyclerCoverFlow

import java.util.ArrayList

import androidx.fragment.app.Fragment

class QIBusSoftUIFragmentOffers : Fragment(), View.OnClickListener,
    QIBusSoftUIPackageAdapter.onClickListener {
    private var mAdapterSoftUI: QIBusSoftUIPackageAdapter? = null
    private var mPopularAdapterSoftUI: QIBusSoftUIPackageAdapter? = null
    private var mRvNewPackage: QIBusSoftUIRecyclerCoverFlow? = null
    private var mRvPopularPackage: QIBusSoftUIRecyclerCoverFlow? = null
    private var mRvTrendingPackage: QIBusSoftUIRecyclerCoverFlow? = null
    private var mPackageListSoftUI: ArrayList<QIBusSoftUINewPackageModel>? = null
    private var mPopularList: ArrayList<QIBusSoftUINewPackageModel>? = null
    private var mTvNewPackageAll: TextView? = null
    private var mTvPopularPackageAll: TextView? = null
    private var mTvTrendingPackageAll: TextView? = null

    /* create view */
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.qibus_softui_fragment_offers, null)

        bindView(view)
        setListener()
        getData()
        return view
    }

    /* set listener */
    private fun setListener() {

        mTvNewPackageAll!!.setOnClickListener(this)
        mTvPopularPackageAll!!.setOnClickListener(this)
        mTvTrendingPackageAll!!.setOnClickListener(this)


    }

    /* bind view */
    private fun bindView(view: View) {
        mRvNewPackage = view.findViewById(R.id.rvNewPackage)
        mRvPopularPackage = view.findViewById(R.id.rvPopularPackage)
        mRvTrendingPackage = view.findViewById(R.id.rvTrendingPackage)
        mTvNewPackageAll = view.findViewById(R.id.tvViewAllNewPackage)
        mTvPopularPackageAll = view.findViewById(R.id.tvViewallPopularPackage)
        mTvTrendingPackageAll = view.findViewById(R.id.tvViewAllTrendingPackage)
    }

    /*get cricketdata */
    private fun getData() {
        mPackageListSoftUI = ArrayList()
        mPopularList = ArrayList()
        mPackageListSoftUI!!.add(
            QIBusSoftUINewPackageModel(
                getString(R.string.QIBus_softui_lbl_goa),
                getString(R.string.QIBus_softui_lbl_package_duration1),
                getString(R.string.QIBus_softui_lbl_package_rate1),
                getString(R.string.QIBus_softui_lbl_package_price1),
                getString(R.string.QIBus_softui_lbl_package_booking1),
                R.drawable.qibus_softui_ic_goa
            )
        )
        mPackageListSoftUI!!.add(
            QIBusSoftUINewPackageModel(
                getString(R.string.QIBus_softui_lbl_amritsar),
                getString(R.string.QIBus_softui_lbl_package_duration2),
                getString(R.string.QIBus_softui_lbl_package_rate3),
                getString(R.string.QIBus_softui_lbl_package_price2),
                getString(R.string.QIBus_softui_lbl_package_bookin2),
                R.drawable.qibus_softui_ic_amritsar
            )
        )
        mPackageListSoftUI!!.add(
            QIBusSoftUINewPackageModel(
                getString(R.string.QIBus_softui_lbl_andaman),
                getString(R.string.QIBus_softui_lbl_package_duration3),
                getString(R.string.QIBus_softui_lbl_package_rate5),
                getString(R.string.QIBus_softui_lbl_package_price3),
                getString(R.string.QIBus_softui_lbl_package_booking3),
                R.drawable.qibus_softui_ic_andamans
            )
        )
        mPackageListSoftUI!!.add(
            QIBusSoftUINewPackageModel(
                getString(R.string.QIBus_softui_lbl_delhi),
                getString(R.string.QIBus_softui_lbl_package_duration1),
                getString(R.string.QIBus_softui_lbl_package_rate4),
                getString(R.string.QIBus_softui_lbl_package_price4),
                getString(R.string.QIBus_softui_lbl_package_booking1),
                R.drawable.qibus_softui_ic_delhi
            )
        )
        mPackageListSoftUI!!.add(
            QIBusSoftUINewPackageModel(
                getString(R.string.QIBus_softui_lbl_shimla),
                getString(R.string.QIBus_softui_lbl_package_duration1),
                getString(R.string.QIBus_softui_lbl_package_rate4),
                getString(R.string.QIBus_softui_lbl_package_price5),
                getString(R.string.QIBus_softui_lbl_package_bookin2),
                R.drawable.qibus_softui_ic_temp
            )
        )
        mPackageListSoftUI!!.add(
            QIBusSoftUINewPackageModel(
                getString(R.string.QIBus_softui_lbl_udaipur),
                getString(R.string.QIBus_softui_lbl_package_duration2),
                getString(R.string.QIBus_softui_lbl_package_rate5),
                getString(R.string.QIBus_softui_lbl_package_price1),
                getString(R.string.QIBus_softui_lbl_package_booking1),
                R.drawable.qibus_softui_ic_udaipur
            )
        )


        mPopularList!!.add(
            QIBusSoftUINewPackageModel(
                getString(R.string.QIBus_softui_lbl_delhi),
                getString(R.string.QIBus_softui_lbl_package_duration3),
                getString(R.string.QIBus_softui_lbl_package_rate5),
                getString(R.string.QIBus_softui_lbl_package_price5),
                getString(R.string.QIBus_softui_lbl_package_booking3),
                R.drawable.qibus_softui_ic_delhi
            )
        )
        mPopularList!!.add(
            QIBusSoftUINewPackageModel(
                getString(R.string.QIBus_softui_lbl_shimla),
                getString(R.string.QIBus_softui_lbl_package_duration1),
                getString(R.string.QIBus_softui_lbl_package_rate5),
                getString(R.string.QIBus_softui_lbl_package_price2),
                getString(R.string.QIBus_softui_lbl_package_booking1),
                R.drawable.qibus_softui_ic_temp
            )
        )
        mPopularList!!.add(
            QIBusSoftUINewPackageModel(
                getString(R.string.QIBus_softui_lbl_udaipur),
                getString(R.string.QIBus_softui_lbl_package_duration1),
                getString(R.string.QIBus_softui_lbl_package_rate5),
                getString(R.string.QIBus_softui_lbl_package_price1),
                getString(R.string.QIBus_softui_lbl_package_booking1),
                R.drawable.qibus_softui_ic_udaipur
            )
        )
        mPopularList!!.add(
            QIBusSoftUINewPackageModel(
                getString(R.string.QIBus_softui_lbl_andaman),
                getString(R.string.QIBus_softui_lbl_package_duration1),
                getString(R.string.QIBus_softui_lbl_package_rate5),
                getString(R.string.QIBus_softui_lbl_package_price1),
                getString(R.string.QIBus_softui_lbl_package_booking1),
                R.drawable.qibus_softui_ic_andamans
            )
        )



        mPopularList!!.addAll(mPopularList!!)

        mAdapterSoftUI = QIBusSoftUIPackageAdapter(activity!!, mPackageListSoftUI!!)
        mPopularAdapterSoftUI = QIBusSoftUIPackageAdapter(activity!!, mPopularList!!)
        mRvNewPackage!!.adapter = mAdapterSoftUI
        mAdapterSoftUI!!.setOnClickListener(this)
        mPopularAdapterSoftUI!!.setOnClickListener(this)

        mRvPopularPackage!!.adapter = mPopularAdapterSoftUI
        mRvTrendingPackage!!.adapter = mAdapterSoftUI


    }

    /* onClick listener */
    override fun onClick(v: View) {
        if (v === mTvNewPackageAll) {
            val intent = Intent(activity, QIBusSoftUISoftUIPackageActivity::class.java)
            intent.putExtra(QIBusSoftUIConstants.intentdata.PACKAGE, mPackageListSoftUI)
            startActivity(intent)
        } else if (v === mTvPopularPackageAll) {
            val intent = Intent(activity, QIBusSoftUISoftUIPackageActivity::class.java)
            intent.putExtra(QIBusSoftUIConstants.intentdata.PACKAGE, mPackageListSoftUI)
            startActivity(intent)
        } else if (v === mTvTrendingPackageAll) {
            val intent = Intent(activity, QIBusSoftUISoftUIPackageActivity::class.java)
            intent.putExtra(QIBusSoftUIConstants.intentdata.PACKAGE, mPackageListSoftUI)
            startActivity(intent)
        }
    }

    /* onClick listener for item */
    override fun onClick(aPackageModelSoftUI: QIBusSoftUINewPackageModel) {

        val intent = Intent(activity, QIBusSoftUIBusSoftUIListActivity::class.java)
        intent.putExtra(
            QIBusSoftUIConstants.intentdata.PACKAGE_NAME,
            aPackageModelSoftUI.destination
        )
        startActivity(intent)


    }

    companion object {
        /*variable declaration*/
        val mTitle = "Packages"
    }
}
