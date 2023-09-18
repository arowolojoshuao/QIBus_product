package com.iqonicthemes.qibus_softui.ui.activity

import android.os.Bundle
import android.text.TextUtils
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.RelativeLayout
import android.widget.TextView

import com.iqonicthemes.qibus_softui.QIBusSoftUIBaseActivity
import com.iqonicthemes.qibus_softui.R

class QIBusSoftUISoftUIPassengerDetailActivity : QIBusSoftUIBaseActivity(), View.OnClickListener {
    /*variable declaration*/
    private var mBtnBook: Button? = null
    private var mLlDynamicContent: LinearLayout? = null
    private var mCount: Int = 0
    private var mSplited: Array<String>? = null
    private var mEdEmail: EditText? = null
    private var mEdMobileNumber: EditText? = null
    private var mEdFirstName: EditText? = null
    private var mEdAge: EditText? = null
    private var mIVBack: ImageView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.qibus_softui_activity_passenger_detail)
        initLayouts()
        initializeListeners()

    }

    /* init layout */
    private fun initLayouts() {
        mBtnBook = findViewById(R.id.btnBook)
        mLlDynamicContent = findViewById(R.id.llDynamicContent)
        mEdEmail = findViewById(R.id.edEmail)
        mEdMobileNumber = findViewById(R.id.edMobileNumber)
        mIVBack = findViewById(R.id.ivBack)
    }

    /* initialize listener */
    private fun initializeListeners() {
        mBtnBook!!.setOnClickListener(this)
        mIVBack!!.setOnClickListener(this)
        mBtnBook!!.stateListAnimator = null
        mCount = QIBusSoftUISoftUIBookingActivity.mCountSeat
        setTypeFace(mEdMobileNumber!!)

        mSplited = QIBusSoftUISoftUIBookingActivity.mSb.toString().split(" ".toRegex())
            .dropLastWhile({ it.isEmpty() }).toTypedArray()
        var i = 0
        while (i < mCount) {
            i++
            val view1 = layoutInflater.inflate(
                R.layout.qibus_softui_item_passenger1,
                mLlDynamicContent,
                false
            )
            val mTvSeatNo = view1.findViewById<TextView>(R.id.tvSeatNo)
            val mRlHeading = view1.findViewById<RelativeLayout>(R.id.rlHeading)
            val mRlSubHeading = view1.findViewById<RelativeLayout>(R.id.rlSubHeading)
            val mIvIcon = view1.findViewById<ImageView>(R.id.ivIcon)
            mEdFirstName = view1.findViewById(R.id.edFirstName)
            mEdAge = view1.findViewById(R.id.edAge)
            setTypeFace(mEdAge!!)
            mRlHeading.setOnClickListener {
                if (mRlSubHeading.visibility == View.VISIBLE) {

                    mIvIcon.setImageDrawable(resources.getDrawable(R.drawable.qibus_softui_ic_keyboard_arrow_down_black))

                    hideView(mRlSubHeading)
                } else {
                    mIvIcon.setImageDrawable(resources.getDrawable(R.drawable.qibus_softui_ic_keyboard_arrow_up_black))
                    showView(mRlSubHeading)

                }
            }
            mTvSeatNo.setText(String.format("L%s", mSplited!![i - 1]))
            mLlDynamicContent!!.addView(view1)
        }
    }

    /* onClick listener */
    override fun onClick(v: View) {
        if (v === mBtnBook) {
            // if (validate()) {
            startActivity(QIBusSoftUISoftUIPaymentActivity::class.java)
            //  }
        } else if (v === mIVBack) {
            onBackPressed()
        }
    }

    /* validations */
    private fun validate(): Boolean {
        var flag = true
        if (TextUtils.isEmpty(mEdEmail!!.text)) {
            flag = false
            showToast(getString(R.string.QIBus_softui_msg_email_id))
        } else if (TextUtils.isEmpty(mEdMobileNumber!!.text)) {
            flag = false
            showToast(getString(R.string.QIBus_softui_msg_mobile_number))
        } else if (TextUtils.isEmpty(mEdFirstName!!.text)) {
            flag = false
            showToast(getString(R.string.QIBus_softui_msg_name))
        } else if (TextUtils.isEmpty(mEdAge!!.text)) {
            flag = false
            showToast(getString(R.string.QIBus_softui_ms_age))
        }
        return flag
    }
}
