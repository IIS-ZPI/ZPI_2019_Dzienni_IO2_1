package com.zpi.datamodel;

import java.util.List;

public class CurrencyNoteA {
    String table;
    String currency;
    String code;
    List<RateA> rates;
	public String getTable() {
		return table;
	}
	public String getCurrency() {
		return currency;
	}
	public String getCode() {
		return code;
	}
	public List<RateA> getRates() {
		return rates;
	}
}