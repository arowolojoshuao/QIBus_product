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


class QIBusSoftUISoftUIProfileSettingsActivity : QIBusSoftUIBaseActivity(), View.OnClickListener {

    /*variable declaration*/
    private var mIvBack: ImageView? = null
    private var mIvNotification: ImageView? = null
    private var mIvAddProfile: ImageView? = null
    private var mBtnSave: TextView? = null
    private var mEdFirstName: EditText? = null
    private var mEdLastName: EditText? = null
    private var mEdEmail: EditText? = null
    private var mEdContact: EditText? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.qibus_softui_activity_profile)

        initLayouts()
        initializeListeners()

    }

    /* initialize listener */
    private fun initializeListeners() {
        mIvBack!!.setOnClickListener(this)
        mBtnSave!!.setOnClickListener(this)
        mIvNotification!!.setOnClickListener(this)
        mIvAddProfile!!.setOnClickListener(this)
        mBtnSave!!.stateListAnimator = null
    }

    /* init layout */
    private fun initLayouts() {
        mIvBack = findViewById(R.id.ivBack)
        mBtnSave = findViewById(R.id.btnSave)
        mEdFirstName = findViewById(R.id.edFirstName)
        mEdLastName = findViewById(R.id.edLastName)
        mEdEmail = findViewById(R.id.edEmail)
        mEdContact = findViewById(R.id.edContact)
        mIvAddProfile = findViewById(R.id.ivAddProfile)
        mIvNotification = findViewById(R.id.ivNotification)
        SetNotificationImage(mIvNotification!!)
    }

    /* onClick listener */
    override fun onClick(v: View) {
        if (v === mIvBack)
            onBackPressed()
        else if (v === mIvNotification)
            startActivity(QIBusSoftUISoftUINotificationActivity::class.java)
        else if (v === mBtnSave) {
            //  if (validate()) {
            showToast(getString(R.string.QIBus_softui_msg_saved))
            //  }
        }
    }

    /* validations */
    private fun validate(): Boolean {
        var flag = true
        if (TextUtils.isEmpty(mEdFirstName!!.text)) {
            flag = false
            showToast(getString(R.string.QIBus_softui_msg_first_name))
        } else if (TextUtils.isEmpty(mEdLastName!!.text)) {
            flag = false
            showToast(getString(R.string.QIBus_softui_msg_last_name))
        } else if (TextUtils.isEmpty(mEdEmail!!.text)) {
            flag = false
            showToast(getString(R.string.QIBus_softui_msg_email_required))
        } else if (TextUtils.isEmpty(mEdContact!!.text)) {
            flag = false
            showToast(getString(R.string.QIBus_softui_msg_mobile_number))
        }

        return flag
    }


}
