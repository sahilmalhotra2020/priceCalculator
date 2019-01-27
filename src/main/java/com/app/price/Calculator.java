package com.app.price;

import com.app.price.pricing.MarketUpdate;
import com.app.price.pricing.TwoWayPrice;

public interface Calculator {
    TwoWayPrice applyMarketUpdate(final MarketUpdate twoWayMarketPrice);
}
