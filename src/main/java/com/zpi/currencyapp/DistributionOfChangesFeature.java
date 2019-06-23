package com.zpi.currencyapp;

import java.text.DecimalFormat;
import java.util.List;

import com.zpi.datamodel.CurrencyNoteA;
import com.zpi.datamodel.RateA;

/**
 * This is a model class of distribution of changes
 *
 * @see java.lang.Object
 * @author dominik3131
 */

public class DistributionOfChangesFeature {

    private DecimalFormat doubleFormat = new DecimalFormat("#0.0000");

    /**
     * method to print of distribution of changes between two currency notes
     *
     * @see #CurrencyNoteA
     * @param note1
     *            of first currency
     * @param note2
     *            of second currency
     */

    public void printDistributionOfChanges(CurrencyNoteA note1, CurrencyNoteA note2) {
        if (note1.getRates()
                 .size() != note2.getRates()
                                 .size()) {
            System.out.println("number of rates are diffrent");
        } else {
            List<RateA> listNote1 = note1.getRates();
            List<RateA> listNote2 = note2.getRates();
            RateA rate1Yesterday = listNote1.get(0);
            RateA rate2Yesterday = listNote2.get(0);
            System.out.println("------------------------------------------------------------");
            System.out.print("|   Date   |");
            System.out.print("  mid " + note1.getCode() + "  |");
            System.out.print("   change  |");
            System.out.print("  mid " + note2.getCode() + "  |");
            System.out.println("   change  |");
            for (int i = 1; i < note1.getRates()
                                     .size(); i++) {
                printRow(rate1Yesterday, note1.getRates()
                                              .get(i),
                        rate2Yesterday, note2.getRates()
                                             .get(i));
                rate1Yesterday = note1.getRates()
                                      .get(i);
                rate2Yesterday = note2.getRates()
                                      .get(i);
            }
            System.out.println("------------------------------------------------------------");
        }
    }

    /**
     * method to print row of rates
     *
     * @see #RateA
     * @param rate1Yesterday
     *            rate from yesterday
     * @param rate1Today
     *            today rate
     * @param rate2Yesterday
     *            rate from yestarday2
     * @param rate2Today
     *            today rate 2
     */

    private void printRow(RateA rate1Yesterday, RateA rate1Today, RateA rate2Yesterday, RateA rate2Today) {
        System.out.print("|" + rate1Today.getEffectiveDate() + "|");
        System.out.print(convertDoubleToSpecificFormat(rate1Today.getMid()) + "|");
        System.out.print(convertDoubleToSpecificFormat(calculateDiffrence(rate1Yesterday, rate1Today)) + "|");
        System.out.print(convertDoubleToSpecificFormat(rate2Today.getMid()) + "|");
        System.out.println(convertDoubleToSpecificFormat(calculateDiffrence(rate2Yesterday, rate2Today)) + "|");
    }

    /**
     * method to calculate difference between yesterday and today rate
     *
     * @see #RateA
     * @param rateYesterday
     *            yesterday rat
     * @param rateToday
     *            today rate
     * @return a <code> double </code> difference of mid values
     */

    private double calculateDiffrence(RateA rateYesterday, RateA rateToday) {
        return rateToday.getMid() - rateYesterday.getMid();
    }

    /**
     * method to convert double to special currency format
     *
     * @param value
     *            to convert
     * @return a <code> string </code> correct value code
     */

    private String convertDoubleToSpecificFormat(double value) {

        return String.format("%1$" + 11 + "s", doubleFormat.format(value));
    }
}
