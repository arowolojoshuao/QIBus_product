package com.iqonicthemes.qibus_softui.utils

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.widget.TextView
import android.widget.Toast

import com.iqonicthemes.qibus_softui.R


class QIBusSoftUICustomToast/*constructor*/
(/*variable declaration*/
        private val mCtx: Context) : Toast(mCtx) {

    override fun setView(view: View) {
        super.setView(view)
    }

    /*set custom view*/
    fun setCustomView(message: String) {
        val inflater = mCtx.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        val view = inflater.inflate(R.layout.qibus_softui_custom_toast, null)
        val mTvToastText = view.findViewById<TextView>(R.id.tvToastMessage)
        mTvToastText.text = message
        setView(view)
    }
}