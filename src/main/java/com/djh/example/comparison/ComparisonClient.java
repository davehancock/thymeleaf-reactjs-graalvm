package com.djh.example.comparison;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author David Hancock
 */
public class ComparisonClient {
    public Map<String, Object> retrieveComparisonsFor(String foo) {

        // Just being abit lazy here, we'd never actually just create untyped maps inline...
        Map<String, Object> quoteProps1 = new HashMap<>();
        quoteProps1.put("id", 22);
        quoteProps1.put("name", "Western Union");
        quoteProps1.put("alias", "western-union");
        quoteProps1.put("logo", "https://dq8dwmysp7hk1.cloudfront.net/logos/western-union.svg");
        quoteProps1.put("rate", 0.8486);
        quoteProps1.put("fees", 2.9);
        quoteProps1.put("receivedAmount", 1270.44);
        quoteProps1.put("dateCollected", "2018-10-23T12:03:27");

        Map<String, Object> quoteProps2 = new HashMap<>();
        quoteProps2.put("id", 666);
        quoteProps2.put("name", "Bank of Mum and Dad");
        quoteProps2.put("alias", "janayna");
        quoteProps2.put("logo", "https://dq8dwmysp7hk1.cloudfront.net/logos/santander.svg");
        quoteProps2.put("rate", 0.8986);
        quoteProps2.put("fees", 2.0);
        quoteProps2.put("receivedAmount", 1280.44);
        quoteProps2.put("dateCollected", "2018-10-23T12:03:27");

        List<Object> quotes = new ArrayList<>();
        quotes.add(quoteProps1);
        quotes.add(quoteProps2);

        Map<String, Object> rateRoute = new HashMap<>();
        rateRoute.put("rate", 0.876);
        rateRoute.put("source", "USD");
        rateRoute.put("target", "AUD");
        rateRoute.put("time", "2019-02-07T22:10:04+0000");

        Map<String, Object> comparisonDataStuff = new HashMap<>();
        comparisonDataStuff.put("quotes", quotes);
        comparisonDataStuff.put("rateRoute", rateRoute);
        comparisonDataStuff.put("amount", 1001);
        comparisonDataStuff.put("source", "USD");
        comparisonDataStuff.put("target", "AUD");

        return comparisonDataStuff;
    }
}
