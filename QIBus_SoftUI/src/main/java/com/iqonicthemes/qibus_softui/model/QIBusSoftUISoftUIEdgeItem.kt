package com.iqonicthemes.qibus_softui.model

import com.iqonicthemes.qibus_softui.utils.menu.QIBusSoftUIAbstractItem

class QIBusSoftUISoftUIEdgeItem/*constructor*/
(aValueOf: String) : QIBusSoftUIAbstractItem(aValueOf) {

    /*getter*/
    override val type: Int
        get() = QIBusSoftUIAbstractItem.TYPE_EDGE

}
