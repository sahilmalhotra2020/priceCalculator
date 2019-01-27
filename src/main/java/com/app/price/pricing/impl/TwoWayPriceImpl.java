package com.app.price.pricing.impl;

import com.app.price.entity.Instrument;
import com.app.price.entity.State;
import com.app.price.pricing.TwoWayPrice;

public class TwoWayPriceImpl implements TwoWayPrice {

    private final Instrument instrument;
    private final State state;
    private final double bidPrice;
    private final double offerAmount;
    private final double offerPrice;
    private final double bidAmount;

    public TwoWayPriceImpl(Instrument instrument, State state, double bidPrice, double offerAmount, double offerPrice, double bidAmount) {
        this.instrument = instrument;
        this.state = state;
        this.bidPrice = bidPrice;
        this.offerAmount = offerAmount;
        this.offerPrice = offerPrice;
        this.bidAmount = bidAmount;
    }

    @Override
    public Instrument getInstrument() {
        return instrument;
    }

    @Override
    public State getState() {
        return state;
    }

    @Override
    public double getBidPrice() {
        return bidPrice;
    }

    @Override
    public double getOfferAmount() {
        return offerAmount;
    }

    @Override
    public double getOfferPrice() {
        return offerPrice;
    }

    @Override
    public double getBidAmount() {
        return bidAmount;
    }
}
