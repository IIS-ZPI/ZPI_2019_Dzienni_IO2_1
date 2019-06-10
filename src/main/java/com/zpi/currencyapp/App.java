package com.zpi.currencyapp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class App 
{
	private BufferedReader reader;
	private String choosenOption;
	public App() {
		reader = new BufferedReader(new
		        InputStreamReader(System.in));
	}
    public static void main( String[] args ) throws IOException
    {
    	new App().start();
    }
    public void start() throws IOException {
    	mainMenuControl();
    }
    private void mainMenuControl() throws IOException {
    	printMainMenu();
    	boolean isChoosenOptionCorrect=false;
    	while(!isChoosenOptionCorrect) {
    		System.out.println("What you want to do?");
        	choosenOption=reader.readLine();
        	isChoosenOptionCorrect=true;
        	switch(choosenOption) {
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
        			Runtime.getRuntime().exit(0);
        			break;
        		default:
        			System.out.println("There is not such option to choose. Type Again.");
        			isChoosenOptionCorrect=false;
        	}
    	}
    	
	}
	private void distributionOfChangesControl() {
		// TODO Auto-generated method stub
	}
	private void statisticalMeasuresControl() {
		// TODO Auto-generated method stub
	}
	private void sessionsStaticticsControl() {
		// TODO Auto-generated method stub
		
	}
	private void printMainMenu() {
    	System.out.println("---------------------------------");
    	System.out.println("1. Growth, downward and stable sessions statistics");
    	System.out.println("2. Statistical measures");
    	System.out.println("3. Distribution of changes");
    	System.out.println("4. Exit from app");
    	System.out.println("---------------------------------");
    }
    private void choosePeriod() {
		// TODO Auto-generated method stub
    }
    private void chooseCurrency() {
		// TODO Auto-generated method stub
    }
    private void chooseOperation() {
		// TODO Auto-generated method stub
    }
}
