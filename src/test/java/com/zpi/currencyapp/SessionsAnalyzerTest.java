package com.zpi.currencyapp;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;

import java.io.FileNotFoundException;
import java.io.FileReader;

import org.junit.Before;
import org.junit.Test;

import com.google.gson.Gson;
import com.zpi.datamodel.CurrencyNoteA;
import com.zpi.datamodel.RateA;

public class SessionsAnalyzerTest {

    private SessionsAnalyzer sessionsAnalyzer;
    private CurrencyNoteA noteA;
    private RateA rateA;
    private String sessionsPath;
    Gson gson = new Gson();

    FileReader reader;

    @Before
    public void setUp() {
        sessionsAnalyzer = new SessionsAnalyzer();
        noteA = new CurrencyNoteA();
        sessionsPath = "USD_2019_01_01_2019_02_02.json";
        try {
            reader = new FileReader(sessionsPath);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void calcuateGrownthSessionsForEmptyListListOfRates() {
        RateA rateA = new RateA();
        rateA.setMid(5);
        noteA.getRates()
             .add(rateA);
        int growth = sessionsAnalyzer.calculateGrowthSessionsAmount(noteA);

        assertThat(growth, equalTo(0));
    }

    @Test
    public void calculateGrownthSessionsWith_twoRates_expectOneGrowth() {
        RateA rateA = new RateA();
        rateA.setMid(5);
        noteA.getRates()
             .add(rateA);
        RateA rateA2 = new RateA();
        rateA2.setMid(8);
        noteA.getRates()
             .add(rateA2);
        int growth = sessionsAnalyzer.calculateGrowthSessionsAmount(noteA);

        assertThat(growth, equalTo(1));
    }

    @Test
    public void calculateGrownthSessionsWith_twoRates_expectZeroGrowth() {
        RateA rateA1 = new RateA();
        rateA1.setMid(8);
        noteA.getRates()
             .add(rateA1);
        RateA rateA2 = new RateA();
        rateA2.setMid(5);
        noteA.getRates()
             .add(rateA2);
        int growth = sessionsAnalyzer.calculateGrowthSessionsAmount(noteA);

        assertThat(growth, equalTo(0));
    }

    @Test
    public void calculateGrownthSessionsForFirstMonthOf2019_shouldReturn() {

        CurrencyNoteA data = gson.fromJson(sessionsPath, CurrencyNoteA.class);
        // int growth = sessionsAnalyzer.calculateGrowthSessionsAmount(reader.read);

        // assertThat(growth, equalTo(5));
    }
}
