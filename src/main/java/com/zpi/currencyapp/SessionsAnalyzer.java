package com.zpi.currencyapp;

import com.zpi.datamodel.CurrencyNoteA;
import com.zpi.datamodel.RateA;

public class SessionsAnalyzer {
	/**Calculates how many growth sessions are in passed amount of data
	 * 
	 * @param Note rates to calculate growth sessions 
	 * @return amount of growth sessions
	 */
	public int calculateGrowthSessionsAmount(CurrencyNoteA Note) {
		int growthSession=0;
		boolean isGrowing=false;
		RateA temp=Note.getRates().get(0);
		for(RateA rate : Note.getRates()) {
			if(rate.getMid()>temp.getMid()) {
				if(!isGrowing) {
					growthSession++;
					isGrowing=true;
				}
			}
			else {
				isGrowing=false;
			}
			temp=rate;
		}
		return growthSession;
	}
	/**Calculates how many decrease sessions are in passed amount of data
	 * 
	 * @param Note rates to calculate decrease sessions 
	 * @return amount of decrease sessions
	 */
	public int calculateDownwardSessionsAmount(CurrencyNoteA Note) {
		int decraseSession=0;
		boolean isDecreasing=false;
		RateA temp=Note.getRates().get(0);
		for(RateA rate : Note.getRates()) {
			if(rate.getMid()<temp.getMid()) {
				if(!isDecreasing) {
					decraseSession++;
					isDecreasing=true;
				}
			}
			else {
				isDecreasing=false;
			}
			temp=rate;
		}
		return decraseSession;
	}
	/**Calculates how many stable sessions are in passed amount of data
	 * 
	 * @param Note rates to calculate stable sessions 
	 * @return amount of stable sessions
	 */
	public int calculatestableSessionsAmount(CurrencyNoteA Note) {
		int stableSessions=0;
		boolean isStable=false;
		RateA temp=Note.getRates().get(0);
		for(RateA rate : Note.getRates()) {
			if(rate.getMid()==temp.getMid()) {
				if(!isStable) {
					stableSessions++;
					isStable=true;
				}
			}
			else {
				isStable=false;
			}
			temp=rate;
		}
		return stableSessions;
	}
}
