package com.zpi.currencyapp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import com.zpi.datamodel.CurrencyNoteA;
import com.zpi.datamodel.TableA;
import com.zpi.datamodel.TableRateA;

/**
 * This is a model class of api
 *
 * @see java.lang.Object
 * @author dominik3131
 */

public class App {

    private BufferedReader reader;
    private String choosenOption;
    private SessionsAnalyzer sessionsAnalyzer;
    private DistributionOfChangesFeature distributionOfChangesFeature;
    private TableA availableCurrencies;

    /**
     * App constructor No-argument constructor initializes instance variables
     *
     */

    public App() {
        reader = new BufferedReader(new InputStreamReader(System.in));
        sessionsAnalyzer = new SessionsAnalyzer();
        availableCurrencies = DataDownloader.getListOfCurrencies();
        distributionOfChangesFeature = new DistributionOfChangesFeature();
    }

    /**
     * main function of application
     *
     * @throws IOException
     *             due to read Input data
     */

    public static void main(String[] args) throws IOException {
        new App().start();
    }

    /**
     * start application and transfer control to App object
     *
     * @throws IOException
     *             due to read Input data
     */

    public void start() throws IOException {
        mainMenuControl();
    }

    /**
     * control of application and control the application flow invoke a method with user wish
     *
     * @throws IOException
     *             due to read Input data
     */

    private void mainMenuControl() throws IOException {
        printMainMenuChoices();
        boolean isChoosenOptionCorrect = false;
        while (!isChoosenOptionCorrect) {
            System.out.println("What you want to do?");
            choosenOption = reader.readLine();
            isChoosenOptionCorrect = true;
            switch (choosenOption) {
                case "1":
                    sessionsStaticticsControl();
                    break;
                case "2":
                    statisticalMeasuresControl();
                    break;
                case "3":
                    distributionOfChangesControl();
                    break;
                case "4":
                    System.out.println("Goodbye");
                    Runtime.getRuntime()
                           .exit(0);
                    break;
                default:
                    System.out.println("There is not such option to choose. Type Again.");
                    isChoosenOptionCorrect = false;
            }
        }

    }

    /**
     * method invoke calculate distribution of changes and show results in output
     *
     * @see #DistributionOfChangesFeature
     * @throws IOException
     *             due to read Input data
     */

    private void distributionOfChangesControl() throws IOException {
        System.out.println("Choose first currency");
        String currency1 = chooseCurrency();
        System.out.println("Choose second currency");
        String currency2 = chooseCurrency();
        while (currency1.equals(currency2)) {
            System.out.println("Second currency cannot be the same as first currency. Choose Second currency again");
            currency2 = chooseCurrency();
        }
        LocalDate startDate = choosePeriodOneMonthOrThree();
        CurrencyNoteA note1 = DataDownloader.getDataForSingleCurrency(currency1, startDate, LocalDate.now());
        CurrencyNoteA note2 = DataDownloader.getDataForSingleCurrency(currency2, startDate, LocalDate.now());
        distributionOfChangesFeature.printDistributionOfChanges(note1, note2);
        pressEnterToContinue();
        mainMenuControl();
    }

    /**
     * method invoke calculate statistical measures and show results in output
     *
     * @see #StatisticalMeasures object
     * @throws IOException
     *             due to read Input data
     */

    private void statisticalMeasuresControl() throws IOException {
        String currency = chooseCurrency();
        LocalDate startDate = choosePeriodFromWeekToOneYear();
        CurrencyNoteA note = DataDownloader.getDataForSingleCurrency(currency, startDate, LocalDate.now());
        StatisticalFeature statisticalFeature = new StatisticalFeature(getAllRatesMidFromCurrencyNote(note));
        System.out.println("Data from " + startDate + " to " + LocalDate.now() + " for currency " + currency);
        System.out.println("Median: " + statisticalFeature.calculateMedian());
        System.out.println("Dominant: " + statisticalFeature.calculateDominant());
        System.out.println("Standard Deviation: " + statisticalFeature.standardDeviation());
        System.out.println("Coefficient Of Variation: " + statisticalFeature.coefficientOfVariation());
        pressEnterToContinue();
        mainMenuControl();
    }

