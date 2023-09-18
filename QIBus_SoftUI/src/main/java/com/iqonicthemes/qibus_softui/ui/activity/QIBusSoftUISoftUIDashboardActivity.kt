package com.iqonicthemes.qibus_softui.ui.activity

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.speech.RecognizerIntent
import android.view.View
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.RelativeLayout
import android.widget.TextView

import com.iqonicthemes.qibus_softui.QIBusSoftUIBaseActivity
import com.iqonicthemes.qibus_softui.R
import com.iqonicthemes.qibus_softui.ui.fragment.QIBusSoftUIFragmentOffers
import com.iqonicthemes.qibus_softui.ui.fragment.QIBusSoftUIHomeFragment
import com.iqonicthemes.qibus_softui.ui.fragment.QIBusSoftUIMoreFragment
import com.iqonicthemes.qibus_softui.ui.fragment.QIBusSoftUIBookingFragment

class QIBusSoftUISoftUIDashboardActivity : QIBusSoftUIBaseActivity(), View.OnClickListener {

    /*variable declaration*/
    private var mTvTitle: TextView? = null
    private var mIvNotification: ImageView? = null
    private var mIvHome: ImageView? = null
    private var rvPkgView: RelativeLayout? = null
    private var rvHomeView: RelativeLayout? = null
    private var rvBookingView: RelativeLayout? = null
    private var rvMoreView: RelativeLayout? = null
    private var mIvPackages: ImageView? = null
    private var mIvBooking: ImageView? = null
    private var mIvOther: ImageView? = null
    private val mHomeFragmentNewest = QIBusSoftUIHomeFragment()
    private val mFragmentOffers = QIBusSoftUIFragmentOffers()
    private val mMyBookingFragment = QIBusSoftUIBookingFragment()
    private val mMoreFragment = QIBusSoftUIMoreFragment()
    private var mLlHome: LinearLayout? = null
    private var mLllPackages: LinearLayout? = null
    private var mLlBooking: LinearLayout? = null
    private var mLlMore: LinearLayout? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.qibus_softui_activity_dashboard)

        initLayouts()
        initializeListeners()

        setSelected(mIvHome!!)
        loadFragment(mHomeFragmentNewest)

    }

    /* init layout */
    @SuppressLint("ClickableViewAccessibility")
    fun initLayouts() {

        mTvTitle = findViewById(R.id.tvTitle)
        mIvNotification = findViewById(R.id.ivNotification)
        mLlHome = findViewById(R.id.llHome)
        mLllPackages = findViewById(R.id.llPackage)
        mLlBooking = findViewById(R.id.llBooking)
        mLlMore = findViewById(R.id.llMore)
        mIvHome = findViewById(R.id.ivHome)
        mIvPackages = findViewById(R.id.ivPackages)
        mIvBooking = findViewById(R.id.ivBooking)
        mIvOther = findViewById(R.id.ivMore)
        mTvTitle!!.text = QIBusSoftUIHomeFragment.mTitle
        rvHomeView = findViewById(R.id.rvHomeView)
        rvPkgView = findViewById(R.id.rvPkgView)
        rvBookingView = findViewById(R.id.rvBookingView)
        rvMoreView = findViewById(R.id.rvMoreView)
    }

    /* initialize listener */
    fun initializeListeners() {
        mIvNotification!!.setOnClickListener(this)
        mLlHome!!.setOnClickListener(this)
        mLllPackages!!.setOnClickListener(this)
        mLlBooking!!.setOnClickListener(this)
        mLlMore!!.setOnClickListener(this)
        SetNotificationImage(mIvNotification!!)

    }

    /* set selected item in bottom navigation */
    private fun setSelected(mBarImg: ImageView) {
        //   mBarImg.background = resources.getDrawable(R.drawable.qibus_softui_bg_tint_icon)
    }

    /* Update UI */
    private fun updateUi() {
        mIvHome!!.setImageResource(R.drawable.qibus_softui_ic_home_lite)
        mIvHome!!.background = null
        mIvPackages!!.setImageResource(R.drawable.qibus_softui_ic_local_mall_lite)
        mIvPackages!!.background = null
        mIvBooking!!.setImageResource(R.drawable.qibus_softui_ic_assignment_lite)
        mIvBooking!!.background = null
        mIvOther!!.setImageResource(R.drawable.qibus_softui_ic_more_lite)
        mIvOther!!.background = null
        rvHomeView!!.background = null
        rvPkgView!!.background = null
        rvBookingView!!.background = null
        rvMoreView!!.background = null
    }

    /* onBack press */
    override fun onBackPressed() {
        super.onBackPressed()
    }

    /* onClick listener */
    override fun onClick(v: View) {

        if (v === mIvNotification) {
            startActivity(QIBusSoftUISoftUINotificationActivity::class.java)
            return
        }
        updateUi()

        when (v.id) {

            R.id.llHome -> {
                if (!mHomeFragmentNewest.isVisible) {
                    mTvTitle!!.text = QIBusSoftUIHomeFragment.mTitle
                    loadFragment(mHomeFragmentNewest)
                }
                setSelected(mIvHome!!)
                rvHomeView!!.background =
                    resources.getDrawable(R.drawable.qibus_softui_round)
                mIvHome!!.setImageResource(R.drawable.iqbus_softui_ic_home)
            }
            R.id.llPackage -> {
                if (!mFragmentOffers.isVisible) {
                    mTvTitle!!.text = QIBusSoftUIFragmentOffers.mTitle
                    loadFragment(mFragmentOffers)
                }
                setSelected(mIvPackages!!)
                rvPkgView!!.background = resources.getDrawable(R.drawable.qibus_softui_round)

                mIvPackages!!.setImageResource(R.drawable.qibus_softui_ic_local_mall)
            }
            R.id.llBooking -> {
                if (!mMyBookingFragment.isVisible) {
                    mTvTitle!!.text = QIBusSoftUIBookingFragment.mTitle
                    loadFragment(mMyBookingFragment)
                }
                setSelected(mIvBooking!!)
                rvBookingView!!.background =
                    resources.getDrawable(R.drawable.qibus_softui_round)
                mIvBooking!!.setImageResource(R.drawable.qibus_softui_ic_assignment)
            }
            R.id.llMore -> {
                if (!mMoreFragment.isVisible) {
                    mTvTitle!!.text = QIBusSoftUIMoreFragment.mTitle
                    loadFragment(mMoreFragment)
                }
                setSelected(mIvOther!!)
                rvMoreView!!.background =
                    resources.getDrawable(R.drawable.qibus_softui_round)
                mIvOther!!.setImageResource(R.drawable.qibus_softui_ic_more)
            }
        }
    }

    /* get Activity result */

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        when (requestCode) {
            101 -> {
                if (resultCode == Activity.RESULT_OK && null != data) {
                    val result = data.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS)
                    if (mHomeFragmentNewest.isVisible) {
                        mHomeFragmentNewest.ChangeDestination(result[0])
                    } else {
                        loadFragment(mHomeFragmentNewest)
                    }
                }
            }
        }
    }

}
