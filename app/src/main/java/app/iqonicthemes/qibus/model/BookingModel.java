package app.iqonicthemes.qibus.model;

public class BookingModel {

    /*variable declaration*/

    private String mDestination;
    private String mDuration;
    private String mStartTime;
    private String mTotalTime;
    private String mEndTime;
    private String mSeatNo;
    private String mPassengerName;
    private String mTicketNo;
    private String mPNRNo;
    private String mStatus;
    private String mTotalFare;
    private String mDate;
    private String mMonth;

    /*constructor*/

    public BookingModel(String aDestination, String aDuration, String aMonth, String aStartTime, String aTotalTime, String aEndTime, String aSeatNo, String aPassengerName, String aTicketNo, String aPNRNo, String aTotalFare, String aStatus) {
        this.mDestination = aDestination;
        this.mDuration = aDuration;
        this.mStartTime = aStartTime;
        this.mTotalTime = aTotalTime;
        this.mEndTime = aEndTime;
        this.mSeatNo = aSeatNo;
        this.mPassengerName = aPassengerName;
        this.mTicketNo = aTicketNo;
        this.mPNRNo = aPNRNo;
        this.mTotalFare = aTotalFare;
        this.mMonth = aMonth;
        this.mStatus = aStatus;
    }

    public BookingModel(String aDestination, String aDuration, String aStartTime, String aTotalTime, String aEndTime, String aSeatNo, String aPassengerName, String aTicketNo, String aPNRNo, String aTotalFare, String aStatus) {
        this.mDestination = aDestination;
        this.mDuration = aDuration;
        this.mStartTime = aStartTime;
        this.mTotalTime = aTotalTime;
        this.mEndTime = aEndTime;
        this.mSeatNo = aSeatNo;
        this.mPassengerName = aPassengerName;
        this.mTicketNo = aTicketNo;
        this.mPNRNo = aPNRNo;
        this.mTotalFare = aTotalFare;
        this.mStatus = aStatus;
    }

    public BookingModel(String aDestination, String aDate) {
        this.mDestination = aDestination;
        this.mDate = aDate;
    }

    /*getter*/

    public String getDate() {
        return mDate;
    }

    public String getStatus() {
        return mStatus;
    }

    public String getDestination() {
        return mDestination;
    }

    public String getDuration() {
        return mDuration;
    }

    public String getStartTime() {
        return mStartTime;
    }

    public String getTotalTime() {
        return mTotalTime;
    }

    public String getEndTime() {
        return mEndTime;
    }

    public String getSeatNo() {
        return mSeatNo;
    }

    public String getTicketNo() {
        return mTicketNo;
    }

    public String getPNRNo() {
        return mPNRNo;
    }

    public String getTotalFare() {
        return mTotalFare;
    }

    public String getPassengerName() {
        return mPassengerName;
    }

    public String getMonth() {
        return mMonth;
    }
}
