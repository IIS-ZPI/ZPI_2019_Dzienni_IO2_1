package com.zpi.currencyapp;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

public class SessionsAnalyzerTest {

    private SessionsAnalyzer sessionsAnalyzer;
    private List<Double> rates = new ArrayList<>();

    @Before
    public void setUp() {
        sessionsAnalyzer = new SessionsAnalyzer();
        rates.add(5.0);
    }

    @Test
    public void calcuateSessionsForOneNodeList_sessionWontChanged() {
        int growth = sessionsAnalyzer.calculateGrowthSessionsAmount(rates);
        int downword = sessionsAnalyzer.calculateDownwardSessionsAmount(rates);
        int stable = sessionsAnalyzer.calculateStableSessionsAmount(rates);

        assertThat(growth, equalTo(0));
        assertThat(downword, equalTo(0));
        assertThat(stable, equalTo(0));
    }

    @Test
    public void calculateSessionsWith_twoRates_expectOneGrowth() {
        rates.add(7.0);

        int growth = sessionsAnalyzer.calculateGrowthSessionsAmount(rates);
        int downword = sessionsAnalyzer.calculateDownwardSessionsAmount(rates);
        int stable = sessionsAnalyzer.calculateStableSessionsAmount(rates);

        assertThat(growth, equalTo(1));
        assertThat(downword, equalTo(0));
        assertThat(stable, equalTo(0));
    }

    @Test
    public void calculateSessionsWith_twoRates_expectOneDownword() {
        rates.add(3.0);

        int growth = sessionsAnalyzer.calculateGrowthSessionsAmount(rates);
        int downword = sessionsAnalyzer.calculateDownwardSessionsAmount(rates);
        int stable = sessionsAnalyzer.calculateStableSessionsAmount(rates);

        assertThat(growth, equalTo(0));
        assertThat(downword, equalTo(1));
        assertThat(stable, equalTo(0));
    }

    @Test
    public void calculateSessionsWith_twoRates_expectOneStable() {
        rates.add(5.0);

        int growth = sessionsAnalyzer.calculateGrowthSessionsAmount(rates);
        int downword = sessionsAnalyzer.calculateDownwardSessionsAmount(rates);
        int stable = sessionsAnalyzer.calculateStableSessionsAmount(rates);

        assertThat(growth, equalTo(0));
        assertThat(downword, equalTo(0));
        assertThat(stable, equalTo(1));
    }

    @Test
    public void calculateSessionGrowth_expectFourGrowth() {
        rates.addAll(Arrays.asList(new Double[] {5.0, 7.0, 2.1, 3.5, 3.0, 1.2, 1.7, 2.2, 2.2, 5.0}));
        int growth = sessionsAnalyzer.calculateGrowthSessionsAmount(rates);
        assertThat(growth, equalTo(4));
    }

    @Test
    public void calculateSessionDownword_expectThreeDownword() {
        rates.addAll(Arrays.asList(new Double[] {5.0, 7.0, 2.1, 3.5, 3.0, 1.2, 1.7, 2.2, 2.2, 5.0, 4.5}));
        int downword = sessionsAnalyzer.calculateDownwardSessionsAmount(rates);
        assertThat(downword, equalTo(3));
    }

    @Test
    public void calculateSessionStable_expectTwoStable() {
        rates.addAll(Arrays.asList(new Double[] {5.0, 7.0, 2.1, 3.5, 3.0, 1.2, 1.7, 2.2, 2.2, 5.0}));
        int stable = sessionsAnalyzer.calculateStableSessionsAmount(rates);
        assertThat(stable, equalTo(2));
    }
}
