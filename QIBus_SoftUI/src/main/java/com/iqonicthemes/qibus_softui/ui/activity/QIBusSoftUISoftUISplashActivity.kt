package com.iqonicthemes.qibus_softui.ui.activity

import android.os.Bundle
import android.os.Handler
import android.view.WindowManager
import android.widget.ImageView
import com.iqonicthemes.qibus_softui.QIBusSoftUIBaseActivity
import com.iqonicthemes.qibus_softui.R

class QIBusSoftUISoftUISplashActivity : QIBusSoftUIBaseActivity() {

    /*variable declaration*/
    private var mIVLogo: ImageView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        window.addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN)
        setContentView(R.layout.qibus_softui_activity_splash)

        initLayouts()
        initializeListeners()
    }

    /* init layout */
    private fun initLayouts() {
        mIVLogo = findViewById(R.id.ivLogo)
    }

    /* initialize listener */
    private fun initializeListeners() {
        Handler().postDelayed(
            {
                startActivity(QIBusSoftUISoftUISignInActivity::class.java)
                onBackPressed()
            },
            2000
        )
    }
}
