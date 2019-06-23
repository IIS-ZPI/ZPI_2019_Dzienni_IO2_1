package com.zpi.datamodel;

public class TableRateA {
    String currency;
    String code;
    double mid;
	public String getCurrency() {
		return currency;
	}
	public String getCode() {
		return code;
	}
	public double getMid() {
		return mid;
	}
	@Override
	public String toString() {
		return "TableRateA [currency=" + currency + ", code=" + code + ", mid=" + mid + "]";
	}
}