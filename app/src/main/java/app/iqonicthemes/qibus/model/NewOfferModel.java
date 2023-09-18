package app.iqonicthemes.qibus.model;

import java.io.Serializable;

public class NewOfferModel implements Serializable {

    /*variable declaration*/

    private String aUseCode;

    /*constructor*/

    public NewOfferModel(String aUseCode) {

        this.aUseCode = aUseCode;
    }

    /*getter*/
    public String getUsecode() {
        return aUseCode;
    }

}
