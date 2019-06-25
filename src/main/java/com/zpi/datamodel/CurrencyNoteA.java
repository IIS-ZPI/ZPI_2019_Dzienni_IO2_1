package com.zpi.datamodel;

import java.util.ArrayList;
import java.util.List;

/**
 * This is a model class of keep data of currency tables
 *
 * @see java.lang.Object
 * @author dominik3131
 */
public class CurrencyNoteA {

    private String table;
    private String currency;
    private String code;
    private List<RateA> rates = new ArrayList<>();

    /**
     * Get a table of currencies
     *
     * @return a <code> string </code> table of download currencies inside
     */
    public String getTable() {
        return table;
    }

    /**
     * Get a specify currency
     *
     * @return a <code> string </code> download currency
     */
    public String getCurrency() {
        return currency;
    }

    /**
     * Get a specify code for currency
     *
     * @return a <code> string </code> code for currency
     */
    public String getCode() {
        return code;
    }

    /**
     * Get a specify list of rates
     *
     * @see #RateA
     * @return a <code> List<RateA> </code> rates from list
     */
    public List<RateA> getRates() {
        return rates;
    }

    /**
     * set a string table to this object filed
     *
     * @param table
     *            as string
     */
    public void setTable(String table) {
        this.table = table;
    }

    /**
     * set a currency to this object field
     *
     * @param currency
     *            as string
     */
    public void setCurrency(String currency) {
        this.currency = currency;
    }

    /**
     * set a code to this object field
     *
     * @param code
     *            as string
     */
    public void setCode(String code) {
        this.code = code;
    }

    /**
     * set a rate to this object field list
     *
     * @see #RateA
     * @param rates
     *            as List<RateA>
     *
     */
    public void setRates(List<RateA> rates) {
        this.rates = rates;
    }

    /**
     * override toString object method
     *
     * @return a <code> string </code> table of data
     *
     */
    @Override
    public String toString() {
        return "CurrencyNoteA [table=" + table + ", currency=" + currency + ", code=" + code + ", rates=" + rates + "]";
    }
}
