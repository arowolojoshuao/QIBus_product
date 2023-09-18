package app.iqonicthemes.qibus.model;

import java.io.Serializable;

public class BusModel implements Serializable {

    /*variable declaration*/

    private String mTravelerName;
    private int mRate;
    private String mTypeCoach;
    private String mPrice;
    private String mStarTime;
    private String mEndTime;
    private String mTotalDuration;
    private String mHold;
    private String mStartTimeAA;
    private String mEndTimeAA;

    /*constructor*/

    public BusModel(String aTravelerName, String aStartTime,String aStartTimeAA, String aEndTime,String aEndTimeAA, String aTotalDuration, String aHold, String aTypeCoach, int aRate, String aPrice) {
        this.mTravelerName = aTravelerName;
        this.mStarTime = aStartTime;
        this.mStartTimeAA = aStartTimeAA;
        this.mEndTimeAA = aEndTimeAA;
        this.mEndTime = aEndTime;
        this.mTypeCoach = aTypeCoach;
        this.mRate = aRate;
        this.mPrice = aPrice;
        this.mTotalDuration = aTotalDuration;
        this.mHold = aHold;

    }

    /*getter*/

    public String getStarttime() {
        return mStarTime;
    }

    public String getEnddtime() {
        return mEndTime;
    }

    public String getTotalDuration() {
        return mTotalDuration;
    }

    public String getHold() {
        return mHold;
    }

    public String getTravelerName() {
        return mTravelerName;
    }

    public String getPrice() {
        return mPrice;
    }

    public String getType_coach() {
        return mTypeCoach;
    }

    public int getRate() {
        return mRate;
    }

    public String getmStartTimeAA() { return mStartTimeAA; }

    public String getmEndTimeAA() { return mEndTimeAA; }
}
