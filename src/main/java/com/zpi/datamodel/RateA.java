package com.zpi.datamodel;

/**
 * This is a model class of keep data of rates
 *
 * @see java.lang.Object
 * @author dominik3131
 */
public class RateA {

    private String no;
    private String effectiveDate;
    private double mid;

    /**
     * Get a specify number
     *
     * @return a <code> string </code> number
     */
    public String getNo() {
        return no;
    }

    /**
     * Get an effective date
     *
     * @return a <code> string </code> date
     */
    public String getEffectiveDate() {
        return effectiveDate;
    }

    /**
     * Get an values of currencies from table
     *
     * @return a <code> double </code> values of currencies from table
     */

    public double getMid() {
        return mid;
    }

    /**
     * Set a number to object field
     *
     * @param no
     *            as a number of record
     */
    public void setNo(String no) {
        this.no = no;
    }

    /**
     * Set a effective date to object field
     *
     * @param effectiveDate
     *            as a date depend of download values
     */

    public void setEffectiveDate(String effectiveDate) {
        this.effectiveDate = effectiveDate;
    }

    /**
     * Set a mid values to object field
     *
     * @param mid
     *            values from record
     */
    public void setMid(double mid) {
        this.mid = mid;
    }

    /**
     * override toString object method
     *
     * @return a <code> string </code> table of data
     *
     */
    @Override
    public String toString() {
        return "RateA [no=" + no + ", effectiveDate=" + effectiveDate + ", mid=" + mid + "]";
    }
}
