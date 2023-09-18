package com.iqonicthemes.qibus_softui.ui.activity


import android.os.Bundle
import android.os.Handler
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import com.iqonicthemes.qibus_softui.QIBusSoftUIBaseActivity
import com.iqonicthemes.qibus_softui.R
import com.iqonicthemes.qibus_softui.model.QIBusSoftUIDroppingModel
import com.iqonicthemes.qibus_softui.ui.adapter.QIBusSoftUIDroppingAdapter
import java.util.ArrayList
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class QIBusSoftUISoftUIDroppingPointActivity : QIBusSoftUIBaseActivity(), View.OnClickListener {
    private var mTvPickup: TextView? = null
    private var mTvDropping: TextView? = null
    private var mRvPickup: RecyclerView? = null
    private var mRvDropping: RecyclerView? = null
    private var mPickUpList: MutableList<QIBusSoftUIDroppingModel>? = null
    private var mSoftUIDroppingList: MutableList<QIBusSoftUIDroppingModel>? = null
    private var mPickupAdapterSoftUI: QIBusSoftUIDroppingAdapter? = null
    private var mSoftUIDroppingAdapter: QIBusSoftUIDroppingAdapter? = null
    private var mIvBack: ImageView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.qibus_softui_activity_dropping_point)
        initLayouts()
        initializeListeners()

    }

    /* initialize listener */
    private fun initializeListeners() {
        mTvPickup!!.setOnClickListener(this)
        mTvDropping!!.setOnClickListener(this)
        mIvBack!!.setOnClickListener(this)

        mRvPickup!!.layoutManager = LinearLayoutManager(this, RecyclerView.VERTICAL, false)
        mRvDropping!!.layoutManager = LinearLayoutManager(this, RecyclerView.VERTICAL, false)
        mPickUpList = ArrayList()
        mSoftUIDroppingList = ArrayList()

        mPickUpList!!.add(
            QIBusSoftUIDroppingModel(
                getString(R.string.QIBus_softui_lbl_pickup1),
                getString(R.string.QIBus_softui_lbl_location1),
                getString(R.string.QIBus_softui_lbl_duration1)
            )
        )
        mPickUpList!!.add(
            QIBusSoftUIDroppingModel(
                getString(R.string.QIBus_softui_lbl_pickup2),
                getString(R.string.QIBus_softui_lbl_location1),
                getString(R.string.QIBus_softui_lbl_duration1)
            )
        )

        mSoftUIDroppingList!!.add(
            QIBusSoftUIDroppingModel(
                getString(R.string.QIBus_softui_lbl_dropping1),
                getString(R.string.QIBus_softui_lbl_location1),
                getString(R.string.QIBus_softui_lbl_duration1)
            )
        )
        mSoftUIDroppingList!!.add(
            QIBusSoftUIDroppingModel(
                getString(R.string.QIBus_softui_lbl_droppin2),
                getString(R.string.QIBus_softui_lbl_location1),
                getString(R.string.QIBus_softui_lbl_duration1)
            )
        )

        mPickupAdapterSoftUI = QIBusSoftUIDroppingAdapter(this, mPickUpList!!)
        mSoftUIDroppingAdapter = QIBusSoftUIDroppingAdapter(this, mSoftUIDroppingList!!)

        mRvPickup!!.adapter = mPickupAdapterSoftUI
        mRvDropping!!.adapter = mSoftUIDroppingAdapter


        mPickupAdapterSoftUI!!.setOnClickListener(object :
            QIBusSoftUIDroppingAdapter.onClickListener {
            override fun onClick(i: Int, name: String) {
                Handler().postDelayed(
                    {
                        mTvDropping!!.background =
                            resources.getDrawable(R.drawable.qibus_softui_bg_rightswitch_select)
                        mTvDropping!!.setTextColor(resources.getColor(R.color.QIBus_softui_white))
                        mTvPickup!!.setTextColor(resources.getColor(R.color.QIBus_softui_textHeader))
                        mTvPickup!!.background =
                            resources.getDrawable(R.drawable.qibus_softui_bg_leftswitch)
                        hideView(mRvPickup!!)
                        showView(mRvDropping!!)
                        mPickup = name
                    },
                    500
                )
            }

        })
        mSoftUIDroppingAdapter!!.setOnClickListener(object :
            QIBusSoftUIDroppingAdapter.onClickListener {
            override fun onClick(i: Int, name: String) {
                Handler().postDelayed(
                    {
                        mDropping = name
                        startActivity(QIBusSoftUISoftUIPassengerDetailActivity::class.java)
                    },
                    500
                )
            }
        })
        RunLayoutAnimation(mRvPickup!!)
        RunLayoutAnimation(mRvDropping!!)

    }

    /* init layout */
    private fun initLayouts() {
        mTvPickup = findViewById(R.id.tvPickup)
        mTvDropping = findViewById(R.id.tvDropping)
        mRvPickup = findViewById(R.id.rvPickup)
        mRvDropping = findViewById(R.id.rvDropping)
        mIvBack = findViewById(R.id.ivBack)
    }

    /* onClick listener */
    override fun onClick(v: View) {

        if (v === mTvPickup) {
            mTvPickup!!.background =
                resources.getDrawable(R.drawable.qibus_softui_bg_leftswitch_select)
            mTvPickup!!.setTextColor(resources.getColor(R.color.QIBus_softui_white))

            mTvDropping!!.setTextColor(resources.getColor(R.color.QIBus_softui_textHeader))
            mTvDropping!!.background = resources.getDrawable(R.drawable.qibus_softui_bg_rightswitch)

            hideView(mRvDropping!!)
            showView(mRvPickup!!)
            RunLayoutAnimation(mRvPickup!!)


        } else if (v === mTvDropping) {
            mTvDropping!!.background =
                resources.getDrawable(R.drawable.qibus_softui_bg_rightswitch_select)
            mTvDropping!!.setTextColor(resources.getColor(R.color.QIBus_softui_white))
            mTvPickup!!.setTextColor(resources.getColor(R.color.QIBus_softui_textHeader))
            mTvPickup!!.background = resources.getDrawable(R.drawable.qibus_softui_bg_leftswitch)

            hideView(mRvPickup!!)
            showView(mRvDropping!!)
            RunLayoutAnimation(mRvDropping!!)

        } else if (v === mIvBack) onBackPressed()
    }

    companion object {
        /*variable declaration*/
        var mPickup: String = ""
        var mDropping: String = ""
    }
}
