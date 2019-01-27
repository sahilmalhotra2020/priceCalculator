package com.app.price.pricing.impl;

import com.app.price.entity.Market;
import com.app.price.pricing.MarketUpdate;
import com.app.price.pricing.TwoWayPrice;

public class MarketUpdateImpl implements MarketUpdate {

    private final Market market;
    private final TwoWayPrice twoWayPrice;

    public MarketUpdateImpl(Market market, TwoWayPrice twoWayPrice) {
        this.market = market;
        this.twoWayPrice = twoWayPrice;
    }

    @Override
    public Market getMarket() {
        return market;
    }

    @Override
    public TwoWayPrice getTwoWayPrice() {
        return twoWayPrice;
    }
}
