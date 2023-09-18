package com.iqonicthemes.qibus_softui.model

import java.io.Serializable

class QIBusSoftUICardModel
/*constructor*/
(val cardtype: String,
        /*variable declaration*/

        /*getter */

 val cardbg: Int, val txtDigit1: String, val txtDigit2: String, val txtDigit3: String, val txtDigit4: String, private val mValidDate: String, val txtHolderName: String) : Serializable {


    fun getmValidDate(): String {
        return mValidDate
    }

}
