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

        MarketUpdate marketUpdate1 = new MarketUpdateImpl(Market.MARKET1,new TwoWayPriceImpl(Instrument.INSTRUMENT1, State.FIRM, 10,100, 11, 500)) ;
        TwoWayPrice twoWayPrice = calculator.applyMarketUpdate(marketUpdate1);
        assertEquals("[INSTRUMENT1, bid_wvap=0.10, bid_amount=100.00, offer_wvap=0.02, offer_amount=500.00]", format(twoWayPrice));

        MarketUpdate marketUpdate2 = new MarketUpdateImpl(Market.MARKET2, new TwoWayPriceImpl(Instrument.INSTRUMENT1, State.FIRM, 10,100, 11, 500));
        twoWayPrice = calculator.applyMarketUpdate(marketUpdate2);
        assertEquals("[INSTRUMENT1, bid_wvap=0.10, bid_amount=100.00, offer_wvap=0.02, offer_amount=500.00]", format(twoWayPrice));

        MarketUpdate marketUpdate3 = new MarketUpdateImpl(Market.MARKET1, new TwoWayPriceImpl(Instrument.INSTRUMENT1, State.FIRM, 10,200, 50, 500));
        twoWayPrice = calculator.applyMarketUpdate(marketUpdate3);
        assertEquals("[INSTRUMENT1, bid_wvap=6.70, bid_amount=300.00, offer_wvap=25.01, offer_amount=1000.00]", format(twoWayPrice));
    }


    @Test
    public void shouldReturnZeroForZeroDenominator() {

        Calculator calculator = new CalculatorImpl();

        MarketUpdate marketUpdate1 = new MarketUpdateImpl(Market.MARKET1,new TwoWayPriceImpl(Instrument.INSTRUMENT1, State.FIRM, 10,0, 11, 0)) ;
        TwoWayPrice twoWayPrice = calculator.applyMarketUpdate(marketUpdate1);
        assertTrue(twoWayPrice.getBidAmount() == 0);
        assertTrue(twoWayPrice.getOfferAmount() == 0);


    }


    private static String format(TwoWayPrice twp) {
        return String.format("[%s, bid_wvap=%.2f, bid_amount=%.2f, offer_wvap=%.2f, offer_amount=%.2f]",
                twp.getInstrument(), twp.getBidPrice(), twp.getBidAmount(), twp.getOfferPrice(), twp.getOfferAmount());
    }

}