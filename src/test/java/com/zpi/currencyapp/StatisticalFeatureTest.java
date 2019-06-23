package com.zpi.currencyapp;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;

import org.junit.Test;

public class StatisticalFeatureTest {

    private StatisticalFeature statFeature;
    private List<Double> rates = new ArrayList<>();
    private DecimalFormat df = new DecimalFormat("#.####", new DecimalFormatSymbols(Locale.US));

    @Test
    public void calculateMedian_oneValue_medianEqualsValue() {
        rates.add(2.0);
        statFeature = new StatisticalFeature(rates);
        double median = statFeature.calculateMedian();
        assertThat(median, equalTo(2.0));
    }

    @Test
    public void calculateMedian_multipleValues_oddList() {
        rates = Arrays.asList(new Double[] {5.0, 7.0, 2.5, 2.1, 3.5});
        statFeature = new StatisticalFeature(rates);
        double median = statFeature.calculateMedian();
        assertThat(median, equalTo(3.5));
    }

    @Test
    public void calculateMedian_multipleValues_evenList() {
        rates = Arrays.asList(new Double[] {5.0, 7.0, 2.1, 3.5});
        statFeature = new StatisticalFeature(rates);
        double median = statFeature.calculateMedian();
        assertThat(median, equalTo(4.25));
    }

    @Test
    public void calculateMedian_collectionWithTheEqualsValue() {
        rates = Arrays.asList(new Double[] {5.0, 7.0, 2.1, 5.0});
        statFeature = new StatisticalFeature(rates);
        double median = statFeature.calculateMedian();
        assertThat(median, equalTo(5.0));
    }

    @Test
    public void calulateStandartDeviation_oneValue_stdDevEqualsZero() {
        rates = Arrays.asList(new Double[] {3.5});
        statFeature = new StatisticalFeature(rates);
        double stdDev = statFeature.standardDeviation();
        assertThat(df.format(stdDev), equalTo("0"));
    }

    @Test
    public void calulateStandartDeviation_sameValues() {
        rates = Arrays.asList(new Double[] {5.0, 5.0, 5.0, 5.0});
        statFeature = new StatisticalFeature(rates);
        double stdDev = statFeature.standardDeviation();
        assertThat(df.format(stdDev), equalTo("0"));
    }

    @Test
    public void calulateStandartDeviation_differentValues() {
        rates = Arrays.asList(new Double[] {5.0, 7.0, 2.1, 3.5});
        statFeature = new StatisticalFeature(rates);
        double stdDev = statFeature.standardDeviation();
        assertThat(df.format(stdDev), equalTo("1.818"));
    }

    @Test
    public void calulateCoefficientOfVariation_oneValue_isEquals0() {
        rates = Arrays.asList(new Double[] {5.0});
        statFeature = new StatisticalFeature(rates);
        double coeffVar = statFeature.coefficientOfVariation();
        assertThat(df.format(coeffVar), equalTo("0"));
    }

    @Test
    public void calulateCoefficientOfVariation_sameValues_isEquals0() {
        rates = Arrays.asList(new Double[] {5.0, 5.0, 5.0, 5.0});
        statFeature = new StatisticalFeature(rates);
        double coeffVar = statFeature.coefficientOfVariation();
        assertThat(df.format(coeffVar), equalTo("0"));
    }

    @Test
    public void calulateCoefficientOfVariation_diffrentValues() {
        rates = Arrays.asList(new Double[] {5.0, 7.0, 2.1, 3.5});
        statFeature = new StatisticalFeature(rates);
        double coeffVar = statFeature.coefficientOfVariation();
        assertThat(df.format(coeffVar), equalTo("0.4132"));
    }

    @Test
    public void calulateDominant_oneValue_dominantEqualsValue() {
        rates = Arrays.asList(new Double[] {7.0});
        statFeature = new StatisticalFeature(rates);
        Object[] dominant = statFeature.calculateDominant()
                                       .toArray();
        assertThat(dominant[0], equalTo(7.0));
    }

    @Test
    public void calulateDominant_sameValues() {
        Double[] ratesData = new Double[] {3.5, 3.5, 3.5, 3.5};
        rates = Arrays.asList(ratesData);
        statFeature = new StatisticalFeature(rates);
        Object[] dominant = statFeature.calculateDominant()
                                       .toArray();
        assertThat(dominant[0], equalTo(3.5));
    }

    @Test
    public void calulateDominant_allDiffrentValues_dominant() {
        Double[] ratesData = new Double[] {3.5, 3.6, 3.7, 3.8};
        rates = Arrays.asList(ratesData);
        statFeature = new StatisticalFeature(rates);
        Object[] dominant = statFeature.calculateDominant()
                                       .toArray();
        assertThat(dominant, equalTo(ratesData));
    }

    @Test
    public void calulateDominant_twoSameValues() {
        Double[] ratesData = new Double[] {3.5, 3.5, 8.3, 1.4};
        rates = Arrays.asList(ratesData);
        statFeature = new StatisticalFeature(rates);
        Object[] dominant = statFeature.calculateDominant()
                                       .toArray();
        assertThat(dominant[0], equalTo(3.5));
    }

    @Test
    public void calulateDominant_twoPairsOfSameValues() {
        Double[] ratesData = new Double[] {3.5, 1.4, 3.5, 1.4};
        rates = Arrays.asList(ratesData);
        statFeature = new StatisticalFeature(rates);
        Object[] dominant = statFeature.calculateDominant()
                                       .toArray();
        assertThat(dominant, equalTo(new Double[] {3.5, 1.4}));
    }
}
