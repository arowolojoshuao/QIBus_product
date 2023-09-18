package com.iqonicthemes.qibus_softui.utils.menu

import com.iqonicthemes.qibus_softui.model.QIBusSoftUISeatModel

abstract class QIBusSoftUIAbstractItem {
    lateinit var mSoftUISeatModelList: List<QIBusSoftUISeatModel>
    private var mLabel: String=""

    abstract val type: Int

    /*constructor*/

    constructor(aLabel: String) {
        this.mLabel = aLabel
    }

    constructor(aSoftUISeatModelList: List<QIBusSoftUISeatModel>) {
        this.mSoftUISeatModelList = aSoftUISeatModelList
    }

    /*getter*/

    fun getmLabel(): String {
        return mLabel
    }

    companion object {

        /*variable declaration*/

        val TYPE_CENTER = 0
        val TYPE_EDGE = 1
        val TYPE_EMPTY = 2
    }

}
