package com.iqonicthemes.qibus_softui.ui.activity

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import android.widget.TextView

import com.iqonicthemes.qibus_softui.QIBusSoftUIBaseActivity
import com.iqonicthemes.qibus_softui.R

class QIBusSoftUISoftUIReferEarnActivity : QIBusSoftUIBaseActivity(), View.OnClickListener {

    /*variable declaration*/
    private var mTvLink: TextView? = null
    private var mTvCode: TextView? = null
    private var mIvBack: ImageView? = null
    private var mIvNotification: ImageView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.qibus_softui_activity_refer_earn)

        initLayouts()
        initializeListeners()
    }

    /* init layout */
    private fun initLayouts() {
        mIvBack = findViewById(R.id.ivBack)
        mTvLink = findViewById(R.id.tvLink)
        mTvCode = findViewById(R.id.tvCode)
        mIvNotification = findViewById(R.id.ivNotification)

    }

    /* initialize listener */
    private fun initializeListeners() {
        mIvBack!!.setOnClickListener(this)
        mTvCode!!.setOnClickListener(this)
        mIvNotification!!.setOnClickListener(this)
        mTvLink!!.text = getString(R.string.QIBus_softui_text_link)
        SetNotificationImage(mIvNotification!!)
    }

    /* onClick listener */
    override fun onClick(v: View) {
        if (v === mIvBack)
            onBackPressed()
        else if (v === mTvCode)
            showToast(getString(R.string.QIBus_softui_txt_copy))
        else if (v === mIvNotification) {
            startActivity(QIBusSoftUISoftUINotificationActivity::class.java)
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
