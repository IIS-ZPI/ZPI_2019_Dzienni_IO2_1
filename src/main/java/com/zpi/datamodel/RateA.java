package com.zpi.datamodel;

public class RateA {
    String no;
    String effectiveDate;
    double mid;
	public String getNo() {
		return no;
	}
	public String getEffectiveDate() {
		return effectiveDate;
	}
	public double getMid() {
		return mid;
	}
	@Override
	public String toString() {
		return "RateA [no=" + no + ", effectiveDate=" + effectiveDate + ", mid=" + mid + "]";
	}
}