package com.iqonicthemes.qibus_softui.ui.activity

import android.annotation.SuppressLint
import android.content.Context
import android.os.Bundle
import android.os.CountDownTimer
import android.text.Editable
import android.text.TextUtils
import android.text.TextWatcher
import android.view.KeyEvent
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.EditText
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView

import java.util.concurrent.TimeUnit

import com.iqonicthemes.qibus_softui.QIBusSoftUIBaseActivity
import com.iqonicthemes.qibus_softui.R


class QIBusSoftUISoftUIVerificationActivity : QIBusSoftUIBaseActivity(), View.OnClickListener {

    /*variable declaration*/
    private var mEdDigit1: EditText? = null
    private var mEdDigit2: EditText? = null
    private var mEdDigit3: EditText? = null
    private var mEdDigit4: EditText? = null
    private var mLlVerify: LinearLayout? = null
    private var mTvResend: TextView? = null
    private var mTvTimer: TextView? = null
    private var mIvBack: ImageView? = null
    private var mEds: Array<EditText>? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.qibus_softui_activity_verification)
        initLayouts()
        initializeListeners()
    }

    /* init layout */
    private fun initLayouts() {
        mEdDigit1 = findViewById(R.id.edDigit1)
        mEdDigit2 = findViewById(R.id.edDigit2)
        mEdDigit3 = findViewById(R.id.edDigit3)
        mEdDigit4 = findViewById(R.id.edDigit4)
        mLlVerify = findViewById(R.id.llVerify)
        mTvResend = findViewById(R.id.tvResend)
        mTvTimer = findViewById(R.id.tvTimer)
        mEds = arrayOf(mEdDigit1!!, mEdDigit2!!, mEdDigit3!!, mEdDigit4!!)

        mIvBack = findViewById(R.id.ivBack)
    }

    /* initialize listener */
    private fun initializeListeners() {
        mIvBack!!.setOnClickListener(this)


        mEdDigit1!!.setOnKeyListener(PinOnKeyListener(0))
        mEdDigit2!!.setOnKeyListener(PinOnKeyListener(1))
        mEdDigit3!!.setOnKeyListener(PinOnKeyListener(2))
        mEdDigit4!!.setOnKeyListener(PinOnKeyListener(3))
        mEdDigit1!!.addTextChangedListener(CodeTextWatcher(0))
        mEdDigit2!!.addTextChangedListener(CodeTextWatcher(1))
        mEdDigit3!!.addTextChangedListener(CodeTextWatcher(2))
        mEdDigit4!!.addTextChangedListener(CodeTextWatcher(3))
        mLlVerify!!.setOnClickListener(this)

        object : CountDownTimer(60000, 1000) { // adjust the milli seconds here

            @SuppressLint("DefaultLocale")
            override fun onTick(millisUntilFinished: Long) {
                mTvTimer!!.text = String.format(
                    "%d seconds left",
                    TimeUnit.MILLISECONDS.toSeconds(millisUntilFinished) - TimeUnit.MINUTES.toSeconds(
                        TimeUnit.MILLISECONDS.toMinutes(millisUntilFinished)
                    )
                )
            }

            override fun onFinish() {
                hideView(mTvTimer!!)
                showView(mTvResend!!)
            }
        }.start()

        mEdDigit4!!.setOnEditorActionListener { v, _, _ ->
            startActivity(QIBusSoftUISoftUIDashboardActivity::class.java)
            true
        }

    }

    /* onClick listener */
    override fun onClick(v: View) {
        if (v === mIvBack) {
            onBackPressed()
        }
        if (v === mLlVerify) {
            //  if(validate()) {
            startActivity(QIBusSoftUISoftUIDashboardActivity::class.java)
            // }
        }

    }

    /* Validation */
    private fun validate(): Boolean {
        var flag = true
        if (TextUtils.isEmpty(mEdDigit1!!.text)) {
            flag = false
            showToast(getString(R.string.QIBus_softui_msg_code))
        } else if (TextUtils.isEmpty(mEdDigit2!!.text)) {
            flag = false
            showToast(getString(R.string.QIBus_softui_msg_code))
        } else if (TextUtils.isEmpty(mEdDigit3!!.text)) {
            flag = false
            showToast(getString(R.string.QIBus_softui_msg_code))
        } else if (TextUtils.isEmpty(mEdDigit4!!.text)) {
            flag = false
            showToast(getString(R.string.QIBus_softui_msg_code))
        }

        return flag
    }

    /* back space key handler*/
    inner class PinOnKeyListener internal constructor(private val mCurrentIndex: Int) :
        View.OnKeyListener {

        override fun onKey(v: View, keyCode: Int, event: KeyEvent): Boolean {
            if (keyCode == KeyEvent.KEYCODE_DEL && event.action == KeyEvent.ACTION_DOWN) {
                if (mEds!![mCurrentIndex].text.toString().isEmpty() && mCurrentIndex != 0) {
                    mEds!![mCurrentIndex - 1].requestFocus()

                }

            }
            return false
        }

    }

    /* implement TextWatcher class*/
    inner class CodeTextWatcher internal constructor(private val mCurrentIndex: Int) : TextWatcher {
        private var mIsFirst = false
        private var mIsLast = false
        private var mNewString = ""

        private val isAllEditTextsFilled: Boolean
            get() {
                for (editText in mEds!!)
                    if (editText.text.toString().trim { it <= ' ' }.isEmpty())
                        return false
                return true
            }

        init {

            if (mCurrentIndex == 0)
                this.mIsFirst = true
            else if (mCurrentIndex == mEds!!.size - 1)
                this.mIsLast = true
        }

        override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {

        }

        override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {
            mNewString = s.subSequence(start, start + count).toString().trim { it <= ' ' }
        }

        override fun afterTextChanged(s: Editable) {

            var text = mNewString

            if (text.length > 1)
                text = text[0].toString()

            mEds!![mCurrentIndex].removeTextChangedListener(this)
            mEds!![mCurrentIndex].setText(text)
            mEds!![mCurrentIndex].setSelection(text.length)
            mEds!![mCurrentIndex].addTextChangedListener(this)

            if (text.length == 1)
                moveToNext()
            else if (text.length == 0)
                moveToPrevious()
        }

        private fun moveToNext() {
            if (!mIsLast)
                mEds!![mCurrentIndex + 1].requestFocus()

            if (isAllEditTextsFilled && mIsLast) {
                mEds!![mCurrentIndex].clearFocus()
                hideKeyboard()
            }
        }

        private fun moveToPrevious() {
            if (!mIsFirst)
                mEds!![mCurrentIndex - 1].requestFocus()
        }

        private fun hideKeyboard() {
            if (currentFocus != null) {
                val inputMethodManager =
                    getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
                inputMethodManager.hideSoftInputFromWindow(currentFocus!!.windowToken, 0)
            }

        }
    }
}
