package com.iqonicthemes.qibus_softui.ui.activity

import android.annotation.SuppressLint
import android.app.Dialog
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.os.CountDownTimer
import android.text.Editable
import android.text.TextUtils
import android.text.TextWatcher
import android.text.method.HideReturnsTransformationMethod
import android.text.method.PasswordTransformationMethod
import android.view.View
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.RelativeLayout
import android.widget.Spinner
import android.widget.TextView

import java.util.ArrayList
import java.util.Calendar
import java.util.concurrent.TimeUnit

import com.iqonicthemes.qibus_softui.QIBusSoftUIBaseActivity
import com.iqonicthemes.qibus_softui.R
import com.iqonicthemes.qibus_softui.model.QIBusSoftUICardModel
import com.iqonicthemes.qibus_softui.ui.fragment.QIBusSoftUIHomeFragment
import com.iqonicthemes.qibus_softui.utils.QIBusSoftUIConstants

import com.iqonicthemes.qibus_softui.ui.activity.QIBusSoftUISoftUIBookingActivity.Companion.mTotal
import com.iqonicthemes.qibus_softui.ui.activity.QIBusSoftUISoftUIDroppingPointActivity.Companion.mDropping
import com.iqonicthemes.qibus_softui.ui.activity.QIBusSoftUISoftUIDroppingPointActivity.Companion.mPickup


class QIBusSoftUISoftUICardDetailActivity : QIBusSoftUIBaseActivity(), View.OnClickListener {

    /*variable declaration*/

