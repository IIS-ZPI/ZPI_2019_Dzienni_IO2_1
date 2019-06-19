package com.zpi.datamodel;

public class RateA {

    private String no;
    private String effectiveDate;
    private double mid;

    public String getNo() {
        return no;
    }

    public String getEffectiveDate() {
        return effectiveDate;
    }

    public double getMid() {
        return mid;
    }

    public void setNo(String no) {
        this.no = no;
    }

    public void setEffectiveDate(String effectiveDate) {
        this.effectiveDate = effectiveDate;
    }

    public void setMid(double mid) {
        this.mid = mid;
    }

    @Override
    public String toString() {
        return "RateA [no=" + no + ", effectiveDate=" + effectiveDate + ", mid=" + mid + "]";
    }
}
