# priceCalculator
Calculate efficiently VWAP (Volume-weighted Average Price)

Main points
effective incremental implementation using TDD.

As per request, it is supposed to be used by single thread only, ie it is not thread safe at the moment. But it can easily be made thread-safe by changing hashMap implementation to a concurrentHashMap or a SyncronizedHashMap.


Notes
The assumption was made on the fact that every market has its personal set of instruments and the Instrument will be updated based on the Market sent in the request.

The State (FIRM/INDICATIVE) is hard-coded in the response as FIRM

I also ignored Side (BID/OFFER) in the input, and checking only if TwoWayPrice actually contains a non-zero amount for BID and/or OFFER.

A HashMap is used to store all BID / OFFER data for Market/Instrument pairs. Since the complexity for retrieval of data fr map is O(1), this is more preferable way to fetch data repeatedly om
