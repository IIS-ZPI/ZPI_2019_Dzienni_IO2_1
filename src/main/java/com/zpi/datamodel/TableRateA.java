package com.zpi.datamodel;

/**
 * This is a model class of keep tables download from web
 *
 * @see java.lang.Object
 * @author dominik3131
 */
public class TableRateA {

    String currency;
    String code;
    double mid;

    /**
     * Get a specify currency
     *
     * @return a <code> string </code> currency
     */
    public String getCurrency() {
        return currency;
    }

    /**
     * Get a specify code
     *
     * @return a <code> string </code> code of currency
     */
    public String getCode() {
        return code;
    }

    /**
     * Get a value of currency
     *
     * @return a <code> double </code> value of currency
     */
    public double getMid() {
        return mid;
    }

    /**
     * override toString object method
     *
     * @return a <code> string </code> table of data
     *
     */
    @Override
    public String toString() {
        return "TableRateA [currency=" + currency + ", code=" + code + ", mid=" + mid + "]";
    }
}
