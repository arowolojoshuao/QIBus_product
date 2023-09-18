package app.iqonicthemes.qibus.model;

import java.io.Serializable;

public class CardModel implements Serializable {

    /*variable declaration*/

    private int mCardbg;
    private String mDigit1, mDigit2, mDigit3, mDigit4, mHolderName, mValidDate, mCardType;

    /*constructor*/

    public CardModel(String aCardType, int aCardbg, String aDigit1, String aDigit2, String aDigit3, String aDigit4, String aValid, String aHolderName) {
        this.mCardType = aCardType;
        this.mCardbg = aCardbg;
        this.mDigit1 = aDigit1;
        this.mDigit2 = aDigit2;
        this.mDigit3 = aDigit3;
        this.mDigit4 = aDigit4;
        this.mValidDate = aValid;
        this.mHolderName = aHolderName;
    }

    /*getter */

    public int getCardbg() {
        return mCardbg;
    }

    public String getCardtype() {
        return mCardType;
    }


    public String getTxtDigit1() {
        return mDigit1;
    }


    public String getTxtDigit2() {
        return mDigit2;
    }


    public String getTxtDigit3() {
        return mDigit3;
    }


    public String getTxtDigit4() {
        return mDigit4;
    }


    public String getTxtHolderName() {
        return mHolderName;
    }


    public String getmValidDate() {
        return mValidDate;
    }

}
