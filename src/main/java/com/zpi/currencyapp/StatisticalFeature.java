package com.zpi.currencyapp;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import com.zpi.datamodel.CurrencyNoteA;
import com.zpi.datamodel.RateA;

public class StatisticalFeature {

    private CurrencyNoteA note;

    public StatisticalFeature(String currency, CurrencyNoteA note) {
        this.note = note;
    }

    public double calculateMedian() {
        List<RateA> toCalculate = note.getRates();
        int counter = toCalculate.size();
        Comparator<RateA> c = new Comparator<RateA>() {

            @Override
            public int compare(RateA o1, RateA o2) {
                return Double.valueOf(o1.getMid())
                             .compareTo(Double.valueOf(o2.getMid()));
            }
        };
        Collections.sort(toCalculate, c);
        if (counter % 2 == 0) {
            return toCalculate.get(counter / 2)
                              .getMid();
        } else {
            return (toCalculate.get(counter / 2)
                               .getMid()
                    + toCalculate.get(counter / 2)
                                 .getMid())
                   / 2;
        }
    }

    public double calculateDominant() {
        List<RateA> toCalculate = note.getRates();
        int counter = toCalculate.size();
        double dominanta = 0;
        int maks = 0;
        int licznik = 0;

        for (int i = 0; i < toCalculate.size(); i++) {
            licznik = 0;
            for (int k = 0; k < toCalculate.size(); k++) {
                if (toCalculate.get(i)
                               .getMid() == toCalculate.get(k)
                                                       .getMid()) {
                    licznik++;
                    if (licznik > maks) {
                        dominanta = toCalculate.get(i)
                                               .getMid();
                        maks = licznik;
                    }
                }

            }
        }
        return dominanta;
    }

    public double standardDeviation() {
        List<RateA> toCalculate = note.getRates();
        int counter = toCalculate.size();
        double sum = 0;
        for (int i = 0; i < toCalculate.size(); i++) {
            sum += toCalculate.get(i)
                              .getMid();
        }
        double average = sum / counter;
        sum = 0;
        for (int i = 0; i < toCalculate.size(); i++) {
            sum += Math.pow(toCalculate.get(i)
                                       .getMid()
                            - average,
                    2);
        }
        double standardDev = Math.sqrt(sum / (counter - 1));
        return standardDev;
    }

    public double coefficientOfVariation() {
        List<RateA> toCalculate = note.getRates();
        int counter = toCalculate.size();
        double sum = 0;
        for (int i = 0; i < toCalculate.size(); i++) {
            sum += toCalculate.get(i)
                              .getMid();
        }
        double average = sum / counter;
        sum = 0;
        for (int i = 0; i < toCalculate.size(); i++) {
            sum += Math.pow(toCalculate.get(i)
                                       .getMid()
                            - average,
                    2);
        }
        double standardDev = Math.sqrt(sum / (counter - 1));
        double coefficientOfVariation = standardDev / average;
        return coefficientOfVariation;
    }

}