    /**
     * method invoke sessions statistics and show results in output
     *
     * @see #SessionAnalizyer object
     * @throws IOException
     *             due to read Input data
     */

    private void sessionsStaticticsControl() throws IOException {
        String currency = chooseCurrency();
        LocalDate startDate = choosePeriodFromWeekToOneYear();
        CurrencyNoteA note = DataDownloader.getDataForSingleCurrency(currency, startDate, LocalDate.now());
        List<Double> rates = getAllRatesMidFromCurrencyNote(note);
        System.out.println("Data from " + startDate + " to " + LocalDate.now() + " for currency " + currency);
        System.out.println("growth sessions: " + sessionsAnalyzer.calculateGrowthSessionsAmount(rates));
        System.out.println("downward sessions: " + sessionsAnalyzer.calculateDownwardSessionsAmount(rates));
        System.out.println("stable sessions: " + sessionsAnalyzer.calculateStableSessionsAmount(rates));
        pressEnterToContinue();
        mainMenuControl();
    }

    /**
     * method showing options to choose from
     */

    private void printMainMenuChoices() {
        System.out.println("---------------------------------");
        System.out.println("1. Growth, downward and stable sessions statistics");
        System.out.println("2. Statistical measures");
        System.out.println("3. Distribution of changes");
        System.out.println("4. Exit from app");
        System.out.println("---------------------------------");
    }

    /**
     * method showing periods of time to choose from
     */

    private void printPeriodFromWeekToOneYearChoices() {
        System.out.println("---------------------------------");
        System.out.println("1. last week");
        System.out.println("2. last two weeks");
        System.out.println("3. last month");
        System.out.println("4. last quarter");
        System.out.println("5. last half of the year");
        System.out.println("6. last year");
        System.out.println("---------------------------------");
    }

    private void printPeriodOneMonthOrThreeChoices() {
        System.out.println("---------------------------------");
        System.out.println("1. last month");
        System.out.println("2. last quarter");
        System.out.println("---------------------------------");
    }

    /**
     * method showing available currencies to perform calculations based on them
     */

    private void printAvailableCurrencies() {
        System.out.println("---------------------------------");
        System.out.println("AVAILABLE CURRENCIES TO CHOOSE");
        for (TableRateA item : availableCurrencies.getRates()) {
            System.out.println(item.getCode() + "  -  " + item.getCurrency());
        }
        System.out.println("---------------------------------");
    }

    /**
     * method invoke special function depend on chose period of time
     *
     * @see #DataDownloader
     * @throws IOException
     *             due to read Input data
     * @return a <code> LocalDate </code> actual date
     */

    private LocalDate choosePeriodFromWeekToOneYear() throws IOException {
        printPeriodFromWeekToOneYearChoices();
        boolean isChoosenOptionCorrect = false;
        while (!isChoosenOptionCorrect) {
            System.out.println("What period you want to choose?");
            choosenOption = reader.readLine();
            isChoosenOptionCorrect = true;
            switch (choosenOption) {
                case "1":
                    return getLastWeek();
                case "2":
                    return getLastTwoWeeks();
                case "3":
                    return getLastMonth();
                case "4":
                    return getLastQuarter();
                case "5":
                    return getLastHalfOfYear();
                case "6":
                    return getLastYear();
                default:
                    System.out.println("There is not such option to choose. Type Again.");
                    isChoosenOptionCorrect = false;
            }
        }
        return LocalDate.now();
    }

    /**
     * method invoke special function depend on chose period one or three months
     *
     * @see #DataDownloader
     * @throws IOException
     *             due to read Input data
     * @return a <code> LocalDate </code> actual date
     */

