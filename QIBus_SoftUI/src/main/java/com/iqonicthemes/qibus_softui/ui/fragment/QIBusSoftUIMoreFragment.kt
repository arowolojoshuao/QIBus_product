package com.iqonicthemes.qibus_softui.ui.fragment

import android.app.AlertDialog
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView

import com.iqonicthemes.qibus_softui.QIBusSoftUIBaseActivity
import com.iqonicthemes.qibus_softui.R
import com.iqonicthemes.qibus_softui.ui.activity.QIBusSoftUISoftUICardsActivity
import com.iqonicthemes.qibus_softui.ui.activity.QIBusSoftUISoftUIHelpActivity
import com.iqonicthemes.qibus_softui.ui.activity.QIBusSoftUISoftUIProfileSettingsActivity
import com.iqonicthemes.qibus_softui.ui.activity.QIBusSoftUISoftUIReferEarnActivity
import com.iqonicthemes.qibus_softui.ui.activity.QIBusSoftUISoftUISettingActivity
import com.iqonicthemes.qibus_softui.ui.activity.QIBusSoftUISoftUISignInActivity
import com.iqonicthemes.qibus_softui.ui.activity.QIBusSoftUISoftUIWalletActivity
import com.iqonicthemes.qibus_softui.utils.QIBusSoftUIConstants
import androidx.fragment.app.Fragment

class QIBusSoftUIMoreFragment : Fragment(), View.OnClickListener {
    private var mTvProfileSettings: TextView? = null
    private var mTvWallet: TextView? = null
    private var mTvCards: TextView? = null
    private var mTvReferEarn: TextView? = null
    private var mTvHelp: TextView? = null
    private var mTvLogout: TextView? = null
    private var mTvSetting: TextView? = null
    private val mFlag = "1"

    /* create view */
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.qibus_softui_fragment_more, null)
        initLayouts(view)
        initializeListeners()
        return view
    }

    /* initialize listener */
    private fun initializeListeners() {
        mTvProfileSettings!!.setOnClickListener(this)
        mTvWallet!!.setOnClickListener(this)
        mTvCards!!.setOnClickListener(this)
        mTvReferEarn!!.setOnClickListener(this)
        mTvHelp!!.setOnClickListener(this)
        mTvLogout!!.setOnClickListener(this)
        mTvSetting!!.setOnClickListener(this)
    }

    /* init layout */
    private fun initLayouts(view: View) {
        mTvProfileSettings = view.findViewById(R.id.tvProfileSettings)
        mTvWallet = view.findViewById(R.id.tvWallet)
        mTvCards = view.findViewById(R.id.tvCards)
        mTvReferEarn = view.findViewById(R.id.tvReferEarn)
        mTvHelp = view.findViewById(R.id.tvHelp)
        mTvLogout = view.findViewById(R.id.tvLogout)
        mTvSetting = view.findViewById(R.id.tvSetting)
    }

    /* onClick listener */
    override fun onClick(v: View) {
        if (v === mTvProfileSettings)
            (activity as QIBusSoftUIBaseActivity).startActivity(
                QIBusSoftUISoftUIProfileSettingsActivity::class.java
            )
        else if (v === mTvWallet)
            (activity as QIBusSoftUIBaseActivity).startActivity(QIBusSoftUISoftUIWalletActivity::class.java)
        else if (v === mTvCards) {
            val intent = Intent(activity, QIBusSoftUISoftUICardsActivity::class.java)
            intent.putExtra(QIBusSoftUIConstants.intentdata.CARDFLAG, mFlag)
            startActivity(intent)
        } else if (v === mTvReferEarn)
            (activity as QIBusSoftUIBaseActivity).startActivity(QIBusSoftUISoftUIReferEarnActivity::class.java)
        else if (v === mTvHelp)
            (activity as QIBusSoftUIBaseActivity).startActivity(QIBusSoftUISoftUIHelpActivity::class.java)
        else if (v === mTvSetting)
            (activity as QIBusSoftUIBaseActivity).startActivity(QIBusSoftUISoftUISettingActivity::class.java)
        else if (v === mTvLogout) {
            val builder = AlertDialog.Builder(activity)
            builder.setTitle(getString(R.string.QIBus_softui_text_confirmation))
                .setMessage(getString(R.string.QIBus_softui_msg_logout))
            builder.setPositiveButton(
                getString(R.string.QIBus_softui_text_yes)
            ) { dialog, id ->
                startActivity(
                    Intent(
                        activity,
                        QIBusSoftUISoftUISignInActivity::class.java
                    )
                )
            }
            builder.setNegativeButton(
                getString(R.string.QIBus_softui_text_no)
            ) { dialog, id -> dialog.cancel() }
            val alert = builder.create()
            alert.show()
        }
    }

    companion object {

        /*variable declaration*/
        val mTitle = "More"
    }
}
