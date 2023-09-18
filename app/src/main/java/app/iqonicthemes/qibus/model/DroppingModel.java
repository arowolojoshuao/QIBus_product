package app.iqonicthemes.qibus.model;

public class DroppingModel {

    /*variable declaration*/

    private String mTravelName, mLocation, mDuration;

    /*constructor*/

    public DroppingModel(String aTravelName, String aLocation, String aDuration) {
        this.mTravelName = aTravelName;
        this.mLocation = aLocation;
        this.mDuration = aDuration;
    }

    /*getter*/

    public String getTravelName() {
        return mTravelName;
    }

    public String getLoction() {
        return mLocation;
    }

    public String getDuration() {
        return mDuration;
    }

}
