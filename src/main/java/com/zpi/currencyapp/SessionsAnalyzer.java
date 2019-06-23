package com.zpi.currencyapp;

import java.util.List;

public class SessionsAnalyzer {

    /**
     * Calculates how many growth sessions are in passed amount of data
     *
     * @param Note
     *            rates to calculate growth sessions
     * @return amount of growth sessions
     */
    public int calculateGrowthSessionsAmount(List<Double> ratesMid) {
        int growthSession = 0;
        boolean isGrowing = false;
        double temp = ratesMid.get(0);
        for (Double rate : ratesMid) {
            if (rate > temp) {
                if (!isGrowing) {
                    growthSession++;
                    isGrowing = true;
                }
            } else {
                isGrowing = false;
            }
            temp = rate;
        }
        return growthSession;
    }

    /**
     * Calculates how many decrease sessions are in passed amount of data
     *
     * @param Note
     *            rates to calculate decrease sessions
     * @return amount of decrease sessions
     */
    public int calculateDownwardSessionsAmount(List<Double> ratesMid) {
        int decraseSession = 0;
        boolean isDecreasing = false;
        double temp = ratesMid.get(0);
        for (double rate : ratesMid) {
            if (rate < temp) {
                if (!isDecreasing) {
                    decraseSession++;
                    isDecreasing = true;
                }
            } else {
                isDecreasing = false;
            }
            temp = rate;
        }
        return decraseSession;
    }

    /**
     * Calculates how many stable sessions are in passed amount of data
     *
     * @param Note
     *            rates to calculate stable sessions
     * @return amount of stable sessions
     */
    public int calculateStableSessionsAmount(List<Double> ratesMid) {
        int stableSessions = 0;
        boolean isStable = false;
        double temp = -1;
        for (double rate : ratesMid) {
            if (rate == temp && rate >= 0) {
                if (!isStable) {
                    stableSessions++;
                    isStable = true;
                }
            } else {
                isStable = false;
            }
            temp = rate;
        }
        return stableSessions;
    }
}
