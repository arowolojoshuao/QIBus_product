package com.iqonicthemes.qibus_softui.model

class QIBusSoftUIBookingModel {

    /*variable declaration*/

    var destination: String? = null
        private set
    var duration: String? = null
        private set
    var startTime: String? = null
        private set
    var totalTime: String? = null
        private set
    var endTime: String? = null
        private set
    var seatNo: String? = null
        private set
    var passengerName: String? = null
        private set
    var ticketNo: String? = null
        private set
    var pnrNo: String? = null
        private set
    var status: String? = null
        private set
    var totalFare: String? = null
        private set
    /*getter*/

    var date: String = ""
    var month: String=""

    /*constructor*/

    constructor(aDestination: String, aDuration: String, aMonth: String, aStartTime: String, aTotalTime: String, aEndTime: String, aSeatNo: String, aPassengerName: String, aTicketNo: String, aPNRNo: String, aTotalFare: String, aStatus: String) {
        this.destination = aDestination
        this.duration = aDuration
        this.startTime = aStartTime
        this.totalTime = aTotalTime
        this.endTime = aEndTime
        this.seatNo = aSeatNo
        this.passengerName = aPassengerName
        this.ticketNo = aTicketNo
        this.pnrNo = aPNRNo
        this.totalFare = aTotalFare
        this.month = aMonth
        this.status = aStatus
    }

    constructor(aDestination: String, aDuration: String, aStartTime: String, aTotalTime: String, aEndTime: String, aSeatNo: String, aPassengerName: String, aTicketNo: String, aPNRNo: String, aTotalFare: String, aStatus: String) {
        this.destination = aDestination
        this.duration = aDuration
        this.startTime = aStartTime
        this.totalTime = aTotalTime
        this.endTime = aEndTime
        this.seatNo = aSeatNo
        this.passengerName = aPassengerName
        this.ticketNo = aTicketNo
        this.pnrNo = aPNRNo
        this.totalFare = aTotalFare
        this.status = aStatus
    }

    constructor(aDestination: String, aDate: String) {
        this.destination = aDestination
        this.date = aDate
    }
}
