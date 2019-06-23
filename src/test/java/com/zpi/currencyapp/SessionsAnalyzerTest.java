package com.zpi.currencyapp;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;

import java.io.FileNotFoundException;
import java.io.FileReader;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.google.gson.Gson;
import com.google.gson.stream.JsonReader;
import com.zpi.datamodel.CurrencyNoteA;
import com.zpi.datamodel.RateA;

public class SessionsAnalyzerTest {

    private static final String sessionsPath = "src/test/java/resources/SessionsAnalyzerData.json";
    private SessionsAnalyzer sessionsAnalyzer;
    private CurrencyNoteA noteA;
    private static CurrencyNoteA notaAFromFile;

    FileReader reader;

    @BeforeClass
    public static void setUpClass() throws FileNotFoundException {
        JsonReader reader = new JsonReader(new FileReader(sessionsPath));
        Gson gson = new Gson();
        notaAFromFile = gson.fromJson(reader, CurrencyNoteA.class);
    }

    @Before
    public void setUp() {
        sessionsAnalyzer = new SessionsAnalyzer();
        noteA = new CurrencyNoteA();
        RateA rateA = new RateA();
        rateA.setMid(5);
        noteA.getRates()
             .add(rateA);
    }

    @Test
    public void calcuateSessionsForOneNodeList_sessionWontChanged() {
        int growth = sessionsAnalyzer.calculateGrowthSessionsAmount(noteA);
        int downword = sessionsAnalyzer.calculateDownwardSessionsAmount(noteA);
        int stable = sessionsAnalyzer.calculateStableSessionsAmount(noteA);

        assertThat(growth, equalTo(0));
        assertThat(downword, equalTo(0));
        assertThat(stable, equalTo(0));
    }

    @Test
    public void calculateSessionsWith_twoRates_expectOneGrowth() {
        RateA rateA2 = new RateA();
        rateA2.setMid(8);
        noteA.getRates()
             .add(rateA2);
        int growth = sessionsAnalyzer.calculateGrowthSessionsAmount(noteA);
        int downword = sessionsAnalyzer.calculateDownwardSessionsAmount(noteA);
        int stable = sessionsAnalyzer.calculateStableSessionsAmount(noteA);

        assertThat(growth, equalTo(1));
        assertThat(downword, equalTo(0));
        assertThat(stable, equalTo(0));
    }

    @Test
    public void calculateSessionsWith_twoRates_expectOneDownword() {
        RateA rateA2 = new RateA();
        rateA2.setMid(4);
        noteA.getRates()
             .add(rateA2);
        int growth = sessionsAnalyzer.calculateGrowthSessionsAmount(noteA);
        int downword = sessionsAnalyzer.calculateDownwardSessionsAmount(noteA);
        int stable = sessionsAnalyzer.calculateStableSessionsAmount(noteA);

        assertThat(growth, equalTo(0));
        assertThat(downword, equalTo(1));
        assertThat(stable, equalTo(0));
    }

    @Test
    public void calculateSessionsWith_twoRates_expectOneStable() {
        RateA rateA2 = new RateA();
        rateA2.setMid(5);
        noteA.getRates()
             .add(rateA2);
        int growth = sessionsAnalyzer.calculateGrowthSessionsAmount(noteA);
        int downword = sessionsAnalyzer.calculateDownwardSessionsAmount(noteA);
        int stable = sessionsAnalyzer.calculateStableSessionsAmount(noteA);

        assertThat(growth, equalTo(0));
        assertThat(downword, equalTo(0));
        assertThat(stable, equalTo(1));
    }

    @Test
    public void calculateSessionsFormFile_expectFiveGrowth() throws FileNotFoundException {
        int growth = sessionsAnalyzer.calculateGrowthSessionsAmount(notaAFromFile);
        assertThat(growth, equalTo(5));
    }

    @Test
    public void calculateSessionsFormFile_expectThreeDownword() throws FileNotFoundException {
        int growth = sessionsAnalyzer.calculateDownwardSessionsAmount(notaAFromFile);
        assertThat(growth, equalTo(5));
    }

    @Test
    public void calculateSessionsFormFile_expectTwoStable() throws FileNotFoundException {
        int growth = sessionsAnalyzer.calculateStableSessionsAmount(notaAFromFile);
        assertThat(growth, equalTo(2));
    }
}