    private var mYearAdapter: ArrayAdapter<String>? = null
    private var mMonthAdapter: ArrayAdapter<String>? = null
    private var mEdDigit1: EditText? = null
    private var mEdDigit2: EditText? = null
    private var mEdDigit3: EditText? = null
    private var mEdDigit4: EditText? = null
    private var mEdHolderName: EditText? = null
    private var mEdCode: EditText? = null
    private var mEdCvv: EditText? = null
    private var mSpinYear: Spinner? = null
    private var mSpinMonth: Spinner? = null
    private var mFlagValue: String? = null
    private var mIvBack: ImageView? = null
    private var mIvShowPWd: ImageView? = null
    private var mIVHidePwd: ImageView? = null
    private var mYearList: ArrayList<String>? = null
    private var mMonthList: ArrayList<String>? = null
    private var mSoftUICardList: QIBusSoftUICardModel? = null
    private var mTvTo: TextView? = null
    private var mTvPickup: TextView? = null
    private var mTvDropping: TextView? = null
    private var mTvTotal: TextView? = null
    private var mTvOfferCode: TextView? = null
    private var mTvFrom: TextView? = null
    private var mTvDetail: TextView? = null
    private var mBtnBook: Button? = null
    private var mLlContent: LinearLayout? = null
    private var mRlDetail: RelativeLayout? = null
    private var mRlHeading: RelativeLayout? = null
    private var mTvTitle: TextView? = null
    private var mTvTimer: TextView? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.qibus_softui_activity_card_detail)
        initLayouts()
        initializeListeners()
    }

    /* init layout */
    private fun initLayouts() {
        mEdDigit1 = findViewById(R.id.edDigit1)
        mEdDigit2 = findViewById(R.id.edDigit2)
        mEdDigit3 = findViewById(R.id.edDigit3)
        mEdDigit4 = findViewById(R.id.edDigit4)
        mEdHolderName = findViewById(R.id.edHolderName)
        mBtnBook = findViewById(R.id.btnBook)
        mRlDetail = findViewById(R.id.rlDetail)
        mTvTitle = findViewById(R.id.tvTitle)
        mIvShowPWd = findViewById(R.id.ivShowPwd)
        mEdCvv = findViewById(R.id.edCvv)
        mRlHeading = findViewById(R.id.rlHeading)
        mIVHidePwd = findViewById(R.id.ivHidePwd)

        mSpinYear = findViewById(R.id.spYear)
        mSpinMonth = findViewById(R.id.spMonth)

        mIvBack = findViewById(R.id.ivBack)
        mTvTo = findViewById(R.id.tvFromTo)
        mTvFrom = findViewById(R.id.tvFromName)
        mTvPickup = findViewById(R.id.tvFrom)
        mTvDropping = findViewById(R.id.tvTo)
        mLlContent = findViewById(R.id.llContent)
        mTvDetail = findViewById(R.id.tvDetail)
        mTvTotal = findViewById(R.id.tvTotal)
        mEdCode = findViewById(R.id.edCode)
        mTvOfferCode = findViewById(R.id.tvOfferCode)
        mTvTimer = findViewById(R.id.tvTimer)

    }

    /* initialize listener */
    private fun initializeListeners() {

        mIvBack!!.setOnClickListener(this)
        mBtnBook!!.setOnClickListener(this)


        mRlHeading!!.setOnClickListener(this)
        mIvShowPWd!!.setOnClickListener(this)
        mIVHidePwd!!.setOnClickListener(this)
        mBtnBook!!.stateListAnimator = null

        mEdDigit1!!.addTextChangedListener(GenericTextWatcher(mEdDigit1!!))
        mEdDigit2!!.addTextChangedListener(GenericTextWatcher(mEdDigit2!!))
        mEdDigit3!!.addTextChangedListener(GenericTextWatcher(mEdDigit3!!))
        mEdDigit4!!.addTextChangedListener(GenericTextWatcher(mEdDigit4!!))
        mFlagValue = intent.getStringExtra(QIBusSoftUIConstants.intentdata.CARDFLAG)
        if (mFlagValue != null) {

            if (mFlagValue == "1") {
                hideView(mRlDetail!!)
                hideView(mTvOfferCode!!)
                hideView(mEdCode!!)
                mBtnBook!!.text = getString(R.string.QIBus_softui_text_addcard)
            }

        } else {
            showView(mTvTimer!!)
            mTvTitle!!.text = getString(R.string.QIBus_softui_text_payment)
            mTvTo!!.text = QIBusSoftUIHomeFragment.mTo
            mTvFrom!!.text = QIBusSoftUIHomeFragment.mFrom

            mTvPickup!!.text = mPickup
            mTvDropping!!.text = mDropping

            mTvTotal!!.setText(
                String.format(
                    "%s %s",
                    getString(R.string.QIBus_softui_rs),
                    mTotal.toString()
                )
            )
            showView(mTvOfferCode!!)
            showView(mEdCode!!)


            object : CountDownTimer(300000, 1000) { // adjust the milli seconds here

                @SuppressLint("DefaultLocale")
                override fun onTick(millisUntilFinished: Long) {


                    mTvTimer!!.setText(
                        String.format(
                            "%d : %d ",
                            TimeUnit.MILLISECONDS.toMinutes(millisUntilFinished),
                            TimeUnit.MILLISECONDS.toSeconds(millisUntilFinished) - TimeUnit.MINUTES.toSeconds(
                                TimeUnit.MILLISECONDS.toMinutes(millisUntilFinished)
                            )
                        )
                    )
                }

                override fun onFinish() {
                    finish()
                }
            }.start()


        }
        mSoftUICardList =
            intent.getSerializableExtra(QIBusSoftUIConstants.intentdata.CARDDETAIL) as? QIBusSoftUICardModel

        if (mSoftUICardList != null) {
            mEdDigit1!!.isEnabled = false
            mEdDigit2!!.isEnabled = false
            mEdDigit3!!.isEnabled = false
            mEdDigit4!!.isEnabled = false
            mEdHolderName!!.isEnabled = false
            mEdDigit1!!.setText(mSoftUICardList!!.txtDigit1)
            mEdDigit2!!.setText(mSoftUICardList!!.txtDigit2)
            mEdDigit3!!.setText(mSoftUICardList!!.txtDigit3)
            mEdDigit4!!.setText(mSoftUICardList!!.txtDigit4)
            mEdHolderName!!.setText(mSoftUICardList!!.txtHolderName)

        }

        mYearList = ArrayList()
        mMonthList = ArrayList()

        val month = Calendar.getInstance().get(Calendar.MONTH)

        for (j in 1..12) {
            mMonthList!!.add(Integer.toString(j))
        }
        for (i in 2019..2040) {
            mYearList!!.add(Integer.toString(i))
        }

        mYearAdapter = ArrayAdapter(this, R.layout.qibus_softui_spinner_items, mYearList!!)
        mMonthAdapter = ArrayAdapter(this, R.layout.qibus_softui_spinner_items, mMonthList!!)

        mSpinYear!!.adapter = mYearAdapter
        mSpinMonth!!.adapter = mMonthAdapter

        mSpinMonth!!.setSelection(month)

        mEdCode!!.addTextChangedListener(object : TextWatcher {
            override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {
                if (mEdCode!!.length() > 0) {
                    mEdCode!!.setCompoundDrawablesWithIntrinsicBounds(
                        0,
                        0,
                        R.drawable.qibus_softui_ic_checkbox_circle,
                        0
                    )
                } else {
                    mEdCode!!.setCompoundDrawablesWithIntrinsicBounds(0, 0, 0, 0)
                }
            }

            override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {}

            override fun afterTextChanged(s: Editable) {}
        })
    }


    /* onClick listener */
    override fun onClick(v: View) {
        when (v.id) {
            R.id.ivShowPwd -> {
                hideView(mIvShowPWd!!)
                showView(mIVHidePwd!!)
                mEdCvv!!.setSelection(mEdCvv!!.length())
                mEdCvv!!.transformationMethod = HideReturnsTransformationMethod.getInstance()
            }
            R.id.ivHidePwd -> {
                mEdCvv!!.setSelection(mEdCvv!!.length())
                mEdCvv!!.transformationMethod = PasswordTransformationMethod.getInstance()

                hideView(mIVHidePwd!!)
                showView(mIvShowPWd!!)
            }
            R.id.ivBack -> onBackPressed()
            R.id.btnBook ->
                //    if (validate()) {
                if (mFlagValue != null) {
                    if (mFlagValue == getString(R.string.QIBus_softui_text_1)) {
                        showToast(getString(R.string.QIBus_softui_text_cardadd))
                    }
                } else {
                    val dialog = Dialog(this)
                    dialog.setContentView(R.layout.qibus_softui_dialog_success)
                    dialog.setCancelable(true)
                    dialog.window!!.setBackgroundDrawable(ColorDrawable(0))
                    dialog.findViewById<View>(R.id.btnClose).setOnClickListener {
                        if (dialog.isShowing)
                            dialog.dismiss()
                        startActivity(QIBusSoftUISoftUIDashboardActivity::class.java)
                    }
                    dialog.show()
                }
            R.id.rlHeading -> if (mLlContent!!.visibility == View.GONE) {
                showView(mLlContent!!)
                mTvDetail!!.setCompoundDrawablesWithIntrinsicBounds(
                    0,
                    0,
                    R.drawable.qibus_softui_ic_keyboard_arrow_down_black,
                    0
                )

            } else {
                mTvDetail!!.setCompoundDrawablesWithIntrinsicBounds(
                    0,
                    0,
                    R.drawable.qibus_softui_ic_keyboard_arrow_right,
                    0
                )
                hideView(mLlContent!!)

            }
        }//     }


    }

    /* validation */
    private fun validate(): Boolean {
        var flag = true
        if (TextUtils.isEmpty(mEdDigit1!!.text)) {
            flag = false
            showToast(getString(R.string.QIBus_softui_msg_digit))
        } else if (TextUtils.isEmpty(mEdDigit2!!.text)) {
            flag = false
            showToast(getString(R.string.QIBus_softui_msg_digit))
        } else if (TextUtils.isEmpty(mEdDigit3!!.text)) {
            flag = false
            showToast(getString(R.string.QIBus_softui_msg_digit))
        } else if (TextUtils.isEmpty(mEdDigit4!!.text)) {
            flag = false
            showToast(getString(R.string.QIBus_softui_msg_digit))
        } else if (TextUtils.isEmpty(mEdHolderName!!.text)) {
            flag = false
            showToast(getString(R.string.QIBus_softui_msg_holdername))
        }
        return flag
    }

    /* implement textwatcher */
    inner class GenericTextWatcher constructor(private val view: View) : TextWatcher {

        override fun afterTextChanged(editable: Editable) {
            val text = editable.toString()
            when (view.id) {
                R.id.edDigit1 -> if (text.length == 4)
                    mEdDigit2!!.requestFocus()
                R.id.edDigit2 -> if (text.length == 4)
                    mEdDigit3!!.requestFocus()
                else if (text.length == 0)
                    mEdDigit1!!.requestFocus()
                R.id.edDigit3 -> if (text.length == 4)
                    mEdDigit4!!.requestFocus()
                else if (text.length == 0)
                    mEdDigit2!!.requestFocus()
                R.id.edDigit4 -> if (text.length == 0)
                    mEdDigit3!!.requestFocus()
            }
        }

        override fun beforeTextChanged(arg0: CharSequence, arg1: Int, arg2: Int, arg3: Int) {}

        override fun onTextChanged(arg0: CharSequence, arg1: Int, arg2: Int, arg3: Int) {}
    }
}
