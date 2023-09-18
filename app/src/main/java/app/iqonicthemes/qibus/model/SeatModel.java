package app.iqonicthemes.qibus.model;

public class SeatModel {

    /*variable declaration*/

    private SeatType mSeatType;
    private boolean mIsSelected =false;

    /*constructor*/

    public SeatModel(SeatType aType) {

        this.mSeatType = aType;
    }

    /*getter*/

    public boolean isSelected() {
        return mIsSelected;
    }

    public SeatType getSeatType() {
        return mSeatType;
    }

    /*setter*/
    public void setSelected(boolean selected) {
        mIsSelected = selected;
    }
}
