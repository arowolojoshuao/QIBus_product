package com.iqonicthemes.qibus_softui.ui.activity

import android.os.Bundle
import android.text.TextUtils
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import com.iqonicthemes.qibus_softui.QIBusSoftUIBaseActivity
import com.iqonicthemes.qibus_softui.R

class QIBusSoftUISoftUIHelpActivity : QIBusSoftUIBaseActivity(), View.OnClickListener {
    /*variable declaration*/
    private var mIvBack: ImageView? = null
    private var mIvNotification: ImageView? = null
    private var mEdContact: EditText? = null
    private var mEdEmail: EditText? = null
    private var mBtnSubmit: TextView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.qibus_softui_activity_help)

        initLayouts()
        initialzeListeners()
    }

    /* initialize listener */
    private fun initialzeListeners() {
        mIvBack!!.setOnClickListener(this)
        mBtnSubmit!!.setOnClickListener(this)
        mIvNotification!!.setOnClickListener(this)
        setTypeFace(mEdContact!!)
    }

    /* init layout */
    private fun initLayouts() {
        mIvBack = findViewById(R.id.ivBack)
        mEdContact = findViewById(R.id.edContact)
        mEdEmail = findViewById(R.id.edEmail)
        mIvNotification = findViewById(R.id.ivNotification)
        mBtnSubmit = findViewById(R.id.btnSubmit)
        mBtnSubmit!!.stateListAnimator = null
        SetNotificationImage(mIvNotification!!)
    }

    /* onClick listener */
    override fun onClick(v: View) {
        if (v === mIvBack)
            onBackPressed()
        else if (v === mBtnSubmit) {
            // if (validate()) {
            onBackPressed()
            // }
        } else if (v === mIvNotification) startActivity(QIBusSoftUISoftUINotificationActivity::class.java)
    }

    /* validations */
    private fun validate(): Boolean {
        var flag = true
        if (TextUtils.isEmpty(mEdEmail!!.text)) {
            flag = false
            showToast(getString(R.string.QIBus_softui_msg_email_required))
        }
        return flag
    }
}