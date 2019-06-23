package com.zpi.currencyapp;

import java.util.Collections;
import java.util.List;

public class StatisticalFeature {

    private List<Double> ratesMid;

    public StatisticalFeature(List<Double> ratesMid) {
        this.ratesMid = ratesMid;
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

    public double calculateDominant() {
        double dominanta = 0;
        int maks = 0;
        int licznik = 0;

        int ratesSize = ratesMid.size();
        for (int i = 0; i < ratesSize; i++) {
            licznik = 0;
            for (int k = 0; k < ratesSize; k++) {
                if (ratesMid.get(i)
                            .equals(ratesMid.get(k))) {
                    licznik++;
                    if (licznik > maks) {
                        dominanta = ratesMid.get(i);
                        maks = licznik;
                    }
                }
            }
        }
        return dominanta;
    }

    public double standardDeviation() {
        int ratesSize = ratesMid.size();
        double sum = 0;
        for (int i = 0; i < ratesSize; i++) {
            sum += ratesMid.get(i);
        }
        double average = sum / ratesSize;
        sum = 0.0;
        for (int i = 0; i < ratesSize; i++) {
            sum += Math.pow(ratesMid.get(i) - average, 2);
        }
        double standardDev = Math.sqrt(sum / ratesSize);
        return standardDev;
    }

    public double coefficientOfVariation() {
        int ratesSize = ratesMid.size();
        double sum = 0;
        for (int i = 0; i < ratesSize; i++) {
            sum += ratesMid.get(i);
        }
        double average = sum / ratesSize;
        sum = 0;
        for (int i = 0; i < ratesSize; i++) {
            sum += Math.pow(ratesMid.get(i) - average, 2);
        }
        double standardDev = Math.sqrt(sum / ratesSize);
        double coefficientOfVariation = standardDev / average;
        return coefficientOfVariation;
    }

}
