package com.iqonicthemes.qibus_softui.model

import com.iqonicthemes.qibus_softui.utils.menu.QIBusSoftUIAbstractItem

class QIBusSoftUISoftUIEmptyItem
/*constructor*/
(aSoftUISeatModelList: List<QIBusSoftUISeatModel>) : QIBusSoftUIAbstractItem(aSoftUISeatModelList) {

    /*getter*/
    override val type: Int
        get() = QIBusSoftUIAbstractItem.TYPE_EMPTY

}
