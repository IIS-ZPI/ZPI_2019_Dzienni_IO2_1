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
    public void calculateMedian() {
        rates = Arrays.asList(new Double[] {5.0, 7.0, 2.1, 3.5});
        statFeature = new StatisticalFeature(rates);
        double median = statFeature.calculateMedian();
        assertThat(median, equalTo(4.25));
    }

    @Test
    public void calulateStandartDeviation() {
        rates = Arrays.asList(new Double[] {5.0, 7.0, 2.1, 3.5});
        statFeature = new StatisticalFeature(rates);
        double stdDev = statFeature.standardDeviation();
        assertThat(df.format(stdDev), equalTo("1.818"));
    }

    @Test
    public void calulateDominant() {
        rates = Arrays.asList(new Double[] {5.0, 7.0, 2.1, 3.5, 7.0});
        statFeature = new StatisticalFeature(rates);
        double dominant = statFeature.calculateDominant();
        assertThat(df.format(dominant), equalTo("7"));
    }

}