    private LocalDate choosePeriodOneMonthOrThree() throws IOException {
        printPeriodOneMonthOrThreeChoices();
        boolean isChoosenOptionCorrect = false;
        while (!isChoosenOptionCorrect) {
            System.out.println("What period you want to choose?");
            choosenOption = reader.readLine();
            isChoosenOptionCorrect = true;
            switch (choosenOption) {
                case "1":
                    return getLastMonth();
                case "2":
                    return getLastQuarter();
                default:
                    System.out.println("There is not such option to choose. Type Again.");
                    isChoosenOptionCorrect = false;
            }
        }
        return LocalDate.now();
    }

    /**
     * method invoke special function depend on chose currency
     *
     * @see #DataDownloader
     * @throws IOException
     *             due to read Input data
     * @return a <code> string </code> currency
     */

    private String chooseCurrency() throws IOException {
        printAvailableCurrencies();
        String currency = null;
        boolean isChoosenOptionCorrect = false;
        while (!isChoosenOptionCorrect) {
            System.out.println("What currency you want to choose? Enter code of choosen currency");
            currency = reader.readLine();
            currency = currency.toUpperCase();
            isChoosenOptionCorrect = checkIfChoosenCurrencyIsCorrect(currency);
            if (!isChoosenOptionCorrect) {
                System.out.println("There is not such currency available. Type Again.");
            }
        }
        return currency;
    }

    /**
     * method to check if currency is correct
     *
     * @param code
     *            as input user code
     * @see #DataDownloader
     * @return a <code> boolean </code> value of the correctness
     */

    private boolean checkIfChoosenCurrencyIsCorrect(String code) {
        for (TableRateA item : availableCurrencies.getRates()) {
            if (item.getCode()
                    .equals(code)) {
                return true;
            }
        }
        return false;
    }

    private void chooseOperation() {
        // TODO Auto-generated method stub
    }

    /**
     * method to get time from now to last week
     *
     * @return a <code> LocalDate </code> time between now and last week
     */

    private LocalDate getLastWeek() {
        return LocalDate.now()
                        .minusWeeks(1)
                        .minusDays(1);
    }

    /**
     * method to get time from now to two last week
     *
     * @return a <code> LocalDate </code> time between now and two last week
     */

    private LocalDate getLastTwoWeeks() {
        return LocalDate.now()
                        .minusWeeks(2)
                        .minusDays(1);
    }

    /**
     * method to get time from now to last month
     *
     * @return a <code> LocalDate </code> time between now and last month
     */

    private LocalDate getLastMonth() {
        return LocalDate.now()
                        .minusMonths(1)
                        .minusDays(1);
    }

    /**
     * method to get time from now to last quarter
     *
     * @return a <code> LocalDate </code> time between now and last quarter
     */

    private LocalDate getLastQuarter() {
        return LocalDate.now()
                        .minusMonths(4)
                        .minusDays(1);
    }

    /**
     * method to get time from now to last half of year
     *
     * @return a <code> LocalDate </code> time between now and to last half of year
     */

    private LocalDate getLastHalfOfYear() {
        return LocalDate.now()
                        .minusMonths(6)
                        .minusDays(1);
    }

    /**
     * method to get time from now to last year
     *
     * @return a <code> LocalDate </code> time between now and to last year
     */

    private LocalDate getLastYear() {
        return LocalDate.now()
                        .minusYears(1)
                        .minusDays(1);
    }

    /**
     * method to get key enter to confirm choice
     *
     * @throws IOException
     *             due to read Input data
     */

    private void pressEnterToContinue() throws IOException {
        System.out.println("Press Enter to continue");
        reader.readLine();
    }

    /**
     * method to get all rates mid from currency note
     *
     * @see #DataDownloader
     * @return a <code> List<Double> </code> all rates mid from currency note
     */

    private List<Double> getAllRatesMidFromCurrencyNote(CurrencyNoteA noteA) {
        return noteA.getRates()
                    .stream()
                    .mapToDouble(rate -> rate.getMid())
                    .collect(ArrayList::new, ArrayList::add, ArrayList::addAll);
    }
}
