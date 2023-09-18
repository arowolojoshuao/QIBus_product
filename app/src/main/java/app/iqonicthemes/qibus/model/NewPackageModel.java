package app.iqonicthemes.qibus.model;

import java.io.Serializable;

public class NewPackageModel implements Serializable {

    /*variable declaration*/

    private String mDestination, mDuration, mRating, mNewPrice, mBooking;
    private int mImage;

    /*constructor*/

    public NewPackageModel(String aDestination, String aDuration, String aRating, String aNewPrice, String aBooking, int aImage) {
        this.mDestination = aDestination;
        this.mDuration = aDuration;
        this.mRating = aRating;
        this.mNewPrice = aNewPrice;
        this.mBooking = aBooking;
        this.mImage = aImage;
    }

    /*getter*/

    public String getDestination() {
        return mDestination;
    }

    public String getDuration() {
        return mDuration;
    }

    public String getRating() {
        return mRating;
    }

    public String getNewprice() {
        return mNewPrice;
    }

    public String getBooking() {
        return mBooking;
    }

    public int getImage() {
        return mImage;
    }

}
