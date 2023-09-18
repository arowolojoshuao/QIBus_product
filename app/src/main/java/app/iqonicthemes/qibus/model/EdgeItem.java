package app.iqonicthemes.qibus.model;

import app.iqonicthemes.qibus.utils.menu.AbstractItem;

public class EdgeItem extends AbstractItem {


    /*constructor*/
    public EdgeItem(String aValueOf) {
        super(aValueOf);
    }

    /*getter*/
    @Override
    public int getType() {
        return TYPE_EDGE;
    }

}
