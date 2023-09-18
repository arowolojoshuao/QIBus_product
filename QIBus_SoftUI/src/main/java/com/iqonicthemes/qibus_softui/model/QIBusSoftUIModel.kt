package com.iqonicthemes.qibus_softui.model

import java.io.Serializable

class QIBusSoftUIModel
/*constructor*/
(
        /*variable declaration*/

        val travelerName: String,
        /*getter*/

        val starttime: String, private val mStartTimeAA: String, val enddtime: String, private val mEndTimeAA: String, val totalDuration: String, val hold: String, val type_coach: String, val rate: Int, val price: String) : Serializable {

    fun getmStartTimeAA(): String {
        return mStartTimeAA
    }

    fun getmEndTimeAA(): String {
        return mEndTimeAA
    }
}
