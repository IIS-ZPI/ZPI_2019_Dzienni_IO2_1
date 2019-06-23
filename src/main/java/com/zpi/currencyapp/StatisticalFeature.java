package com.zpi.currencyapp;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class StatisticalFeature {

    private List<Double> ratesMid;

    public StatisticalFeature(List<Double> ratesMid) {
        this.ratesMid = ratesMid;
    }

    public double averageOfValues() {
        double sum = 0;
        for (Double d : ratesMid) {
            sum += d;
        }
        return sum / ratesMid.size();
    }

    public double calculateMedian() {
        int ratesSize = ratesMid.size();
        Collections.sort(ratesMid);
        if (ratesSize % 2 == 1) {
            return ratesMid.get(ratesSize / 2);
        } else {
            return (ratesMid.get(ratesSize / 2 - 1) + ratesMid.get(ratesSize / 2)) / 2;
        }
    }

    public List<Double> calculateDominant() {
        Map<Double, Integer> seen = new HashMap<Double, Integer>();
        int max = 0;
        List<Double> maxElems = new ArrayList<>();
        for (Double value : ratesMid) {
            if (seen.containsKey(value)) {
                seen.put(value, seen.get(value) + 1);
            } else {
                seen.put(value, 1);
            }
            if (seen.get(value) > max) {
                max = seen.get(value);
                maxElems.clear();
                maxElems.add(value);
            } else if (seen.get(value) == max) {
                maxElems.add(value);
            }
        }
        return maxElems;
    }

    public double standardDeviation() {
        double average = averageOfValues();
        double sum = 0.0;
        for (Double d : ratesMid) {
            sum += Math.pow(d - average, 2);
        }
        double standardDev = Math.sqrt(sum / ratesMid.size());
        return standardDev;
    }

    public double coefficientOfVariation() {
        return standardDeviation() / averageOfValues();
    }

}
