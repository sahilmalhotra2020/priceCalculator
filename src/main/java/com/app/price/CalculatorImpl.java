package com.app.price;

import com.app.price.entity.State;
import com.app.price.pricing.MarketUpdate;
import com.app.price.pricing.TwoWayPrice;
import com.app.price.pricing.impl.TwoWayPriceImpl;

import java.util.HashMap;
import java.util.Map;

public class CalculatorImpl implements Calculator {

    // Map of Market which will hold details od its instruments stored based on Instrument Name.
    private Map<String, Map<String, InstrumentFraction>> marketInstrumentMap = new HashMap<>();

    @Override
    public TwoWayPrice applyMarketUpdate(MarketUpdate twoWayMarketPrice) {
        if (null != twoWayMarketPrice && twoWayMarketPrice.getMarket() == null) throw new IllegalArgumentException("Market cannot be null");
        if (null != twoWayMarketPrice &&  twoWayMarketPrice.getTwoWayPrice() == null) throw new IllegalArgumentException("TwoWayPrice cannot be null");
        if (null != twoWayMarketPrice &&  twoWayMarketPrice.getTwoWayPrice().getInstrument() == null) throw new IllegalArgumentException("TwoWayPrice. Instrument cannot be null");

        TwoWayPrice price = twoWayMarketPrice.getTwoWayPrice();

        String marketName = twoWayMarketPrice.getMarket().name();
        String instrumentName = price.getInstrument().name();

        marketInstrumentMap.compute(marketName, (k,v) -> {
            InstrumentFraction instrumentFraction;
            if(v == null){
                return  new HashMap<String, InstrumentFraction>(){{
                    put(instrumentName, new InstrumentFraction(price.getBidPrice(), price.getBidAmount(), price.getOfferPrice(), price.getOfferAmount()));
                }};
            }else{
               instrumentFraction = v.get(instrumentName);
                if (price.getBidAmount() > 0) {
                    // update bid details only if input contains bid amount
                    instrumentFraction.bidNumerator   += price.getBidAmount() * price.getBidPrice();
                    instrumentFraction.bidDenominator += price.getBidAmount();
                }
                if (price.getOfferAmount() > 0) {
                    // update offer details only if input contains offer amount
                    instrumentFraction.offerNumerator   += price.getOfferAmount() * price.getOfferPrice();
                    instrumentFraction.offerDenominator += price.getOfferAmount();
                }
                return v;
            }
        });

        InstrumentFraction finalFraction = marketInstrumentMap.get(marketName).get(instrumentName);
        return new TwoWayPriceImpl(
                twoWayMarketPrice.getTwoWayPrice().getInstrument(),
                State.FIRM,
                computeVwapForBid(finalFraction),
                getTotalBidAmount(finalFraction),
                computeVwapForOffer(finalFraction),
                getTotalOfferAmount(finalFraction)
        );
    }

    // total final amount, which is calculated as denominator
    private double getTotalBidAmount(InstrumentFraction vwap) {
        return vwap.bidDenominator;
    }


    private double computeVwapForBid(InstrumentFraction vwap) {
        // to avoid the case where denominator can be zero
        if (vwap.bidDenominator == 0) return 0;
        return vwap.bidNumerator / vwap.bidDenominator;
    }

    // total final amount, which is calculated as denominator
    private double getTotalOfferAmount(InstrumentFraction vwap) {
        return vwap.offerDenominator;
    }

    private double computeVwapForOffer(InstrumentFraction vwap) {
        if (vwap.offerDenominator == 0) return 0;
        return vwap.offerNumerator / vwap.offerDenominator;
    }

    private Map<String, InstrumentFraction> calculateTwoWayVWAP(String marketName, InstrumentFraction instrumentFraction) {
        return null;
    }


    // Inner Class to store sum of Numerator and Denominator
    private class InstrumentFraction{
        private double bidNumerator = 0;
        private double bidDenominator = 0;
        private double offerNumerator = 0;
        private double offerDenominator = 0;

        public InstrumentFraction(double bidNumerator, double bidDenominator, double offerNumerator, double offerDenominator) {
            this.bidNumerator = bidNumerator;
            this.bidDenominator = bidDenominator;
            this.offerNumerator = offerNumerator;
            this.offerDenominator = offerDenominator;
        }
    }
}
