package com.app.price.pricing;

import com.app.price.entity.Market;

public interface MarketUpdate {
    Market getMarket();
    TwoWayPrice getTwoWayPrice();
}
