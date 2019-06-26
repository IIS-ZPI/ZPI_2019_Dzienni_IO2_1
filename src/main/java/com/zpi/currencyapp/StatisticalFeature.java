package com.zpi.currencyapp;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * This is a model class to measure statistical functions
 *
 * @see java.lang.Object
 * @author pawo97
 */

public class StatisticalFeature {

    private List<Double> ratesMid;

    /**
     * StatisticalFeature constructor
     *
     * @param ratesMid
     *            is a lists of currency values download from Web
     */

    public StatisticalFeature(List<Double> ratesMid) {
        this.ratesMid = ratesMid;
    }

    /**
     * calculate average of all download values
     *
     * @return a <code> double </code>
     */

    public double averageOfValues() {
        double sum = 0;
        for (Double d : ratesMid) {
            sum += d;
        }
        return sum / ratesMid.size();
    }

    /**
     * calculate median of all download values
     *
     * @return a <code> double </code>
     */

    public double calculateMedian() {
        int ratesSize = ratesMid.size();
        Collections.sort(ratesMid);
        if (ratesSize % 2 == 1) {
            return ratesMid.get(ratesSize / 2);
        } else {
            return (ratesMid.get(ratesSize / 2 - 1) + ratesMid.get(ratesSize / 2)) / 2;
        }
    }

    /**
     * calculate dominant of all download values
     *
     * @return a <code> List Double </code> of dominantes values
     */

    public List<Double> calculateDominant() {
        Map<Double, Integer> seen = new HashMap<>();
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

    /**
     * calculate standard deviation of all download values
     *
     * @return a <code> double </code>
     */

    public double standardDeviation() {
        double average = averageOfValues();
        double sum = 0.0;
        for (Double d : ratesMid) {
            sum += Math.pow(d - average, 2);
        }
        double standardDev = Math.sqrt(sum / ratesMid.size());
        return standardDev;
    }

    /**
     * calculate coefficient of variation of all download values
     *
     * @return a <code> double </code>
     */
    public double coefficientOfVariation() {
        return standardDeviation() / averageOfValues();
    }

}
