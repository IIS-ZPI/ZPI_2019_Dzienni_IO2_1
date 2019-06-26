package com.zpi.datamodel;

import java.util.List;

/**
 * This is a model class of keep tables download from web
 *
 * @see java.lang.Object
 * @author dominik3131
 */
public class TableA {

    String table;
    String no;
    String effectiveDate;
    List<TableRateA> rates;

    /**
     * Get a specify tables of values
     *
     * @return a <code> string </code> table
     */

    public String getTable() {
        return table;
    }

    /**
     * Get a specify number of value in record
     *
     * @return a <code> string </code> number of value in record
     */

    public String getNo() {
        return no;
    }

    /**
     * Get a specify date depend of download values
     *
     * @return a <code> string </code> date depend of download values
     */
    public String getEffectiveDate() {
        return effectiveDate;
    }

    /**
     * Get a specify list of rates
     *
     * @see com.zpi.datamodel.TableRateA
     * @return a <code> List of TableRateA </code> rates
     */
    public List<TableRateA> getRates() {
        return rates;
    }

    /**
     * override toString object method
     *
     * @return a <code> string </code> table of data
     *
     */
    @Override
    public String toString() {
        return "TableA [table=" + table + ", no=" + no + ", effectiveDate=" + effectiveDate + "]";
    }
}
