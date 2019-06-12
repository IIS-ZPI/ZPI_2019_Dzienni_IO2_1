package com.zpi.currencyapp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.time.LocalDate;

import com.zpi.datamodel.CurrencyNoteA;
import com.zpi.datamodel.TableA;
import com.zpi.datamodel.TableRateA;

public class App {
	private BufferedReader reader;
	private String choosenOption;
	private SessionsAnalyzer sessionsAnalyzer;
	private TableA availableCurrencies;
	public App() {
		reader = new BufferedReader(new InputStreamReader(System.in));
		sessionsAnalyzer=new SessionsAnalyzer();
		availableCurrencies = DataDownloader.getListOfCurrencies();
	}

	public static void main(String[] args) throws IOException {
		new App().start();
	}

	public void start() throws IOException {
		mainMenuControl();
	}

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
				Runtime.getRuntime().exit(0);
				break;
			default:
				System.out.println("There is not such option to choose. Type Again.");
				isChoosenOptionCorrect = false;
			}
		}

	}

	private void distributionOfChangesControl() throws IOException {
		// TODO Auto-generated method stub
		System.out.println("not yet implemented");
		pressEnterToContinue();
		mainMenuControl();
	}

	private void statisticalMeasuresControl() throws IOException {
		// TODO Auto-generated method stub
		System.out.println("not yet implemented");
		pressEnterToContinue();
		mainMenuControl();
	}

	private void sessionsStaticticsControl() throws IOException {
		String currency=chooseCurrency();
		LocalDate startDate=choosePeriodFromWeekToOneYear();
		CurrencyNoteA note = DataDownloader.getDataForSingleCurrency(currency, startDate, LocalDate.now());
		System.out.println("Data from "+ startDate+ " to " + LocalDate.now()+" for currency "+currency);
		System.out.println("growth sessions: " + sessionsAnalyzer.calculateGrowthSessionsAmount(note));
		System.out.println("downward sessions: " + sessionsAnalyzer.calculateDownwardSessionsAmount(note));
		System.out.println("stable sessions: " + sessionsAnalyzer.calculatestableSessionsAmount(note));
		pressEnterToContinue();
		mainMenuControl();
	}

	private void printMainMenuChoices() {
		System.out.println("---------------------------------");
		System.out.println("1. Growth, downward and stable sessions statistics");
		System.out.println("2. Statistical measures");
		System.out.println("3. Distribution of changes");
		System.out.println("4. Exit from app");
		System.out.println("---------------------------------");
	}

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

	private void printAvailableCurrencies() {
		System.out.println("---------------------------------");
		System.out.println("AVAILABLE CURRENCIES TO CHOOSE");
		for(TableRateA item:availableCurrencies.getRates()) {
			System.out.println(item.getCode()+"  -  "+ item.getCurrency());
		}
		System.out.println("---------------------------------");
	}

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

	private LocalDate choosePeriodOneMonthOrThree() throws IOException {
		printPeriodFromWeekToOneYearChoices();
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

	private String chooseCurrency() throws IOException {
		printAvailableCurrencies();
		String currency = null;
		boolean isChoosenOptionCorrect = false;
		while (!isChoosenOptionCorrect) {
			System.out.println("What currency you want to choose? Enter code of choosen currency");
			currency = reader.readLine();
			currency=currency.toUpperCase();
			isChoosenOptionCorrect = checkIfChoosenCurrencyIsCorrect(currency);
			if(!isChoosenOptionCorrect) {
				System.out.println("There is not such currency available. Type Again.");
			}
		}
		return currency;
	}
	private boolean checkIfChoosenCurrencyIsCorrect(String code) {
		for(TableRateA item: availableCurrencies.getRates()) {
			if(item.getCode().equals(code)) return true;
		}
		return false;
	}
	private void chooseOperation() {
		// TODO Auto-generated method stub
	}

	private LocalDate getLastWeek() {
		return LocalDate.now().minusWeeks(1).minusDays(1);
	}

	private LocalDate getLastTwoWeeks() {
		return LocalDate.now().minusWeeks(2).minusDays(1);
	}

	private LocalDate getLastMonth() {
		return LocalDate.now().minusMonths(1).minusDays(1);
	}

	private LocalDate getLastQuarter() {
		return LocalDate.now().minusMonths(4).minusDays(1);
	}

	private LocalDate getLastHalfOfYear() {
		return LocalDate.now().minusMonths(6).minusDays(1);
	}

	private LocalDate getLastYear() {
		return LocalDate.now().minusYears(1).minusDays(1);
	}
	
	private void pressEnterToContinue() throws IOException {
		System.out.println("Press Enter to continue");
		reader.readLine();
	}
}
