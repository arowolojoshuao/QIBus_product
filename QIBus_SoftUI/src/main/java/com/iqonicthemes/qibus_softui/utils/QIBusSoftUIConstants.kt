package com.iqonicthemes.qibus_softui.utils

import java.text.SimpleDateFormat
import java.util.Locale

object QIBusSoftUIConstants {
    /*variable declaration*/
    val NA = "N/A"

    /*Date format*/
    object DateFormat {
        var dd_MM = "dd-MMM"
        var dd_MM_yyyy = "dd - MMM - yyyy"
        val DAY_MONTH_FORMATTER = SimpleDateFormat(dd_MM, Locale.getDefault())
        val DAY_MONTH_YEAR_FORMATTER = SimpleDateFormat(dd_MM_yyyy, Locale.getDefault())

    }

    /*intent cricketdata*/
    object intentdata {
        var CARDDETAIL = "carddetail"
        var TRAVELLERNAME = "TravellerName"
        var TYPECOACH = "typecoach"
        var PRICE = "price"
        var HOLD = "hold"
        var PACKAGE = "package"
        var OFFER = "offer"
        var TRIP_KEY = "trip_key"
        var SEARCH_BUS = "search_bus"
        var PACKAGE_NAME = "package_name"
        var CARDFLAG = "cardflag"
    }

}
