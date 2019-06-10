package com.zpi.currencyapp;

import com.zpi.datamodel.CurrencyNoteA;
import com.zpi.datamodel.RateA;

public class SessionsAnalyzer {
	/**Calculates how many growth sessions are in passed amount of data
	 * 
	 * @param Note rates to calculate growth sessions 
	 * @return amount of growth sessions
	 */
	public int calculateGrowthSessionAmount(CurrencyNoteA Note) {
		int growthSession=0;
		boolean isGrowing=false;
		RateA temp=Note.getRates().get(0);
		for(RateA rate : Note.getRates()) {
			System.out.println(rate.getMid());
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
}
