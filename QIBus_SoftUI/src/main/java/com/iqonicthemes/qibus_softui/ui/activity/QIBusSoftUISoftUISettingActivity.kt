package com.iqonicthemes.qibus_softui.ui.activity


import android.os.Bundle
import android.view.View
import android.widget.ImageView
import com.iqonicthemes.qibus_softui.QIBusSoftUIBaseActivity
import com.iqonicthemes.qibus_softui.R

class QIBusSoftUISoftUISettingActivity : QIBusSoftUIBaseActivity(), View.OnClickListener {
    /*variable declaration*/
    private var mIvBack: ImageView? = null
    private var mIvNotification: ImageView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.qibus_softui_activity_setting)
        initLayouts()
        initializeListeners()
    }

    /* initialize listener */
    private fun initializeListeners() {

        mIvBack!!.setOnClickListener(this)
        mIvNotification!!.setOnClickListener(this)
        SetNotificationImage(mIvNotification!!)
    }

    /* init layout */
    private fun initLayouts() {
        mIvBack = findViewById(R.id.ivBack)
        mIvNotification = findViewById(R.id.ivNotification)

    }

    /* onClick listener */
    override fun onClick(v: View) {

        if (v === mIvBack) {
            onBackPressed()
        } else if (v === mIvNotification) {
            startActivity(QIBusSoftUISoftUINotificationActivity::class.java)
        }
    }
}
