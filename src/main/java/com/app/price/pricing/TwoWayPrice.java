package com.app.price.pricing;

import com.app.price.entity.Instrument;
import com.app.price.entity.State;

public interface TwoWayPrice {
    Instrument getInstrument();
    State getState();
    double getBidPrice();
    double getOfferAmount();
    double getOfferPrice();
    double getBidAmount();
}
