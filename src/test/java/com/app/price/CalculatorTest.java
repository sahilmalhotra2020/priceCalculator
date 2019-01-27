package com.app.price;

import com.app.price.entity.Instrument;
import com.app.price.entity.Market;
import com.app.price.entity.State;
import com.app.price.pricing.MarketUpdate;
import com.app.price.pricing.TwoWayPrice;
import com.app.price.pricing.impl.MarketUpdateImpl;
import com.app.price.pricing.impl.TwoWayPriceImpl;
import org.junit.Test;

import static org.junit.Assert.*;

public class CalculatorTest {

    @Test
    public void applyMarketUpdateTest() {

        Calculator calculator = new CalculatorImpl();

        MarketUpdate marketUpdate1 = new MarketUpdateImpl(getMarket1(), getTwoWayPrice1()) ;
        TwoWayPrice twoWayPrice = calculator.applyMarketUpdate(marketUpdate1);
        assertEquals("[INSTRUMENT1, bid_wvap=10.00, bid_amount=200.00, offer_wvap=11.00, offer_amount=1000.00]", format(twoWayPrice));
    }

    private TwoWayPrice getTwoWayPrice1() {
        return new TwoWayPriceImpl(Instrument.INSTRUMENT1, State.FIRM, 10,100, 11, 500);

    }

    private Market getMarket1() {
        return Market.MARKET1;
    }

    private static String format(TwoWayPrice twp) {
        return String.format("[%s, bid_wvap=%.2f, bid_amount=%.2f, offer_wvap=%.2f, offer_amount=%.2f]",
                twp.getInstrument(), twp.getBidPrice(), twp.getBidAmount(), twp.getOfferPrice(), twp.getOfferAmount());
    }

}