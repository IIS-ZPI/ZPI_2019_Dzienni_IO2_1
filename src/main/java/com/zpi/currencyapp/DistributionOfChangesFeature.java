package com.zpi.currencyapp;

import java.text.DecimalFormat;
import java.util.List;

import com.zpi.datamodel.CurrencyNoteA;
import com.zpi.datamodel.RateA;

public class DistributionOfChangesFeature {
	private DecimalFormat doubleFormat = new DecimalFormat("#0.0000");
	public void printDistributionOfChanges(CurrencyNoteA note1, CurrencyNoteA note2) {
		if(note1.getRates().size()!=note2.getRates().size()) {
			System.out.println("number of rates are diffrent");
		}
		else {
			List<RateA> listNote1 =note1.getRates();
			List<RateA> listNote2 =note2.getRates();
			RateA rate1Yesterday=listNote1.get(0);
			RateA rate2Yesterday=listNote2.get(0);
			System.out.println("------------------------------------------------------------");
			System.out.print("|   Date   |");
			System.out.print( "  mid "+note1.getCode()+"  |");
			System.out.print("   change  |");
			System.out.print( "  mid "+note2.getCode()+"  |");
			System.out.println("   change  |");
			for (int i = 1; i < note1.getRates().size(); i++) {
				printRow(rate1Yesterday,note1.getRates().get(i),rate2Yesterday,note2.getRates().get(i));
				rate1Yesterday=note1.getRates().get(i);
				rate2Yesterday=note2.getRates().get(i);
			}
			System.out.println("------------------------------------------------------------");
		}
	}

	private void printRow(RateA rate1Yesterday, RateA rate1Today, RateA rate2Yesterday, RateA rate2Today) {
		System.out.print("|"+rate1Today.getEffectiveDate()+"|");	
		System.out.print(convertDoubleToSpecificFormat(rate1Today.getMid())+"|");
		System.out.print(convertDoubleToSpecificFormat(calculateDiffrence(rate1Yesterday,rate1Today))+"|");
		System.out.print(convertDoubleToSpecificFormat(rate2Today.getMid())+"|");
		System.out.println(convertDoubleToSpecificFormat(calculateDiffrence(rate2Yesterday,rate2Today))+"|");
	}

	private double calculateDiffrence(RateA rateYesterday, RateA rateToday) {
		return rateToday.getMid()-rateYesterday.getMid();
	}
	private String convertDoubleToSpecificFormat(double value) {
		
	    return String.format("%1$"+11+ "s", doubleFormat.format(value));
	}
}
