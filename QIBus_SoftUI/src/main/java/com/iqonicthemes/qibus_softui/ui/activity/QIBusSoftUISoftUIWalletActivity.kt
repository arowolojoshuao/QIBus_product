package com.iqonicthemes.qibus_softui.ui.activity

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import com.iqonicthemes.qibus_softui.QIBusSoftUIBaseActivity
import com.iqonicthemes.qibus_softui.R

class QIBusSoftUISoftUIWalletActivity : QIBusSoftUIBaseActivity(), View.OnClickListener {

    /*variable declaration*/
    private var mIvBack: ImageView? = null
    private var mIvNotification: ImageView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.qibus_softui_activity_wallet)
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

        when {
            v === mIvBack -> onBackPressed()
            v === mIvNotification -> startActivity(QIBusSoftUISoftUINotificationActivity::class.java)
        }
    }

    /* share cricketdata */
    private fun sharedata() {
        val sendIntent = Intent()
        sendIntent.action = Intent.ACTION_SEND
        sendIntent.putExtra(Intent.EXTRA_TEXT, R.string.QIBus_softui_msg_shareapp)
        sendIntent.type = "text/plain"
        startActivity(
            Intent.createChooser(
                sendIntent,
                resources.getText(R.string.QIBus_softui_send_to)
            )
        )
    }
}

